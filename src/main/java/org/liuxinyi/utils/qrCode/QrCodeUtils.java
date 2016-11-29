package org.liuxinyi.utils.qrCode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric.Liu on 2016/11/29.
 */
@Slf4j
public class QrCodeUtils {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static final String DEFAULT_QR_FORMAT = "png";
    private static final String CHARSET = "UTF-8";

    private static final int DEFAULT_QR_SIZE = 400;

    @Test
    public void test1() {
        try {
            String content = RandomStringUtils.randomAlphanumeric(30);
            OutputStream outputStream = new FileOutputStream("C:/tmp/qr/qrSimple.png");
            generateQrCode(content, outputStream);
        } catch (Exception e) {
            log.error("failed to generateQrCode", e);
        }
    }

    @Test
    public void test2() {
        try {
            String content = RandomStringUtils.randomAlphanumeric(30);
            InputStream logoStream = new FileInputStream("c:/tmp/qr/logo1.jpg");
            OutputStream outputStream = new FileOutputStream("C:/tmp/qr/qrLogo.png");
            generateQrCodeWithLogo(content, logoStream, outputStream);
        } catch (Exception e) {
            log.error("failed to generateQrCode", e);
        }
    }

    @Test
    public void test3() {
        try {
            InputStream qrStream1 = new FileInputStream("c:/tmp/qr/qrSimple.png");
            String content1 = parseQrCode(qrStream1);
            log.info("qrSimple : {} ", content1);
            InputStream qrStream2 = new FileInputStream("c:/tmp/qr/qrLogo.png");
            String content2 = parseQrCode(qrStream2);
            log.info("qrLogo : {} ", content2);
        } catch (Exception e) {
            log.error("failed to generateQrCode", e);
        }
    }

    /**
     * 生成二维码
     *
     * @param content      content
     * @param outputStream outputStream
     * @throws Exception if
     */
    public static void generateQrCode(String content,
                                      OutputStream outputStream) throws Exception {
        BitMatrix bitMatrix = generateBitMatrix(content, ErrorCorrectionLevel.M);
        MatrixToImageWriter.writeToStream(bitMatrix, DEFAULT_QR_FORMAT, outputStream);
    }

    /**
     * 生成带logo二维码
     *
     * @param content      content
     * @param logoStream   logoStream
     * @param outputStream outputStream
     * @throws Exception if
     */
    public static void generateQrCodeWithLogo(String content,
                                              InputStream logoStream,
                                              OutputStream outputStream) throws Exception {
        BitMatrix bitMatrix = generateBitMatrix(content, ErrorCorrectionLevel.H);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        bufferedImage = logoMatrix(bufferedImage, logoStream);
        ImageIO.write(bufferedImage, DEFAULT_QR_FORMAT, outputStream);
    }

    /**
     * 根据内容生成BitMatrix,带Logo的纠错能力最高,不带logo的纠错低
     *
     * @param content              content
     * @param errorCorrectionLevel errorCorrectionLevel
     * @return BitMatrix
     * @throws Exception if
     */
    private static BitMatrix generateBitMatrix(String content, ErrorCorrectionLevel errorCorrectionLevel) throws Exception {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
        return new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, DEFAULT_QR_SIZE, DEFAULT_QR_SIZE, hints);// 生成矩阵
    }

    /**
     * @param matrixImage matrixImage
     * @param logoStream  logoStream
     * @return BufferedImage
     * @throws IOException if
     */
    private static BufferedImage logoMatrix(BufferedImage matrixImage, InputStream logoStream) throws IOException {
        // 读取二维码图片，并构建绘图对象
        Graphics2D g2 = matrixImage.createGraphics();

        int matrixWidth = matrixImage.getWidth();
        int matrixHeigh = matrixImage.getHeight();

        // 读取Logo图片
        BufferedImage logo = ImageIO.read(logoStream);

        //开始绘制图片
        g2.drawImage(logo, matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, null);//绘制
        BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke);// 设置笔画对象
        //指定弧度的圆角矩形
        RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, 20, 20);
        g2.setColor(Color.white);
        g2.draw(round);// 绘制圆弧矩形

        //设置logo 有一道灰色边框
        BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke2);// 设置笔画对象
        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth / 5 * 2 + 2, matrixHeigh / 5 * 2 + 2, matrixWidth / 5 - 4, matrixHeigh / 5 - 4, 20, 20);
        g2.setColor(new Color(128, 128, 128));
        g2.draw(round2);// 绘制圆弧矩形

        g2.dispose();
        matrixImage.flush();
        return matrixImage;
    }

    /**
     * 解析二维码
     *
     * @param qrCodeStream qrCodeStream
     * @return Text
     * @throws Exception if
     */
    public static String parseQrCode(InputStream qrCodeStream) throws Exception {
        BufferedImage image;
        image = ImageIO.read(qrCodeStream);
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Map<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
        return result.getText();
    }


}
