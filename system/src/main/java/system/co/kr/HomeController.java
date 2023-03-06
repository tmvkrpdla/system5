package system.co.kr;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import system.co.kr.util.Api;
import system.co.kr.util.FileUtil;
import system.co.kr.util.SessionUtil;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final boolean devMode = false;
	
	@RequestMapping(value = "/")
	public String indexLogin(HttpSession session, Model model) throws Exception {
		String moveUrl = "index";
//		if(!SessionUtil.isNull(session, "ADMIN")) {
//			 
//			moveUrl = "redirect:main/home";
//		}		
		
		return moveUrl;
	}
	
	@RequestMapping(value ="/main/home")
	public String mainHome(HttpSession session, 
			@RequestParam(value="mainMenu", defaultValue="main") String mainMenu,
			@RequestParam(value="subMenu", defaultValue="home") String subMenu,
			Model model) throws Exception{
		
		String moveUrl = "redirect:/";
		
		if(!SessionUtil.isNull(session, "ADMIN")) {
			
			//moveUrl ="main/home";
			moveUrl ="apt/estate";
			
			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);
			
		}
		
		return moveUrl;
	}
	
	
	@RequestMapping(value = "/login/workerLogin")
	public String workerLogin(HttpSession session, @RequestParam HashMap<String, String> paramMap,
			RedirectAttributes rttr, Model model) throws Exception {

		String moveUrl = "";

		String WorkerId = paramMap.get("userId");
		String Password = paramMap.get("userPwd");
		String DeviceId = "device_id_000";
		String DeviceOs = "web";
		String Version = "211208";

		HashMap<String, Object> loginCheckMap = Api.CheckLogin(WorkerId, Password, DeviceId, DeviceOs, Version);
		String seq_admin = String.valueOf(loginCheckMap.get("seq_worker_found"));

		if ("0".equals(seq_admin)) {
			// seq_admin == 0 占싱몌옙 占싸깍옙占쏙옙 占쏙옙占쏙옙
			logger.info("= = = = = LOGIN FAIL LOG START = = = = =");

			logger.info("login proc paramMap : " + paramMap.toString());
			logger.info("login check return value : " + loginCheckMap.toString());
			logger.info("= = = = = LOGIN FAIL LOG END = = = = =");

			rttr.addFlashAttribute("ISLOGIN", "FAIL");
			moveUrl = "redirect:../";

		} else {

			HashMap<String, Object> adminInfoMap = Api.getWorkerInfo(seq_admin);
			HashMap worker_info = (HashMap) adminInfoMap.get("worker_info");
			//HashMap<String,String> adminInfoMap = new HashMap<String,String>();//HashMap占쏙옙占쏙옙
			String is_director = String.valueOf(worker_info.get("is_director"));
			String worker_name = String.valueOf(worker_info.get("worker_name"));

			worker_info.put("userPwd", Password);
			worker_info.put("userId", WorkerId);
			worker_info.put("seq_admin", seq_admin);
			//adminInfoMap.put("is_director", is_director);
			//adminInfoMap.put("worker_name", worker_name);

			session.setAttribute("ADMIN", worker_info);
			session.setMaxInactiveInterval(-1);
			logger.info("= = = = = LOGIN SUCCESS LOG START = = = = =");
			logger.info("ADMIN INFO : " + adminInfoMap.toString());
			logger.info("= = = = = LOGIN SUCCESS LOG END = = = = =");

			moveUrl = "redirect:../apt/estate";

		}

		return moveUrl;
	}
	
	@RequestMapping(value="/login/logout")
	public String logout(HttpSession session)throws Exception{
		String moveUrl = "";
		
		session.invalidate();
		
		moveUrl = "redirect:../";
		return moveUrl;
	}
	
	@RequestMapping(value="/file/uploadError", method=RequestMethod.POST)
	public void uploadError(HttpServletRequest request, HttpServletResponse response, HttpSession session, 
			@RequestParam HashMap paramMap) throws Exception{
		
		
		logger.info("= = = = = FILE UPLOAD ERROR LOG START = = = = =");
		logger.info("uploadError admin_id : " + SessionUtil.getString(session, "ADMIN", "admin_id") );
		logger.info("uploadError admin_name : " + SessionUtil.getString(session, "ADMIN", "admin_name") );
		
		logger.info("uploadError param : " + paramMap.toString());
		logger.info("= = = = = FILE UPLOAD ERROR LOG END = = = = =");
		
	}
	
	@ResponseBody
	@RequestMapping(value="/file/uploadAjax", method=RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> uploadAjax(@RequestParam MultipartFile file) throws Exception{
		ResponseEntity<HashMap<String, Object>> returnEntity = null;
		
		System.out.println("占썽엔占쏙옙 占쏙옙占쏙옙");
		
		//logger.info("data : " + file.getSize());
		//logger.info("data : " + file.getOriginalFilename());
		
		String basePath = "D:/kepco_safety/file";
		String baseTempPath = "D:/tempPath/kepco_safety/file";
		
		logger.info("= = = = = FILE UPLOAD LOG START = = = = =");
		
		Map<String, Object> fileInfoMap = FileUtil.fileUpload(file, basePath, baseTempPath);
		logger.info("uploadAjax result : " + fileInfoMap.toString());
		
		logger.info("= = = = = FILE UPLOAD LOG END = = = = =");
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("result", fileInfoMap);
		
		returnEntity = new ResponseEntity<HashMap<String, Object>> (returnMap, HttpStatus.OK); 
		
		return returnEntity; 
	}
	
	/**
	 * 占싱뱄옙占쏙옙 占쏙옙占싸듸옙 처占쏙옙占싹댐옙 占쌉쇽옙
	 * @param request
	 * @param multiFile
	 * @param upload
	 * @throws Exception
	 */
	@RequestMapping(value="/file/imageUpload", method=RequestMethod.POST)
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, HttpSession session, 
			@RequestParam MultipartFile upload) throws Exception{

	    PrintWriter printWriter = null;
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	      
	    String oriFileName = upload.getOriginalFilename();		
	    boolean isImage = FileUtil.isImage(oriFileName);
	   
	    
	    try {
	    	printWriter = response.getWriter();
	    	
	    	 //	占싱뱄옙占쏙옙占쏙옙 占싣니몌옙 ...
		    if(!isImage) {
		    	
		    	logger.info("= = = = = IMAGE UPLOAD LOG START = = = = =");
		    	logger.info("imageUpload admin_id : " + SessionUtil.getString(session, "ADMIN", "admin_id") );
				logger.info("imageUpload admin_name : " + SessionUtil.getString(session, "ADMIN", "admin_name") );
				
		    	logger.info("not image : " + oriFileName);
		    	logger.info("= = = = = IMAGE UPLOAD LOG END = = = = =");
		    	
		    	printWriter.print("0"+"|"+ oriFileName + " 占쏙옙 占싱뱄옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싣닙니댐옙.");
		    
		        
		    }else {
		    	
		    	long maxSize = 10485760;
		    	if(upload.getSize()>maxSize) {
		    		printWriter.print("0"+"|"+ "10MB 占쏙옙占쏙옙 占싱몌옙占쏙옙占쏙옙 占시뤄옙占쌍시깍옙 占쌕띰옙占싹댐옙.");
 
			        

		    	}else {
		    		//	占쏙옙占쏙옙 占쏙옙占싸듸옙 占쏙옙占쏙옙 占싻쏙옙
		    		//String basePath = "D:/kepco_safety/image";
		    		String basePath = "C:/enernet/kepco_safety_web_image";
				    //	占쌈쏙옙 占쏙옙占쏙옙 占싻쏙옙
				    //String baseTempPath = "D:/tempPath/kepco_safety/image";
		    		String baseTempPath = "C:/enernet/kepco_safety_web_image_temp";
				    
				    //	占쏙옙占싱쏙옙 URL	(dev)
				    String baseFileUrl = null;
				   
				    
				    if(devMode) {
				    	//	占쏙옙占쏙옙占쏙옙 占쏙옙퓨占쏙옙
				    	baseFileUrl = "http://192.168.100.111:15000/safety/image";
				    	
				    }else {
					    //	占쏘영 占쏙옙占쏙옙		
				    	
				    	//baseFileUrl = "https://www.egservice.co.kr/safety/image";
				    	baseFileUrl ="https://www.kepcosafety.co.kr/image";
				    }
				    
				    logger.info("= = = = = IMAGE UPLOAD LOG START = = = = =");
				    logger.info("imageUpload admin_id : " + SessionUtil.getString(session, "ADMIN", "admin_id") );
					logger.info("imageUpload admin_name : " + SessionUtil.getString(session, "ADMIN", "admin_name") );
					
				    Map<String, Object> fileInfoMap = FileUtil.fileUpload(upload, basePath, baseTempPath);
				    String datePath = (String) fileInfoMap.get("datePath");			    
				    String fileName = (String) fileInfoMap.get("fileName");
				    
			    	logger.info("imageUpload imageName : " + oriFileName);
					
				    logger.info("imageUpload fileInfo : " + fileInfoMap.toString());
				    logger.info("= = = = = IMAGE UPLOAD LOG END = = = = =");
				    
				    String url = baseFileUrl +"/" + datePath + "/" + fileName;
				       
				    //logger.info(url); 
				    //	| 占쏙옙占쏙옙占쌘뤄옙 占싹울옙占쏙옙 占쏙옙占쏙옙
				    //	占쏙옙占쏙옙 1, message , url
				    printWriter.print("1"+"|"+"占싱뱄옙占쏙옙占쏙옙 占쏙옙占쏙옙臼占쏙옙占쏙옙求占�." + "|" + url);
				   
				    
		    	}
		    }
		    
		    printWriter.flush();
		    
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	
	    }finally {
	        if (printWriter != null) {
                printWriter.close();
            }
	    }
		    
    }//	end
	
	@RequestMapping(value = "/common/error{error_code}")
	public ModelAndView error(HttpServletRequest request, HttpSession session, Exception exception,
			@PathVariable String error_code) {

		ModelAndView mv = new ModelAndView("/common/error");

		String exceptionMsg = exception.getMessage();
		// 占쏙옙占쏙옙
		String statckTrace = "";

		StackTraceElement[] ele = exception.getStackTrace();
		for (int i = 0; i < ele.length; i++) {
			statckTrace += "\r\n" + ele[i];
		}

		String msg = "";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS_CODE", request.getAttribute("javax.servlet.error.status_code"));
		map.put("REQUEST_URI", request.getAttribute("javax.servlet.error.request_uri"));
		map.put("EXCEPTION_TYPE", request.getAttribute("javax.servlet.error.exception_type"));
		map.put("EXCEPTION", request.getAttribute("javax.servlet.error.exception"));
		map.put("SERVLET_NAME", request.getAttribute("javax.servlet.error.servlet_name"));
		map.put("ERROR_MESSAGE", request.getAttribute("javax.servlet.error.message"));
		map.put("STATCKTRACE", statckTrace);

		try {
			
			if("400".equals(error_code)) {
				msg = "占쌩몌옙占쏙옙 占쏙옙청占쌉니댐옙.";
				
			}else if("403".equals(error_code)) {
				
				msg = "占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占실억옙占쏙옙占싹댐옙.";
				
			}else if("404".equals(error_code)) {
				
				msg = "占쏙옙占쏙옙占쏙옙占쏙옙 찾占쏙옙 占쏙옙 占쏙옙占쏙옙占싹댐옙.";
				
			}else if("405".equals(error_code)) {
				
				msg = "占쏙옙청占쏙옙 占쌨소드가 占쏙옙占쏙옙占쏙옙 占십쏙옙占싹댐옙.";
				
			}else if("500".equals(error_code)) {
				
				msg = "占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쌩삼옙占싹울옙占쏙옙占싹댐옙.";
				
			}else {
				
				msg = "占쏙옙 占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쌩삼옙占싹울옙占쏙옙占싹댐옙.";
			}
			
		} catch (Exception e) {
			msg = "占쏙옙타 占쏙옙占쏙옙占쏙옙 占쌩삼옙占싹울옙占쏙옙占싹댐옙.";
			
		} finally {
			
			map.put("MESSAGE", msg);
		}

		// logging
		if (map.isEmpty() == false) {

			logger.info("= = = = = ERROR LOG START = = = = =");

			if (!SessionUtil.isNull(session, "ADMIN")) {
				logger.info("ERROR LOG admin_id : " + SessionUtil.getString(session, "ADMIN", "admin_id"));
				logger.info("ERROR LOG admin_name : " + SessionUtil.getString(session, "ADMIN", "admin_name"));
			} else {
				logger.info("session is null");
			}
			
			
			for (String key : map.keySet()) {
				// Object value = map.get(key);
				logger.info("key : " + key + "," + "value : " + map.get(key));
			}
			
			logger.info("= = = = = ERROR LOG END = = = = =");
			
		}

		mv.addObject("error", map);
		return mv;
	}
	
	
	
	  @ResponseBody  
	  @RequestMapping("/ajax/error") public void ajaxError(HttpSession session, 
			  @RequestBody HashMap<String, Object> paramMap ) throws Exception{
		  logger.info("= = = = = AJAX ERROR LOG START = = = = =");
		  logger.info("ajaxError admin_id : " + SessionUtil.getString(session, "ADMIN", "admin_id") );
		  logger.info("ajaxError admin_name : " + SessionUtil.getString(session, "ADMIN", "admin_name") );
		  logger.info("ajaxError param : " + paramMap.toString());
		  logger.info("= = = = = AJAX ERROR LOG END = = = = =");
	  }
	 
	
}
