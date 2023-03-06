package system.co.kr.util;

import java.util.ArrayList;
import java.util.HashMap;

public class ApiUtil {
	
	/**
	 * ������� ����Ʈ�� ��ȯ
	 * @param memberList
	 * @return
	 */
	public static ArrayList getDirMember(ArrayList memberList) {
		ArrayList returnList = new ArrayList();
		
		for(int i=0; i<memberList.size(); i++) {
			HashMap tempMap = (HashMap) memberList.get(i);
			int member_type = (Integer) tempMap.get("member_type");	//	���� ���� : 1, �Ϲ� �۾��� : 2
			
			if(member_type == 2) {
				continue;
			}
			
			
			//System.out.println(tempMap.toString());
			returnList.add(tempMap);
		}
		
		return returnList;
	}
	
	
//	==	��������	==
	/**
	 * �ְ��� �������� ���� ���� üũ �ϴ� �Լ�
	 * list_str_answer ����  �� ������ �� �� ����
	 * @param survMap
	 * @return
	 */
	public static HashMap checkSurv(HashMap survMap) {
		HashMap returnMap = survMap;
		
		ArrayList listQuestion = (ArrayList) returnMap.get("list_question");
		for(int i=0; i<listQuestion.size(); i++) {
			HashMap questionMap =  (HashMap) listQuestion.get(i);
			int question_type = (Integer) questionMap.get("question_type");
			
			if(question_type == 3) {
				int cnt = 0;
				ArrayList answerList = (ArrayList) questionMap.get("list_str_answer");
				for(int j=0; j<answerList.size();j++) {
					String answer = String.valueOf(answerList.get(j));
					
					if(!"".equals(answer)) {
						cnt++;
					}
					
				}//	end of for j 
				
				questionMap.put("mod_answered_member_count", cnt);
			}//	end of if
			
		}//	end of i	
		
		
		return returnMap;
	}
	
	
}
