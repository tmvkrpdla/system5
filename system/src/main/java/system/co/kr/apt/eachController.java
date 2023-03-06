package system.co.kr.apt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import system.co.kr.util.ManagerApi;
import system.co.kr.util.PageUtil;
import system.co.kr.util.SessionUtil;
import system.co.kr.util.StringUtil;

@Controller
@RequestMapping("/each")
public class eachController {
	
	// �떆媛꾨�蹂�? �궗�슜�웾
	@RequestMapping("/eachTime")
	public String aptEachTime(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "each") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "eachtime") String subMenu,
			@RequestParam(value = "SeqSite", defaultValue = "85") String SeqSite,
			@RequestParam(value = "dateTarget", defaultValue = "") String dateTarget,
			@RequestParam(value = "seq_apt_dong", defaultValue = "112") String seq_apt_dong,
			@RequestParam(value = "seq_apt_ho", defaultValue = "6882") String seq_apt_ho) throws Exception {

		String moveUrl = "redirect:../";

		StringUtil stringUtil = new StringUtil();
		
		HashMap resultSiteCountMap = ManagerApi.GetSiteCount();
		int count_site = (Integer) resultSiteCountMap.get("count_site");

		int IndexFrom = 1;
		int IndexTo = 1000;

		HashMap siteMap = ManagerApi.GetSiteListForPaging(IndexFrom, count_site);

		List list_site = (List) siteMap.get("list_site");
		
		// �궇吏� �뵒�뤃�듃 媛�
		if ("".equals(dateTarget)) {
			dateTarget = stringUtil.getFullYearYYYY() + "-" + stringUtil.getMonthMM() + "-" + stringUtil.getDayDD();
		}

		String[] dateArr = dateTarget.split("-");

		if ("".equals(SeqSite)) {
			HashMap seqSiteMap = (HashMap) list_site.get(0);
			SeqSite = String.valueOf(seqSiteMap.get("seq_site"));
		}
		
		if (!"".equals(seq_apt_ho)) {

			HashMap apt_dong_list = ManagerApi.getDongListBySite(SeqSite);
			List list_dong = (List) apt_dong_list.get("list_dong");

//		if ("".equals(seq_apt_dong)) {
//			HashMap aptDongMap = (HashMap) list_dong.get(0);
//			seq_apt_dong = String.valueOf(aptDongMap.get("seq_dong"));
//		}

		// �샇 紐⑸�? 議고?��
		HashMap apt_ho_list = ManagerApi.GetHoListByDong(seq_apt_dong);
		//System.out.println("apt_ho_list = " + apt_ho_list);

		List list_ho = (List) apt_ho_list.get("list_ho");

//		if ("".equals(seq_apt_ho)) {
//			HashMap aptHoMap = (HashMap) list_ho.get(0);
//			seq_apt_ho = String.valueOf(aptHoMap.get("seq_ho"));
//		}

		// �븘�뙆�듃 �떒吏� �꽭��蹂� 1�씪 �궗�슜�웾 紐⑸줉�?�� 媛��졇�삷
		HashMap usageListMap = ManagerApi.GetUsageListHourly(seq_apt_ho, dateTarget.replaceAll("-", ""));
		//System.out.println("seq_apt_ho = " + seq_apt_ho);
		ArrayList list_usage = (ArrayList) usageListMap.get("list_usage");
		//System.out.println("list_usage = " + list_usage);

		
		model.addAttribute("LIST_DONG", list_dong);
		model.addAttribute("LIST_HO", list_ho);
		
		model.addAttribute("LIST_USAGE", list_usage);

		model.addAttribute("SEQ_APT_DONG", seq_apt_dong);
		model.addAttribute("SEQ_APT_HO", seq_apt_ho);

		model.addAttribute("mainMenu", mainMenu);
		model.addAttribute("subMenu", subMenu);
		
		}
		
		model.addAttribute("SEQSITE", SeqSite);
		model.addAttribute("LIST_SITE", list_site);
		model.addAttribute("DATETARGET", dateTarget);
		
		moveUrl = "/each/aptEachTime";

		return moveUrl;

	}
	
	// �궇吏쒕?? �궗�슜�웾
	@RequestMapping("/eachDay")
	public String aptEachDay(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "each") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "eachday") String subMenu,
			@RequestParam(value = "SeqSite", defaultValue = "85") String SeqSite,
			@RequestParam(value = "dateFrom", defaultValue = "") String dateFrom,
			@RequestParam(value = "dateTo", defaultValue = "") String dateTo, 
			@RequestParam(value="seq_apt_dong", defaultValue = "112") String seq_apt_dong,
			@RequestParam(value="seq_apt_ho", defaultValue = "6882") String seq_apt_ho) throws Exception {

		String moveUrl = "redirect:../";
		
		StringUtil stringUtil = new StringUtil();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		HashMap resultSiteCountMap = ManagerApi.GetSiteCount();
		int count_site = (Integer) resultSiteCountMap.get("count_site");

		int IndexFrom = 1;
		int IndexTo = 1000;

		HashMap siteMap = ManagerApi.GetSiteListForPaging(IndexFrom, count_site);

		List list_site = (List) siteMap.get("list_site");

		if ("".equals(SeqSite)) {
			HashMap seqSiteMap = (HashMap) list_site.get(0);
			SeqSite = String.valueOf(seqSiteMap.get("seq_site"));
		}
		
		// �궇吏� �뵒�뤃�듃 媛�
		if ("".equals(dateFrom)) {
			dateFrom = stringUtil.getFullYearYYYY() + "-" + stringUtil.getMonthMM() + "-"
					+ stringUtil.getDayDD();

			Date date = df.parse(dateFrom);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, -31);

			dateFrom = df.format(c.getTime());
		}
		
		//System.out.println("dateFrom = " + dateFrom);

		if ("".equals(dateTo)) {

			dateTo = stringUtil.getFullYearYYYY() + "-" + stringUtil.getMonthMM() + "-" + stringUtil.getDayDD();

			Date d = df.parse(dateTo);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(Calendar.DATE, -1);

			dateTo = df.format(c.getTime());
		}
		
		//System.out.println("dateTo = " + dateTo);
		
		//�룞 紐⑸�? 議고?��
		HashMap apt_dong_list = ManagerApi.getDongListBySite(SeqSite);
		List list_dong = (List) apt_dong_list.get("list_dong");

		if ("".equals(seq_apt_dong)) {
			HashMap aptDongMap = (HashMap) list_dong.get(0);
			seq_apt_dong = String.valueOf(aptDongMap.get("seq_dong"));
		}

		// �샇 紐⑸�? 議고?��
		HashMap apt_ho_list = ManagerApi.GetHoListByDong(seq_apt_dong);
		//System.out.println("apt_ho_list = " + apt_ho_list);

		List list_ho = (List) apt_ho_list.get("list_ho");

		if ("".equals(seq_apt_ho)) {
			HashMap aptHoMap = (HashMap) list_ho.get(0);
			seq_apt_ho = String.valueOf(aptHoMap.get("seq_ho"));
		}
		
		HashMap usageListMap = ManagerApi.getUsageListDaily(seq_apt_ho, dateFrom.replaceAll("-", ""), dateTo.replaceAll("-", ""));
		ArrayList list_usage = (ArrayList) usageListMap.get("list_usage");
		//System.out.println("list_usage = " + list_usage);
		
		moveUrl = "/each/aptEachDay.tiles";
		
		model.addAttribute("LIST_SITE", list_site);
		model.addAttribute("LIST_DONG", list_dong);
		model.addAttribute("LIST_HO", list_ho);
		model.addAttribute("SEQSITE", SeqSite);
		model.addAttribute("LIST_USAGE", list_usage);

		model.addAttribute("DATEFROM", dateFrom);
		model.addAttribute("DATETO", dateTo);

		model.addAttribute("SEQ_APT_DONG", seq_apt_dong);
		model.addAttribute("SEQ_APT_HO", seq_apt_ho);

		model.addAttribute("mainMenu", mainMenu);
		model.addAttribute("subMenu", subMenu);

		
		return moveUrl;
	}
	
	// �썡蹂� �궗�슜�웾
	@RequestMapping("/eachMonth")
	public String aptEachMonth(HttpSession session, Model model,
			@RequestParam(value = "mainMenu", defaultValue = "each") String mainMenu,
			@RequestParam(value = "subMenu", defaultValue = "eachmonth") String subMenu,
			@RequestParam(value = "SeqSite", defaultValue = "85") String SeqSite,
			@RequestParam(value = "seq_apt_dong", defaultValue = "112") String seq_apt_dong,
			@RequestParam(value = "seq_apt_ho", defaultValue = "6882") String seq_apt_ho,
			@RequestParam(value = "monthFrom", defaultValue = "") String monthFrom,
			@RequestParam(value = "monthTo", defaultValue = "") String monthTo) throws Exception {

		String moveUrl = "redirect:../";

		StringUtil stringUtil = new StringUtil();
		DateFormat df = new SimpleDateFormat("yyyy-MM");

		HashMap resultSiteCountMap = ManagerApi.GetSiteCount();
		int count_site = (Integer) resultSiteCountMap.get("count_site");

		int IndexFrom = 1;
		int IndexTo = 1000;

		HashMap siteMap = ManagerApi.GetSiteListForPaging(IndexFrom, count_site);

		List list_site = (List) siteMap.get("list_site");

		if ("".equals(monthFrom)) {
			monthFrom = stringUtil.getFullYearYYYY() + "-" + stringUtil.getMonthMM();

			Date date = df.parse(monthFrom);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MONTH, -11);

			monthFrom = df.format(c.getTime());
		}

		if ("".equals(monthTo)) {
			monthTo = stringUtil.getFullYearYYYY() + "-" + stringUtil.getMonthMM();
		}

		if ("".equals(SeqSite)) {
			HashMap seqSiteMap = (HashMap) list_site.get(0);
			SeqSite = String.valueOf(seqSiteMap.get("seq_site"));
		}

		if (!"".equals(seq_apt_ho)) {

			HashMap apt_dong_list = ManagerApi.getDongListBySite(SeqSite);
			List list_dong = (List) apt_dong_list.get("list_dong");

			// �샇 紐⑸�? 議고?��
			HashMap apt_ho_list = ManagerApi.GetHoListByDong(seq_apt_dong);
			// System.out.println("apt_ho_list = " + apt_ho_list);

			List list_ho = (List) apt_ho_list.get("list_ho");

			HashMap usageListMap = ManagerApi.getUsageListMonthly(seq_apt_ho, monthFrom.replaceAll("-", ""), monthTo.replaceAll("-", ""));
			ArrayList list_usage = (ArrayList) usageListMap.get("list_usage");

			model.addAttribute("LIST_DONG", list_dong);
			model.addAttribute("LIST_HO", list_ho);

			model.addAttribute("LIST_USAGE", list_usage);

			model.addAttribute("SEQ_APT_DONG", seq_apt_dong);
			model.addAttribute("SEQ_APT_HO", seq_apt_ho);

			model.addAttribute("mainMenu", mainMenu);
			model.addAttribute("subMenu", subMenu);

		}

		model.addAttribute("SEQSITE", SeqSite);
		model.addAttribute("LIST_SITE", list_site);
		model.addAttribute("MONTHFROM", monthFrom);
		model.addAttribute("MONTHTO", monthTo);

		moveUrl = "/each/aptEachMonth.tiles";

		return moveUrl;
	}
		
		

}
