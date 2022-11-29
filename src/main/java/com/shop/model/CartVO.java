package com.shop.model;

import lombok.Data;

@Data
public class CartVO {
	private int cartNum; //장바구니번호
	private int idx_fk; //회원번호
	private int pnum_fk; //상품번호
	private int oqty; //수량
	private java.sql.Date indate; //
	
	//장바구니 상품정보와 관련된 프로퍼티 선언
	private String pname;
	private String pimage1;
	private int price;
	private int saleprice;
	private int point;
	
	private int totalPrice; //saleprice*oqty => 개별 상품의 총합계금액 -db에서 계산할것임. 
	private int totalPoint; //point*oqty => 개별상품의 총 적립 포인트
	
	private int cartTotalPrice; //장바구니에 담은 모든 상품의 총액
	private int cartTotalPoint; //장바구니에 담은 모든 상품의 포인트
}
