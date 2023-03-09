package system.co.kr.apt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import system.co.kr.beans.AppRequestVO;
import system.co.kr.beans.networkVO;
import system.co.kr.beans.networkVOUpd;
import system.co.kr.inter.ServicesRule;
import system.co.kr.util.ManagerApi;
import system.co.kr.util.PageUtil;

@Service
public class Apt implements ServicesRule {
	
	
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	
	
	public void backController(String serviceCode, ModelAndView mav) {
		System.out.println("backControllerMAV");
		

		//세션 유무 확인 및 세션의 유저타입 유저코드 저장
		
		switch(serviceCode) {
		case "aptNetwork":
			this.aptNetwork(mav);
			break;
		case "addNetworkForm":
			this.addNetworkForm(mav);
			break;
		case "delNetwork":
			this.delNetwork(mav);
			break;
		case "badMeters":
			this.badMeters(mav);
			break;
		case "getLpListByMid":
			this.getLpListByMid(mav);
			break;	
		case "appRequest":
			this.appRequest(mav);
			break;	
		case "appRequestSend":
			this.appRequestSend(mav);
			break;		
		default:
		}
	}
	
	


	public void backController(String serviceCode, Model model) {
		System.out.println("backControllerModel");
		

		//세션 유무 확인 및 세션의 유저타입 유저코드 저장
		
		switch(serviceCode) {
		case "moveDashboard":
//			this.moveDashboard(mav);
			break;

		default:
		}
	}
	
	// mav 방식 메서드 
	
	
	private void aptNetwork(ModelAndView mav) {
		List<Map<String,Object>>  getNetworkInfo = (List)sessionTemplate.selectList("getNetworkInfo");
		ArrayList<networkVO> networkInfoList = new ArrayList<networkVO>();
		mav.addObject("mainMenu", "apt");
		mav.addObject("subMenu", "aptNetwork");
		
		for(int i=0; i < getNetworkInfo.size() ; i++) {
				networkVO n = new networkVO();
				n.setnSeqSite(getNetworkInfo.get(i).get("nSeqSite").toString());
				n.setSiteCode((String) getNetworkInfo.get(i).get("SiteCode"));
				n.setName((String) getNetworkInfo.get(i).get("Name"));
				n.setnSiteState((String) getNetworkInfo.get(i).get("nSiteState").toString());
				if(i == 0) {
					n.setindex(0);
					networkInfoList.add(n);
				}else{
					boolean checkNSeqSite = false;
					
					for(int j=0; j < networkInfoList.size() ; j++)
						if(networkInfoList.get(j).getnSeqSite().equals(n.getnSeqSite())) {
							checkNSeqSite = true;
						}
					
					if(!checkNSeqSite) {
						n.setindex(networkInfoList.size());
						
						networkInfoList.add(n);
					}
				}				
			}
		for(int i=0; i < networkInfoList.size() ; i++) {
			for(int j=0; j < getNetworkInfo.size() ; j++) {
				if(getNetworkInfo.get(j).get("Name").equals(networkInfoList.get(i).getName())) {
					try { 
						networkInfoList.get(i).getLocation().add(getNetworkInfo.get(j).get("location").toString());
						networkInfoList.get(i).getDcuid().add(getNetworkInfo.get(j).get("dcuid").toString());
						networkInfoList.get(i).getnPortSsh2().add(getNetworkInfo.get(j).get("nPortSsh2").toString());
						networkInfoList.get(i).getNport().add(getNetworkInfo.get(j).get("nport").toString());
						networkInfoList.get(i).getnPortSnmp().add(getNetworkInfo.get(j).get("nPortSnmp").toString());
						networkInfoList.get(i).getAddressInfo().add(getNetworkInfo.get(j).get("AddressInfo").toString());
						networkInfoList.get(i).getLteSn().add(getNetworkInfo.get(j).get("LteSn").toString());
						networkInfoList.get(i).getIp().add(getNetworkInfo.get(j).get("ip").toString());
						networkInfoList.get(i).getComment().add(getNetworkInfo.get(j).get("Comment").toString());	
			        } catch (NullPointerException  e) {
			            System.out.println("들어오는 배열값이 널입니다.");
			        }catch(IndexOutOfBoundsException e) {
			         }
				}
			}
		}
		
		// 스테이트값이 1인 리스트
		List<networkVO> networkInfoList2 = new ArrayList<networkVO>();
		List<networkVO> networkInfoList3 = new ArrayList<networkVO>();
		
		
		for(int i=0 ;i < networkInfoList.size(); i++) {
			if(networkInfoList.get(i).getnSiteState().equals("1")) {
				networkInfoList2.add(networkInfoList.get(i)); // 
				networkInfoList2.get(networkInfoList2.size()-1).setindex(networkInfoList2.size()-1);
			}else {
				networkInfoList3.add(networkInfoList.get(i)); // 
				networkInfoList3.get(networkInfoList3.size()-1).setindex(networkInfoList3.size()-1);
			}
		}
		
		List<networkVO> list_site = (List<networkVO>) networkInfoList2;
		List<networkVO> list_site2 = (List<networkVO>) networkInfoList3;
		
		Gson gson = new Gson();
		
			networkVO a = new networkVO();
			JsonObject json2 = gson.toJsonTree(a).getAsJsonObject();
			JsonArray json3 = gson.toJsonTree(list_site2).getAsJsonArray();
			
			
			JsonArray json4 = gson.toJsonTree(list_site).getAsJsonArray();
			 
			mav.addObject("isFirst", true);
			mav.addObject("LIST_SITE", list_site);
			mav.addObject("LIST_DCU", json2);
			mav.addObject("LIST_SITE2", json3);
			mav.addObject("LIST_SITE3", json4);  
			mav.addObject("mainMenu", "apt");
			mav.addObject("subMenu", "aptNetwork");
			mav.addObject("action", null);

			mav.setViewName("apt/aptNetwork");
			
		
	};
	
	private void addNetworkForm(ModelAndView mav) {
		
		networkVO networtVO = (networkVO) mav.getModel().get("networtVO");
		List <networkVOUpd> networkVOUpdList = new ArrayList<networkVOUpd>();
		
		
		for(int i = 0; i < networtVO.getDcuid().size() ;i++ ) {
			networkVOUpd networkVOUpd = new networkVOUpd();
			
			networkVOUpd.setDcuid(networtVO.getDcuid().get(i));
			networkVOUpd.setLocation(networtVO.getLocation().get(i));
			networkVOUpd.setnPortSsh2( Integer.parseInt(networtVO.getnPortSsh2().get(i)));
			networkVOUpd.setAddressInfo(networtVO.getAddressInfo().get(i));
			networkVOUpd.setLteSn(networtVO.getLteSn().get(i));
			networkVOUpd.setComment(networtVO.getComment().get(i));
			
			if(networtVO.getIp().size() != 0) {
				networkVOUpd.setIp(networtVO.getIp().get(i));
			}
			
			networkVOUpdList.add(networkVOUpd);
		}
		
		sessionTemplate.update("updDcuInfo", networkVOUpdList);
		
		this.aptNetwork(mav);
		mav.addObject("action", networtVO.getAction());
		
	}
	
	private void delNetwork(ModelAndView mav) {
		String nSeqSite =(String) mav.getModel().get("nSeqSite");
		sessionTemplate.delete("delNetwork", nSeqSite);
		this.aptNetwork(mav);
		mav.addObject("action", "del");
		
	}
	private void badMeters(ModelAndView mav)  {
		HashMap apiData = null;
		try {
			apiData = ManagerApi.collectBadMeters();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	private void getLpListByMid(ModelAndView mav)  {
		
		HashMap apiData = null;
		try {
			apiData = ManagerApi.getLpListByMid((String)mav.getModel().get("Mid"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List list_lp = (List) apiData.get("list_lp");
		HashMap ho_info = (HashMap) apiData.get("ho_info");

		mav.addObject("list_lp", list_lp);
		mav.addObject("total", list_lp.size());
		mav.addObject("ho_info", ho_info);

		mav.setViewName("apt/getLp");
	}
	
	// model 방식 메서드
	
	
	//입주민 앱승인
	private void appRequest(ModelAndView mav) {
		
		String SeqSite = (String)mav.getModel().get("SeqSite");
		int nowPage = (int)mav.getModel().get("nowPage");
		HashMap resultSiteCountMap;
		List list_site = null;
		try {
			resultSiteCountMap = ManagerApi.GetSiteCount();
			int count_site = (Integer) resultSiteCountMap.get("count_site");

			int IndexFrom = 1;
			int IndexTo = 1000;

			HashMap siteMap = ManagerApi.GetSiteListForPaging(IndexFrom, count_site);

			list_site = (List) siteMap.get("list_site");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ("".equals(SeqSite)) {
			mav.addObject("LIST_SITE", list_site);
		} else {
			
			
			HashMap CountMap = null;
			List list_ho = null;
			PageUtil pageUtil = null;
			try {
				CountMap = ManagerApi.getAppRequestCount(SeqSite);
				int count_ho = (Integer) CountMap.get("count_ho");
				pageUtil = new PageUtil(nowPage, count_ho, 30);
				HashMap hoMap = ManagerApi.getAppRequestListForPaging(SeqSite, pageUtil.getStartNum(), pageUtil.getEndNum());
				list_ho = (List) hoMap.get("list_ho");
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mav.addObject("LIST_SITE", list_site);
			mav.addObject("LIST_HO", list_ho);
			mav.addObject("SEQSITE", SeqSite);
			mav.addObject("nowPage", nowPage);

		}
		mav.addObject("mainMenu", "apt");
		mav.addObject("subMenu", "appRequestLGJ");
		mav.setViewName("apt/appRequestLGJ");
	}

	
	
	
	private void appRequestSend(ModelAndView mav) {
		AppRequestVO appRequestVO = (AppRequestVO)mav.getModel().get("appRequestVO");
		List<String> acceptList = appRequestVO.getAcceptList();
		List<String> rejectList = appRequestVO.getRejectList();
		
		String accept = "";
		String reject = "";
		List <String[]> rejectComment = new ArrayList<>();
		
		if(acceptList!=null) {
			for(int i=0; i <acceptList.size(); i++ ) {
				if(i==0) {
					accept+=acceptList.get(i);
				}else {
					accept+= ","+ acceptList.get(i);
				}
			}
		}
		if(rejectList!=null) {
			for(int i=0; i <rejectList.size(); i++ ) {
				if(rejectList.get(i).contains(":")) {
					String[] rejectArray = rejectList.get(i).split(":");
					rejectList.set(i, rejectArray[0]);
					rejectComment.add(rejectArray);
				}else {
					String[] rejectArray = new String[2];
					rejectArray[0] = rejectList.get(i);
					rejectArray[1] = "";
					rejectList.set(i, rejectList.get(i));
					rejectComment.add(rejectArray);
				}
				
				if(i==0) {
					reject+=rejectList.get(i);
				}else {
					reject+= ","+ rejectList.get(i);
				}
			}
		}
		
		System.out.println("accept : "+accept);
		System.out.println("reject : "+reject);
		
		try {
			ManagerApi.setAppRequestsAsAccepted(accept);
			ManagerApi.setAppRequestsAsRejected(reject);
			
//			for (int i=0;i<acceptList.size();i++ ) {
//				ManagerApi.SetAppRequestComment(acceptList.get(i) ,"");
//			}
//			for (int i = 0; i < rejectComment.size(); i++) {
//				if (rejectComment.get(i)[1].equals("")) {
//					rejectComment.get(i)[1] = "";
//				}
//				ManagerApi.SetAppRequestComment(rejectComment.get(i)[0], rejectComment.get(i)[1]);
//				System.out.println("seq_ho : " + rejectComment.get(i)[0] + " Comment : " + rejectComment.get(i)[1]);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int nowPage = (int)mav.getModel().get("nowPage");
		
		System.out.println("nowPage : " + nowPage);
		
		this.appRequest(mav);
		
		
	}
	
	
	
	
	
}