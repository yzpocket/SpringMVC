package com.my.multiweb;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.model.CartVO;
import com.shop.service.ShopService;
import com.user.model.UserVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")
@Log4j
public class CartController {

	@Inject
	private ShopService shopService;
	
	
	//-----------SET 1 START------------------------------
	@PostMapping("/cartAdd")
	public String addCard(//session에 loginUser처리되서 HttpSession 추가됨.
						Model m, HttpSession session,
						@RequestParam(defaultValue="0")int pnum,
						@RequestParam(defaultValue="0")int oqty){
		log.info("pnum=="+pnum+ " , oqty=="+oqty);
		if(pnum==0 || oqty==0) {
			return "redirect:../index";
		}
		//session에 저장한 loginUser idx_fk얻어서 임의회원부분 변경됨.
		UserVO loginUser=(UserVO)session.getAttribute("loginUser");
		int idx_fk=loginUser.getIdx();
		
		CartVO cvo=new CartVO();
		cvo.setPnum_fk(pnum);
		cvo.setOqty(oqty);
		//회원번호는 세션에서 로그인한 사람의 정보를 꺼내서 CartVO객체에 setting 한다.
		//일단 임의로 회원 지정. status 0인 30
		//임의회원 변경부분 30->idx_fk
		cvo.setIdx_fk(idx_fk);
		
		//장바구니에 상품 추가
		int n=this.shopService.addCart(cvo);
		
		//장바구니 목록 가져오기[X 아래서 redirect로 돌려야함]
		//여기서 forward이동 하면 브라우저 새로고침 시 계속 상품이 추가되는 현상이 발생된다.
		//List<CartVO> cartArr=this.shopService.selectCartView(cvo.getIdx_fk());
		//m.addAttribute("cartArr",cartArr);
		////return "shop/cartList";
		
		//장바구니 금액이 증가하는 문제발생 => redirect로 이동해야 한다.
		
		
		return "redirect:cartList";
	}
	//장바구니 목록 가져오기[redirect 시킨 부분]
	@GetMapping("/cartList")
	public String cartList(Model m, HttpSession session) {//session에 loginUser처리되서 HttpSession 추가됨.
		//session에 저장한 loginUser idx_fk얻어서 임의회원부분 변경됨.
		UserVO loginUser=(UserVO)session.getAttribute("loginUser");
		int idx_fk=loginUser.getIdx();
		//임의회원 변경부분 30->idx_fk
		List<CartVO> cartArr=this.shopService.selectCartView(idx_fk);
		//특정회원(파라미터로 입력받은)장바구니 총액 가져오기
		CartVO cartVo=this.shopService.getCartTotal(idx_fk);
		m.addAttribute("cartArr",cartArr);
		m.addAttribute("cartTotal", cartVo);
		return "shop/cartList";
	}
	//-----------SET 1 END------------------------------
	
	//장바구니 항목 삭제
	@PostMapping("/cartDel")
	public String cartDelete(@RequestParam(defaultValue="0") int cartNum) {
		if(cartNum==0) {
			return "redirect:cartList";
		}
		int n=shopService.delCart(cartNum);
		return "redirect:cartList";
	}
	
	//장바구니 수량 수정
	@PostMapping("/cartEdit")
	public String cartEdit(@ModelAttribute("cvo") CartVO cvo) {
		log.info("cvo===="+cvo);
		
		shopService.editCart(cvo);
		
		return "redirect:cartList";
	}
}
