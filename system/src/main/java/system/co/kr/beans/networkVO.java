package system.co.kr.beans;

import java.util.ArrayList;
import java.util.List;

public class networkVO{
	private String nSeqSite = "";
    private String SiteCode = "";
    private String Name = "";
    private String nSiteState = "";
    private int index = -1;
    private String action ="";
    
    private List<String> location = new ArrayList<String>();
    private List<String> dcuid= new ArrayList<String>();
    private List<String> nPortSsh2= new ArrayList<String>();
    private List<String> nport= new ArrayList<String>();
    private List<String> nPortSnmp= new ArrayList<String>();
    private List<String> AddressInfo= new ArrayList<String>();
    private List<String> LteSn= new ArrayList<String>();
    private List<String> ip= new ArrayList<String>();
    private List<String> Comment = new ArrayList<String>();
    
    
    
    
    @Override
	public String toString() {
		return "networkVO [nSeqSite=" + nSeqSite + ", SiteCode=" + SiteCode + ", Name=" + Name + ", nSiteState="
				+ nSiteState + ", index=" + index + ", action=" + action + ", location=" + location + ", dcuid=" + dcuid
				+ ", nPortSsh2=" + nPortSsh2 + ", nport=" + nport + ", nPortSnmp=" + nPortSnmp + ", AddressInfo="
				+ AddressInfo + ", LteSn=" + LteSn + ", ip=" + ip + ", Comment=" + Comment + "]";
	}
	public int getindex() {
		return index;
	}
	public void setindex(int index) {
		this.index = index;
	}
    
    
	public String getnSiteState() {
		return nSiteState;
	}
	public void setnSiteState(String nSiteState) {
		this.nSiteState = nSiteState;
	}
	
	public String getnSeqSite() {
		return nSeqSite;
	}
	public void setnSeqSite(String nSeqSite) {
		this.nSeqSite = nSeqSite;
	}
	public String getSiteCode() {
		return SiteCode;
	}
	public void setSiteCode(String siteCode) {
		SiteCode = siteCode;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public List<String> getLocation() {
		return location;
	}
	public void setLocation(List<String> location) {
		this.location = location;
	}
	public List<String> getDcuid() {
		return dcuid;
	}
	public void setDcuid(List<String> dcuid) {
		this.dcuid = dcuid;
	}
	public List<String> getnPortSsh2() {
		return nPortSsh2;
	}
	public void setnPortSsh2(List<String> nPortSsh2) {
		this.nPortSsh2 = nPortSsh2;
	}
	public List<String> getnPortSnmp() {
		return nPortSnmp;
	}
	public void setnPortSnmp(List<String> nPortSnmp) {
		this.nPortSnmp = nPortSnmp;
	}
	public List<String> getNport() {
		return nport;
	}
	public void setNport(List<String> nport) {
		this.nport = nport;
	}
	public List<String> getAddressInfo() {
		return AddressInfo;
	}
	public void setAddressInfo(List<String> addressInfo) {
		AddressInfo = addressInfo;
	}
	public List<String> getLteSn() {
		return LteSn;
	}
	public void setLteSn(List<String> lteSn) {
		LteSn = lteSn;
	}
	public List<String> getIp() {
		return ip;
	}
	public void setIp(List<String> ip) {
		this.ip = ip;
	}
	public List<String> getComment() {
		return Comment;
	}
	public void setComment(List<String> comment) {
		Comment = comment;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}
    
    
    
    
}
