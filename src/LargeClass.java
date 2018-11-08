import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LargeClass {
    
	

	public static void main(String[] args) throws Exception {
		final File folder = new File("test");
		listFilesForFolder(folder);
	}

	public static void listFilesForFolder(final File folder) throws FileNotFoundException {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				run(fileEntry);
			}
		}
	}

	private static void run(File f) throws FileNotFoundException {
		FileInputStream in = new FileInputStream(f);

		CompilationUnit cu = JavaParser.parse(in);

		cu.accept(new LargeClassVisitor(), null);
	}

    private static class LargeClassVisitor extends VoidVisitorAdapter<Void> {
    	private final NodeList<Statement> statements = new NodeList<>();
    	private boolean largeClass = false;
        @Override
        public void visit(BlockStmt n, Void arg) {
        	
            n.getStatements().forEach(statements::add);


            if (statements.size() > 100 && largeClass == false) {
            	System.out.println(n.getAncestorOfType(ClassOrInterfaceDeclaration.class).get().getNameAsString() + " is a large class");
            	largeClass = true;
            }

            super.visit(n, arg);
        }
    }

}
