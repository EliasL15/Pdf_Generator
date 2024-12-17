
# PDF Generator

This project is a PDF creator application developed as part of Gearset Software Engineering Internship 2025 assessment. It takes input from a text file, processes formatting commands, and generates a PDF document with the specified formatting and layout. 

## Project Overview

The input file (`input.txt`) contains a series of commands and text that describe what should be included in the PDF output and how it should be formatted. The output is a PDF document with correctly formatted text, following the instructions provided.

## Assumptions

- The project assumes that you have **Maven** installed on your system.
- The application is configured to use **iText** for PDF generation in Java.
- All text and formatting commands are stored in the `input.txt` file located at `./src/main/resources/input.txt`.
- The output file will be generated in the root directory and named `output.pdf` by default, though you can specify any output path in the command line.

## Requirements

- Java 8 or higher
- Maven (Ensure Maven is installed and accessible from your command line)

## Setup Instructions

1. **Open the Project in an IDE**: You can open this project in **Visual Studio Code**, **IntelliJ IDEA**, or any other IDE that supports Java and Maven.
2. **Install Maven Dependencies**: If your IDE doesn’t automatically install Maven dependencies, run the following command to download and set up necessary libraries:
   ```bash
   mvn clean install
   ```

## Modifying the Input File

- The input file `input.txt` is located at `./src/main/resources/input.txt`.
- Update this file with your desired text and formatting commands.
- Supported commands are:
  - `.paragraph`: Start a new paragraph.
  - `.fill`: Set paragraph alignment to fully justified.
  - `.nofill`: Default alignment with natural line breaks.
  - `.regular`: Reset font to normal.
  - `.italic`: Set font to italic.
  - `.bold`: Set font to bold.
  - `.indent <number>`: Indent the specified amount.
  - `.large`: Increase font size.
  - `.normal`: Set font size back to normal.

## Running the Application

To generate the PDF, use the following command:

```bash
mvn exec:java -Dexec.mainClass="com.Elias_Liassides.pdfcreator.App" -Dexec.args="src/main/resources/input.txt output.pdf"
```

- This command reads `input.txt` and writes the generated PDF to `output.pdf` in the project root.

## Troubleshooting

- **Maven Issues**: Ensure Maven is installed and accessible in your PATH. Run `mvn -v` to verify your installation.
- **Dependency Errors**: Run `mvn clean install` to rebuild the project and download dependencies.
- **PDF Formatting**: Confirm that `input.txt` is structured according to the supported commands listed above. Incorrect formatting in `input.txt` may lead to unexpected output.

## Additional Notes

- The specification provided states that `.italic` should set the font to italic. However, in the example input, `.italics` is used instead. This implementation adheres to the specification by using `.italic` rather than the `.italics` found in the example input.
- This project is designed for easy setup and use, with customizable inputs through `input.txt`.
- In certain areas, I’ve made trade-offs between implementing a more optimal design and meeting the project deadline. While the current approach is functional, I would explore alternative methods and refactor sections for better code design given more time.
- Unit tests are not included in the codebase. Given time constraints, I was unable to implement unit tests or make certain refactorings that would improve code readability and maintainability.
- For any issues not covered in this document, please reach out with details.
---

Thank you for reviewing my solution!
