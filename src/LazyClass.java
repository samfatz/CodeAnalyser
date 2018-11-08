import java.io.FileInputStream;
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

public class LazyClass
{
    public static void main(String[] args) throws Exception {

        FileInputStream in = new FileInputStream("Couplers\\MiddleMan\\AccountManager.java");
        CompilationUnit cu = JavaParser.parse(in);
        cu.accept(new LargeClassVisitor(), null);

    }

    private static class LargeClassVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        	if(n.findAll(BlockStmt.class).size() <= 2) {
        			if (n.getMethods().size() <= 2) {
        				System.out.println(n.getNameAsString() + " is a lazy class!");
        			}
        		
        	}
        	super.visit(n,arg);
        }
    }
}
