package framework.editor.flowdesigner.parser;

import java.nio.file.Path;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class JavaParser {
	public static CompilationUnit parser(char[] source, Path sourcePath) {
		ASTParser parser = ASTParser.newParser(AST.JLS13);
        parser.setBindingsRecovery(true);
        parser.setStatementsRecovery(true);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        
        parser.setSource(source);
        parser.setUnitName(sourcePath.toAbsolutePath().toString());
        
        CompilationUnit unit = (CompilationUnit) parser.createAST(null);
        
        return unit;
	}
}
