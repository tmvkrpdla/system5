package system.co.kr.beans;



public class networkVOUpd{
	
	private String location = new String();
	private int nPortSsh2= 0;
    private String AddressInfo= new String();
    private String LteSn= new String();
    private String ip= new String();
    private String Comment = new String();
    private String dcuid= new String();
    
    @Override
	public String toString() {
		return "networkVOUpd [location=" + location + ", nPortSsh2=" + nPortSsh2 + ", AddressInfo=" + AddressInfo
				+ ", LteSn=" + LteSn + ", ip=" + ip + ", Comment=" + Comment + ", dcuid=" + dcuid + "]";
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDcuid() {
		return dcuid;
	}
	public void setDcuid(String dcuid) {
		this.dcuid = dcuid;
	}
	public int getnPortSsh2() {
		return nPortSsh2;
	}
	public void setnPortSsh2(int nPortSsh2) {
		this.nPortSsh2 = nPortSsh2;
	}
	
	public String getAddressInfo() {
		return AddressInfo;
	}
	public void setAddressInfo(String addressInfo) {
		AddressInfo = addressInfo;
	}
	public String getLteSn() {
		return LteSn;
	}
	public void setLteSn(String lteSn) {
		LteSn = lteSn;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	
   
    
    
    
    
}
