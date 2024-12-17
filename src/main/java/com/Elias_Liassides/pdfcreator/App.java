package com.Elias_Liassides.pdfcreator;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Check if input and output file paths are provided
        if (args.length < 2) {
            System.err.println("Usage: java -jar pdfcreator.jar <input_file_path> <output_file_path>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        try {
            // Initialize the Parser
            Parser parser = new Parser(inputFilePath);
            List<TextElement> textElements = parser.parse();

            // Initialize the PDFGenerator
            PDFGenerator pdfGenerator = new PDFGenerator(outputFilePath);
            pdfGenerator.generatePDF(textElements);

            System.out.println("PDF generated successfully at " + outputFilePath);
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred during PDF generation: " + e.getMessage());
        }
    }
}
