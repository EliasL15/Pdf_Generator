package com.Elias_Liassides.pdfcreator;

public class FormattingState implements Cloneable {
    public static final int NORMAL = 12;
    public static final int LARGE = 24;
    
    private boolean isBold;
    private boolean isItalic;
    private int fontSize;
    private int indentationLevel;
    private boolean isFill;

    public FormattingState() {
        this.isBold = false;
        this.isItalic = false;
        this.fontSize = NORMAL;
        this.indentationLevel = 0;
        this.isFill = true;
    }

    // Getters and setters
    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean isBold) {
        this.isBold = isBold;
    }

    public boolean isItalic() {
        return isItalic;
    }

    public void setItalic(boolean isItalic) {
        this.isItalic = isItalic;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getIndentationLevel() {
        return indentationLevel;
    }

    public void increaseIndentation(int amount) {
        this.indentationLevel += amount;
    }

    public void decreaseIndentation(int amount) {
        this.indentationLevel = Math.max(0, this.indentationLevel - amount);
    }

    public boolean isFill() {
        return isFill;
    }

    public void setFill(boolean isFill) {
        this.isFill = isFill;
    }

    public void setIndentationLevel(int level) {
        this.indentationLevel = level;
    }
    // Clone method to create a copy of the current state
    @Override
    public FormattingState clone() {
        try {
            return (FormattingState) super.clone();
        } catch (CloneNotSupportedException e) {
            // Should never happen since we are Cloneable
            throw new RuntimeException("Clone not supported", e);
        }
    }
}