package com.Elias_Liassides.pdfcreator;

public class TextElement {
    private String text;
    private FormattingState formattingState;
    private boolean isNewParagraph;

    public TextElement(String text, FormattingState formattingState, boolean isNewParagraph) {
        this.text = text;
        this.formattingState = formattingState;
        this.isNewParagraph = isNewParagraph;
    }

    // Getters
    public String getText() {
        return text;
    }

    public FormattingState getFormattingState() {
        return formattingState;
    }

    public boolean isNewParagraph() {
        return isNewParagraph;
    }
}
