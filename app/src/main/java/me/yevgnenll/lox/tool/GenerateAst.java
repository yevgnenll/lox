package me.yevgnenll.lox.tool;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class GenerateAst {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: generate_ast <output directory>");
            System.exit(64);
        }

        String outputDir = args[0];

        //> call-define-ast
        defineAst(outputDir, "Expr", Arrays.asList(
                "Assign   : Token name, Expr value",
                "Binary   : Expr left, Token operator, Expr right",
                "Call     : Expr callee, Token paren, List<Expr> arguments",
                "Get      : Expr object, Token name",
                "Grouping : Expr expression",
                "Literal  : Object value",
                "Logical  : Expr left, Token operator, Expr right",
                "Set      : Expr object, Token name, Expr value",
                "Super    : Token keyword, Token method",
                "This     : Token keyword",
/* Representing Code call-define-ast < Statements and State var-expr
      "Unary    : Token operator, Expr right"
*/
//> Statements and State var-expr
                "Unary    : Token operator, Expr right",
                "Variable : Token name",
                "If       : Expr condition, Stmt thenBranch, Stmt elseBranch"
        ));

        defineAst(outputDir, "Stmt", Arrays.asList(
                "Expression : Expr expression",
                "Print      : Expr expression"
        ));
    }


    private static void defineAst(
            String outputDir, String baseName, List<String> types
    ) throws IOException {
        String path = outputDir + "/" + baseName + ".java";

        PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8);

        writer.println("package me.yevgnenll.lox;");
        writer.println();
        writer.println("import java.util.List;");
        writer.println();
        writer.println("abstract class " + baseName + " {");

        defineVisitor(writer, baseName, types);

        // AST class
        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, baseName, className, fields);
        }

        // base class accept method
        writer.println();
        writer.println("    abstract <R> R accept(Visitor<R> visitor);");

        writer.println("}");
        writer.close();
    }

    private static void defineVisitor(PrintWriter writer, String baseName, List<String> types) {
        writer.println("    interface Visitor<R> {");

        for (String type : types) {
            String typeName = type.split(":")[0].trim();
            writer.println("        R visit" + typeName + baseName + "(" + typeName + " " + baseName.toLowerCase() + ");");
        }

        writer.println("    }");
    }

    private static void defineType(PrintWriter writer, String baseName, String className, String fieldsList) {
        writer.println("   static class " + className + " extends " + baseName + " {");

        // constructor
        writer.println("    " + className + "(" + fieldsList + ") {");
        writer.println("    // constructor");

        // 매개변수 저장
        String[] fields = fieldsList.split(", ");

        for (String field : fields) {
            String name = field.split(" ")[1];
            writer.println("      this." + name + " = " + name + ";");
        }
        writer.println("    }");

        writer.println();
        // visitor pattern
        writer.println();
        writer.println("     @Override");
        writer.println("     <R> R accept(Visitor<R> visitor) {");
        writer.println("        return visitor.visit" + className + baseName + "(this);");
        writer.println("     }");

        writer.println("     // fields");
        // fields
        for (String field : fields) {
            writer.println("     final " + field + ";");
        }
       writer.println("   }");
    }
}
