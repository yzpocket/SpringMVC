package com.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.memo.model.MemoDAO;
import com.memo.model.MemoVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemoController {

	// by type으로 주입한다. MemoDAO타입의 객체가 있으면 주입한다.
	@Autowired
	private MemoDAO memoDao;

	@RequestMapping(value = "/memo", method = RequestMethod.GET)
	public String memoForm() {
		return "memo/input";
		// /WEB-INF/views/memo/input.jsp 를 찾아가게 된다.

	}

	// html input name과 VO객체의 property명이 같고
	// @ModelAttribute 어노테이션을 붙여주면 파라미터값들을 자동으로 VO객체에 넣어준다. (setter를호출해서넣어줌)
	@RequestMapping(value = "/memo", method = RequestMethod.POST)
	public String memoInsert(Model model, @ModelAttribute("memo") MemoVO memo) {
		// System.out.println("memo===="+memo);
		// log level = debug<info<warning<error 순서
		log.info("memo>>>" + memo);

		int n = memoDao.insertMemo(memo);
		String str = (n > 0) ? "등록 성공" : "등록 실패";
		String loc = (n > 0) ? "memoList" : "javascript:history.back()";

		model.addAttribute("message", str);
		model.addAttribute("loc", loc);
		return "msg";
		// /WEB-INF/views/msg.jsp 를 찾아가게 된다.

	}

	@RequestMapping(value = "/memoList", method = RequestMethod.GET)
	public String memoList(Model model) {
		int totalCount = memoDao.getTotalCount();
		List<MemoVO> arr = memoDao.listMemo();

		model.addAttribute("memoArr", arr);
		model.addAttribute("totalCount", totalCount);
		return "memo/list";
		// /WEB-INF/views/memo/list.jsp 를 찾아가게 된다.
	}
	
	
	@RequestMapping(value ="/memoDel")
	public String memoDelete(Model model, @RequestParam(defaultValue = "0") int idx) {
		log.info("idx====:"+idx); //idx파라미터 값이 넘어오지 않으면 디폴트값을 0으로 준다
		if(idx==0) {
			return "redirect:memoList"; //redirect 방식으로 이동 (브라우저에 /memoDel을 직접입력해보면 확인 가능)
		}
		int n=memoDao.deleteMemo(idx);
		String str=(n>0)?"삭제 성공":"삭제 실패";
		
		model.addAttribute("message", str);
		model.addAttribute("loc", "memoList");
		return "msg";
	}
	
	@RequestMapping(value="/memoEdit", method=RequestMethod.GET)
	public String memoEditForm(Model model, @RequestParam(defaultValue="0") int idx) {
		if(idx==0) {
			return "redirect:memoList"; //redirect 시키는
		}
		MemoVO memo=memoDao.getMemo(idx);
		
		model.addAttribute("memo", memo);
		return "memo/edit";
	}
	
	@RequestMapping(value="/memoEdit", method=RequestMethod.POST)
	public String memoEditEnd(Model model, @ModelAttribute("memo") MemoVO memo) {
		log.info("memo edit===="+memo);
		int n = memoDao.updateMemo(memo);
		String str = (n > 0) ? "수정 성공" : "수정 실패";
		String loc = (n > 0) ? "memoList" : "javascript:history.back()";

		model.addAttribute("message", str);
		model.addAttribute("loc", loc);
		return "msg";
		// /WEB-INF/views/msg.jsp 를 찾아가게 된다.
	}
}
