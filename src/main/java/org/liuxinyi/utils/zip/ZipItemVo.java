package org.liuxinyi.utils.zip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;

/**
 * Created by Eric.Liu on 2016/10/10.
 */
@Getter
@Setter
@ToString
public class ZipItemVo {
    private File file;
    /**
     * zip压缩包里面的文件名,如果和源文件名一样可以不传
     */
    private String destFileName;
}
