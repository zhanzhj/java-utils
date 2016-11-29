package org.liuxinyi.utils.pdf;

import com.itextpdf.text.*;
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
            //font.setColor(5, 5, 5);
            Paragraph paragraph = new Paragraph("PDF 中文测试", font);
            paragraph.setAlignment(Element.ALIGN_CENTER); // 设置居中
            document.add(paragraph);
            Image image = Image.getInstance("C:/tmp/qr/logo1.jpg");
            image.scaleAbsolute(200, 150);
            document.add(image);
            document.close();
        } catch (Exception e) {
            log.error("failed to create pdf!", e);
        }
    }
}
