package com.board.model;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	private String mode;
	//글쓰기: write, 답변글쓰기: rewrite, 글수정: edit
	
	private int num;
	private String name;
	private String passwd;
	private String subject;
	private String content;
	
	private Date wdate;
	private int readnum;
	private String filename; //물리적파일명
	private String originFilename; //원본(논리적)파일명
	private long filesize;
	
	private int refer;
	private int lev;
	private int sunbun;
}
