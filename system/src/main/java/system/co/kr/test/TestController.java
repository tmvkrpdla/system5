package system.co.kr.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import system.co.kr.apt.aptController;
import system.co.kr.dto.TestVO;
import system.co.kr.dto.siteListVO;
import system.co.kr.service.TestService;

@Controller
public class TestController {

	private final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Inject
	private TestService service;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) throws Exception {

		String moveUrl = "redirect:../";

		logger.info("home");

		List<TestVO> testList = service.selectDcu();
		logger.info("testList", testList);
		System.out.println("testList = " + testList);
		
		List<TestVO> siteList = service.siteList();
		//System.out.println("siteList = " + siteList);
		
		model.addAttribute("testList", testList);
		model.addAttribute("LIST_SITE", siteList);

		moveUrl = "current/dcuNetwork";

		return moveUrl;

	}

}
