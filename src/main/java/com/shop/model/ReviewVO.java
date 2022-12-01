package com.shop.model;

import lombok.Data;

@Data
public class ReviewVO {
	//DB 테이블 컬럼들 그대로 선언
	private int num;
	private String userid;
	private String content;
	private int score;
	private String filename;
	private java.sql.Date wdate;
	private int pnum_fk;
}
