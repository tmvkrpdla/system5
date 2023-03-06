package system.co.kr.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import system.co.kr.dto.TestVO;
import system.co.kr.dto.siteListVO;

@Repository
public class TestDAOImpl implements TestDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "system.co.kr.mappers.testMapper";
	
	@Override
	public List<TestVO> selectDcu() throws Exception {
		
		return sqlSession.selectList(Namespace+ ".selectDcu");
	}
	
	@Override
	public List<TestVO> siteList() throws Exception {
		
		return sqlSession.selectList(Namespace+ ".siteList");
	}

	@Override
	public List<TestVO> addOpenSiteDcu() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+ ".addOpenSiteDcu");
	}

	@Override
	public List<TestVO> updateDcu(List<TestVO> arrayTestVO) throws Exception {
		// TODO Auto-generated method stub 
		return sqlSession.selectList(Namespace+ ".updateDcu");
	}
	


}
