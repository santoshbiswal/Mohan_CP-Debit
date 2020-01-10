package com.dtdc.cd.deligate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.dtdc.cd.bo.BlockShipmentBO;
import com.dtdc.cd.bo.BlockShipmentResultBO;
import com.dtdc.cd.dto.ResultLogStatusDTO;
import com.dtdc.cd.service.DtdcCollectionService;
import com.dtdc.cd.service.ResultStatusService;
import com.dtdc.cd.service.ShipmentBookingService;
import com.dtdc.cd.service.ShipmentDeliveryService;

@Component
public class BlockCdsGenerationDelegate {
	
	@Autowired
	private ShipmentBookingService bookingService;
	
	@Autowired
	private ShipmentDeliveryService deliveryService;
	
	@Autowired
	private DtdcCollectionService collectionService;
	
	@Autowired
	private ResultStatusService statusService;
	

	public List<BlockShipmentResultBO> processFile(MultipartFile multipartFile,HttpSession session,HttpServletRequest request) throws ParseException {
		
		List<BlockShipmentBO> shipList = processFileConversion(multipartFile);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy hh:mm:ss aa");
		
		Date dateTime = null;
		try {
			dateTime = sdf.parse(sdf.format(new Date()));
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		
		for(BlockShipmentBO bo : shipList) {
			
			String reasonToBlock = bo.getReasonToBlock();
			
			if(reasonToBlock.length()>100) {
				continue;
			}
			
			String shipmentNumber = bo.getShipmentNumber();
			
			if(shipmentNumber.charAt(0) == 'I' && shipmentNumber.length() == 5) {
				
				System.out.println("shipment number is valid format");
				// check shipment booking data is valid or not
				boolean isBookingAvailable = bookingService.isShipmentAvailable(shipmentNumber);
				
					if(isBookingAvailable == true) {
						
						// check shipment is delivered or not
						boolean isDelivered = deliveryService.isShipmentDelivered(shipmentNumber);
						
							if(isDelivered == true) {
								
								//check payment collected or not
								boolean isPaymentDone = collectionService.isPaymentCollected(shipmentNumber);
								
									if(isPaymentDone == true) {
										
										// check CDS is generated or not
										boolean isCdsNoAvailable = collectionService.isCDSGenerated(shipmentNumber);
										
											if(!isCdsNoAvailable) {
												
												// check already shipment block for cp debit
												boolean isAlreadyBlockedForCPDebit = collectionService.isAlreadyBlocked(shipmentNumber);
												
													if(isAlreadyBlockedForCPDebit == false) {
														
														//update into ndx-collection	
														String excelFileRemarks = reasonToBlock;
														String status = "Success";
														String statusRemarks = "Blocked on "+dateTime;
														Date processDateTime = dateTime;
														ResultLogStatusDTO dto = getLogFileStatus(multipartFile, session, request);
														String consignmentNumber = bo.getShipmentNumber();
														
														collectionService.updateBlockForCDS(excelFileRemarks,status,statusRemarks,processDateTime,dto,consignmentNumber);
							
														//save success data into log table
														statusService.saveLogStaus(dto, excelFileRemarks, status, statusRemarks, shipmentNumber, processDateTime, consignmentNumber);
													
														
													}else {
														
														//Failure as Shipment already blocked for CP Debit on dd/mm/yyyy
														String excelFileRemarks = reasonToBlock;
														String status = "Fail";
														String statusRemarks = "Shipment already blocked for cp debit on dd/mm/yyyy";
														Date processDateTime = dateTime;
														ResultLogStatusDTO dto = getLogFileStatus(multipartFile, session, request);
														String consignmentNumber = bo.getShipmentNumber();
														
														//save failure data into log table
														statusService.saveLogStaus(dto, excelFileRemarks, status, statusRemarks, shipmentNumber, processDateTime, consignmentNumber);
													}
												
											}else {
												
												//Failure as CDS is already generated
												String excelFileRemarks = reasonToBlock;
												String status = "Fail";
												String statusRemarks = "CDS is already generated";
												Date processDateTime = dateTime;
												ResultLogStatusDTO dto = getLogFileStatus(multipartFile, session, request);
												String consignmentNumber = bo.getShipmentNumber();
												
												//save failure data into log table
												statusService.saveLogStaus(dto, excelFileRemarks, status, statusRemarks, shipmentNumber, processDateTime, consignmentNumber);
											}
									}else {
										
										//Failure as Payment Data not found
										String excelFileRemarks = reasonToBlock;
										String status = "Fail";
										String statusRemarks = "Payment Data not found";
										Date processDateTime = dateTime;
										ResultLogStatusDTO dto = getLogFileStatus(multipartFile, session, request);
										String consignmentNumber = bo.getShipmentNumber();
										
										//save failure data into log table
										statusService.saveLogStaus(dto, excelFileRemarks, status, statusRemarks, shipmentNumber, processDateTime, consignmentNumber);
									}
							}else {
								
								//Failure as Delivery data not found
								String excelFileRemarks = reasonToBlock;
								String status = "Fail";
								String statusRemarks = "Delivery data not found";
								Date processDateTime = dateTime;
								ResultLogStatusDTO dto = getLogFileStatus(multipartFile, session, request);
								String consignmentNumber = bo.getShipmentNumber();
								
								//save failure data into log table
								statusService.saveLogStaus(dto, excelFileRemarks, status, statusRemarks, shipmentNumber, processDateTime, consignmentNumber);
							}
						
					}else {
						
						//Failure as Booking data not found
						String excelFileRemarks = reasonToBlock;
						String status = "Fail";
						String statusRemarks = "Booking data not found";
						Date processDateTime = dateTime;
						ResultLogStatusDTO dto = getLogFileStatus(multipartFile, session, request);
						String consignmentNumber = bo.getShipmentNumber();
						
						//save failure data into log table
						statusService.saveLogStaus(dto, excelFileRemarks, status, statusRemarks, shipmentNumber, processDateTime, consignmentNumber);
					}
			}else {
				
				//Failure as Invalid Shipment Number to log table
				String excelFileRemarks = reasonToBlock;
				String status = "Fail";
				String statusRemarks = "Invalid format Shipment Number";
				Date processDateTime = dateTime;
				ResultLogStatusDTO dto = getLogFileStatus(multipartFile, session, request);
				String consignmentNumber = bo.getShipmentNumber();
				
				//save failure data into log table
				statusService.saveLogStaus(dto,excelFileRemarks, status, statusRemarks, shipmentNumber,processDateTime, consignmentNumber);
			}
		}
		
		List<BlockShipmentResultBO> getShipmentList =statusService.getAllCpDebitResultInfo(dateTime); 
		
		return getShipmentList;
		
	}
	
	/*
	 * public List<BlockShipmentResultBO> getCpDebitResults(Date dateTime) throws
	 * ParseException {
	 * 
	 * return statusService.getAllCpDebitResultInfo(dateTime); }
	 */
	
	private ResultLogStatusDTO getLogFileStatus(MultipartFile multipartFile,HttpSession session,HttpServletRequest request) {
		
		int user = getUserId(session);
		File fileName=convertInToFile(multipartFile);
		String uploadFileName = fileName.getName();
		String downloadFileName= "CDS_Block_" + user + "_download" + new Date() + ".xlsx";
		String processType = "CDSB";
		String nodeID = request.getLocalAddr(); 
		
		ResultLogStatusDTO dto = new ResultLogStatusDTO();
		dto.setDownloadFileName(downloadFileName);
		dto.setProcessType(processType);
		dto.setNodeID(nodeID);
		dto.setUserID(user);
		dto.setUploadFileName(uploadFileName);
		
		return dto;
		
	}
	
	//get User ID from session
	public Integer getUserId(HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		return userId;
	}

	private List<BlockShipmentBO> processFileConversion(MultipartFile multipartFile){
		
		//File
		File file = convertInToFile(multipartFile);
		
		//Iterator<Row>
		Iterator<Row> rowIterator = getRowIterator(file);
		
		//List<Object>
		List<BlockShipmentBO> shipList = readRowIterator(rowIterator);
		
		return shipList;
	}
	
	private File convertInToFile(MultipartFile multipartFile) {
		
		File convertFile = new File(multipartFile.getOriginalFilename());
		
		try {
			convertFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convertFile);
			fos.write(multipartFile.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return convertFile;
	}
	
	private Iterator<Row> getRowIterator(File fileName) {
		
		Workbook workbook = null;
		
		// Iterate through each rows one by one
		String myFile = fileName.getName();
		String extension = myFile.substring(myFile.indexOf("."));

		Iterator<Row> rowIterator = null;
			
			try {
								
				if(extension.equalsIgnoreCase(".xls")) {
					
					workbook = new HSSFWorkbook(new FileInputStream(myFile));
					HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
					rowIterator = sheet.iterator();
					
				} else if (extension.equalsIgnoreCase(".xlsx")) {
					
					workbook = new XSSFWorkbook(new FileInputStream(myFile));
					XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
					rowIterator = sheet.iterator();
					
				}
			} catch (Exception e) {		
					e.printStackTrace();
			}
			
			return rowIterator;
	}
	
	private List<BlockShipmentBO> readRowIterator(Iterator<Row> rowIterator){
		
		List<BlockShipmentBO> shipmentList = new ArrayList<BlockShipmentBO>();
		
		boolean firstRowflag = true;
		
		while(rowIterator.hasNext()) {
			
			Row currentRow = rowIterator.next();
			
			if(firstRowflag == true) {
				
				firstRowflag = false;
				continue;
			}
			
			Iterator<Cell> cellIterator = currentRow.cellIterator();
			
			BlockShipmentBO bo = new BlockShipmentBO();
			boolean flag = false;
			
			while(cellIterator.hasNext()) {
				
				Cell cell = cellIterator.next();
				
				switch(cell.getCellType()) {
				
				  case Cell.CELL_TYPE_NUMERIC:
					  
					  break;
				  case Cell.CELL_TYPE_BLANK:
					  
					  break;
				  case Cell.CELL_TYPE_STRING:
					  
					  if(flag == false) {
						
						  // shipment number
						  bo.setShipmentNumber(cell.getStringCellValue());
						  flag = true;
					  }
					  
					  if(flag == true) {
						  
						  // reason to block
						  bo.setReasonToBlock(cell.getStringCellValue());
						  //flag = false;
					  }
					  
					  break;
				}
				
			} //end of inner loop
			
			shipmentList.add(bo);
			
		} //end of outer loop
		
		return shipmentList;
	}
}
