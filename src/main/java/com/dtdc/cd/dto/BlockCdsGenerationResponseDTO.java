package com.dtdc.cd.dto;

import java.util.List;

import com.dtdc.cd.bo.BlockShipmentResultBO;

public class BlockCdsGenerationResponseDTO {

	private String message;
	private boolean flag;
	private List<BlockShipmentResultBO> data;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public List<BlockShipmentResultBO> getData() {
		return data;
	}
	public void setData(List<BlockShipmentResultBO> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "BlockCdsGenerationResponseDTO [message=" + message + ", flag=" + flag + ", data=" + data + "]";
	}
	
}
