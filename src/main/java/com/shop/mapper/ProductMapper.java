package com.shop.mapper;

import java.util.List;

import com.shop.model.CategoryVO;
import com.shop.model.ProductVO;

//datasource-context.xml에 myatis-spring:scan을 설정하면 XXXMapper인터페이스를 구현한 객체
//(Proxy)객체를 스프링이 대신 만들어 준다.
public interface ProductMapper {
	int productInsert(ProductVO vo);
	
	List<ProductVO> getProducts();
	List<ProductVO> selectByPspec(String pspec);
	List<ProductVO> selectByCategory(CategoryVO cvo);
	ProductVO selectByPnum(int pnum);
	
}
