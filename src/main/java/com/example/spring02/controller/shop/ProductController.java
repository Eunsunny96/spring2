package com.example.spring02.controller.shop;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.ProductDTO;
import com.example.spring02.service.shop.ProductService;

@Controller
@RequestMapping("/shop/product/*")
public class ProductController {

	@Inject
	ProductService productService;
	
	@RequestMapping("delete.do")
	public String delete(int product_id, HttpServletRequest request) {
		//첨부파일 이름
		String filename=productService.fileInfo(product_id);
		//첨부파일이 있으면
		if(filename!=null && !filename.equals("-")) {
			ServletContext application=
					request.getSession().getServletContext();
			//배포 디렉토리의 이미지 파일 경로
			String path=application.getRealPath("/WEB-INF/views/images/");
			File f=new File(path+filename);
			//파일이 존재하면 삭제
			if(f.exists()) {
				f.delete();
			}
		}
		productService.deleteProduct(product_id);
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("write.do")
	public String write() {
		return "shop/product_write";
	}
	
	@RequestMapping("edit/{product_id}")
	public ModelAndView edit(@PathVariable("product_id") int product_id, 
			ModelAndView mav) {
		mav.setViewName("/shop/product_edit");
		mav.addObject("dto", productService.detailProduct(product_id));
		return mav;
	}
	
	@RequestMapping("insert.do")
	public String insert(ProductDTO dto, HttpServletRequest request) {
		String filename="-";
		//첨부파일이 있으면
		if(!dto.getFile1().isEmpty()) {
			filename=dto.getFile1().getOriginalFilename();
			try {
				ServletContext application=
						request.getSession().getServletContext();
				//배포 디렉토리
				String path=application.getRealPath(
						"/WEB-INF/views/images/");
				new File(path).mkdir(); //디렉토리 생성
				//임시디렉토리에 저장된 파일을 이동
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setPicture_url(filename);
		productService.insertProduct(dto);
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("update.do")
	public String update(ProductDTO dto, HttpServletRequest request) {
		String filename="-";
		if(!dto.getFile1().isEmpty()) { //첨부파일이 있을 때
			filename=dto.getFile1().getOriginalFilename();
			try {
				ServletContext application=
						request.getSession().getServletContext();
				String path=application.getRealPath(
						"/WEB-INF/views/images/");
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);
		}else { //첨부파일이 없을 때
			ProductDTO dto2=productService.detailProduct(
					dto.getProduct_id());
			dto.setPicture_url(dto2.getPicture_url());
		}
		productService.updateProduct(dto);
		return "redirect:/shop/product/list.do";
	}	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("/shop/product_list"); // 출력 페이지 지정
		//페이지에 전달할 데이터
		mav.addObject("list", productService.listProduct()); 
		return mav;
	}
	
	@RequestMapping("detail/{product_id}")
	public ModelAndView detail(@PathVariable int product_id, 
			ModelAndView mav) {
		mav.setViewName("/shop/product_detail");
		mav.addObject("dto", productService.detailProduct(product_id));
		return mav;
	}
	
}








