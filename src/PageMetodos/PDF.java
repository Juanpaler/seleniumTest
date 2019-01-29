package PageMetodos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;


public class PDF {
	public String ContenidoPDF(String sArchivo) throws IOException {

		PDFParser pdfParser = new PDFParser(new FileInputStream(sArchivo));
        pdfParser.parse();
        PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
        org.apache.pdfbox.util.PDFTextStripper pdfTextStripper = new org.apache.pdfbox.util.PDFTextStripper();
        String texto = pdfTextStripper.getText(pdDocument);
        System.out.println("Texto> "+texto);
        return texto;
        
	}
}