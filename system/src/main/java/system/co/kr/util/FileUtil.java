package system.co.kr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author 
 * 2020.05.14
 *
 */
public class FileUtil {
	
	private static String[] imageExt = {"png", "jpg", "jpeg", "gif", "bmp"};
	
	private static String pdfExt = "pdf";
	
	/**
	 * �̹��� �������� üũ�ϴ� �Լ�		
	 * �̹����̸� true �ƴϸ� false
	 * @param imgFile
	 * @return
	 */
	public static boolean isImage (String imgFile) {
		boolean result = false;
		
		String temp = imgFile.substring(imgFile.lastIndexOf(".")+1);
		//System.out.println(temp);
		for(int i=0; i<imageExt.length; i++) {
			if(imageExt[i].toUpperCase().equals(temp.toUpperCase())) {
				result =true;
				break;
			}
		}
		return result;
	}
	
	/**
	 * �̹��� ������ Ȯ���ڸ� �������ִ� �Լ�
	 * ex) png, jpg, jpeg, gif
	 * @param imgFile
	 * @return
	 */
	public static String getImageExt(String imgFile) {
		String temp = imgFile.substring(imgFile.lastIndexOf(".")+1);
		String ext = "";
		
		for(int i=0; i<imageExt.length; i++) {
			if(imageExt[i].toUpperCase().equals(temp.toUpperCase())) {
				ext = imageExt[i];
				break;
			}
		}
		return ext;
	}
	
	/**
	 * pdf �������� Ȯ��
	 * @param pdfFile
	 * @return
	 */
	public static boolean isPdf(String pdfFile) {
		boolean result = false;
		String temp = pdfFile.substring(pdfFile.lastIndexOf(".")+1);
		if(pdfExt.toUpperCase().equals(temp.toUpperCase())) {
			result = true;
		}
		return result;
	}
	
	/**
	 * ���� Ȯ���ڸ� �������� �Լ�
	 * @param file
	 * @return
	 */
	public static String getExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	public static Map<String, Object> fileUpload(MultipartFile multipartFile, String basePath, String baseTempPath) throws Exception{

		InputStream in = null;
		OutputStream out = null;
		
		
		String oriFileName = multipartFile.getOriginalFilename();
		StringUtil stringUtil = new StringUtil();
		  
	    //	��¥���� ������ ����	2020/03/25	
	    String datePath = stringUtil.getFullYearYYYY()+"/"+stringUtil.getMonthMM()+"/"+stringUtil.getDayDD();
	    
	    //	Ȯ���ڸ� ������
	    String ext = getExt(oriFileName);	//FileUtil.getImageExt(oriFileName);

	    //	1585114192707
	    String timeMili = String.valueOf(System.currentTimeMillis());
	    
	    //	C:/tempPath/iamge/egservice/2020/03/25
	    String tempPath = baseTempPath+"/"+datePath;
	    
	    //	C:/tempPath/iamge/egservice/2020/03/25/1585114192707.png
	    String tempFile = tempPath + "/" + timeMili + "."+ext;
	    
	    Map<String, Object> returnMap = new HashMap<String, Object>();
	    
	    try{
			 
	    	//	�ӽ������� �̹������ε� �� temp �н�
	    	File tempFilePath = new File(tempPath+"/");
	    	//	�ӽ������� �̹��� ���ε� �� �н� + �̹��� �̸�
	        File tempFullPath = new File(tempFile);
	    	
	    	//	�ӽ������� �̹��� ���丮 ����
	    	if(!tempFilePath.exists()) {
	    		tempFilePath.mkdirs();
	    	}
	        
	        byte[] bytes = multipartFile.getBytes();
	        
	        out = new FileOutputStream(tempFullPath);
	        out.write(bytes);
	        
	        out.close();
	        
	        
	        String fileName = tempFullPath.getName();
	        
	        //////////////////////////////////
	        //	�̹��� ���ε� ������ �̵���Ŵ
	        
	        //	C:/fileupload/image/egservice/2020/03/25	
	        File realPath	  = new File(basePath+"/"+datePath);
	        //	C:/fileupload/image/egservice/2020/03/25/1585114192707.png		
	        File realFullPath = new File(realPath.getAbsolutePath()+"/"+fileName); 
	        if(!realPath.exists()) {
	        	realPath.mkdirs();
	    	}
	        
	        //	
	        in = new FileInputStream(tempFullPath.getAbsoluteFile());
	        out = new FileOutputStream(realFullPath.getAbsoluteFile());
	        while(true) {
	        	//�̹����� �о�´�.
	            int data = in.read();
	            if(data == -1){
	                break;
	            }
	            //�̹����� ����.
	            out.write(data);
	        }
	        
	        
	        out.close();
	        in.close();
	        
	        //	temp ���� ���� (�ӽ� ���� ����)
	        tempFullPath.delete();
	        
	        returnMap.put("datePath", datePath);			//	
	        returnMap.put("fileName", fileName);			//
	        returnMap.put("oriFileName", oriFileName);		//
	        returnMap.put("realFullPath", realFullPath);	//
	        returnMap.put("realPath", realPath);			//	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	try {
	            if (out != null) {
	                out.close();
	            }
	            
	            if (in != null) {
	            	in.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		return returnMap;
	}//

	

	
}
