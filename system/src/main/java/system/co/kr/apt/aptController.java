package system.co.kr.apt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import system.co.kr.apt.service.Apt;
import system.co.kr.beans.networkVO;
import system.co.kr.dto.ManagerVO;
import system.co.kr.dto.TestVO;
import system.co.kr.util.Api;
import system.co.kr.util.JSonApi;
import system.co.kr.util.ManagerApi;
import system.co.kr.util.PageUtil;
import system.co.kr.util.SessionUtil;
import system.co.kr.util.StringUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/apt")
public class aptController {

	private final Logger logger = LoggerFactory.getLogger(aptController.class);
	
	
	@Inject
	private AptService aptService;
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	@Autowired
	private Apt apt;
	

	@RequestMapping("/test")
	public String test(HttpSession session, Model model) throws Exception {

		String moveUrl = "redirect:../";
		
		System.out.println("하하하 api 다뺏다 ㅋ");

		moveUrl = "apt/jusoTest";

		return moveUrl;

	}

	@RequestMapping("/estate")
	public String aptEstate(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "manager") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "estate") String subMenu,
			@RequestParam(value = "index", defaultValue = "1") String index,
			@RequestParam(value="nowPage", defaultValue="1")int nowPage) throws Exception {
			//잘가라
		String moveUrl = "redirect:../";

		if (!SessionUtil.isNull(session, "ADMIN")) {

			HashMap sessionMap = SessionUtil.getSessionMap(session, "ADMIN");
			//System.out.println("sessionMap = " + sessionMap);
			
			HashMap resultSiteCountMap =  ManagerApi.GetSiteCount();
			//System.out.println("resultProjectCountMap = " + resultProjectCountMap);
			int count_site = (Integer) resultSiteCountMap.get("count_site");
			
			PageUtil pageUtil = new PageUtil(nowPage, count_site, 30);
			
			int IndexFrom = 1;
			int IndexTo = 1000;
			
			HashMap resultSiteMap = ManagerApi.GetSiteListForPaging(pageUtil.getStartNum(), pageUtil.getEndNum());
			List list_site = (List) resultSiteMap.get("list_site");
			//System.out.println("list_site = " + list_site);
			
			HashMap resultSiteMapForIndex = ManagerApi.GetSiteListForPaging(IndexFrom, IndexTo);
			List list_site_forindex = (List) resultSiteMapForIndex.get("list_site");
			
			
			//HashMap indexMap = resultSiteMap.get("index");
			

			moveUrl = "apt/aptEstate";
			
			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			model.addAttribute("LIST_SITE", list_site);
			model.addAttribute("INDEX", index);
			model.addAttribute("list_site_forindex", list_site_forindex);
			
			//?????????
			model.addAttribute("PAGEUTIL", pageUtil);
			model.addAttribute("nowPage", nowPage);

		}

		return moveUrl;

	}
	
	@RequestMapping("/aptment")
	public String aptment(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "manager") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "aptment") String subMenu,
			@RequestParam(value= "SeqSite", defaultValue = "") String SeqSite,
			@RequestParam(value= "SeqDong", defaultValue = "") String SeqDong,
			@RequestParam(value="nowPage", defaultValue="1") int nowPage) throws Exception {

		String moveUrl = "redirect:../";

		//if (!SessionUtil.isNull(session, "ADMIN")) {
		
			HashMap resultSiteCountMap =  ManagerApi.GetSiteCount();
			int count_site = (Integer) resultSiteCountMap.get("count_site");
			
			int IndexFrom = 1;
			
			HashMap siteMap = ManagerApi.GetSiteListForPaging(IndexFrom, count_site);
			List list_site = (List) siteMap.get("list_site");
			
			if ("".equals(SeqSite)) {
				
				moveUrl = "apt/aptment";

				model.addAttribute("mainMenu", mainMenu);
				model.addAttribute("subMenu", subMenu);
				model.addAttribute("LIST_SITE", list_site);

				return moveUrl;

			} else {
				
				HashMap apt_dong_list = ManagerApi.getDongListBySite(SeqSite);
				List list_dong = (List) apt_dong_list.get("list_dong");
				
				
				if ("".equals(SeqDong)) {
					HashMap seqDongMap = (HashMap) list_dong.get(0);
					SeqDong = String.valueOf(seqDongMap.get("seq_dong"));
				}
				
				// 2023 01 10 paging api�� ����
				HashMap CountMap =  ManagerApi.getHoCountByDong(SeqDong);
				int count_ho = (Integer) CountMap.get("count_ho");
				
				PageUtil pageUtil = new PageUtil(nowPage, count_ho, 30);
				
				HashMap dongHoMap = ManagerApi.getHoListByDongForPaging(SeqDong, pageUtil.getStartNum(), pageUtil.getEndNum());
				//HashMap site_info = (HashMap) dongHoMap.get("site_info");
				List list_dong_ho = (List) dongHoMap.get("list_ho");
				//System.out.println("list_dong_ho = " + list_dong_ho);
				
				HashMap dongHoMapForExcel = ManagerApi.getHoListByDongForPaging(SeqDong, 1, 1000);
				
				//20230109 �����ٿ� TEST 
				Gson gson = new Gson();
	
				JsonObject json = gson.toJsonTree(dongHoMapForExcel).getAsJsonObject();
				
				
				moveUrl = "apt/aptment";
				
				model.addAttribute("mainMenu", mainMenu);
				model.addAttribute("subMenu", subMenu);
				model.addAttribute("DONGHOMAP", dongHoMap);
				//model.addAttribute("SITE_INFO", site_info);
				model.addAttribute("LIST_SITE", list_site);
				model.addAttribute("LIST_DONG", list_dong);
				model.addAttribute("LIST_DONG_HO", list_dong_ho);
				model.addAttribute("SEQSITE", SeqSite);
				model.addAttribute("SEQDONG", SeqDong);
				model.addAttribute("PAGEUTIL", pageUtil);
				model.addAttribute("nowPage", nowPage);
				
				model.addAttribute("json", json);
				
				return moveUrl;
			}

	}
	
	@RequestMapping("/valueOld")
	public String valueOld(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "apt") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "valueOld") String subMenu,
			@RequestParam(value = "SeqSite", defaultValue = "") String SeqSite,
			@RequestParam(value = "SiteName", defaultValue = "") String SiteName,
			@RequestParam(value = "nowPage", defaultValue = "1") int nowPage) throws Exception {

		String moveUrl = "redirect:../";

		// if (!SessionUtil.isNull(session, "ADMIN")) {

		HashMap resultSiteCountMap = ManagerApi.GetSiteCount();
		int count_site = (Integer) resultSiteCountMap.get("count_site");

		int IndexFrom = 1;
		int IndexTo = 1000;

		HashMap siteMap = ManagerApi.GetSiteListForPaging(IndexFrom, count_site);

		List list_site = (List) siteMap.get("list_site");

//			if ("".equals(SeqSite)) {
//				HashMap seqSiteMap = (HashMap) list_site.get(0);
//				SeqSite = String.valueOf(seqSiteMap.get("seq_site"));
//			}
		System.out.println("SeqSite = " + SeqSite);

		if ("".equals(SeqSite)) {

			moveUrl = "apt/valueOld";

			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			model.addAttribute("LIST_SITE", list_site);

			return moveUrl;

		} else {

			HashMap CountMap = ManagerApi.getHoCountBySite(SeqSite);
			int count_ho = (Integer) CountMap.get("count_ho");

			PageUtil pageUtil = new PageUtil(nowPage, count_ho, 30);

			HashMap hoMap = ManagerApi.getHoListBySiteForPaging(SeqSite, pageUtil.getStartNum(), pageUtil.getEndNum());
			List list_ho = (List) hoMap.get("list_ho");
			System.out.println("list_ho = " + list_ho);

			HashMap dongHoMapForExcel = ManagerApi.getHoListBySiteForPaging(SeqSite, 1, 10000);

			// 2023 01 10 �����ٿ� �׽�Ʈ
			Gson gson = new Gson();

			JsonObject json = gson.toJsonTree(dongHoMapForExcel).getAsJsonObject();

			moveUrl = "apt/valueOld";

			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			// model.addAttribute("SITE_INFO", site_info);
			model.addAttribute("LIST_SITE", list_site);
			model.addAttribute("LIST_HO", list_ho);
			model.addAttribute("SEQSITE", SeqSite);
			model.addAttribute("SITENAME", SiteName);
			model.addAttribute("PAGEUTIL", pageUtil);
			model.addAttribute("nowPage", nowPage);

			model.addAttribute("json", json);

			// }

			return moveUrl;
		}
	}
	
	@RequestMapping(value = "/oldmeterExcelView", method = { RequestMethod.POST })
	public String oldmeterExcelView(@RequestParam HashMap paramMap, @RequestParam(value = "json2") String json2,
			@RequestParam(value = "SiteName") String siteName,
			HttpSession session, Model model, HttpServletResponse response) throws Exception {

		// JSON String to Map jacksonLibrary
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> data = mapper.readValue(json2, new TypeReference<Map<String, Object>>() {
		});

		// System.out.println("json2 = " + json2);

		// .0 ????? -> gson???? ??????��???? ????
		List list_ho = (List) data.get("list_ho");
		System.out.println("list_ho = " + list_ho);

		//String siteName = SessionUtil.getString(session, "ADMIN", "site_name");
		String fileName = String.valueOf(siteName) + "_지침값";
		fileName = fileName.replaceAll("," , "_");
		System.out.println("fileName before = " + fileName);
		fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		System.out.println("fileName after = " + fileName);

		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Transfer-Encoding", "binary");

//		model.addAttribute("PARAMMAP", paramMap);
		model.addAttribute("METERINGMAP", data);
		model.addAttribute("list_ho", list_ho);
		model.addAttribute("fileName", fileName);

		return "oldmeterExcelView";

	}
	
	
	@RequestMapping(value = "/oldmeterExcelViewA", method = { RequestMethod.POST })
    public String oldmeterExcelView2(@RequestParam HashMap paramMap, @RequestParam(value = "json2") String json2,
          @RequestParam(value = "SiteName") String siteName,
          HttpSession session, Model model, HttpServletResponse response) throws Exception {
       System.out.println("������ ��Ʈ�Ѵ�");
       // JSON String to Map jacksonLibrary
       ObjectMapper mapper = new ObjectMapper();
       
       Map<String, Object> data = mapper.readValue(json2, new TypeReference<Map<String, Object>>() {
       });

       // System.out.println("json2 = " + json2);

       // .0       -> gson      ?   ?��         
       List list_ho = (List) data.get("list_ho");
       System.out.println("list_ho = " + list_ho);

       //String siteName = SessionUtil.getString(session, "ADMIN", "site_name");
       String fileName = String.valueOf(siteName) + "_지침값_관리자";
       fileName = fileName.replaceAll("," , "_");
       System.out.println("fileName before = " + fileName);
       fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
       System.out.println("fileName after = " + fileName);

       response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
       response.setContentType("Application/Msexcel");
       response.setHeader("Content-Transfer-Encoding", "binary");

//       model.addAttribute("PARAMMAP", paramMap);
       model.addAttribute("METERINGMAP", data);
       model.addAttribute("list_ho", list_ho);
       model.addAttribute("fileName", fileName);

       return "oldmeterExcelView2";

    }
	
	@RequestMapping("/aptDetailInfo")
	public String aptInfo(HttpSession session, Model model,
			@RequestParam(value = "seq_ho", defaultValue = "") String seq_ho) throws Exception {

		String moveUrl = "redirect:../";

		HashMap detailInfoMap = ManagerApi.getHoInfo(seq_ho);
		HashMap ho_info = (HashMap) detailInfoMap.get("ho_info");
		List list_image = (List) detailInfoMap.get("list_image_meter");
		//System.out.println("list_image = " + list_image);
		
		String seq_admin = SessionUtil.getString(session, "ADMIN", "seq_admin");

		moveUrl = "/apt/aptDetailInfo";
		
		model.addAttribute("HO_INFO", ho_info);
		model.addAttribute("LIST_IMAGE", list_image);
		model.addAttribute("SEQ_HO", seq_ho);
		model.addAttribute("SEQ_ADMIN", seq_admin);

		return moveUrl;
	}
	
	@RequestMapping("/addApt")
	public String addApt(HttpSession session, Model model,
			@RequestParam(value = "siteName", defaultValue = "") String siteName,
			@RequestParam(value= "nowPage", defaultValue = "1") int nowPage) throws Exception {
		
		String moveUrl = "redirect:../";
		//System.out.println("nowPage = " + nowPage);
		//System.out.println("siteName = " + siteName);

		if (!SessionUtil.isNull(session, "ADMIN")) {
			moveUrl = "apt/addApt";
			
			model.addAttribute("SITENAME", siteName);
			
			
		}
		return moveUrl;
	}
	
	// kakao 테스트
	@RequestMapping("/addApt2")
	public String addApt2(HttpSession session, Model model,
			@RequestParam(value = "siteName", defaultValue = "") String siteName,
			@RequestParam(value= "nowPage", defaultValue = "1") int nowPage) throws Exception {
		
		String moveUrl = "redirect:../";
		//System.out.println("nowPage = " + nowPage);
		//System.out.println("siteName = " + siteName);
		System.out.println("경준씨 받아주세요 (4:26)");
		System.out.println("경준씨 이것도 받아주세요 (5:01)");

		if (!SessionUtil.isNull(session, "ADMIN")) {
			moveUrl = "apt/addApt2";
			
			model.addAttribute("SITENAME", siteName);
			
			
		}
		return moveUrl;
	}
	
	@RequestMapping("/popDongMod")
	public String dongInfo(HttpSession session, Model model) throws Exception {

		String moveUrl = "redirect:../";

		if (!SessionUtil.isNull(session, "ADMIN")) {
			moveUrl = "apt/popDongInfo";
		}
		return moveUrl;
	}
	
	
	@RequestMapping("/metering")
	public String aptMetering(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "apt") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "metering") String subMenu,
			@RequestParam(value = "SeqSite", defaultValue = "") String SeqSite,
			@RequestParam(value = "SiteName", defaultValue = "") String SiteName,
			@RequestParam(value = "To", defaultValue = "") String To,
			@RequestParam(value = "to_hh", defaultValue = "") String to_hh,
			@RequestParam(value = "to_yymmdd", defaultValue = "") String to_yymmdd,
			@RequestParam(value = "to_hhmm", defaultValue = "") String to_hhmm) throws Exception {

		String moveUrl = "redirect:../";
		
		System.out.println("SeqSite = " + SeqSite);

		StringUtil stringUtil = new StringUtil();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Date date_now = new Date(System.currentTimeMillis()); 
		// ????????? 12??? ????
		SimpleDateFormat To_format = new SimpleDateFormat("yyyyMMddHHmm");

		HashMap resultSiteCountMap = ManagerApi.GetSiteCount();
		int count_site = (Integer) resultSiteCountMap.get("count_site");

		int IndexFrom = 1;
		int IndexTo = 1000;

		HashMap siteMap = ManagerApi.GetSiteListForPaging(IndexFrom, count_site);

		List list_site = (List) siteMap.get("list_site");


		if ("".equals(SeqSite)) {
			
			String today = null;
			String yesterday = null;
			Date date = new Date();
			// ���˺��� ( ����� �ú���)
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdformat2 = new SimpleDateFormat("HH");
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			today = sdformat.format(cal.getTime());
			
			to_hhmm = sdformat2.format(cal.getTime()) + "00";
			to_hh = sdformat2.format(cal.getTime());
			System.out.println("to_hh = " + to_hh);
			System.out.println("to_hhmm = " + to_hhmm);

			cal.setTime(date);
			cal.add(Calendar.DATE, -1);
			yesterday = sdformat.format(cal.getTime());

			to_yymmdd = today;
			
			moveUrl = "apt/aptMetering";

			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			model.addAttribute("LIST_SITE", list_site);
			model.addAttribute("TO", To);
			model.addAttribute("TO_HH", to_hh);
			model.addAttribute("TO_YYMMDD", to_yymmdd);
			model.addAttribute("TO_HHMM", to_hhmm);

			return moveUrl;

		} else {
			
			String Tohhmm = String.valueOf(To.replaceAll("-", "")) + String.valueOf(to_hhmm);
			System.out.println("Tohhmm = " + Tohhmm);
			
			HashMap meteringMap = ManagerApi.getSiteMeteringResult(SeqSite, Tohhmm);
			to_hh = String.valueOf(to_hhmm.replaceAll("00", ""));
			List list_fap = (List) meteringMap.get("list_fap");
			System.out.println("list_fap = " + list_fap);
			int total = (int) meteringMap.get("total");
			int empty = (int) meteringMap.get("empty");

			moveUrl = "apt/aptMetering";
			
			Gson gson = new Gson();
			JsonObject json = gson.toJsonTree(meteringMap).getAsJsonObject();

			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			model.addAttribute("LIST_SITE", list_site);

			model.addAttribute("SEQSITE", SeqSite);
			model.addAttribute("SITENAME", SiteName);
			
			model.addAttribute("TO", To);
			model.addAttribute("TOHHMM", Tohhmm);
			model.addAttribute("TO_HH", to_hh);
			model.addAttribute("TO_YYMMDD", to_yymmdd);
			model.addAttribute("TO_HHMM", to_hhmm);

			model.addAttribute("METERINGMAP", meteringMap);
			model.addAttribute("LIST_FAP", list_fap);
			model.addAttribute("TOTAL", total);
			model.addAttribute("EMPTY", empty);
			
			model.addAttribute("json", json);

			return moveUrl;
		}
	}
	
	@RequestMapping("/meteringNew")
	public String aptMeteringNew(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "apt") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "metering") String subMenu,
			@RequestParam(value = "sort", defaultValue = "0") String sort,
			@RequestParam(value = "SeqSite", defaultValue = "") String SeqSite,
			@RequestParam(value = "SiteName", defaultValue = "") String SiteName) throws Exception {

		String moveUrl = "redirect:../";

		StringUtil stringUtil = new StringUtil();
		System.out.println("SeqSite = " + SeqSite);
		System.out.println("sort = " + sort);


		HashMap resultSiteCountMap = ManagerApi.GetSiteCount();
		int count_site = (Integer) resultSiteCountMap.get("count_site");

		int IndexFrom = 1;
		int IndexTo = 1000;

		HashMap siteMap = ManagerApi.GetSiteListForPaging(IndexFrom, count_site);

		List list_site = (List) siteMap.get("list_site");

		if ("".equals(SeqSite)) {

			moveUrl = "apt/aptMeteringNew";

			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			model.addAttribute("LIST_SITE", list_site);

			return moveUrl;

		} else {

			HashMap meteringMap = ManagerApi.getSiteMeteringState(SeqSite, "10");

			List list_fap = (List) meteringMap.get("list_fap");
			System.out.println("list_fap = " + list_fap);
			int total = (int) meteringMap.get("total");
			int empty = (int) meteringMap.get("empty");

			moveUrl = "apt/aptMeteringNew";

			Gson gson = new Gson();
			JsonObject json = gson.toJsonTree(meteringMap).getAsJsonObject();
			// System.out.println("json = " + json);

			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			model.addAttribute("LIST_SITE", list_site);

			model.addAttribute("SEQSITE", SeqSite);
			model.addAttribute("SITENAME", SiteName);

			model.addAttribute("METERINGMAP", meteringMap);
			model.addAttribute("LIST_FAP", list_fap);
			model.addAttribute("TOTAL", total);
			model.addAttribute("EMPTY", empty);
			
			model.addAttribute("json", json);

			return moveUrl;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/imgDown2", method = { RequestMethod.POST })
	public void imgDown2(HttpSession session,
			@RequestParam(value = "url_image", defaultValue = "") String url_image) throws Exception {
		System.out.println("gg2");
		
		File dir = new File("C:\\Users\\enernet99\\Downloads");
//		File dir = new File(System.getProperty("user.home"));
		BufferedImage image =null;
		URL url =new URL(url_image);
		image = ImageIO.read(url);

		dir.mkdir();
		String fileName = "test3";
//		String fileName urlCodecUtil.decode(goodsInfo.getGoodsTitle()).replace("/", "");

//		ImageIO.write(image, "png", new File(dir.toString() + "/" + fileName + ".png"));
		ImageIO.write(image, "png", new File(dir.toString() + "/" + fileName + ".png"));
		

	}
	
	@ResponseBody
	@RequestMapping("/imgDown")
	public void imgDown(HttpSession session,
			@RequestParam(value = "url_image", defaultValue = "") String url_image) throws Exception {
		System.out.println("gg");
		
		File dir = new File("C:\\Users\\enernet99\\Downloads");
//		File dir = new File(System.getProperty("user.home"));
		BufferedImage image =null;
		URL url =new URL(url_image);
		image = ImageIO.read(url);

		dir.mkdir();
		String fileName = "test3";
//		String fileName urlCodecUtil.decode(goodsInfo.getGoodsTitle()).replace("/", "");

//		ImageIO.write(image, "png", new File(dir.toString() + "/" + fileName + ".png"));
		ImageIO.write(image, "png", new File(dir.toString() + "/" + fileName + ".png"));
		

	}
	

	@RequestMapping(value = "/excelDownTest", method = { RequestMethod.POST })
	public String excelDwon(@RequestParam HashMap paramMap,
			@RequestParam(value = "SeqSite") String SeqSite,
			@RequestParam(value = "SiteName") String siteName,
			@RequestParam(value = "To") String To, @RequestParam(value = "Tohhmm") String Tohhmm,
			@RequestParam(value = "json2") String json2, HttpSession session, Model model, HttpServletResponse response)
			throws Exception {

		// JSON String to Map jacksonLibrary
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> data = mapper.readValue(json2, new TypeReference<Map<String, Object>>() {
		});

		// .0 ����� -> gson���� �轼���̺귯���� ����
		List list_fap = (List) data.get("list_fap");
		System.out.println("list_fap = " + list_fap);

		// ���ϸ� �ۼ�
		//String siteName = SessionUtil.getString(session, "ADMIN", "site_name");
		String fileName = String.valueOf(siteName) + "_" + String.valueOf(Tohhmm);
		fileName = fileName.replaceAll("," , "_");
		System.out.println("fileName before = " + fileName);

		fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		System.out.println("fileName after = " + fileName);

		// ��� �ۼ�
		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
		// servlet-context.xml name�� excelView(ExcelView)�ΰ� ȣ��
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Transfer-Encoding", "binary");

		model.addAttribute("PARAMMAP", paramMap);
		model.addAttribute("METERINGMAP", data);
		model.addAttribute("list_fap", list_fap);
		model.addAttribute("fileName", fileName);

		return "memberexcelView";

	}
	
	
	@RequestMapping(value = "/excelDownNew", method = { RequestMethod.POST })
	public String excelDwonNew(@RequestParam HashMap paramMap,
			@RequestParam(value = "SeqSite") String SeqSite,
			@RequestParam(value = "SiteName") String siteName,
			@RequestParam(value = "json2") String json2, HttpSession session, Model model, HttpServletResponse response)
			throws Exception {

		// JSON String to Map jacksonLibrary
		System.out.println(json2);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> data = mapper.readValue(json2, new TypeReference<Map<String, Object>>() {
		});

		// .0 ����� -> gson���� �轼���̺귯���� ����
		List list_fap = (List) data.get("list_fap");
		System.out.println("list_fap = " + list_fap);

		// ���ϸ� �ۼ�
		//String siteName = SessionUtil.getString(session, "ADMIN", "site_name");
		String fileName = String.valueOf(siteName);
		fileName = fileName.replaceAll("," , "_");
		System.out.println("dd : " + fileName);
		System.out.println("fileName before = " + fileName);

		fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		System.out.println("fileName after = " + fileName);

		// ��� �ۼ�
		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
		// servlet-context.xml name�� excelView(ExcelView)�ΰ� ȣ��
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Transfer-Encoding", "binary");

		model.addAttribute("PARAMMAP", paramMap);
		model.addAttribute("METERINGMAP", data);
		model.addAttribute("list_fap", list_fap);
		model.addAttribute("fileName", fileName);

		return "meterExcelNew";

	}
	

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public void test(MultipartHttpServletRequest req) throws Exception {
		String test = req.getParameter("aa");
		MultipartFile file = req.getFile("cc");

		System.out.println(test);
		System.out.println(file);

	}
	
	@RequestMapping("/aptNetwork")
	public ModelAndView aptNetwork(HttpSession session, ModelAndView mav) throws Exception {
		mav.setViewName("redirect:../"); 
		if (!SessionUtil.isNull(session, "ADMIN")) {
			mav.setViewName("redirect:../"); 
			apt.backController("aptNetwork", mav);	
			mav.addObject("mainMenu", "apt");
			mav.addObject("subMenu", "aptNetwork");
		}
		return mav;
	}
	
	
	@RequestMapping("/addAptNetwork")
	public String addAptNetwork(HttpSession session, Model model) throws Exception {
		
		String moveUrl = "redirect:../";
		if (!SessionUtil.isNull(session, "ADMIN")) {
			
			
			
			moveUrl = "apt/addAptNetwork";
		}
		return moveUrl;
	}
	
	@RequestMapping(value = "/addNetworkForm" , method = RequestMethod.POST)
	public ModelAndView addNetworkForm(HttpSession session, ModelAndView mav,
			
			@ModelAttribute("getChangedInfo") networkVO networtVO
			) throws Exception {	
		if (!SessionUtil.isNull(session, "ADMIN")) {
			mav.addObject("networtVO", networtVO);
			apt.backController("addNetworkForm", mav);
		}
		
		return mav;
	
	}
	
	@RequestMapping(value = "/delNetwork", method = RequestMethod.POST)
	public ModelAndView delNetwork(HttpSession session, ModelAndView mav,
			@RequestParam(value = "nSeqSiteH") String nSeqSite) throws Exception {
		mav.addObject("nSeqSite", nSeqSite);
		if (!SessionUtil.isNull(session, "ADMIN")) {
			apt.backController("delNetwork", mav);
			mav.addObject("action", "del");
		}
		return mav;
	}
	
	@RequestMapping(value = "/badMeters")
	public ModelAndView badMeters(HttpSession session, ModelAndView mav) throws Exception {
		if (!SessionUtil.isNull(session, "ADMIN")) {
			HashMap apiData = ManagerApi.collectBadMeters();
			List list_bad_meter = (List) apiData.get("list_bad_meter");

			Gson gson = new Gson();
			JsonObject json = gson.toJsonTree(apiData).getAsJsonObject();

			mav.addObject("json", json);

			mav.addObject("list_bad_meter", list_bad_meter);
			mav.addObject("total", list_bad_meter.size());
			mav.addObject("mainMenu", "apt");
			mav.addObject("subMenu", "badMeter");

			mav.setViewName("apt/badMeters");
		}
		return mav;
	}
	
	@RequestMapping(value = "/getLpListByMid")
	public ModelAndView getLpListByMid(HttpSession session, ModelAndView mav, @RequestParam(value = "Mid") String Mid)
			throws Exception {
		if (!SessionUtil.isNull(session, "ADMIN")) {

			HashMap apiData = ManagerApi.getLpListByMid(Mid);
			List list_lp = (List) apiData.get("list_lp");
			HashMap ho_info = (HashMap) apiData.get("ho_info");

			mav.addObject("list_lp", list_lp);
			mav.addObject("total", list_lp.size());
			mav.addObject("ho_info", ho_info);

			mav.setViewName("apt/getLp");
		}

		return mav;
	}
	
	
	@RequestMapping("/siteReport")
	public String siteReport(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "apt") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "siteReport") String subMenu) throws Exception {

		String moveUrl = "redirect:../";

		if (!SessionUtil.isNull(session, "ADMIN")) {

			int Decor = 0;
			JSONObject resultSiteMap = JSonApi.GetSiteListForReport(Decor);
			JSONArray list_site = (JSONArray) resultSiteMap.get("list_site");

//		JSONArray percentList = new JSONArray();
			List<Float> percentList = new ArrayList<Float>();
			List<Integer> meterList = new ArrayList<Integer>();

			for (int i = 0; i < list_site.size(); i++) {
				
				JSONObject list_site_map = (JSONObject) list_site.get(i);
				Float percent_comm = Float.valueOf(String.valueOf(list_site_map.get("percent_lp")));
				percentList.add(percent_comm);
				
				int count_lp = Integer.valueOf(String.valueOf(list_site_map.get("count_lp")));
				meterList.add(count_lp);
		
			}
//			System.out.println("percentList = " + percentList);

			float total = 0;
			float avg = 0;

			for (int i = 0; i < percentList.size(); i++) {

				total += percentList.get(i);
				avg = total / percentList.size();
			}
			
			
			int total2 = 0;

			for (int i = 0; i < meterList.size(); i++) {

				total2 += meterList.get(i);
			}
			System.out.println("total2 = " + total2);
			
			

			moveUrl = "/apt/siteReport";
			
			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			model.addAttribute("LIST_SITE", list_site);
			model.addAttribute("avg", avg);
			model.addAttribute("total2", total2);
		}
		
		return moveUrl;

	}
	
	
	@RequestMapping(value = "/excelDownBadMeter", method = { RequestMethod.POST })
	public String excelDownBadMeter(@RequestParam HashMap paramMap,
			@RequestParam(value = "json2") String json2, HttpSession session, Model model, HttpServletResponse response)
			throws Exception {
		
				
		// JSON String to Map jacksonLibrary
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> data = mapper.readValue(json2, new TypeReference<Map<String, Object>>() {
				});
				
		List list_bad_meter = (List) data.get("list_bad_meter");
		
		
		Date now = new Date();
		 
        // ���� ��¥/�ð� ���
 
        // ������ ����
        SimpleDateFormat formatter = new SimpleDateFormat("yy_MM_dd__HH_mm_s");
 
        // ������ ����
        String formatedNow = formatter.format(now);

		// ���ϸ� �ۼ�
		//String siteName = SessionUtil.getString(session, "ADMIN", "site_name");
		
		
		String fileName = String.valueOf("불량계량기목록_"+formatedNow);
		fileName = fileName.replaceAll("," , "_");
		fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");

		// ��� �ۼ�
		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
		// servlet-context.xml name�� excelView(ExcelView)�ΰ� ȣ��
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Transfer-Encoding", "binary");

		model.addAttribute("PARAMMAP", paramMap);
		model.addAttribute("METERINGMAP", data);
		model.addAttribute("list_bad_meter", list_bad_meter);
		model.addAttribute("fileName", fileName);
//
		return "excelViewBadMeters";

	}
	
	
	
}
