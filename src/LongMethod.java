
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LongMethod {

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

		cu.accept(new LongMethodVisitor(), null);
	}

	private static class LongMethodVisitor extends VoidVisitorAdapter<Void> {
		@Override
		public void visit(ClassOrInterfaceDeclaration n, Void arg) {
			for (int i = 0; i < n.getMethods().size(); i++) {
				if (!n.getMethods().get(i).getBody().toString().equals("Optional.empty")) {
					if (n.getMethods().get(i).getBody().get().getStatements().size() > 5) {
						System.out.println(n.getMethods().get(i).getNameAsString() + " is a long method in class "
								+ n.getNameAsString());
					}
				}
			}

			super.visit(n, arg);
		}
	}

}
