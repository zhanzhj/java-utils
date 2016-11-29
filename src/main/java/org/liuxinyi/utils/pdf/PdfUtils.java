package org.liuxinyi.utils.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Eric.Liu on 2016/11/29.
 */
@Slf4j
public class PdfUtils {

    @Test
    public void test1() {
        try {
            Document document = new Document();
            OutputStream outputStream = new FileOutputStream("C:/tmp/pdf/hello_world.pdf");
            PdfWriter.getInstance(document, outputStream);
            document.open();
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(baseFont, 12, Font.NORMAL);
            document.add(new Paragraph("PDF 中文测试", font));
            // document.add(new Paragraph("PDF"));
            document.close();
        } catch (Exception e) {
            log.error("failed to create pdf!", e);
        }
    }
}
