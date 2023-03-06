package system.co.kr.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import system.co.kr.dao.TestDAO;
import system.co.kr.dto.TestVO;
import system.co.kr.dto.siteListVO;

@Service
public class TestServiceImple implements TestService {

	@Inject
	private TestDAO dao;

	@Override
	public List<TestVO> selectDcu() throws Exception {

		return dao.selectDcu();

	}

	@Override
	public List<TestVO> siteList() throws Exception {
		// TODO Auto-generated method stub
		return dao.siteList();
	}

	@Override
	public List<TestVO> addOpenSiteDcu() throws Exception {
		// TODO Auto-generated method stub
		return dao.addOpenSiteDcu();
	}

	@Override
	public List<TestVO> updateDcu(List<TestVO> arrayTestVO) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateDcu(arrayTestVO);
	}

	

}
