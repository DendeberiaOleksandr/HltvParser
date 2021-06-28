package com.dendeberia;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonParserTest {

    private CommonParser commonParser;

    @BeforeEach
    public void init(){
        this.commonParser = new CommonParser();
    }

    @Test
    public void getPageShouldReturnNull(){
        Document page = commonParser.getPage("");
        Assertions.assertEquals(null, page);
    }

    @Test
    public void getPageShouldNotReturnNull(){
        Document page = commonParser.getPage("https://www.google.com/");
        Assertions.assertNotEquals(null, page);
    }
}
