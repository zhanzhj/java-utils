package org.liuxinyi.utils.zip;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Eric.Liu on 2016/10/10.
 */
@Slf4j
public class ZipUtils {

    public static void zipFiles(ZipVo zipVo) {
        ZipArchiveOutputStream zaos = null;
        InputStream inputStream = null;
        try {
            File zipFile = new File(zipVo.getZipFilePath());
            zaos = new ZipArchiveOutputStream(zipFile);
            zaos.setUseZip64(Zip64Mode.AsNeeded);
            for (ZipItemVo itemVo : zipVo.getZipItemVos()) {
                try {
                    ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(itemVo.getDestFileName());
                    zaos.putArchiveEntry(zipArchiveEntry);
                    inputStream = new FileInputStream(itemVo.getFile());
                    byte[] buffer = new byte[1024 * 4];
                    int len = -1;
                    while ((len = inputStream.read(buffer)) != -1) {
                        zaos.write(buffer, 0, len);
                    }
                    zaos.closeArchiveEntry();
                } finally {
                    IOUtils.closeQuietly(inputStream);
                }
            }
            zaos.finish();
        } catch (Exception e) {
            log.error(" zip file failed with message: {} ", e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(zaos);
        }
    }


    public static long getFileSize(String filePath) {
        return getFileSize(new File(filePath));
    }

    public static long getFileSize(File file) {
        if (file.exists() && file.isFile()) {
            return file.length() / 1024;
        } else {
            return 0;
        }
    }

}
