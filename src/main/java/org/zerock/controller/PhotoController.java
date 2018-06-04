package org.zerock.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.domain.PhotoVO;
import org.zerock.service.PhotoService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/photo/*")
@Log4j
public class PhotoController {


	@Autowired
	private PhotoService service;
	
	@GetMapping("/list")
	public void photoList(Model model,Criteria cri) {
		PageMaker pm = new PageMaker();
		cri.setPerPageNum(12);
		pm.setCri(cri);
		model.addAttribute("join", service.joinList(cri));
//		model.addAttribute("photoList", service.pageList(cri));
		model.addAttribute("pageMaker", pm);
		
	}
	
	@GetMapping("/write")
	public void photoWrite() {
		
	}
	
	@PostMapping("write")
	public String writePOST(PhotoVO vo, Model model)throws Exception {
		log.info("write come in.........");
		log.info(Arrays.toString(vo.getImages()));
		service.write(vo);
		return "redirect:/photo/list";
	}
}
