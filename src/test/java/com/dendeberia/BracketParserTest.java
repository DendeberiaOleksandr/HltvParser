package com.dendeberia;

import com.dendeberia.domain.event.DoubleEliminationBracket;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class BracketParserTest {

    @Test
    public void testBracketParse() throws IOException {
        BracketParser bracketParser = mock(BracketParser.class);

        DoubleEliminationBracket bracket = bracketParser.parseBracket("https://www.hltv.org/events/5604/blast-premier-spring-final-2021");
        System.out.println(bracket);
    }
}
