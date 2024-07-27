package me.yevgnenll.lox;

import java.util.List;

abstract class Stmt {
    interface Visitor<R> {
        R visitExpressionStmt(Expression stmt);
        R visitPrintStmt(Print stmt);
    }
   static class Expression extends Stmt {
    Expression(Expr expression) {
    // constructor
      this.expression = expression;
    }


     @Override
     <R> R accept(Visitor<R> visitor) {
        return visitor.visitExpressionStmt(this);
     }
     // fields
     final Expr expression;
   }
   static class Print extends Stmt {
    Print(Expr expression) {
    // constructor
      this.expression = expression;
    }


     @Override
     <R> R accept(Visitor<R> visitor) {
        return visitor.visitPrintStmt(this);
     }
     // fields
     final Expr expression;
   }

    abstract <R> R accept(Visitor<R> visitor);
}
