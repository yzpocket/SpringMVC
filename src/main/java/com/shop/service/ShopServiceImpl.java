package com.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.ProductMapper;
import com.shop.model.CartVO;
import com.shop.model.ProductVO;
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired //@Inject와 동일함.
	private ProductMapper productMapper;
	
	@Override
	public List<ProductVO> selectByPspec(String pspec) {
		return this.productMapper.selectByPspec(pspec);
	}

	@Override
	public List<ProductVO> selectByCategory(int cg_num) {
		return null;
	}

	@Override
	public ProductVO selectByPnum(int pnum) {
		return this.productMapper.selectByPnum(pnum);
	}

	@Override
	public int addCart(CartVO cartVo) {
		return 0;
	}

	@Override
	public int updateCartQty(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editCart(CartVO cartVo) {
		return 0;
	}

	@Override
	public List<CartVO> selectCartView(int midx) {
		return null;
	}

	@Override
	public int delCart(int cartNum) {
		return 0;
	}

	@Override
	public int delCartAll(CartVO cartVo) {
		return 0;
	}

	@Override
	public int delCartOrder(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCartCountByIdx(CartVO cartVo) {
		return 0;
	}

	@Override
	public CartVO getCartTotal(int midx_fk) {
		return null;
	}

	@Override
	public void delCartByOrder(int midx_fk, int pnum) {

	}

}
