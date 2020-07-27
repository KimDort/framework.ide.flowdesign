package framework.editor.flowdesigner.editors;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;

import framework.editor.flowdesigner.entities.Node;
import framework.editor.flowdesigner.finders.CreateNode;
import framework.editor.flowdesigner.parser.JavaParser;

/**
 * An example showing how to create a multi-page editor.
 * This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class MainEditorPage extends MultiPageEditorPart implements IResourceChangeListener{

	//Default Java Editor
	private CompilationUnitEditor javaEditor;
	private MainNodeEditor nodeEditor;
	
	private CompilationUnit unit;
	
	private CreateNode createNode = new CreateNode();
	
	public MainEditorPage() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
		
	}

	void createJavaSourcePage() throws IOException {
		try {
			//Java Editor Create
			this.javaEditor = new CompilationUnitEditor();
			//Editor Register
			int index = addPage(this.javaEditor, getEditorInput());
			
			setPageText(index, "Java");
		} catch (PartInitException e) {
			ErrorDialog.openError(
				getSite().getShell(),
				"Error creating nested text editor",
				null,
				e.getStatus());
		}
	}
	
	public void createFlowNodePage() {
		try {
			nodeEditor = new MainNodeEditor();
			int index = addPage(nodeEditor, getEditorInput());
			setPageText(index, "Flow");
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	public Node setGlobalNode(IEditorInput editorInput) {
		Node node=null;
		try {
    		IFile file = editorInput.getAdapter(IFile.class);
    		String path = file.getRawLocation().toOSString();
    		File resources = new File(path);
    		Path sourcePath = Paths.get(resources.toURI());
    		
    		String sourceString = new String(Files.readAllBytes(sourcePath), "UTF-8");
    		char[] source = sourceString.toCharArray();
    		unit = JavaParser.parser(source, sourcePath);
    		node = createNode.create(unit);
		} catch (Exception e) {
			System.out.println("Flow Designer File Load Exception");
		}
		return node;
	}
	
	protected void createPages(){
		try {
			createJavaSourcePage();
			createFlowNodePage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Editor 변경 시 Flow Node 새로 생성
	public void recreateNode(IEditorInput editorInput) {
		
		Node node = setGlobalNode(editorInput);
		List<Node> nodeList = node.getNodeList();
		
		try {
			nodeEditor.recreateNode(nodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}

	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
	}

	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
	}

	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}

	//초기화 진행
	public void init(IEditorSite site, IEditorInput editorInput)throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		setTitle(editorInput.getName());
		
		super.init(site, editorInput);
	}

	public boolean isSaveAsAllowed() {
		return true;
	}

	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);

		if (newPageIndex == 1) {
			recreateNode(javaEditor.getEditorInput());
		}

	}

	public void resourceChanged(final IResourceChangeEvent event){
		if(event.getType() == IResourceChangeEvent.PRE_CLOSE){
			Display.getDefault().asyncExec(() -> {
				IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
				for (int i = 0; i<pages.length; i++){
					if(((FileEditorInput)javaEditor.getEditorInput()).getFile().getProject().equals(event.getResource())){
						IEditorPart editorPart = pages[i].findEditor(javaEditor.getEditorInput());
						pages[i].closeEditor(editorPart,true);
					}
				}
			});
		}
	}
	
	public String getActiveOpenFileString(CompilationUnitEditor editor) {
		IDocumentProvider provider = editor.getDocumentProvider();
		IEditorInput editorInput = editor.getEditorInput();
		IDocument document = provider.getDocument(editorInput);
		String docStr = document.get();
		return docStr;
	}
}
