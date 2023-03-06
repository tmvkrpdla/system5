package system.co.kr.company.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import system.co.kr.util.ManagerApi;
import system.co.kr.util.PageUtil;
import system.co.kr.util.SessionUtil;

@Controller
@RequestMapping("/company")
public class companyController {

	private final Logger logger = LoggerFactory.getLogger(companyController.class);

	@RequestMapping("/companyList")
	public String companyList(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "company") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "companyList") String subMenu,
			@RequestParam(value="nowPage", defaultValue="1") int nowPage) throws Exception {

		String moveUrl = "redirect:../";

		if (!SessionUtil.isNull(session, "ADMIN")) {

			HashMap sessionMap = SessionUtil.getSessionMap(session, "ADMIN");
			
			HashMap companyCountMap =  ManagerApi.getCompanyCount();
			int count_company = (Integer) companyCountMap.get("count_company");
			
			//������ ��ƿ
			PageUtil pageUtil = new PageUtil(nowPage, count_company, 20);
			
			int IndexFrom = 1;
			int IndexTo = 1000;
			
			HashMap compamyMap = ManagerApi.getCompanyListForPaging(pageUtil.getStartNum(), pageUtil.getEndNum());
			List list_company = (List) compamyMap.get("list_company");

			moveUrl = "company/companyList";
			
			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			model.addAttribute("LIST_COMPANY", list_company);
			
			//������ ��ƿ
			model.addAttribute("PAGEUTIL", pageUtil);
			model.addAttribute("nowPage", nowPage);

		}

		return moveUrl;

	}
	
	@RequestMapping("/worker")
	public String companyWorker(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "company") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "worker") String subMenu,
			@RequestParam(value= "SeqCompany", defaultValue = "") String SeqCompany,
			@RequestParam(value="nowPage", defaultValue="1") int nowPage) throws Exception {

		String moveUrl = "redirect:../";

		if (!SessionUtil.isNull(session, "ADMIN")) {

			HashMap sessionMap = SessionUtil.getSessionMap(session, "ADMIN");
			
			//��ü
			HashMap companyCountMap =  ManagerApi.getCompanyCount();
			int count_company = (Integer) companyCountMap.get("count_company");
			
			int IndexFrom = 1;
			int IndexTo = 1000;
			
			HashMap compamyMap = ManagerApi.getCompanyListForPaging(IndexFrom, IndexTo);
			List list_company = (List) compamyMap.get("list_company");
			
			// �۾���
			if ("".equals(SeqCompany)) {
				SeqCompany = "0";
			}
			
			HashMap workerCountMap =  ManagerApi.getWorkerCount(SeqCompany);
			int count_worker = (Integer) workerCountMap.get("count_worker");
			
			PageUtil pageUtil = new PageUtil(nowPage, count_worker, 10);
			
			HashMap workerMap = ManagerApi.getWorkerListForPaging(SeqCompany, IndexFrom, IndexTo);
			List list_worker = (List) workerMap.get("list_worker");

			moveUrl = "company/companyWorker";
			
			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			
			model.addAttribute("LIST_COMPANY", list_company);
			model.addAttribute("LIST_WORKER", list_worker);
			model.addAttribute("SEQCOMPANY", SeqCompany);
			
			model.addAttribute("PAGEUTIL", pageUtil);
			model.addAttribute("nowPage", nowPage);
			

		}

		return moveUrl;

	}
	
	// �˸� ���� ��� ���� �˾�
	@RequestMapping("/addWorker")
	public String addWorker(HttpSession session, Model model) throws Exception {

		String moveUrl = "redirect:../";

		if (!SessionUtil.isNull(session, "ADMIN")) {
			
			//��ü
			HashMap companyCountMap =  ManagerApi.getCompanyCount();
			int count_company = (Integer) companyCountMap.get("count_company");
			System.out.println("count_company = " + count_company);
			
			int IndexFrom = 1;
			int IndexTo = 1000;
			
			HashMap compamyMap = ManagerApi.getCompanyListForPaging(IndexFrom, IndexTo);
			System.out.println("compamyMap = " + compamyMap);
			
			List list_company = (List) compamyMap.get("list_company");
			System.out.println("list_company = " + list_company);
			

			moveUrl = "company/addWorker";
			
			model.addAttribute("LIST_COMPANY", list_company);

		}

		return moveUrl;

	}

}
