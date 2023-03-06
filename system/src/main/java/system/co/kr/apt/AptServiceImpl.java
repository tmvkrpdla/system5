package system.co.kr.apt;

import java.util.HashMap;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class AptServiceImpl implements AptService {
	
	@Inject
	private AptDAO aptDAO;
	
	@Override
	public HashMap insertCoordinate(HashMap hashMap) throws Exception {
		
		return aptDAO.insertCoordinate(hashMap);
	}

}
