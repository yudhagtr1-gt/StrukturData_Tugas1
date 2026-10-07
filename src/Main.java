import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        File file = new File("Data/putusan.pdf");

        PDDocument document = Loader.loadPDF(file);

        PDFTextStripper stripper = new PDFTextStripper();

        String teks = stripper.getText(document);

        System.out.println(teks);

        document.close();
    }
}