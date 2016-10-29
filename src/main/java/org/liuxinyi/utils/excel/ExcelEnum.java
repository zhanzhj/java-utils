package org.liuxinyi.utils.excel;

/**
 * 适用于生成少量数据的excel
 * <p>
 * 大量数据的excel参照 ExcelUtils的说明
 * SXSSF(".xlsx", "excel 2010+")
 */
public enum ExcelEnum {
    /**
     * 适用于excel2007的xls文件
     */
    HSSF(".xls", "excel 2007"),
    /**
     * 适用于excel2010+的xlsx文件
     */
    XSSF(".xlsx", "excel 2010+");


    private ExcelEnum(String extension, String desc) {
        this.extension = extension;
        this.desc = desc;
    }

    private String extension;
    private String desc;

    public String getExtension() {
        return extension;
    }

    public String getDesc() {
        return desc;
    }
}
