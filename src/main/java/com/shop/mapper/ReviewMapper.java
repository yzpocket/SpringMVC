package com.shop.mapper;

import java.util.List;

import com.shop.model.ReviewVO;

public interface ReviewMapper {
	//ReviewService 선언된것들 그대로 복사 
	public int addReview(ReviewVO rvo);
	
	public List<ReviewVO> listReview(int pnum_fk);
	
	public int getReviewCount(int pnum_fk);
	
	public ReviewVO getReview(int num);
	
	public int updateReview(ReviewVO rvo);
	
	public int deleteReview(int num);
}
