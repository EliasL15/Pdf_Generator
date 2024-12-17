package com.Elias_Liassides.pdfcreator;

import java.io.IOException;
import java.util.List;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.*;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;

public class PDFGenerator {
    private String outputFilePath;

    public PDFGenerator(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    // Generate a PDF file from a list of TextElement objects
    public void generatePDF(List<TextElement> textElements) throws IOException {
        PdfWriter writer = new PdfWriter(outputFilePath);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        document.setMargins(36, 36, 36, 36); // 0.5 inch margins

        Paragraph paragraph = null;
        for (int i = 0; i < textElements.size(); i++) {
            TextElement element = textElements.get(i);
            if (element.isNewParagraph() || paragraph == null) {
                if (paragraph != null && !paragraph.isEmpty()) {
                    document.add(paragraph);
                }
                paragraph = new Paragraph();
                float indentation = element.getFormattingState().getIndentationLevel() * 20f;
                paragraph.setMarginLeft(indentation);
                if (element.getFormattingState().isFill()) {
                    paragraph.setTextAlignment(TextAlignment.JUSTIFIED);
                } else {
                    paragraph.setTextAlignment(TextAlignment.LEFT);
                }
                paragraph.setMarginBottom(10f);
            }

            FormattingState state = element.getFormattingState();
            Text text = new Text(element.getText());
            PdfFont font = getFont(state);
            text.setFont(font);
            text.setFontSize(state.getFontSize());

            // Only add a space if the previous text ends with a word character and the current text does not start with punctuation
            if (i > 0 && !element.isNewParagraph()) {
                String previousText = textElements.get(i - 1).getText();
                if (previousText.matches(".*[a-zA-Z0-9]$") && !element.getText().matches("^[.,!?].*")) {
                    paragraph.add(new Text(" "));
                }
            }

            paragraph.add(text);
        }

        if (paragraph != null && !paragraph.isEmpty()) {
            document.add(paragraph);
        }
        document.close();
    }

    // Get the appropriate font based on the formatting state
    private PdfFont getFont(FormattingState state) throws IOException {
        String fontName = StandardFonts.HELVETICA;

        if (state.isBold() && state.isItalic()) {
            fontName = StandardFonts.HELVETICA_BOLDOBLIQUE;
        } else if (state.isBold()) {
            fontName = StandardFonts.HELVETICA_BOLD;
        } else if (state.isItalic()) {
            fontName = StandardFonts.HELVETICA_OBLIQUE;
        }

        return PdfFontFactory.createFont(fontName);
    }
}
