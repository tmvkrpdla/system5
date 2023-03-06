package system.co.kr.dto;

import java.util.List;

public class ManagerVO {
	
	private String dong_name;
	private String ho_name;
	private String seq_dong;
	private String seq_ho;
	private String meter_id;
	private String seq_meter;
	private String dcu_id;
	private String time_lp;
	private String fap;
	
	private List<ManagerVO> managerVOList;
	
	
	
	public String getDong_name() {
		return dong_name;
	}
	public void setDong_name(String dong_name) {
		this.dong_name = dong_name;
	}
	public String getHo_name() {
		return ho_name;
	}
	public void setHo_name(String ho_name) {
		this.ho_name = ho_name;
	}
	public String getSeq_dong() {
		return seq_dong;
	}
	public void setSeq_dong(String seq_dong) {
		this.seq_dong = seq_dong;
	}
	public String getSeq_ho() {
		return seq_ho;
	}
	public void setSeq_ho(String seq_ho) {
		this.seq_ho = seq_ho;
	}
	public String getMeter_id() {
		return meter_id;
	}
	public void setMeter_id(String meter_id) {
		this.meter_id = meter_id;
	}
	public String getSeq_meter() {
		return seq_meter;
	}
	public void setSeq_meter(String seq_meter) {
		this.seq_meter = seq_meter;
	}
	public String getDcu_id() {
		return dcu_id;
	}
	public void setDcu_id(String dcu_id) {
		this.dcu_id = dcu_id;
	}
	public String getTime_lp() {
		return time_lp;
	}
	public void setTime_lp(String time_lp) {
		this.time_lp = time_lp;
	}
	public String getFap() {
		return fap;
	}
	public void setFap(String fap) {
		this.fap = fap;
	}
	
	
	
	public List<ManagerVO> getManagerVOList() {
		return managerVOList;
	}
	public void setManagerVOList(List<ManagerVO> managerVOList) {
		this.managerVOList = managerVOList;
	}
	@Override
	public String toString() {
		return "ManagerVO [dong_name=" + dong_name + ", ho_name=" + ho_name + ", seq_dong=" + seq_dong + ", seq_ho="
				+ seq_ho + ", meter_id=" + meter_id + ", seq_meter=" + seq_meter + ", dcu_id=" + dcu_id + ", time_lp="
				+ time_lp + ", fap=" + fap + "]";
	}
	
	

}
