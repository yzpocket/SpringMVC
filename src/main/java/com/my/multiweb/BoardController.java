package com.my.multiweb;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.board.model.BoardVO;
import com.board.service.BoardService;
import com.common.CommonUtil;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	@Resource(name="boardServiceImpl")
	private BoardService boardService;
	
	@Inject
	private CommonUtil util;
	
	@GetMapping("/write")
	public String boardForm() {
		
		return "board/boardWrite";
	}
	
	@PostMapping("/write")
	public String boardInsert(Model m, HttpServletRequest req,
			@RequestParam("mfilename") MultipartFile mfilename, @ModelAttribute BoardVO board){
		log.info("board==="+board);
		//1. 파일 업로드 처리
		ServletContext app=req.getServletContext();
		String upDir=app.getRealPath("/resources/board_upload");
		File dir=new File(upDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		if(!mfilename.isEmpty()) {//첨부 파일이 있다면
			//1) 먼저 첨부파일명과 파일 크기를 알아내자. 
			String originFname=mfilename.getOriginalFilename(); //원본 파일명
			long fsize=mfilename.getSize(); //원본 파일의 크기
			log.info(originFname+"<<name||size>>"+fsize);
			
			//2) 동일한 파일 명이 서버에 있을 경우 덮어쓰기를 방지하기 위해
			//물리적인 파일명 "랜덤한문자열_원본파일명"으로 생성하도록 세팅한다.
			UUID uuid=UUID.randomUUID();//랜덤한 16진수 값을 만들어 내는 메서드.
			String filename=uuid.toString()+"_"+originFname; //<<실제 업로드되는 물리적 파일명 완성
			log.info("filename====>"+filename);
			
			//3) 업로드 처리
			try {
				mfilename.transferTo(new File(upDir, filename));
				log.info("upDir====>"+upDir);
			} catch (Exception e) {
				log.error("board write upload error: "+e);
			}
			//4) BoardVO객체에 filename, originFilename, filesize 셋팅
			board.setFilename(filename);
			board.setOriginFilename(originFname);
			board.setFilesize(fsize);
		}
		
		//2. 유효성 체크(subject,name,passwd)
		if(board.getName()==null || board.getSubject()==null || board.getPasswd()==null ||
				board.getName().trim().isEmpty()||board.getSubject().trim().isEmpty()||board.getPasswd().trim().isEmpty()) {
			return "redirect:write";
		}
		
		//3. boardService의 insertBoard()호출
		int n=0;
		String str="", loc="";
		
		if("write".equals(board.getMode())) {//글쓰기 모드라면
			n=this.boardService.insertBoard(board);
			str="글쓰기 ";
		}else if("rewrite".equals(board.getMode())) {//답변글쓰기 모드라면
			
		}else if("edit".equals(board.getMode())) {//글 수정 모드라면
			
		}
		str+=(n>0)?"성공":"실패";
		loc+=(n>0)?"list":"javascript:history.back()";
		//4. 결과 message, loc저장
//		m.addAttribute("message","test");
//		m.addAttribute("loc","list");
		
		return util.addMsgLoc(m, str, loc);//msg를 반환
	}
}
