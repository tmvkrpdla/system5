package system.co.kr.service;

import java.util.List;

import system.co.kr.dto.TestVO;
import system.co.kr.dto.siteListVO;

public interface TestService {
	
//	List<TestVO> TestVO();
	
	public List<TestVO> selectDcu() throws Exception;
	
	public List<TestVO> siteList() throws Exception;
	
	public List<TestVO> addOpenSiteDcu() throws Exception;
	
	public List<TestVO> updateDcu(List<TestVO> arrayTestVO) throws Exception;
	
	

}
