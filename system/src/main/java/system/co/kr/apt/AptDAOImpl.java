package system.co.kr.apt;

import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class AptDAOImpl implements AptDAO{
	
	private String namespace = "aptMapper";
	
	@Inject
	private SqlSession sql;
	
	
	@Override
	public HashMap insertCoordinate(HashMap hashMap) throws Exception {
		
		return sql.selectOne(namespace+".insertCoordinate", hashMap);
	}

}
