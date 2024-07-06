package me.yevgnenll.lox;

import java.util.List;

public abstract class Expr{
    public interface Visitor<R> {
    R visitAssignExpr(Assign expr);
    R visitBinaryExpr(Binary expr);
    R visitCallExpr(Call expr);
    R visitGetExpr(Get expr);
    R visitGroupingExpr(Grouping expr);
    R visitLiteralExpr(Literal expr);
    R visitLogicalExpr(Logical expr);
    R visitSetExpr(Set expr);
    R visitSuperExpr(Super expr);
    R visitThisExpr(This expr);
    R visitUnaryExpr(Unary expr);
    R visitVariableExpr(Variable expr);
    }
   static class Assign extends Expr{
    Assign(Token name, Expr value) {
    // constructor
      this.name = name;
      this.value = value;
    }


    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visitAssignExpr(this);
     }
 // fields
     final Token name;
     final Expr value;
   }
   static class Binary extends Expr{
    Binary(Expr left, Token operator, Expr right) {
    // constructor
      this.left = left;
      this.operator = operator;
      this.right = right;
    }


    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visitBinaryExpr(this);
     }
 // fields
     final Expr left;
     final Token operator;
     final Expr right;
   }
   static class Call extends Expr{
    Call(Expr callee, Token paren, List<Expr> arguments) {
    // constructor
      this.callee = callee;
      this.paren = paren;
      this.arguments = arguments;
    }


    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visitCallExpr(this);
     }
 // fields
     final Expr callee;
     final Token paren;
     final List<Expr> arguments;
   }
   static class Get extends Expr{
    Get(Expr object, Token name) {
    // constructor
      this.object = object;
      this.name = name;
    }


    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visitGetExpr(this);
     }
 // fields
     final Expr object;
     final Token name;
   }
   static class Grouping extends Expr{
    Grouping(Expr expression) {
    // constructor
      this.expression = expression;
    }


    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visitGroupingExpr(this);
     }
 // fields
     final Expr expression;
   }
   static class Literal extends Expr{
    Literal(Object value) {
    // constructor
      this.value = value;
    }


    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visitLiteralExpr(this);
     }
 // fields
     final Object value;
   }
   static class Logical extends Expr{
    Logical(Expr left, Token operator, Expr right) {
    // constructor
      this.left = left;
      this.operator = operator;
      this.right = right;
    }


    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visitLogicalExpr(this);
     }
 // fields
     final Expr left;
     final Token operator;
     final Expr right;
   }
   static class Set extends Expr{
    Set(Expr object, Token name, Expr value) {
    // constructor
      this.object = object;
      this.name = name;
      this.value = value;
    }


    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visitSetExpr(this);
     }
 // fields
     final Expr object;
     final Token name;
     final Expr value;
   }
   static class Super extends Expr{
    Super(Token keyword, Token method) {
    // constructor
      this.keyword = keyword;
      this.method = method;
    }


    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visitSuperExpr(this);
     }
 // fields
     final Token keyword;
     final Token method;
   }
   static class This extends Expr{
    This(Token keyword) {
    // constructor
      this.keyword = keyword;
    }


    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visitThisExpr(this);
     }
 // fields
     final Token keyword;
   }
   static class Unary extends Expr{
    Unary(Token operator, Expr right) {
    // constructor
      this.operator = operator;
      this.right = right;
    }


    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visitUnaryExpr(this);
     }
 // fields
     final Token operator;
     final Expr right;
   }
   static class Variable extends Expr{
    Variable(Token name) {
    // constructor
      this.name = name;
    }


    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visitVariableExpr(this);
     }
 // fields
     final Token name;
   }

    abstract <R> R accept(Visitor<R> visitor);
}
