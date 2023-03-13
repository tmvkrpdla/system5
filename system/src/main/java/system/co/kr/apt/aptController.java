package system.co.kr.apt;

import java.awt.PageAttributes.MediaType;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import system.co.kr.apt.service.Apt;
import system.co.kr.beans.AppRequestVO;
import system.co.kr.beans.networkVO;
import system.co.kr.beans.sampleSessionVO;
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

//			HashMap hoMap = ManagerApi.getHoListBySiteForPaging(SeqSite, pageUtil.getStartNum(), pageUtil.getEndNum());
//			List list_ho = (List) hoMap.get("list_ho");
			
			//to json
			
			JSONObject hoMap = JSonApi.getHoListBySiteForPaging(SeqSite, pageUtil.getStartNum(), pageUtil.getEndNum());
			JSONArray list_ho = (JSONArray) hoMap.get("list_ho");
//			System.out.println("list_ho = " + list_ho);

			HashMap dongHoMapForExcel = ManagerApi.getHoListBySiteForPaging(SeqSite, 1, 10000);
			
			
			JSONObject hoMapForDown = JSonApi.getHoListBySiteForPaging(SeqSite, 1, 100000);
			JSONArray list_ho_ForDown = (JSONArray) hoMapForDown.get("list_ho");
			

			Gson gson = new Gson();

			JsonObject json = gson.toJsonTree(dongHoMapForExcel).getAsJsonObject();

			moveUrl = "apt/valueOld";

			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			// model.addAttribute("SITE_INFO", site_info);
			model.addAttribute("LIST_SITE", list_site);
			model.addAttribute("LIST_HO", list_ho);
			model.addAttribute("LIST_HO_FORDOWN", list_ho_ForDown);
			
			
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
		
		System.out.println("dd");
		
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
	
	
	@RequestMapping(value = "/meterImageDown", method = { RequestMethod.POST })
	public String meterImageDown(@RequestParam HashMap paramMap, @RequestParam(value = "LIST_HO2") String LIST_HO2,
			HttpSession session, Model model, HttpServletResponse response, HttpServletRequest req) throws Exception {
		// JSON String to Map jacksonLibrary

		Gson gson = new Gson();
//		JsonObject json = gson.toJsonTree(LIST_HO2).getAsJsonObject();
//		System.out.println("json = " + json);

		JsonParser parser = new JsonParser();

		JsonArray jarry = (JsonArray) parser.parse(LIST_HO2);
		System.out.println("jarry : " + jarry);
//        Object obj = parser.parse(LIST_HO2);
//        JsonObject jobj = (JsonObject)parser.parse(LIST_HO2);

		List<Integer> seqHoList = new ArrayList<Integer>();

		for (int i = 0; i < jarry.size(); i++) {
			JsonObject seqHoMap = (JsonObject) jarry.get(i);
			int seqHo = Integer.valueOf(String.valueOf(seqHoMap.get("seq_ho")));
			seqHoList.add(seqHo);
		}

		System.out.println("seqHoList = " + seqHoList);
//		System.out.println(SiteName2 + "이 아파트 전체 세대 = " + seqHoList.size() + "세대");

//		for (int h = 0; h < jarry.size(); h++) {
//		JSONObject detailInfoMap = JSonApi.getHoInfo(seqHoList.get(h));
		JSONObject detailInfoMap = JSonApi.getHoInfo(seqHoList.get(0));
		JSONObject ho_info = (JSONObject) detailInfoMap.get("ho_info");
		JSONArray list_image_meter = (JSONArray) detailInfoMap.get("list_image_meter");
		System.out.println("list_image_meter : " + list_image_meter);

		List<String> imgUrlList = new ArrayList<String>();

		for (int i = 0; i < list_image_meter.size(); i++) {
			JSONObject list_image_meter_map = (JSONObject) list_image_meter.get(i);
			String url_image = String.valueOf(list_image_meter_map.get("url_image"));
			imgUrlList.add(url_image);
		}

		System.out.println("imgUrlList : " + imgUrlList);

		// 디렉토리 만들기
//			Path directoryPath = Paths.get("C:\\Users\\enernet99\\Downloads\\new_test_0313");
//			Files.createDirectory(directoryPath);
//
//			System.out.println(directoryPath + " 디렉토리가 생성되었습니다.");

//			File dir = new File("C:\\Users\\enernet99\\\\Downloads\\");
//		File dir = new File(System.getProperty("user.home"));
			BufferedImage image = null;

		System.out.println("갯수 : " + imgUrlList.size());

		for (int j = 0; j < imgUrlList.size(); j++) {
//				URL url = new URL(imgUrlList.get(j));
//				image = ImageIO.read(url);

//				dir.mkdir();
//				String fileName = "test_meter_img7_" + j;
//		String fileName urlCodecUtil.decode(goodsInfo.getGoodsTitle()).replace("/", "");

//		ImageIO.write(image, "png", new File(dir.toString() + "/" + fileName + ".png"));
//				ImageIO.write(image, "png", new File(dir.toString() + "/" + fileName + ".png"));  
//				ImageIO.write(image, "png", response.getOutputStream());
//				System.out.println("저장성공ㅋ");

			// // //
			

			String spec = "test.png";
			String outputDir = "C:\\Users\\enernet99\\Downloads\\new_test_0313";

			InputStream is = null;

			// This will get input data from the server
			InputStream inputStream = null;

			// This will read the data from the server;
			OutputStream outputStream = null;
			FileOutputStream os = null;
			try {
				
				URL url = new URL(imgUrlList.get(j));

				// This user agent is for if the server wants real humans to visit
				String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				int responseCode = conn.getResponseCode();
				
				
				System.out.println("responseCode " + responseCode);

				// Status 가 200 일 때
				if (responseCode == HttpURLConnection.HTTP_OK) {
					
//					conn.setRequestProperty("User-Agent", USER_AGENT);
					
					String fileName = "";
					String fileName2 = "";
					String fileName3 = "";
					String disposition = conn.getHeaderField("Content-Disposition");
					String contentType = conn.getContentType();

					// 일반적으로 Content-Disposition 헤더에 있지만
					// 없을 경우 url 에서 추출해 내면 된다.
					if (disposition != null) {
						String target = "filename=";
						int index = disposition.indexOf(target);
						if (index != -1) {
							fileName = disposition.substring(index + target.length() + 1);
						}
					} else {
						fileName = imgUrlList.get(j).substring(imgUrlList.get(j).lastIndexOf("/") + 1);
						fileName2 = imgUrlList.get(j);
					}

//					System.out.println("Content-Type = " + contentType);
//					System.out.println("Content-Disposition = " + disposition);
//					System.out.println("fileName = " + fileName);
//					System.out.println("fileName2 = " + fileName2);
//
//					is = conn.getInputStream();
//					os = new FileOutputStream(new File(outputDir, fileName));
//
//					final int BUFFER_SIZE = 4096;
//					int bytesRead;
//					byte[] buffer = new byte[BUFFER_SIZE];
//					while ((bytesRead = is.read(buffer)) != -1) {
//						os.write(buffer, 0, bytesRead);
//					}
//					os.close();
//					is.close();
//					System.out.println("File downloaded");
//					System.out.println("저장성공ㅋ");
					
					
//					String uploadPath = "C:\\Users\\enernet99\\Downloads\\new_test_0313\\";
					String uploadPath =  imgUrlList.get(j);
//				 	String saveFileName = imgUrlList.get(j);
//			        String originalFileName = imgUrlList.get(j);
					
					
					String browser = req.getHeader("User-Agent");
					
					BufferedImage img = ImageIO.read(url);
					File file = new File("downloaded.jpg");
					ImageIO.write(img, "jpg", file);
			        
//			        File downloadFile = new File(uploadPath + fileName);
//			        File downloadFile = new File(uploadPath);
//					File downloadFile = Paths.get(url.toURI()).toFile();
//			        File file = Paths.get(url.toURI()).toFile();
//			        FileUtils.copyURLToFile(new URL(uploadPath), downloadFile);
			        
			        byte fileByte[] = FileUtils.readFileToByteArray(file);
			        
			        response.setContentType("application/octet-stream");
			        response.setContentLength(fileByte.length);
			        
			        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName,"UTF-8") +"\";");
			        response.setHeader("Content-Transfer-Encoding", "binary");
			        response.getOutputStream().write(fileByte);
			        response.getOutputStream().flush();
			        response.getOutputStream().close();
					
					
				} 
				
				
				
				else {
					System.out.println("No file to download. Server replied HTTP code: " + responseCode);
				}
				conn.disconnect();
			} catch (Exception e) {
				System.out.println("An error occurred while trying to download a file.");
				e.printStackTrace();
				try {
					if (is != null) {
						is.close();
					}
					if (os != null) {
						os.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			
			
		}
		
		

//		}
		
		String moveUrl = "apt/valueOld";
		return moveUrl;

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
	public String addApt2(HttpSession session, Model model) throws Exception {
		
		String moveUrl = "redirect:../";
		//System.out.println("nowPage = " + nowPage);
		//System.out.println("siteName = " + siteName);

		if (!SessionUtil.isNull(session, "ADMIN")) {
			moveUrl = "apt/addApt2";
			
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
	
	
	
	@RequestMapping("/appRequest")
	public String appRequest(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "apt") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "appRequest") String subMenu,
			@RequestParam(value = "SeqSite", defaultValue = "") String SeqSite,
			@RequestParam(value="nowPage", defaultValue="1")int nowPage) throws Exception {

		String moveUrl = "redirect:../";

		StringUtil stringUtil = new StringUtil();
		
		HashMap siteMap = ManagerApi.GetSiteList();
		List list_site = (List) siteMap.get("list_site");
		System.out.println("list_site = " + list_site);

		if ("".equals(SeqSite)) {

			moveUrl = "apt/appRequest";

			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			model.addAttribute("LIST_SITE", list_site);

			return moveUrl;

		} else {
			
			HashMap appRequestCountMap = ManagerApi.GetAppRequestCount(SeqSite);
			int count_ho = (Integer) appRequestCountMap.get("count_ho");
			System.out.println("count_ho = " + count_ho);
			
			PageUtil pageUtil = new PageUtil(nowPage, count_ho, 30);

			int IndexFrom = 1;

			HashMap appRequestListMap = ManagerApi.GetAppRequestListForPaging(SeqSite, pageUtil.getStartNum(), pageUtil.getEndNum());
			List list_ho = (List) appRequestListMap.get("list_ho");


			moveUrl = "apt/appRequest";

			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);

			model.addAttribute("SEQSITE", SeqSite);
			model.addAttribute("PAGEUTIL", pageUtil);
			model.addAttribute("nowPage", nowPage);

			model.addAttribute("LIST_SITE", list_site);
			model.addAttribute("LIST_HO", list_ho);
			
			return moveUrl;
		}
	}
	
	
	@RequestMapping("/appRequest_test")
	public String appRequest_test(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "apt") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "appRequest") String subMenu,
			@RequestParam(value = "SeqSite", defaultValue = "") String SeqSite,
			@RequestParam(value="nowPage", defaultValue="1")int nowPage) throws Exception {

		String moveUrl = "redirect:../";

		StringUtil stringUtil = new StringUtil();
		
		HashMap siteMap = ManagerApi.GetSiteList();
		List list_site = (List) siteMap.get("list_site");
		System.out.println("list_site = " + list_site);

		if ("".equals(SeqSite)) {

			moveUrl = "apt/appRequest_test";

			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			model.addAttribute("LIST_SITE", list_site);

			return moveUrl;

		} else {
			
			HashMap appRequestCountMap = ManagerApi.GetAppRequestCount(SeqSite);
			int count_ho = (Integer) appRequestCountMap.get("count_ho");
			System.out.println("count_ho = " + count_ho);
			
			PageUtil pageUtil = new PageUtil(nowPage, count_ho, 30);

			int IndexFrom = 1;

			HashMap appRequestListMap = ManagerApi.GetAppRequestListForPaging(SeqSite, pageUtil.getStartNum(), pageUtil.getEndNum());
			List list_ho = (List) appRequestListMap.get("list_ho");


			moveUrl = "apt/appRequest_test";

			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);

			model.addAttribute("SEQSITE", SeqSite);
			model.addAttribute("PAGEUTIL", pageUtil);
			model.addAttribute("nowPage", nowPage);

			model.addAttribute("LIST_SITE", list_site);
			model.addAttribute("LIST_HO", list_ho);
			
			return moveUrl;
		}
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
	
	//lgj
	@RequestMapping("/appRequestLGJ")
	public ModelAndView appRequest(HttpSession session, ModelAndView mav, 
			@RequestParam(value = "SeqSite", defaultValue="") String SeqSite,
			@RequestParam(value="nowPage", defaultValue="1") int nowPage
			) throws Exception {
		mav.addObject("SeqSite" , SeqSite);
		mav.addObject("nowPage" , nowPage);
		
		if (!SessionUtil.isNull(session, "ADMIN")) {
			apt.backController("appRequest", mav );
		} else {mav.setViewName("redirect:../");}
		return mav;
	}
	
	@RequestMapping("/appRequestSendLGJ")
	public ModelAndView appRequestSend(HttpSession session, ModelAndView mav, 
			@ModelAttribute("appRequestSend") AppRequestVO appRequestVO,
			@RequestParam(value = "SeqSite", defaultValue="") String SeqSite,
			@RequestParam(value="nowPage", defaultValue="1") int nowPage
			) throws Exception {
		mav.addObject("SeqSite" , SeqSite);
		mav.addObject("nowPage" , nowPage);
		mav.addObject("appRequestVO", appRequestVO);
		
		if (!SessionUtil.isNull(session, "ADMIN")) {
			apt.backController("appRequestSend", mav );
		} else {mav.setViewName("redirect:../");}
		return mav;
	}
	
	@RequestMapping("/lteIp") //lgj
	public  void lteIp(HttpSession session, Model model, HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "jsonLte") String jsonLte,
			@RequestParam(value = "snList") String snList
			) throws Exception {
		
		sampleSessionVO sample = new sampleSessionVO();
		
		// sampleSession Load 
		String sad = sample.getSad();
		
		
		if (!SessionUtil.isNull(session, "ADMIN")) {
			
			JsonParser parser = new JsonParser();
			ObjectMapper mapper = new ObjectMapper();
			
			JsonArray jsonLteArray = (JsonArray) parser.parse(jsonLte);
			Map<String, Object> data = mapper.readValue(snList, new TypeReference<Map<String, Object>>() {
			});
			
			
			JsonObject snListObject = (JsonObject) parser.parse(snList);
			
			
			String aptName = (String) data.get("Name");
			ArrayList lteSn = (ArrayList)data.get("LteSn");
			ArrayList dcuId = (ArrayList)data.get("dcuid");
			ArrayList nPortSsh2 = (ArrayList)data.get("nPortSsh2");
			ArrayList location = (ArrayList)data.get("location");
			
			
			System.out.println("zzz2" + data);
			
			
			List <Map<String, Object>> a = new ArrayList <Map<String, Object>>() ; // a :  jsonLte List 
			List<Map<String, Object>> sessionInfoList = new ArrayList<Map<String, Object>>() ; // b :  snList List 
			
			for(int i = 0; i < jsonLteArray.size(); i++) {
			a.add(mapper.readValue(jsonLteArray.get(i).toString(), new TypeReference<Map<String, Object>>() {}));  
			}
			
			
			System.out.println("ltesnsize" + lteSn.size());
			System.out.println("asize"+a.size());
			for(int i=0; i < lteSn.size() ; i++) {
				for(int j=0; j < a.size() ; j++) {
					
					Map<String,Object> map = new HashMap <String, Object>();
					
					if(lteSn.get(i).equals(a.get(j).get("memo"))) {
						map.put("lteSn", lteSn.get(i));
						map.put("dcuId", dcuId.get(i));
						
						String changePort = (String)nPortSsh2.get(i);
						
						if(changePort.equals("50001")){changePort="0000c351";}
						else if(changePort.equals("50002")){changePort="0000c352";}
						else if(changePort.equals("50003")){changePort="0000c353";}
						else if(changePort.equals("50004")){changePort="0000c354";}
						else if(changePort.equals("50005")){changePort="0000c355";}
						else if(changePort.equals("50006")){changePort="0000c356";}
						
						map.put("nPortSsh2",  changePort);
						map.put("location", location.get(i));
						map.put("ipa", a.get(j).get("ipa"));
						
						sessionInfoList.add(map); 
						
					}
				}
			}
			
			System.out.println("sessionInfoList : " + sessionInfoList);
			 	
			List<File> sessionList=  new ArrayList<File>(); 
			
			
//			FileReader reader = new FileReader("../");
			// 50001 : 0000c351
			
			for(int i=0; i<sessionInfoList.size(); i++ ) {
					File newSession = new File((String) sessionInfoList.get(i).get("dcuId")+ "_"+(String) sessionInfoList.get(i).get("location") + ".ini");
			 	
		        try {
		        	
		        	
		        	sad = sad.replaceAll("ipa", (String)sessionInfoList.get(i).get("ipa"));
		        	sad = sad.replaceAll("sshport", (String)sessionInfoList.get(i).get("nPortSsh2"));
		        	
		        	System.out.println(sad);
		            //write string to file
		            FileUtils.writeStringToFile(newSession, sad);
		            System.out.print("Data written to file successfully.");
		            
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        sessionList.add(newSession);
			}
			
		        
		        if (sessionList.get(0).isFile()) {System.out.println("이안에 파일 있다.");}
		        
		        String folderPath = System.getProperty("user.dir");
		        
		        String fileName = aptName + ".zip";
		        
		        String tempPath = "C:\\Users\\we285\\git\\system5\\system\\src\\main\\webapp\\resources\\session\\";
		       
		        File zipFile = new File(tempPath, fileName);
		        byte[] buf = new byte[4096];
		 
		        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile))) {
		 
		            for (File file : sessionList) {
		                try (FileInputStream in = new FileInputStream(file)) {
		                    ZipEntry ze = new ZipEntry(file.getName());
		                    out.putNextEntry(ze);
		 
		                    int len;
		                    while ((len = in.read(buf)) > 0) {
		                        out.write(buf, 0, len);
		                    }
		 
		                    out.closeEntry();
		                }
		 
		            }
		        }
		        
		        
		        
		        System.out.println("압축 파일 생성 성공");
		 
		        
		}else { }
		
	}
	
	
	
	@RequestMapping(value="/multi-file", method=RequestMethod.POST)
	public void multiFileUpload(@RequestParam("multiFile") List<MultipartFile> multiFileList, @RequestParam String fileContent, HttpServletRequest request) throws IOException {
		System.out.println("안녕");
		// 받아온것 출력 확인
		
		System.out.println("multiFileList : " + multiFileList);
		System.out.println("fileContent : " + fileContent);
		
	}
	
	
	
}
