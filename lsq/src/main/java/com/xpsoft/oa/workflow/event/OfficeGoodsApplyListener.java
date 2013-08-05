package com.xpsoft.oa.workflow.event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.oa.model.admin.GoodsApply;
import com.xpsoft.oa.model.admin.OfficeGoods;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.admin.GoodsApplyService;
import com.xpsoft.oa.service.admin.OfficeGoodsService;
import com.xpsoft.oa.service.flow.ProcessRunService;


/**
 * 易耗品领用申请流程审批完成后，调用此监听器
 * 
 * @author
 * 
 */
public class OfficeGoodsApplyListener implements EventListener {

	private Log logger = LogFactory.getLog(OfficeGoodsApplyListener.class);

	@Override
	public void notify(EventListenerExecution execution) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.info("enter the NameCardListener notify method...");
		}
		
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");

		OfficeGoodsService officeGoodsService = (OfficeGoodsService) AppUtil
				.getBean("officeGoodsService");
		GoodsApplyService goodsApplyService = (GoodsApplyService) AppUtil
				.getBean("goodsApplyService");

		// 获取表单参数
		OpenProcessInstance pi = execution.getProcessInstance();

		Object applyDate = pi.getVariable("applyDate");// 申请日期
		Object resultData = pi.getVariable("goodresultGridData");// 类型
		Object applyDescription = pi.getVariable("applyDescription");// 说明
		String applyGoodUseType=(String)execution.getProcessInstance().getVariable("applyGoodUseType");//使用类型
		Long applyGoodProId = null;
		if("项目使用".equals(applyGoodUseType)){
			applyGoodProId = Long.parseLong((String)pi.getVariable("applyGoodProId"));//合同id
		}
		String strApplyDate = null;
		String strResultData = null;
		String strApplyDescription = null;

		if (applyDate != null)
			strApplyDate = applyDate.toString();
		if (resultData != null)
			strResultData = resultData.toString();
		if (applyDescription != null)
			strApplyDescription = applyDescription.toString();

		List<OfficeGoods> goodsTemp = new ArrayList<OfficeGoods>();
		String resultDatas[] = strResultData.split(";");
		for (int i = 0; i < resultDatas.length; i++) {
			String officeGoodStr = resultDatas[i];
			String officeGoodObj[] = officeGoodStr.split(",");
			OfficeGoods officeGoods = new OfficeGoods();
			for (int j = 0; j < officeGoodObj.length; j++) {
				if (officeGoodObj[j].contains("ID")) {
					officeGoods.setGoodsId(Long.parseLong(officeGoodObj[j]
							.substring(officeGoodObj[j].indexOf(":") + 1)));
				}
				if (officeGoodObj[j].contains("数量")) {
					officeGoods
							.setStockCounts(Integer
									.parseInt(officeGoodObj[j]
											.substring(officeGoodObj[j]
													.indexOf(":") + 1)));
				}
			}
			goodsTemp.add(officeGoods);
		}

		for (int i = 0; i < goodsTemp.size(); i++) {
			//System.out.println(goodsTemp.get(i));

			OfficeGoods officeGoods = officeGoodsService.get(goodsTemp.get(i)
					.getGoodsId());
			Integer con = goodsTemp.get(i).getStockCounts();
			Integer least = officeGoods.getStockCounts() - con;
//			if (least >= 0) {

				GoodsApply goodsApply = new GoodsApply();
				goodsApply.setApplyDate(DateUtil.parse(strApplyDate,"yyyy-MM-dd"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-SSSS");// 自动生成申请号
				goodsApply.setApplyNo("GA" + sdf.format(new Date()));
				goodsApply.setApprovalStatus((short) 1);
				goodsApply.setUseCounts(goodsTemp.get(i).getStockCounts());
				goodsApply.setNotes(strApplyDescription);
				goodsApply.setUserId(flowStartUser.getUserId());
				goodsApply.setProposer(flowStartUser.getFullname());
				goodsApply.setOfficeGoods(new OfficeGoods(goodsTemp.get(i).getGoodsId()));
				goodsApply.setUseType(applyGoodUseType);
				goodsApply.setProId(applyGoodProId);
				
				ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
				ProcessRun processRun=processRunService.getByExeId(pi.getId());
				goodsApply.setProcessRunId(processRun.getRunId());
				
				goodsApplyService.save(goodsApply);

				officeGoods.setStockCounts(least);
				
				officeGoodsService.save(officeGoods);

//			}

		}
		
		
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);
	}

}
