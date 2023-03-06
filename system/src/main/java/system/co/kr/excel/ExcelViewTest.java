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

 
public class ExcelViewTest extends AbstractExcelPOIView {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelViewTest.class);
 
   // @SuppressWarnings("unchecked")
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, 
    	HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	
    	logger.info("= = = = = EXCELTEST DOWN LOG START = = = = =");
		logger.info("fileName : " + model.get("fileName"));
		logger.info("= = = = = EXCEL DOWN LOG END = = = = =");
		
		Map meterringMap = (Map) model.get("METERINGMAP");
		List list_fap = (List) meterringMap.get("list_fap");
		//System.out.println("list_fap = " + list_fap);
		
		Sheet sheet = workbook.createSheet();
		Row row = null;
		Cell cell = null;
		
		int rowCnt = 0;
		
		row = sheet.createRow(rowCnt++);	//	row == 0
		row.createCell(0).setCellValue("동");
		row.createCell(1).setCellValue("호");
		row.createCell(2).setCellValue("계량기 ID");
		row.createCell(3).setCellValue("지침값");
		row.createCell(4).setCellValue("검침시간");
		
		//	rowCnt++;
		
		for(int i=0; i<list_fap.size(); i++) {
			Map meterMap = (Map) list_fap.get(i);
			
			String dong_name = String.valueOf(meterMap.get("dong_name"));
			String ho_name = String.valueOf(meterMap.get("ho_name"));
			String meter_id = String.valueOf(meterMap.get("meter_id"));
			String fap = String.valueOf(meterMap.get("fap"));
			String _fap = String.valueOf(fap.replaceAll("\\.0$", ""));
			String time_lp = String.valueOf(meterMap.get("time_lp"));
			
			row = sheet.createRow(rowCnt++);
			
			row.createCell(0).setCellValue(dong_name);
			row.createCell(1).setCellValue(ho_name);
			row.createCell(2).setCellValue(meter_id);
			row.createCell(3).setCellValue(_fap);
			row.createCell(4).setCellValue(time_lp);
			
			sheet.autoSizeColumn(i); // 엑셀크기 자동조절
			sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 ); //엑셀크기 자동 +512 해주기
			
		} // for end
		
    }
    
    @Override
    protected void buildExcelDocument2(Map<String, Object> model, Workbook workbook, HttpServletRequest request, 
    	HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	
    	logger.info("= = = = = EXCELTEST DOWN LOG START = = = = =");
		logger.info("fileName : " + model.get("fileName"));
		logger.info("= = = = = EXCEL DOWN LOG END = = = = =");
		
		Map data = (Map) model.get("list_bad_meter");
		List list_bad_meter = (List) data.get("list_bad_meter");
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
			
			String site_name = String.valueOf(meterMap.get("time_lp"));
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
			
			sheet.autoSizeColumn(i); // 엑셀크기 자동조절
			sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 ); //엑셀크기 자동 +512 해주기
			
		} // for end
		
    }

    @Override
    protected Workbook createWorkbook() {
        return new XSSFWorkbook();
    }

}
