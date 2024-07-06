package me.yevgnenll.lox;

public class AstPrinter implements Expr.Visitor {

    public static void main(String[] args) {
        Expr expr = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)),
                    new Token(TokenType.STAR, "*", null, 1),
                    new Expr.Grouping(
                            new Expr.Literal(46.67)
                    )
                );

        System.out.println(new AstPrinter().print(expr));
    }

    String print(Expr expr) {
        return (String) expr.accept(this);
    }

    @Override
    public Object visitAssignExpr(Expr.Assign expr) {
        return expr.accept(this);
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public Object visitCallExpr(Expr.Call expr) {
        return null;
    }

    @Override
    public Object visitGetExpr(Expr.Get expr) {
        return null;
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public Object visitLogicalExpr(Expr.Logical expr) {
        return null;
    }

    @Override
    public Object visitSetExpr(Expr.Set expr) {
        return null;
    }

    @Override
    public Object visitSuperExpr(Expr.Super expr) {
        return null;
    }

    @Override
    public Object visitThisExpr(Expr.This expr) {
        return null;
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    @Override
    public Object visitVariableExpr(Expr.Variable expr) {
        return null;
    }
}
