package org.liuxinyi.utils.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Arrays;

@Slf4j
public class AesUtils {

    //算法名
    public static final String KEY_ALGORITHM = "AES";
    //加解密算法/模式/填充方式
    //可以任意选择，为了方便后面与iOS端的加密解密，采用与其相同的模式与填充方式
    //ECB模式只用密钥即可对数据进行加密解密，CBC模式需要添加一个参数iv
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

    //生成密钥
    public static byte[] generateKey() throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }

    //生成iv
    public static AlgorithmParameters generateIV() throws Exception {
        //iv 为一个 16 字节的数组，这里采用和 iOS 端一样的构造方法，数据全为0
        byte[] iv = new byte[16];
        Arrays.fill(iv, (byte) 0x00);
        AlgorithmParameters params = AlgorithmParameters.getInstance(KEY_ALGORITHM);
        params.init(new IvParameterSpec(iv));
        return params;
    }

    //转化成JAVA的密钥格式
    public static Key convertToKey(byte[] keyBytes) throws Exception {
        SecretKey secretKey = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        return secretKey;
    }

    //加密
    public static byte[] encrypt(byte[] data, byte[] keyBytes, AlgorithmParameters iv) throws Exception {
        //转化为密钥
        Key key = convertToKey(keyBytes);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(data);
    }

    //解密
    public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes, AlgorithmParameters iv) throws Exception {
        Key key = convertToKey(keyBytes);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return cipher.doFinal(encryptedData);
    }

    @Test
    public void test1() {
        //明文
        String plainTextString = "Hello,Bouncy Castle";
        log.info("明文 :{} ", plainTextString);
        byte[] key;
        try {
            //初始化密钥
            key = generateKey();
            //初始化iv
            AlgorithmParameters iv = generateIV();
            log.info("密钥 :{}", toHex(key));
            //进行加密
            byte[] encryptedData = encrypt(plainTextString.getBytes(), key, iv);
            //输出加密后的数据
            log.info("加密后的数据 :{}", toHex(encryptedData));
            //进行解密
            byte[] data = decrypt(encryptedData, key, iv);
            log.info("解密得到的数据 : " + new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {
        byte b = 1;
        log.info(toHex(b));
    }

    public static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(Integer.toHexString(b & 0xFF));
        }
        return sb.toString();
    }


    public static String toHex(byte b) {
        String result = Integer.toHexString(b & 0xFF);
        if (result.length() == 1) {
            result = '0' + result;
        }
        return result;
    }

}