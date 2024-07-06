package me.yevgnenll.lox;

import junit.framework.TestCase;
import org.junit.Test;

public class AstPrinterTest extends TestCase {

    @Test
    public void test() {
        Expr expr = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Literal(46.67)
                )
        );

        assertEquals("(* (- 123) (group 46.67))", new AstPrinter().print(expr));
    }

}