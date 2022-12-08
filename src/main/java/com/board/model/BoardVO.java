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
	
	private String old_filename;//예전에 첨부했던 파일(물리적파일명)

}
