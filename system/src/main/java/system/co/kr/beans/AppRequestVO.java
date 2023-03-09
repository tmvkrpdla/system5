package system.co.kr.beans;

import java.util.ArrayList;
import java.util.List;

public class AppRequestVO{
	
    
    public List<String> getAcceptList() {
		return acceptList;
	}
	public void setAcceptList(List<String> acceptList) {
		this.acceptList = acceptList;
	}
	public List<String> getRejectList() {
		return rejectList;
	}
	public void setRejectList(List<String> rejectList) {
		this.rejectList = rejectList;
	}
	@Override
	public String toString() {
		return "AppRequestVO [acceptList=" + acceptList + ", rejectList=" + rejectList + "]";
	}
	private List<String> acceptList = new ArrayList<String>();
    private List<String> rejectList= new ArrayList<String>();
   
    
}
