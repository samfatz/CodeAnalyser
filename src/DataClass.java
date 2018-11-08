import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class DataClass {

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

		cu.accept(new DataClassVisitor(), null);
	}

	private static class DataClassVisitor extends VoidVisitorAdapter<Void> {
		@Override
		public void visit(ClassOrInterfaceDeclaration n, Void arg) {
			List<Statement> getList = new ArrayList<>();
			List<Statement> setList = new ArrayList<>();

			
			for (MethodDeclaration e : n.getMethods()) {
				if(!e.getBody().toString().equals("Optional.empty")) {
				if (e.getBody().get().getStatements().size() == 1) {
					for (Statement s : e.getBody().get().getStatements()) {
						if (s.isReturnStmt()) {
							getList.add(s);
						} else {
							setList.add(s);
						}
					}

				}}
			}
			if (setList.size() + getList.size() == n.getMethods().size()) {
				System.out.println("Possible Data Class " + n.getName());
			}
			super.visit(n, arg);
		}
	}
}
