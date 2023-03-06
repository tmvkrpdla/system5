package system.co.kr.excel;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import system.co.kr.util.SessionUtil;
import system.co.kr.util.StringUtil;

 
public class ExcelViewBadMeters extends AbstractExcelPOIView {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelViewBadMeters.class);
 
   // @SuppressWarnings("unchecked")
    
    
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, 
    	HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	
    	
    	logger.info("= = = = = EXCELTEST DOWN LOG START = = = = =");
		logger.info("fileName : " + model.get("fileName"));
		logger.info("= = = = = EXCEL DOWN LOG END = = = = =");
		
		System.out.println("list_bad_meter: " +  model.get("list_bad_meter"));
		System.out.println("list_bad_meter Name:" + model.get("list_bad_meter").getClass().getName());
		
		List list_bad_meter = (List) model.get("list_bad_meter");
		//System.out.println("list_fap = " + list_fap);
		
		Sheet sheet = workbook.createSheet();
		Row row = null;
		Cell cell = null;
		
		int rowCnt = 0;
		
		
		row = sheet.createRow(rowCnt++);	//	row == 0
		row.createCell(0).setCellValue("단지");
		row.createCell(1).setCellValue("동");
		row.createCell(2).setCellValue("호");
		row.createCell(3).setCellValue("MID");
		row.createCell(4).setCellValue("이상발생횟수");
		
		//	rowCnt++;
		
		for(int i=0; i<list_bad_meter.size(); i++) {
			Map meterMap = (Map) list_bad_meter.get(i);
			
			String site_name = String.valueOf(meterMap.get("site_name"));
			String dong_name = String.valueOf(meterMap.get("dong_name"));
			String ho_name = String.valueOf(meterMap.get("ho_name"));
			String Mid = String.valueOf(meterMap.get("Mid"));
			String count_minus = String.valueOf(meterMap.get("count_minus"));
			
			
			row = sheet.createRow(rowCnt++);
			
			row.createCell(0).setCellValue(site_name);
			row.createCell(1).setCellValue(dong_name);
			row.createCell(2).setCellValue(ho_name);
			row.createCell(3).setCellValue(Mid);
			row.createCell(4).setCellValue(count_minus);
			
			sheet.autoSizeColumn(i); // ����ũ�� �ڵ�����
			sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 ); //����ũ�� �ڵ� +512 ���ֱ�
			
		} // for end
    }

    @Override
    protected Workbook createWorkbook() {
        return new XSSFWorkbook();
    }

}
