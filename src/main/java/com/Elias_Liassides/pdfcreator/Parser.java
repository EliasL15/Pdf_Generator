package com.Elias_Liassides.pdfcreator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private String inputFilePath;
    private FormattingState formattingState;
    private boolean isNewParagraph;

    public Parser(String inputFilePath) {
        this.inputFilePath = inputFilePath;
        this.formattingState = new FormattingState();
        this.isNewParagraph = false;
    }
    // Parse the input file and return a list of TextElement objects
    public List<TextElement> parse() throws IOException {
        List<TextElement> textElements = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            StringBuilder textBuffer = new StringBuilder();
            FormattingState currentState = formattingState.clone();

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith(".")) {
                    if (textBuffer.length() > 0) {
                        addTextElement(textElements, textBuffer.toString(), currentState.clone());
                        textBuffer.setLength(0);
                    }

                    String command = line.substring(1).trim();
                    if (command.startsWith("indent ")) {
                        handleIndentCommand(command, currentState);
                    } else {
                        switch (command) {
                            case "paragraph":
                                isNewParagraph = true;
                                break;
                            case "fill":
                                currentState.setFill(true);
                                break;
                            case "nofill":
                                currentState.setFill(false);
                                isNewParagraph = true;
                                break;
                            case "regular":
                                currentState.setBold(false);
                                currentState.setItalic(false);
                                break;
                            case "bold":
                                currentState.setBold(true);
                                break;
                            case "italic":
                                currentState.setItalic(true);
                                break;
                            case "large":
                                currentState.setFontSize(FormattingState.LARGE);
                                break;
                            case "normal":
                                currentState.setFontSize(FormattingState.NORMAL);
                                break;
                            default:
                                System.err.println("Unknown command: ." + command);
                                break;
                        }
                    }
                } else if (line.isEmpty()) {
                    if (textBuffer.length() > 0) {
                        addTextElement(textElements, textBuffer.toString(), currentState.clone());
                        textBuffer.setLength(0);
                    }
                    isNewParagraph = true;
                } else {
                    if (textBuffer.length() > 0 && !textBuffer.toString().endsWith(" ")) {
                        textBuffer.append(" ");
                    }
                    textBuffer.append(line);
                }
            }

            if (textBuffer.length() > 0) {
                addTextElement(textElements, textBuffer.toString(), currentState.clone());
            }
        }
        return textElements;
    }
    
    // Handle the indent command
    private void handleIndentCommand(String command, FormattingState currentState) {
        try {
            String[] parts = command.split(" ");
            if (parts.length == 2) {
                int indentValue = Integer.parseInt(parts[1]);
                if (indentValue > 0) {
                    currentState.increaseIndentation(indentValue);
                } else {
                    currentState.decreaseIndentation(-indentValue);
                }
            } else {
                System.err.println("Invalid indent command: ." + command);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid indent value: ." + command);
        }
    }

    // Add a new TextElement to the list of text elements
    private void addTextElement(List<TextElement> textElements, String text, FormattingState state) {
        text = text.trim();
        if (!text.isEmpty()) {
            TextElement textElement = new TextElement(text, state, isNewParagraph);
            textElements.add(textElement);
        }
        isNewParagraph = false;
    }
}