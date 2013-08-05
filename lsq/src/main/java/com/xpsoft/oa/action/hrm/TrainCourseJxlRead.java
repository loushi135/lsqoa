package com.xpsoft.oa.action.hrm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.xpsoft.core.util.StringUtil;
import com.xpsoft.oa.model.hrm.TrainCourse;
import com.xpsoft.oa.model.system.FileAttach;
public class TrainCourseJxlRead {
	public static List ready(String[] filePaths) throws Exception {
		List lists = new ArrayList();
		FileAttach fileAttach = new FileAttach();
    	for(String filePath:filePaths){
    		List list = read(filePath);
    		lists.addAll(list);
    	}
    	return lists;
	}

	public static List read(String filePath) throws Exception {
		Workbook workbook = null;
 		workbook = Workbook.getWorkbook(new File(filePath));
		Sheet sheet = workbook.getSheet(0);
		Cell cell = null;
		int columnCount = 17;
		int rowCount = sheet.getRows();
		List<TrainCourse> list = new ArrayList<TrainCourse>();
		lableA://行的循环的标签 
		for (int i = 1; i < rowCount; i++) {//行---文档上从第二行开始为有效数据、
			TrainCourse trainCourse = new TrainCourse();
			lableB://列的循环的标签 
			for (int j = 0; j < columnCount; j++) {//列
				cell = sheet.getCell(j, i);
				switch(j){
					case 0:
						//第一列文件上显示序号，这边不需要保存数据，只用于判断最后一行
						if("".equals(cell.getContents())){
							break lableA;
						}
						break;
					case 1:
						trainCourse.setCourseNo(cell.getContents());//课程编号
						break;
					case 2:
						trainCourse.setCourseName(cell.getContents());//课程名称
						break;
					case 3:
						trainCourse.setDeptName(cell.getContents());//来源部门
						break;
					case 4:
						trainCourse.setCourseType(cell.getContents());//课程类型
						break;
					case 5:
						trainCourse.setTrainTarget(cell.getContents());//培训对象
						break;
					case 6:
						trainCourse.setTrainCause(cell.getContents());//培训目的
						break;
					case 7:
						trainCourse.setCoursePriority(cell.getContents());//课程优先级
						break;
					case 8:
						trainCourse.setTrainType(cell.getContents());//培训类型
						break;
					case 9:
						trainCourse.setTrainWay(cell.getContents());//培训方式
					case 10:
						trainCourse.setCourseTotal(cell.getContents());//总课时
						break;
					case 11:
						trainCourse.setCourseTime(cell.getContents());//课时
						break;
					case 12:
						trainCourse.setTrainTeacher(cell.getContents());//讲师\
						break;
					case 13:
						trainCourse.setCheckType(cell.getContents());//考核方式
						break;
					case 14:
						if(StringUtils.isNotBlank(cell.getContents())){
							trainCourse.setTrainBudget(Integer.valueOf(cell.getContents()));//培训预算
						}else{
							trainCourse.setTrainBudget(null);
						}
						
						break;
					case 15:
						trainCourse.setRemarks(cell.getContents());//备注
						break;
					case 16:
						if(StringUtils.isNotBlank(cell.getContents())){
							trainCourse.setCredit(Integer.valueOf(cell.getContents()));//学分
						 }else{
							 trainCourse.setCredit(null);
						 }
						break;
					case 17:
						trainCourse.setDescription(cell.getContents());//描述
						break;
				}
			}
			list.add(trainCourse);
		}
		workbook.close();
		return list;
	}
	
	/**
	 * 读取课程excel
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static List ready2(String[] filePaths) throws Exception {
		List lists = new ArrayList();
		FileAttach fileAttach = new FileAttach();
    	for(String filePath:filePaths){
    		List list = read(filePath);
    		lists.addAll(list);
    	}
    	return lists;
	}
}
