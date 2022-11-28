package com.shop.mapper;

import java.util.List;

import com.shop.model.CategoryVO;
//datasource-context.xml에 myatis-spring:scan을 설정하면 XXXMapper인터페이스를 구현한 객체
//(Proxy)객체를 스프링이 대신 만들어 준다.
public interface CategoryMapper {
	public List<CategoryVO> getUpcategory();
	public List<CategoryVO> getDownCategory(String upCg_code);
	public int categoryAdd(CategoryVO cvo);
	public int categoryDelete(int cg_code);
}
