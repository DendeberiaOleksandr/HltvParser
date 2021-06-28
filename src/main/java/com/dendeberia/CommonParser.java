package com.dendeberia;

import com.sun.istack.internal.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CommonParser {
    private static final Logger log = LogManager.getLogger();

    @Nullable
    public Element getElementByClass(Element parent, String elementClass) {
        if(parent != null && elementClass !=  null){

            log.info("getElementByClass: " + parent.toString() + " " + elementClass);

            Elements elementsByClass = getElementsByClass(parent, elementClass);
            if(elementsByClass != null){
                return elementsByClass.get(0);
            }
        }
        return null;
    }

    @Nullable
    public Elements getElementsByClass(Element parent, String elementClass) {
        if(parent != null && elementClass !=  null){
            log.info("getElementsByClass: " + parent.toString() + " " + elementClass);
            Elements elementsByClass = parent.getElementsByClass(elementClass);
            return elementsByClass;
        }
        return null;
    }

    @Nullable
    public Element getElementByTag(Element parent, String tag) {
        if(parent != null && tag !=  null){
            log.info("getElementByTag: " + parent.toString() + " " + tag);
            Elements elements = getElementsByTag(parent, tag);
            if (elements != null){
                return elements.get(0);
            }
        }
        return null;
    }

    @Nullable
    public Elements getElementsByTag(Element parent, String tag) {
        if(parent != null && tag !=  null){
            log.info("getElementsByTag: " + parent.toString() + " " + tag);
            Elements elements = parent.getElementsByTag(tag);
            return elements;
        }

        return null;
    }

    @Nullable
    public Element getElementByClass(Element parent, String elementClass, int index){
        Elements elements = getElementsByClass(parent, elementClass);
        if(elements != null){
            if(elements.size() > index){
                return elements.get(index);
            }
        }
        return null;
    }

    @Nullable
    public Element getElementByTag(Element parent, String tag, int index){
        Elements elements = getElementsByClass(parent, tag);
        if(elements != null){
            if(elements.size() > index){
                return elements.get(index);
            }
        }
        return null;
    }

    @Nullable
    public Document getPage(String url)  {
        Document page = null;
        try {
            page = Jsoup.connect(url).get();
            return page;
        } catch (IOException e) {
            log.error("getPage: error when GET page " +  url);
        }

        return page;
    }
}
