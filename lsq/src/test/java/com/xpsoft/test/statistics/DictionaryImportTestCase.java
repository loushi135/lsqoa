package com.xpsoft.test.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.xpsoft.core.util.ExcelUtils;
import com.xpsoft.oa.dao.system.DictionaryDao;
import com.xpsoft.test.BaseTestCase;

public class DictionaryImportTestCase extends BaseTestCase {
	@Resource
	private DictionaryDao dictionaryDao;

	private Logger log = Logger.getLogger("");

	@Test
	public void importAdd() {

		String filePath = "员工花名册.xls";// 文件路径
		
		String fileName = "";// 文件名
		
		int count = 0;
		Map<String,Object> map = ExcelUtils.readExcel(this.getClass()
				.getResource("").getPath().replace("%20", " ")
				+ filePath,1,21);

		if (null == map) {
			log.error("数据异常3");
			throw new RuntimeException("{success:false,data:'数据异常'}");
		}
		List sheetNameList = (List)map.get("sheetNameList");
		List sheetList = (List)map.get("sheetList");
		int length = sheetList.size();
		for (int i=0;i<length;i++) {
			List<ArrayList> rowList = (List<ArrayList>)sheetList.get(i);
			String sheetName = (String)sheetNameList.get(i);
			// excle的所有行
			for (ArrayList a : rowList) {
				String id = (String)a.get(0);
				String name = "";
				if(a.size()==2){
					name = (String)a.get(1);
				}
				System.out.println("INSERT INTO dictionary(itemName,itemValue,glodMantisId,descp) VALUES('金螳螂-"+sheetName+"','"+name+"','"+id+"','"+name+"');");
			}
		}

		System.out.println("{success:true,data:'共上传" + count + "条数据'}");
	}
}