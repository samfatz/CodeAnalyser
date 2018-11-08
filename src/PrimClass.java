import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class PrimClass {

	static List<String> prims = new ArrayList<>(
			Arrays.asList("short", "int", "long", "byte", "char", "float", "double", "boolean"));
	static int count = 0;

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
			List<VariableDeclarator> vList = n.findAll(VariableDeclarator.class);
			vList.forEach(var -> {
				if (prims.contains(var.getType().toString()))
					count++;
			});
			if (vList.size() > 0) {
				if (count / vList.size() > 0.8) {
					System.out.println(n.getNameAsString() + " may be primitive obsessed");
				}
			}
			count = 0;
			super.visit(n, arg);
		}
	}

}
