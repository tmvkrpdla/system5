package system.co.kr.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StringUtil {
	
	/**
	 * ����⵵�� ���ϴ� �Լ�	String
	 * ex) 2020
	 * @return
	 */
	public String getFullYearYYYY() {
		return String.valueOf(getFullYearInt()); 
	}
	
	/**
	 * ����⵵�� ���ϴ� �Լ�	int
	 * @return
	 */
	public int getFullYearInt() {
		Calendar cal = Calendar.getInstance();
		return cal.get(cal.YEAR);
	}
	
	
	/**
	 * ���� ���� ���ϴ� �Լ�	
	 * ex) 2�ڸ��̸�, 1~9���ϰ�� 0�� ���� 09
	 * @return
	 */
	public String getMonthMM() {
		int month = getMonthInt();
		String temp = "";
		
		if(month<10) {
			temp = "0"+month;
		}else {
			temp = ""+month;
		}
		
		return temp;
	}
	
	
	/**
	 * ���� ���� ���ϴ� �Լ�
	 * ex) 1��~9���� 1�ڸ� // 10~12���� 2�ڸ�	
	 * @return
	 */
	public String getMonthString() {
		
		return String.valueOf(getMonthInt());
	}
	
	/**
	 * ���� ���� ���ϴ� �Լ�
	 * ex) 1��~9���� 1�ڸ� // 10~12���� 2�ڸ�
	 * @return
	 */
	public int getMonthInt() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get (cal.MONTH) + 1 ;
		return month;
	}
	
	/**
	 * ���� ���� ���ϴ� �Լ�
	 * 1~9�� 1�ڸ�
	 * @return
	 */
	public int getDayInt() {
		Calendar cal = Calendar.getInstance();
		return cal.get(cal.DATE);
	}
	
	/**
	 * ���� ���� ���ϴ� �Լ�
	 * 1~9�� 0�� �ٿ��� 01~09 �̸�, �������� 2�ڸ�
	 * @return
	 */
	public String getDayDD() {
		int date = getDayInt();
		String temp = "";
		if(date<10) {
			temp = "0"+date;
		}else {
			temp = ""+date;
		}
		return temp;
	}
	
	/**
	 * ���ó��ڸ� ���ϴ� �Լ�
	 * YYYY-MM-DD
	 * @return
	 */
	public String getDateYYMMDD(){
		return getFullYearYYYY() + "-" + getMonthMM() + "-" + getDayDD();
	}

	/**
	 * ���ó�¥�� ���ϴ��Լ�
	 * YYYYMMDD
	 * @return
	 */
	public String getToday() {
		return getFullYearYYYY()  + getMonthMM() +  getDayDD();
	}
	
	/**
	 * 201810
	 * @return
	 */
	public String getTimeHHmmss() {
		SimpleDateFormat format1 = new SimpleDateFormat ( "HHmmss");
		String formatTime1 = format1.format (System.currentTimeMillis());	
		
		return formatTime1;
	}
	
	public String round3(String num) {
		BigDecimal bigDec= new BigDecimal(num);
	    DecimalFormat formatter = new DecimalFormat("0.00");
	    return formatter.format(bigDec);
		
	}
}
