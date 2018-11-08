
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LongParameter {

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

            cu.accept(new LongParameterVisitor(), null);
        }

        private static class LongParameterVisitor extends VoidVisitorAdapter<Void> {
            @Override
            public void visit(MethodDeclaration n, Void arg) { 
            	Optional<ClassOrInterfaceDeclaration> parentNode = n.findAncestor(ClassOrInterfaceDeclaration.class);
                NodeList<Parameter> parameters = n.getParameters();
                
                if (parameters.size()> 5) {
                    System.out.println(n.getName() + " has a long parameter list of " + parameters.size() + " in class " + parentNode.get().getNameAsString());
                }

                super.visit(n, arg);
            }
        }

    }
