package org.kwong.bishe.common.util;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 操作poi excel的工具类
 * 
 */
public class ExcelUtils {
	
	/**
	 * 为单元格设值
	 * @param hs 工作区对象
	 * @param row 单元格所在行数
	 * @param col 单元格所在列数
	 * @param value 设置的值
	 */
	public static void setValue(HSSFSheet hs, int row, int col, String value) {
		setValue(hs, row, col, value, null);
	}
	
	/**
	 * 为单元格设值
	 * @param style 单元格样式对象
	 */
	public static void setValue(HSSFSheet hs, int row, int col, String value, HSSFCellStyle style) {
		HSSFRow hr = hs.getRow(row) == null ? hs.createRow(row) : hs.getRow(row);
		HSSFCell hc = hr.getCell(col) == null ? hr.createCell(col) : hr.getCell(col);
		if (null != style) {
			hc.setCellStyle(style);
		}
		hc.setCellValue(value);
	}
	
	/**
	 * 为单元格设值，同时给单元格添加边框。
	 * @param wb 单元格所在excel对象
	 * @param hs 单元格所在工作区对象
	 * @param row 单元格所在行数
	 * @param col 单元格所在列数
	 * @param value 设置的值
	 */
	public static void setValueWithBorder(HSSFWorkbook wb, HSSFSheet hs, int row, int col, String value) {
		setValueWithBorder(wb, hs, row, col, value, null);
	}
	
	/**
	 * 为单元格设值，同时给单元格添加边框。
	 * @param style 单元格样式对象
	 */
	public static void setValueWithBorder(HSSFWorkbook wb, HSSFSheet hs, int row, int col, String value, HSSFCellStyle style) {
		HSSFRow hr = hs.getRow(row) == null ? hs.createRow(row) : hs.getRow(row);
		HSSFCell hc = hr.getCell(col) == null ? hr.createCell(col) : hr.getCell(col);
		HSSFCellStyle cs;
		if (null != style) {
			cs = style;
		}else{
			cs = wb.createCellStyle();
		}
		setCsBorder(cs);
		hc.setCellStyle(cs);
		hc.setCellValue(value);
	}

	/**
	 * 给单元格设置边框
	 * @param cs
	 */
	public static void setCsBorder(HSSFCellStyle cs) {
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
	}
	
	/**
	 * 给单元格设值
	 * @param cell 单元格对象
	 * @param value 设置的值
	 */
	public static void setCellValue(HSSFCell cell, Object value) {
		if(value == null){
			return;
		}
		if (value instanceof Long || value instanceof Integer || value instanceof Double) {
			cell.setCellValue(Double.valueOf(String.valueOf(value)));
		} else {
			cell.setCellValue(String.valueOf(value));
		}
	}

	/**
	 * 调用此方法，直接下载到客户端
	 * @param response
	 * @param fileName
	 */
	public static void download(HttpServletResponse response, HSSFWorkbook workbook, String fileName) throws FileNotFoundException,
			SecurityException, IllegalArgumentException, IOException, NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		response.setHeader("content-disposition", "attachment;filename=\"" + new String(fileName.getBytes("gb2312"), "iso8859-1") + "\"");
		response.setContentType("APPLICATION/msexcel");
		OutputStream out = response.getOutputStream();
		workbook.write(out);
		out.close();
	}
	
	/**
	 * 从url读出excel
	 */
	public static HSSFWorkbook loadWorkbook(String filePath) throws FileNotFoundException, IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
		return new HSSFWorkbook(fs);
	}
	
	/**
	 * 把excel保存到磁盘
	 */
	public static void saveToHarddisk(String url, HSSFWorkbook workbook) throws IOException {
		BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(url));
		workbook.write(bo);
		bo.close();
	}
	
	/**
	 * 合并单元格
	 * @param sheet
	 * @param startRow 开始单元格所在行
	 * @param startCol 开始单元格所在列
	 * @param endRow 结束单元格所在行
	 * @param endCol 结束单元格所在列
	 */
	public static void mergeRegion(HSSFSheet sheet, int startRow, short startCol, int endRow, short endCol, HSSFCellStyle cs){
		CellRangeAddress address = new CellRangeAddress(startRow, startCol, endRow, endCol);
		sheet.addMergedRegion(address);
		if(cs != null){
			setRegionStyle(sheet, address, cs);
		}
	}
	
	private static void setRegionStyle(HSSFSheet sheet, CellRangeAddress cellRangeAddress , HSSFCellStyle cs) {
        for (int i = cellRangeAddress.getFirstColumn(); i <= cellRangeAddress.getLastColumn(); i ++) {
            HSSFRow row = sheet.getRow(i) == null? sheet.createRow(i) : sheet.getRow(i);
            for (int j = cellRangeAddress.getFirstColumn(); j <= cellRangeAddress.getLastColumn(); j++) {
                HSSFCell cell = row.getCell(j) == null? row.createCell(j) : row.getCell(j);
                cell.setCellStyle(cs);
            }
        }
	}
	
	/**
	 * 创建样式
	 * @param workbook
	 * @param color
	 */
	public static HSSFCellStyle createFCStyle(HSSFWorkbook workbook, short color) {
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont fort = workbook.createFont();
		fort.setColor(color);
		style.setFont(fort);
		return style;
	}
	
	/**
	 * 拷贝excel区域
	 * @param wb
	 * @param pSourceSheetName 源sheet索引
	 * @param pTargetSheetName 目标sheet索引
	 * @param pStartRow 开始行
	 * @param pEndRow 结束行
	 * @param pPosition 目标sheet的开始行
	 */
	public static void copyRows(HSSFWorkbook wb, int sourceSheetIndex, int pTargetSheetIndex, int pStartRow,
			int pEndRow, int pPosition) {
		HSSFSheet sourceSheet = wb.getSheetAt(sourceSheetIndex);
		HSSFSheet targetSheet = wb.getSheetAt(pTargetSheetIndex);
		copyRows(wb, sourceSheet, targetSheet, pStartRow, pEndRow, pPosition);
	}
	
	/**
	 * 拷贝excel区域
	 * @param wb
	 * @param pSourceSheetName 源sheet
	 * @param pTargetSheetName 目标sheet
	 * @param pStartRow 开始行
	 * @param pEndRow 结束行
	 * @param pPosition 目标sheet的开始行
	 */
	public static void copyRows(HSSFWorkbook wb, String pSourceSheetName, String pTargetSheetName, int pStartRow,
			int pEndRow, int pPosition) {
		HSSFSheet sourceSheet = wb.getSheet(pSourceSheetName);
		HSSFSheet targetSheet = wb.getSheet(pTargetSheetName);
		copyRows(wb, sourceSheet, targetSheet, pStartRow, pEndRow, pPosition);
	}
	
	/**
	 * 拷贝excel区域
	 */
	private static void copyRows(HSSFWorkbook wb, HSSFSheet sourceSheet, HSSFSheet targetSheet, int pStartRow,
			int pEndRow, int pPosition) {
		HSSFRow sourceRow = null;
		HSSFRow targetRow = null;
		HSSFCell sourceCell = null;
		HSSFCell targetCell = null;
		CellRangeAddress region = null;
		int cType;
		int i;
		int j;
		int targetRowFrom;
		int targetRowTo;

		if ((pStartRow == -1) || (pEndRow == -1)) {
			return;
		}
		
		// 拷贝合并的单元格
		for (i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
			region = sourceSheet.getMergedRegion(i);
			if ((region.getFirstRow() >= pStartRow) && (region.getLastRow() <= pEndRow)) {
				targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
				targetRowTo = region.getLastRow() - pStartRow + pPosition;
				region.setFirstRow(targetRowFrom);
				region.setLastRow(targetRowTo);
				targetSheet.addMergedRegion(region);
			}
		}
		// 设置列宽
		for (i = pStartRow; i <= pEndRow; i++) {
			sourceRow = sourceSheet.getRow(i);
			if (sourceRow != null) {
				for (j = sourceRow.getLastCellNum(); j >= sourceRow.getFirstCellNum(); j--) {
					targetSheet.setColumnWidth(j, sourceSheet.getColumnWidth(j));
					targetSheet.setColumnHidden(j, false);
				}
				break;
			}
		}
		// 拷贝行并填充数据
		for (; i <= pEndRow; i++) {
			sourceRow = sourceSheet.getRow(i);
			if (sourceRow == null) {
				continue;
			}
			targetRow = targetSheet.createRow(i - pStartRow + pPosition);
			targetRow.setHeight(sourceRow.getHeight());
			for (j = sourceRow.getFirstCellNum(); j < sourceRow.getPhysicalNumberOfCells(); j++) {
				sourceCell = sourceRow.getCell(j);
				if (sourceCell == null) {
					continue;
				}
				targetCell = targetRow.createCell(j);
//				targetCell.setEncoding(sourceCell.getEncoding());
				targetCell.setCellStyle(sourceCell.getCellStyle());
				cType = sourceCell.getCellType();
				targetCell.setCellType(cType);
				switch (cType) {
				case HSSFCell.CELL_TYPE_BOOLEAN:
					targetCell.setCellValue(sourceCell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					targetCell.setCellFormula(parseFormula(sourceCell.getCellFormula()));
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					targetCell.setCellValue(sourceCell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					targetCell.setCellValue(sourceCell.getRichStringCellValue());
					break;
				}
			}
		}
	}

	private static String parseFormula(String pPOIFormula) {
		final String cstReplaceString = "ATTR(semiVolatile)"; //$NON-NLS-1$
		StringBuffer result = null;
		int index;

		result = new StringBuffer();
		index = pPOIFormula.indexOf(cstReplaceString);
		if (index >= 0) {
			result.append(pPOIFormula.substring(0, index));
			result.append(pPOIFormula.substring(index + cstReplaceString.length()));
		} else {
			result.append(pPOIFormula);
		}

		return result.toString();
	}
	
	public static String getCellStringValue(HSSFCell cell) {
	    return getCellStringValue(cell, null);
	  }
	
	public static String getCellStringValue(HSSFCell cell, DecimalFormat decimalFormat) {
		DecimalFormat df = decimalFormat == null ? new DecimalFormat("#") : decimalFormat;
	    String text = "";
	    // 判断单元格的类别,并且全部转换成String 类型
	    switch (cell.getCellType()) {
	      case 0: // Numeric
	        text = StringUtils.trimToEmpty(df.format(cell.getNumericCellValue()));
	        break;
	      case 1: // String
	        text =  StringUtils.trimToEmpty(cell.getStringCellValue());
	      default:
	        text =  StringUtils.trimToEmpty(cell.getStringCellValue());
	        break;
	    } 
	    return text;
	  }
	
	public static void main(String[] args) {
		  try {
		   POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
		     "d:\\test.xls"));
		   HSSFWorkbook wb = new HSSFWorkbook(fs);

		//source ,target 为,源sheet 页和目标sheet页,
//		   copyRows(wb, "Sheet1", "Sheet2", 14, 23, 0);
//		   wb.cloneSheet(0);
//		   wb.setSheetName(2, "Sheet1");
		   setValue(wb.getSheetAt(0), 5, 5, "ddd");
		   setValueWithBorder(wb, wb.getSheetAt(0), 5, 8, "ddd2");
		   FileOutputStream fileOut = new FileOutputStream("d:\\test333.xls");
		   wb.write(fileOut);
		   fileOut.flush();
		   fileOut.close();
		   LoggerUtil.info("Operation finished");
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }


}
