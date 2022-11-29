package com.my.multiweb;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.model.ProductVO;
import com.shop.service.ShopService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class ProductController {
	@Inject
	private ShopService shopService;
	
	//HIT, NEW, BEST 스펙 구분별로 상품 목록 가져오기
	@GetMapping("/prodPspec")
	public String productByPspec(Model m, @RequestParam(name="pspec", defaultValue="HIT") String pspec) {
		log.info("pspec=="+pspec);
		List<ProductVO> pList=shopService.selectByPspec(pspec);
		
		m.addAttribute("pList",pList);
		return "shop/mallHit";
	}
	
	// /prodDetail?pnum=1 ==> ProductController에서 맵핑하기
	// ShowService의 selectByPnum(pnum) 호출
	// 모델에 저장 "prod", ProductVO 저장
	// 뷰페이지는 show/prodDetail 반환. 
	// ShopServiceImpl 상품 번호로 정보 가져오기.
	@GetMapping("/prodDetail")
	public String productByPnum(Model m, @RequestParam(defaultValue="0") int pnum) {
		if(pnum==0) {
			return "redirect:index";
		}
		ProductVO vo=this.shopService.selectByPnum(pnum);
		
		m.addAttribute("prod", vo);
		return "shop/prodDetail";
	}
}
