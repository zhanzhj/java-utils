package org.liuxinyi.utils.zip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric.Liu on 2016/10/10.
 */
@Getter
@Setter
@ToString
public class ZipVo {
    /**
     * 压缩包的名字
     */
    private String zipFilePath;
    private List<ZipItemVo> zipItemVos = new ArrayList<>();
}
