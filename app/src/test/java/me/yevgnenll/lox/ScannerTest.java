package me.yevgnenll.lox;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static me.yevgnenll.lox.TokenType.BANG_EQUAL;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScannerTest extends TestCase {

    @Test
    public void test() {
        Scanner scanner = new Scanner("!=");

        List<Token> tokens = scanner.scanTokens();
        assertThat("token size is 2", tokens.size() == 2);
        assertThat("BANG_EQUAL contains", tokens.stream().map(it -> it.type).collect(Collectors.toSet()).contains(BANG_EQUAL) );
    }

}