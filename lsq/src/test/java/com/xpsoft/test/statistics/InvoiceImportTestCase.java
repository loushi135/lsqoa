package com.xpsoft.test.statistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.xpsoft.core.util.ExcelUtils;
import com.xpsoft.core.util.Trans2RMB;
import com.xpsoft.oa.dao.statistics.BillDao;
import com.xpsoft.oa.model.customer.SuppliersAssess;
import com.xpsoft.oa.model.statistics.Bill;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.service.customer.SuppliersAssessService;
import com.xpsoft.oa.service.statistics.ProjectNewService;
import com.xpsoft.test.BaseTestCase;

public class InvoiceImportTestCase extends BaseTestCase {
	@Resource
	private BillDao billDao;
	@Resource
	private ProjectNewService projectNewService;
	@Resource
	private SuppliersAssessService assessService;

	private Logger log = Logger.getLogger("");

	@Test
	public void importAdd() {

		String filePath = "供应商发票截止13.4.30.xls";// 文件路径
		
		String fileName = "";// 文件名
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(2013, 4, 30);
		
		Date date=calendar.getTime();
		
		long id = -1;

		Trans2RMB trans2rmb = new Trans2RMB();
		Bill bill = null;
		int count = 0;
		List<ArrayList> list = ExcelUtils.readExcel(this.getClass()
				.getResource("").getPath().replace("%20", " ")
				+ filePath);

		if (null == list) {
			log.error("数据异常3");
			throw new RuntimeException("{success:false,data:'数据异常'}");
		}
		// excle的所有sheet
		int s, r, c;
		s = c = 0;
		r = 1;
		for (ArrayList<ArrayList> arr : list) {
			s++;
			String tempPro = null;
			// excle的所有行
			for (ArrayList a : arr) {
				r++;
				if (r < 4) {
					continue;
				}
				if (a.size() != 5) {
					log.info(fileName + "文件,sheet:" + s + ",行：" + r
							+ ",数据列数异常3");
					throw new RuntimeException("{success:false,data:'"
							+ fileName + "文件,sheet:" + s + ",行：" + r
							+ ",数据列数异常'}");
				}
				// excle的所有列
				{
					for (Object o : a) {
						c++;
						System.out.print((String) o);
						System.out.print("||");
					}
					System.out.println();
				}
				if (null == a.get(1) || ((String) a.get(1)).trim().equals("")
						|| ((String) a.get(1)).trim().equals("合计")
						|| ((String) a.get(0)).trim().equals("合计")) {
					continue;
				}
				bill = new Bill();

				String money = (String) a.get(3);

				if (null == money) {
					log
							.info(fileName + "文件,sheet:" + s + ",行：" + r
									+ ",发票金额为空");
					throw new RuntimeException("{success:false,data:'"
							+ fileName + "文件,sheet:" + s + ",行：" + r
							+ ",发票金额为空'}");
				} else {
					money = money.trim().replace(",", "");
					if (money.startsWith("(")) {
						money = "-" + money.substring(1, money.length() - 1);
					}
				}

				try {
					bill.setAmount(new BigDecimal(money));
					bill.setAmountBig(trans2rmb.toRMB(money));
				} catch (Exception e) {
					log.info(fileName + "文件,sheet:" + s + ",行：" + r
							+ ",收款金额格式错误");
					throw new RuntimeException("{success:false,data:'"
							+ fileName + "文件,sheet:" + s + ",行：" + r
							+ ",收款金额格式错误'}");
				}

				String r0 = ((String) a.get(0)).trim();

				if (StringUtils.isEmpty(r0)) {
					r0 = tempPro;
				} else {
					tempPro = r0;
				}

				int proSpilt = r0.indexOf("-");
				String proNo = r0.substring(0, proSpilt);
				String proName = r0.substring(proSpilt + 1);

				ProjectNew projectNews = projectNewService
						.getByProName(proName);

				if (null == projectNews) {
					log.info(fileName + "文件,sheet:" + s + ",行：" + r
							+ ",项目名称不存在");
					// throw new
					// RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",请导入存在的项目名称'}");
					continue;
				}

				if (!proNo.equals(projectNews.getProNo())) {
					log.error(fileName + "文件,sheet:" + s + ",行：" + r
							+ ",项目名称和项目编号不匹配");
					// throw new
					// RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",项目名称和项目编号不匹配'}");
					System.err.println(fileName + "文件,sheet:" + s + ",行：" + r
							+ ",项目名称和项目编号不匹配");
				}

				int assSpilt = ((String) a.get(1)).trim().indexOf("-");
				String assessName = ((String) a.get(1)).trim().substring(
						assSpilt + 1);
				String assessNO = ((String) a.get(1)).trim().substring(0,
						assSpilt);

				if (assessName.lastIndexOf(")") == assessName.length() - 1
						|| assessName.lastIndexOf("）") == assessName.length() - 1) {
					int last = assessName.lastIndexOf("(") > assessName
							.lastIndexOf("（") ? assessName.lastIndexOf("(")
							: assessName.lastIndexOf("（");
					assessName = assessName.substring(0, last);
				}

				SuppliersAssess suppliersAssess = null;
				try {
					suppliersAssess = assessService.getByName(assessName);
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
					// log.error(fileName+"文件,sheet:"+s+",行："+r+",/n"+e.getMessage());

					suppliersAssess = assessService.getByName(assessName,
							assessNO);

				}

				if (null == suppliersAssess) {
					log.error(fileName + "文件,sheet:" + s + ",行：" + r
							+ ",供应商名称不存在");
					// throw new
					// RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",请导入存在的供应商名称}");
					continue;
				}

				if (!assessNO.equals(suppliersAssess.getSuppliersNo())) {
					log.error(fileName + "文件,sheet:" + s + ",行：" + r
							+ ",供应商名称和供应商编号不匹配");
					// throw new
					// RuntimeException("{success:false,data:'"+fileName+"文件,sheet:"+s+",行："+r+",供应商名称和供应商编号不匹配'}");
				}
				bill.setBillTime(date);
				bill.setSuppliersAssess(suppliersAssess);
				bill.setProjectNew(projectNews);
				bill.setCreatetime(new Date());
				billDao.save(bill);
				count++;
			}
		}

		System.out.println("{success:true,data:'共上传" + count + "条数据'}");
	}
}