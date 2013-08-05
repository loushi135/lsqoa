package com.xpsoft.core.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelImportUtil {
	
	
	/**
	 * excel导入，只导入
	 * @param filePath 文件全路径
	 * @param sheetNum 工作薄号
	 * @param startRow 数据开始行数
	 * @param columnCount 总共列数
	 * @param classPath 对应类的包名
	 * @return
	 */
	public static List ImportData(String filePath, int sheetNum,int startRow,int columnCount,String classPath){
		List list = null;
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(filePath));
			Sheet sheet = workbook.getSheet(sheetNum);
			Cell cell = null;
			String value="";
			int rowCount = sheet.getRows();
			Class objClazz=Class.forName(classPath);
			
			list=new ArrayList();
			//行的循环的标签 
			for (int i = startRow; i < rowCount; i++) {//行
				Object obj=objClazz.newInstance();
				
				int l=0;
				//列循环的标签 
				for (int j = 0; j < columnCount; j++) {//列
					cell =sheet.getCell(j, i);
					value=cell.getContents();
					
					Field field[] = objClazz.getDeclaredFields();
					// 获得属性字段的名字
					String fieldName = field[j+1].getName();
					String fieldType=field[j+1].getType().getName();
					
					if("id".equals(fieldName)){
						continue;
					}
					Method m = objClazz.getDeclaredMethod("set"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1, fieldName.length()),
							String.class);
					
					if(!"".equals(value)&&null!=value){
						m.invoke(obj, new Object[]{value});
						l++;
					}
				}
				if(l==columnCount){
					list.add(obj);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			workbook.close();
		}
		
		return list;
	}
}
