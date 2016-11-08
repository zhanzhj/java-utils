package org.liuxinyi.utils.excel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建excel 单元格格式全部为文本格式,不打印任何日志;
 * <p>
 * 少量数据时,建议直接使用  generateWorkBook
 * <p>
 * 大量数据时,建议
 * 1 使用 createWorkBookByModel 或 createWorkBookByHeaders 得到WorkBook
 * 2 使用 appendWorkBook 添加数据 , 可以多次添加(防止OOM)
 * 3 使用 writeWorkBookToFile 将数据写入文件
 * <p>
 * 有异常时,会抛出运行时异常
 *
 * @author xinyi.Liu
 */
public class ExcelUtils {


    /**
     * 通过头部数据得到work
     *
     * @param headers
     * @param rowInMemory
     * @return
     */
    public static Workbook createWorkBookByHeaders(List<String> headers, int rowInMemory) {
        Workbook workbook = null;
        try {
            workbook = new SXSSFWorkbook(rowInMemory);
            Sheet sheet = workbook.createSheet();
            createRow(sheet, 0, headers);
            return workbook;
        } catch (Exception e) {
            throw new RuntimeException("failed to createWorkBookByHeaders" + e.getMessage(), e);
        }
    }

    /**
     * 添加数据到workBook
     *
     * @param workbook
     * @param dataRows
     * @return
     */
    public static Workbook appendWorkBook(Workbook workbook, List<List<String>> dataRows) {
        Sheet sheet = workbook.getSheetAt(0);
        int rowStart = sheet.getPhysicalNumberOfRows();
        for (List<String> dataRow : dataRows) {
            createRow(sheet, rowStart++, dataRow);
        }
        return workbook;
    }

    /**
     * 将workBook写入到文件
     *
     * @param workbook
     * @param destFilePath
     */
    public static void writeWorkBookToFile(Workbook workbook, String destFilePath) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(destFilePath);
            workbook.write(outputStream);
        } catch (Exception e) {

        } finally {
            try {
                workbook.close();
                IOUtils.closeQuietly(outputStream);
            } catch (Exception e) {
            }
        }
    }


    /**
     * 生成普通的excel文件
     *
     * @param headers  excel表头
     * @param dataRows excel行数据
     * @param destPath 目标文件全路径
     */
    public static void generateWorkBook(ExcelEnum excelEnum,
                                        List<String> headers,
                                        List<List<String>> dataRows,
                                        String destPath) {
        validateParams(headers, dataRows, destPath);
        Workbook workbook = null;
        OutputStream outputStream = null;
        try {
            if (excelEnum == ExcelEnum.HSSF) {
                workbook = new HSSFWorkbook();
            } else if (excelEnum == ExcelEnum.XSSF) {
                workbook = new XSSFWorkbook();
            }
            Sheet sheet = workbook.createSheet();
            createSheet(sheet, headers, dataRows);
            outputStream = new FileOutputStream(destPath);
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new RuntimeException("failed to generate WorkBook " + e.getMessage(), e);
        } finally {
            try {
                IOUtils.closeQuietly(outputStream);
                workbook.close();
            } catch (Exception e) {
            }
        }

    }

    /**
     * 通过模板生成excel文件
     *
     * @param modelPath 模板文件路径
     * @param dataRows  excel行数据
     * @param destPath  目标文件全路径
     */
    public static void generateWorkBook(ExcelEnum excelEnum,
                                        String modelPath,
                                        List<List<String>> dataRows,
                                        String destPath) {
        validateParams(modelPath, dataRows, destPath);
        InputStream inputStream = null;
        Workbook workbook = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(modelPath);
            if (excelEnum == ExcelEnum.HSSF) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (excelEnum == ExcelEnum.XSSF) {
                workbook = new XSSFWorkbook(inputStream);
            }
            Sheet sheet = workbook.getSheetAt(0);
            createSheet(sheet, dataRows);
            outputStream = new FileOutputStream(destPath);
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new RuntimeException("failed to generate WorkBook " + e.getMessage(), e);
        } finally {
            try {
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly(outputStream);
                workbook.close();
            } catch (Exception e) {
            }
        }
    }


    private static void createSheet(Sheet sheet, List<String> headers, List<List<String>> dataRows) {
        createRow(sheet, 0, headers);
        createSheet(sheet, dataRows);
    }

    private static void createSheet(Sheet sheet, List<List<String>> dataRows) {
        for (int i = 1; i <= dataRows.size(); i++) {
            createRow(sheet, i, dataRows.get(i - 1));
        }
    }

    private static void createRow(Sheet sheet, int rowNum, List<String> rowData) {
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i < rowData.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(rowData.get(i));
        }
    }


    private static List<CellRangeAddress> getChildRegions(Sheet sheet, CellRangeAddress parentRegion) {
        List<CellRangeAddress> childRegions = new ArrayList<>();
        for (CellRangeAddress region : sheet.getMergedRegions()) {
            if (region.getFirstColumn() != parentRegion.getFirstColumn()) {
                if (region.getFirstRow() >= parentRegion.getFirstRow()
                        && region.getLastRow() <= parentRegion.getLastRow()) {
                    childRegions.add(region);
                }
            }
        }
        return childRegions;

    }

    /**
     * 判断是不是第一列
     *
     * @param cellRangeAddress
     * @return
     */
    public static boolean isFirstColumn(CellRangeAddress cellRangeAddress) {
        if (cellRangeAddress.getFirstColumn() == 0) {
            return true;
        }
        return false;
    }


    /**
     * 创建一个区域并赋值
     *
     * @param sheet       sheet
     * @param rowStart    开始的行数(从0开始)
     * @param rowEnd      结束的行数
     * @param colStart    开始的列数(从0开始)
     * @param colEnd      结束的列数
     * @param regionValue 区域的值
     */
    public static void createRegion(Sheet sheet, int rowStart, int rowEnd, int colStart, int colEnd, String regionValue) {
        Row row = sheet.getRow(rowStart);
        if (null == row) {
            row = sheet.createRow(rowStart);
        }
        Cell cell = row.createCell(colStart);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(regionValue);
        CellRangeAddress cellRangeAddress =
                new CellRangeAddress(rowStart, rowEnd, colStart, colEnd);
        sheet.addMergedRegion(cellRangeAddress);
    }

    /**
     * 获取区域的值
     *
     * @param sheet            sheet
     * @param cellRangeAddress cellRangeAddress
     * @return regionValue
     */
    public static String getRegionValue(Sheet sheet, CellRangeAddress cellRangeAddress) {
        return getCellValue(sheet, cellRangeAddress.getFirstRow(), cellRangeAddress.getFirstColumn());
    }

    /**
     * 通过sheet和行列号获取单元格的值
     *
     * @param sheet  sheet
     * @param rowNum rowNum
     * @param colNum colNum
     * @return cellValue
     */
    public static String getCellValue(Sheet sheet, int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        return getCellValue(cell);
    }


    /**
     * 通过Cell获取CellValue
     *
     * @param cell cell
     * @return StringCellValue
     */
    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return "NULL";
        }
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue() + "";
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() + "";
            case Cell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue() + "";
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
        }
        return "";
    }

    /**
     * 特殊情况下取单元格的String Value
     * 比如使用公式获取的单元格的值,复制到新的excel中,无法获取到公式的值
     *
     * @param cell
     * @return
     */
    public static String getCellStringValue(Cell cell) {
        if (cell == null) {
            return "NULL";
        }
        return cell.getStringCellValue();
    }

    /**
     * 校验参数
     *
     * @param modelPath
     * @param dataRows
     */
    private static void validateParams(String modelPath,
                                       List<List<String>> dataRows,
                                       String destPath) {
        File file = new File(modelPath);
        if (!file.exists()) {
            throw new RuntimeException("file not exist " + modelPath);
        }
        validateCommonParams(dataRows, destPath);

    }

    /**
     * 校验参数
     *
     * @param headers
     * @param dataRows
     */
    private static void validateParams(List<String> headers,
                                       List<List<String>> dataRows,
                                       String destPath) {
        if (CollectionUtils.isEmpty(dataRows)) {
            throw new RuntimeException("headers is empty ");
        }
        validateCommonParams(dataRows, destPath);
    }

    private static void validateCommonParams(
            List<List<String>> dataRows,
            String destPath) {
        if (CollectionUtils.isEmpty(dataRows)) {
            throw new RuntimeException("dataRows is empty");
        }
        if (StringUtils.isBlank(destPath)) {
            throw new RuntimeException("destPath is empty");
        }
        String dir = destPath.substring(0, destPath.lastIndexOf("/"));
        File file = new File(dir);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new RuntimeException("failed to create dir " + dir);
            }
        }
    }


}
