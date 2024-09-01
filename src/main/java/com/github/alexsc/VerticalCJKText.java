package com.github.alexsc;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class VerticalCJKText {
    public static void main(String[] args) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Load a CJK font
            PDType0Font font = PDType0Font.loadVertical(document, new File("NotoSerifCJKhk-VF.ttf"));
            System.err.println(font.isVertical());

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                String cjkText = "Hello, 你好!"; // Example vertical text in Japanese

                // Set the font and font size
                contentStream.setFont(font, 12);

                // Start the text matrix for vertical writing
                contentStream.beginText();

                // Move the cursor to the start position
                contentStream.newLineAtOffset(100, 700);

                // Rotate the text for vertical writing
                //contentStream.setTextMatrix(0, 1, 1, 0, 100, 700);

                // Write the text
                //for (char c : cjkText.toCharArray()) {
                    contentStream.showText(cjkText);
                    //contentStream.newLineAtOffset(0, -14); // Move down for the next character
                //}

                contentStream.endText();
            }

            document.save("VerticalCJKText.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
