package com.chinaums.commons;


/**
 * 响应ids
 *
 * @author cly
 */
public class ResIds extends Ids {

	public ResIds() {
	}

	public ResIds(String respCode, String respMsg) {
		this.setHead(CommonFields.RESP_CODE, respCode);
		this.setHead(CommonFields.RESP_MSG, respMsg);
	}
}
