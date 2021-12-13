package com.sbs.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String title;
	private String body;
	private String hitCount;
	
	private String extra__writer;
	private boolean extra__actorCanDelete;
	private boolean extra__actorCanModify;
	
	public String getForPrintType1RegDate() {
		return regDate.substring(2,16).replace(" ","<br>");
	}
	public String getForPrintType1UpdateDate() {
		return updateDate.substring(2,16).replace(" ","<br>");
	}
	
	public String getForPrintType2RegDate() {
		return regDate.substring(2, 16);
	}

	public String getForPrintType2UpdateDate() {
		return updateDate.substring(2, 16);
	}
}
