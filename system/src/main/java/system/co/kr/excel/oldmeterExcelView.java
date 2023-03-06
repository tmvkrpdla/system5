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

 
public class oldmeterExcelView extends AbstractExcelPOIView {
	
	private static final Logger logger = LoggerFactory.getLogger(oldmeterExcelView.class);
 
   // @SuppressWarnings("unchecked")
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, 
    	HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	
    	logger.info("= = = = = EXCELTEST DOWN LOG START = = = = =");
//		logger.info("admin_id : " + SessionUtil.getString(session, "ADMIN", "admin_id"));
//		logger.info("param : " + model.get("PARAMMAP"));
//		logger.info("Member : " + model.get("MEMBER_LIST"));
//		logger.info("LIST_MEMBER : " + model.get("LIST_MEMBER"));
		logger.info("fileName : " + model.get("fileName"));
		logger.info("= = = = = EXCEL DOWN LOG END = = = = =");
		
		HashMap meterringMap = (HashMap) model.get("METERINGMAP");
		//System.out.println("meterringMap = " + meterringMap);
		List list_ho = (List) meterringMap.get("list_ho");
//		System.out.println("list_ho.size = " + list_ho.size());
//		System.out.println("list_ho = " + list_ho);
		
		Sheet sheet = workbook.createSheet();
		Row row = null;
		Cell cell = null;
		
		int rowCnt = 0;
		
		row = sheet.createRow(rowCnt++);				//	row == 0
		row.createCell(0).setCellValue("동");
		row.createCell(1).setCellValue("호");
		row.createCell(2).setCellValue("철거지침");
		row.createCell(3).setCellValue("시작지침");
		
//		rowCnt++;
		
		for(int i=0; i<list_ho.size(); i++) {
			HashMap meterMap = (HashMap) list_ho.get(i);
			//System.out.println("meterMap = " + meterMap);
			String dong_name = String.valueOf(meterMap.get("dong_name"));
			String ho_name = String.valueOf(meterMap.get("ho_name"));
			String value_start = String.valueOf(meterMap.get("value_start"));
			String value_last = String.valueOf(meterMap.get("value_last"));
			
			row = sheet.createRow(rowCnt++);
			row.createCell(0).setCellValue(dong_name);
			row.createCell(1).setCellValue(ho_name);
			row.createCell(2).setCellValue(value_last);
			row.createCell(3).setCellValue(value_start);
			
		} // for end
		
    }

    @Override
    protected Workbook createWorkbook() {
        return new XSSFWorkbook();
    }

}
