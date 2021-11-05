package com.sbs.exam.demo.vo;

import lombok.Getter;

public class ResultData<DT> {
	@Getter
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private String data1Name;
	@Getter
	private DT data1;

	ResultData() {

	}

	public static ResultData from(String resultCode, String msg) {
		return ResultData.from(resultCode, msg, null, null);
	}

	public static <DT> ResultData from(String resultCode, String msg, String data1Name, DT data1) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		return rd;

	}

	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}

	public boolean isFail() {
		return isSuccess() == false;
	}

	public static <DT> ResultData<DT> newData(ResultData joinRd, String dataName1, DT Data1) {
		return from(joinRd.getResultCode(), joinRd.getMsg(), dataName1, Data1);

	}

}
