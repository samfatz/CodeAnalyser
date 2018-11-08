
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SwitchStatements {

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

		cu.accept(new SwitchStatementVisitor(), null);
	}
    private static class SwitchStatementVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(SwitchStmt n, Void arg) {
        	
            System.out.println("Switch statement detected " + n.getBegin());

            super.visit(n, arg);
        }
    }

}