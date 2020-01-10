package com.dtdc.cd.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dtdc.cd.bo.BlockShipmentResultBO;

public class ShipmentExcelView {

	public static ByteArrayInputStream shipmentToExcel(List<BlockShipmentResultBO> shipList) throws IOException {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Workbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = (XSSFSheet) workbook.createSheet("CP-Debit Report");
		
		XSSFRow headerRow = sheet.createRow(0);
		
		XSSFCell cell00 = headerRow.createCell(0);
		cell00.setCellValue("Consignment Number");
		
		XSSFCell cell01 = headerRow.createCell(1);
		cell01.setCellValue("Excel File Remarks");
		
		XSSFCell cell02 = headerRow.createCell(2);
		cell02.setCellValue("Status");
		
		XSSFCell cell03 = headerRow.createCell(3);
		cell03.setCellValue("Status Remarks");
		
		XSSFCell cell04 = headerRow.createCell(4);
		cell04.setCellValue("Upload File Name");
		
		XSSFCell cell05 = headerRow.createCell(5);
		cell05.setCellValue("Download File Name");
		
		XSSFCell cell06 = headerRow.createCell(6);
		cell06.setCellValue("Process Type");
		
		//XSSFCell cell07 = headerRow.createCell(7);
		//cell07.setCellValue("Process Date Time");
		
		//XSSFCell cell08 = headerRow.createCell(8);
		//cell08.setCellValue("UserID");
		
		XSSFCell cell07 = headerRow.createCell(7);
		cell07.setCellValue("NodeID");
		
		
		
		for(int i=0;i<shipList.size();i++) {
			
			BlockShipmentResultBO bo = shipList.get(i);
			
			XSSFRow dataRow = sheet.createRow(i+1);
			
			XSSFCell cell10 = dataRow.createCell(0);
			cell10.setCellValue(bo.getShipmentNumber());
			
			XSSFCell cell11 = dataRow.createCell(1);
			cell11.setCellValue(bo.getExcelFileRemarks());
			
			XSSFCell cell12 = dataRow.createCell(2);
			cell12.setCellValue(bo.getStatus());
			
			XSSFCell cell13 = dataRow.createCell(3);
			cell13.setCellValue(bo.getStatusRemarks());
			
			XSSFCell cell14 = dataRow.createCell(4);
			cell14.setCellValue(bo.getUploadFileName());
			
			XSSFCell cell15 = dataRow.createCell(5);
			cell15.setCellValue(bo.getDownldFileName());
			
			XSSFCell cell16 = dataRow.createCell(6);
			cell16.setCellValue(bo.getProcessType());
			
			//XSSFCell cell17 = dataRow.createCell(7);
			//cell17.setCellValue(bo.getProcessDateTime());
			
			//XSSFCell cell18 = dataRow.createCell(8);
			//cell18.setCellValue(bo.getUserID());
			
			XSSFCell cell17 = dataRow.createCell(7);
			cell17.setCellValue(bo.getNodeID());
		}
		
		workbook.write(out);
		
		return new ByteArrayInputStream(out.toByteArray());
	}
}
