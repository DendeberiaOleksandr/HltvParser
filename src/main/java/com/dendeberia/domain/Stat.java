package com.dendeberia.domain;

public class Stat {
    private String label;
    private String value;
    private String color;
    private String tooltext;
    private String displayValue;

    public Stat() {
    }

    public Stat(String label, String value, String color, String tooltext, String displayValue) {
        this.label = label;
        this.value = value;
        this.color = color;
        this.tooltext = tooltext;
        this.displayValue = displayValue;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTooltext() {
        return tooltext;
    }

    public void setTooltext(String tooltext) {
        this.tooltext = tooltext;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
}
