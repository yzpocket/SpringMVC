package com.my.multiweb;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shop.model.CategoryVO;
import com.shop.model.ProductVO;
import com.shop.service.AdminService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin")
public class AdminController {
	@Inject
	private AdminService adminService;

	@GetMapping("/prodForm")
	public String productForm(Model m) {
		List<CategoryVO> upCgList = adminService.getUpcategory();
		log.info("upCgList======" + upCgList);
		m.addAttribute("upCgList", upCgList);
		return "/admin/prodForm";
	}
	
	//ajax요청에 대해 json응답 데이터를 보낸다.
	@GetMapping(value="/getDownCategory", produces="application/json")
	@ResponseBody
	public List<CategoryVO> getDownCategory(@RequestParam("upCg_code") String upCg_code){
		log.info("upCg_code"+upCg_code);
		List<CategoryVO> downCgList=adminService.getDownCategory(upCg_code);
		return downCgList;
	}
	
	@PostMapping("/prodInsert")
	public String productRegister(
			Model m,
			@RequestParam("pimage") List<MultipartFile> pimage, //여러개라 리스트로 받아야함
			@ModelAttribute("product") ProductVO product,
			HttpServletRequest req) { //경로얻기 위해서. 
		log.info("product===="+product);
		
		//1. 업로드 디렉토리 절대 경로 얻기
		ServletContext app=req.getServletContext();
		String upDir=app.getRealPath("/resources/product_images");
		log.info("upDir=="+upDir);
		
		File dir=new File(upDir); //클린하면 없어지기도해서 생성 시키려고함.
		if(!dir.exists()) {
			dir.mkdirs(); //업로드할 디렉토리 생성
		}
		//2. 업로드 처리
		if(pimage!=null) {
			for(int i=0;i<pimage.size();i++) { //pimage는 리스트라 사이즈로 반복문
				MultipartFile mfile=pimage.get(i);
				if(!mfile.isEmpty()) { //첨부파일이 있다
					try {
						//업로드 처리
						mfile.transferTo(new File(upDir,mfile.getOriginalFilename()));
						if(i==0) {
							product.setPimage1(mfile.getOriginalFilename()); //첫번째 파일 pimage1에 파일명넣어주
						}else if(i==1) {
							product.setPimage2(mfile.getOriginalFilename()); //두번째 파일 pimage2에 파일명넣어주
						}else if(i==2) {
							product.setPimage3(mfile.getOriginalFilename()); //세번째 파일 pimage3에 파일명넣어주
						}
					} catch (IOException e) {
						log.error("파일 업로드 실패"+e);
					}
				}
			}
			log.info("업로드 이후 product==="+product);
		}
		int n=adminService.productInsert(product);
		String str=(n>0)?"상품 등록 성공":"등록 실패";
		String loc=(n>0)?"prodList":"javascript:history.back()";
		
		m.addAttribute("message", str);
		m.addAttribute("loc", loc);
		return "msg";	
	}
	
	
	@GetMapping("/prodList")
	public String productList(Model m) {
		
		List<ProductVO> prodArr=adminService.productList();
		m.addAttribute("prodArr",prodArr);
		
		return "/admin/prodList";
	}
	
}
