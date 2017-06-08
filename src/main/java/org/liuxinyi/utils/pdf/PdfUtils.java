package org.liuxinyi.utils.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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

            Document document = new Document(PageSize.A4.rotate(),
                    50, 50,
                    50, 50);
            OutputStream outputStream = new FileOutputStream("D:/Itext/hello_world.pdf");
            PdfWriter.getInstance(document, outputStream);
            document.open();
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(baseFont, 12, Font.NORMAL);
            //font.setColor(5, 5, 5);
            Paragraph paragraph = new Paragraph("PDF 中文测试", font);
            paragraph.setAlignment(Element.ALIGN_CENTER); // 设置居中
            document.add(paragraph);
            Image image = Image.getInstance("D:/Itext/20.jpg");
            image.scaleAbsolute(200, 150);
            //image.setAlignment(Element.ALIGN_RIGHT);
            image.setAbsolutePosition(550, 50);
            document.add(image);
            addTable(document);
            document.close();
        } catch (Exception e) {
            log.error("failed to create pdf!", e);
        }
    }


    private void addTable(Document document) throws Exception {
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 12, Font.NORMAL);

        PdfPTable table = new PdfPTable(new float[]{50, 80, 100, 100});// 4列的表格以及单元格的宽度。
        table.setPaddingTop(2);// 顶部空白区高度
        table.setTotalWidth(360);//表格整体宽度
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Details of past one month on sale."));

        cell.setColspan(8);//占据八列
        cell.setRowspan(2);
        table.addCell(cell);
        table.addCell(new Paragraph("销售单号", font));
        table.addCell(new Paragraph("客户编号", font));
        table.addCell(new Paragraph("客户名称", font));
        table.addCell(new Paragraph("购买方式", font));
        table.addCell(new Paragraph("1", font));
        table.addCell(new Paragraph("2", font));
        table.addCell(new Paragraph("3", font));
        table.addCell(new Paragraph("4", font));


        document.add(table);
    }
}
