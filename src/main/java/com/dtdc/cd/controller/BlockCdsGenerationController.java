package com.dtdc.cd.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dtdc.cd.bo.BlockShipmentResultBO;
import com.dtdc.cd.deligate.BlockCdsGenerationDelegate;
import com.dtdc.cd.dto.BlockCdsGenerationResponseDTO;
import com.dtdc.cd.view.ShipmentExcelView;

@Controller
public class BlockCdsGenerationController {

	@Autowired
	private BlockCdsGenerationDelegate delegate;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody BlockCdsGenerationResponseDTO uploadFile(@RequestParam("file") MultipartFile multipartFile,HttpSession session,HttpServletRequest request) throws ParseException {
	
		//ResponseEntity<BlockCdsGenerationDTO> 
		
		List<BlockShipmentResultBO> shipmentList = delegate.processFile(multipartFile,session,request);
		
		
		
		BlockCdsGenerationResponseDTO responseDTO = new BlockCdsGenerationResponseDTO();
		
		//shipmentList = delegate.getCpDebitResults(dateTime);
		
		responseDTO.setFlag(true);
		responseDTO.setMessage("SUCCESS");
		responseDTO.setData(shipmentList);
		
		return responseDTO;
		
		//return ResponseEntity.ok(responseDTO);
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("consgList") String jsonArrayData) throws IOException, ParseException{
		
		JSONArray jsonArray = new JSONArray(jsonArrayData);
		
		List<BlockShipmentResultBO> shipList = new ArrayList<BlockShipmentResultBO>();
		
		for(int i=0;i<jsonArray.length();i++) {
			
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			BlockShipmentResultBO bo = new BlockShipmentResultBO();
			bo.setShipmentNumber(jsonObject.getString("shipmentNumber"));
			bo.setExcelFileRemarks(jsonObject.getString("excelFileRemarks"));
			bo.setStatus(jsonObject.getString("status"));
			bo.setStatusRemarks(jsonObject.getString("statusRemarks"));
			bo.setUploadFileName(jsonObject.getString("uploadFileName"));
			bo.setDownldFileName(jsonObject.getString("downldFileName"));
			bo.setProcessType(jsonObject.getString("processType"));
			//bo.setProcessDateTime(jsonObject.get("processDateTime"));
			//bo.setUserID(jsonObject.getInt("userID"));
			bo.setNodeID(jsonObject.getString("nodeID"));
			
			shipList.add(bo);
		}
		
		//shipList = delegate.getCpDebitResults(dateTime);
		
		ByteArrayInputStream in = ShipmentExcelView.shipmentToExcel(shipList);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=result.xlsx");
		
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
}
