package framework.editor.flowdesigner.test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import framework.editor.flowdesigner.entities.Node;
import framework.editor.flowdesigner.entities.NodePrint;
import framework.editor.flowdesigner.finders.CreateNode;
import framework.editor.flowdesigner.finders.MethodFinder;
import framework.editor.flowdesigner.finders.StatementFinder;

public class ParserTest {
	public static void main(String[] args) throws Exception{
		ASTParser parser = ASTParser.newParser(AST.JLS13);
        parser.setBindingsRecovery(true);
        parser.setStatementsRecovery(true);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        
        File resource = new File("D:\\HNT\\workspace\\EASY17.ols\\EASY17.ols\\src\\main\\java\\com\\orange\\easy17i\\fnlacc\\bc\\impl\\AccDecHistBcImpl.java");
        Path sourcePath = Paths.get(resource.toURI());
        String sourceString = new String(Files.readAllBytes(sourcePath));
        char[] source = sourceString.toCharArray();
        
        parser.setSource(source);
        parser.setUnitName(sourcePath.toAbsolutePath().toString());
        
        CompilationUnit unit = (CompilationUnit) parser.createAST(null);
        
        CreateNode createNode = new CreateNode();
        Node node = new Node();
        node = createNode.create(unit);
        
        for(Node list : node.getNodeList()) {
        	System.out.println(list);
        }
	}
	
}
