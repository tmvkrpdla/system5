package system.co.kr.dto;

import java.util.List;

public class TestVO {
	
	private String nSeqDcu;
	private String siteName;
	private String dongName;
	private String dcuId;
	private String nPortSsh2;
	private String nFepTrapPort;
	private String nSnmpTrapPort;
	private String addressInfo;
	private String lteSn;
	private String ip;
	private String comment;
	private String nSeqSite;
	
	
	@Override
	public String toString() {
		return "TestVO [nSeqDcu=" + nSeqDcu + ", siteName=" + siteName + ", dongName=" + dongName + ", dcuId=" + dcuId
				+ ", nPortSsh2=" + nPortSsh2 + ", nFepTrapPort=" + nFepTrapPort + ", nSnmpTrapPort=" + nSnmpTrapPort
				+ ", addressInfo=" + addressInfo + ", lteSn=" + lteSn + ", ip=" + ip + ", comment=" + comment
				+ ", nSeqSite=" + nSeqSite + "]";
	}
	
	public String getnSeqDcu() {
		return nSeqDcu;
	}
	public void setnSeqDcu(String nSeqDcu) {
		this.nSeqDcu = nSeqDcu;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getDcuId() {
		return dcuId;
	}
	public void setDcuId(String dcuId) {
		this.dcuId = dcuId;
	}
	public String getnPortSsh2() {
		return nPortSsh2;
	}
	public void setnPortSsh2(String nPortSsh2) {
		this.nPortSsh2 = nPortSsh2;
	}
	public String getnFepTrapPort() {
		return nFepTrapPort;
	}
	public void setnFepTrapPort(String nFepTrapPort) {
		this.nFepTrapPort = nFepTrapPort;
	}
	public String getnSnmpTrapPort() {
		return nSnmpTrapPort;
	}
	public void setnSnmpTrapPort(String nSnmpTrapPort) {
		this.nSnmpTrapPort = nSnmpTrapPort;
	}
	public String getAddressInfo() {
		return addressInfo;
	}
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
	public String getLteSn() {
		return lteSn;
	}
	public void setLteSn(String lteSn) {
		this.lteSn = lteSn;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getnSeqSite() {
		return nSeqSite;
	}

	public void setnSeqSite(String nSeqSite) {
		this.nSeqSite = nSeqSite;
	}


}
