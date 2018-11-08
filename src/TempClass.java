import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class TempClass
{
	
    static Set<Statement> switchif = new HashSet<>();
	static Set<VariableDeclarator> intVar = new HashSet<>();
    static Set<VariableDeclarator> var = new HashSet<>();
    
    
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
        	
        	for (FieldDeclaration f : n.getFields()) {
        		for(VariableDeclarator  v : f.getVariables()) {
        			if(v.getInitializer().isPresent()) {
        				intVar.add(v);
        			}else {
        				var.add(v);
        			}
        		}
        	}
        	
        	for(MethodDeclaration e : n.getMethods()) {
        		for (Statement s : e.getBody().get().getStatements()) {
        			if(s.isIfStmt()) {
        				switchif.add(s);
        			}
        			else if(s.isSwitchStmt()) {
        				switchif.add(s);
        			}
        		}
        		for(VariableDeclarator v : var) {
        			if(switchif.toString().contains(v.toString())) {
        				System.out.println("Possible temp var " + v.toString() + " in class " + n.getNameAsString());
        			}
        		}
        	}
        	super.visit(n,arg);
        }
    }
}
