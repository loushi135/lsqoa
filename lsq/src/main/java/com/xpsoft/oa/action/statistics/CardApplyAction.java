package com.xpsoft.oa.action.statistics;


import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.ExportUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.statistics.CardApply;
import com.xpsoft.oa.model.statistics.ProjectRelatedData;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.statistics.CardApplyService;
import com.xpsoft.oa.service.system.FileAttachService;
/**
 * 
 * @author 
 *
 */
public class CardApplyAction extends BaseAction{
	@Resource
	private CardApplyService cardApplyService;
	private CardApply cardApply;
	
	private Long cardId;
	@Resource
	private FileAttachService fileAttachService;

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public CardApply getCardApply() {
		return cardApply;
	}

	public void setCardApply(CardApply cardApply) {
		this.cardApply = cardApply;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<CardApply> list= cardApplyService.getAll(filter);
		
		Type type=new TypeToken<List<CardApply>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				cardApplyService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		CardApply cardApply=cardApplyService.get(cardId);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(cardApply));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		String cardAttachIDs=getRequest().getParameter("cardAttachIDs");
		if(StringUtils.isNotEmpty(cardAttachIDs)){
			cardApply.getCardFiles().clear();
			String[]fIds=cardAttachIDs.split(",");
			for(int i=0;i<fIds.length;i++){
				FileAttach fileAttach=fileAttachService.get(new Long(fIds[i]));
				cardApply.getCardFiles().add(fileAttach);
			}
		}
		cardApplyService.save(cardApply);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	public String export()throws Exception{
		String format = getRequest().getParameter("format");
		String jasperName= getRequest().getParameter("jasperName");
		String fileName="名片申请统计";
		
		QueryFilter filter=new QueryFilter(getRequest());
		
		filter.addFilter("Q_cardProposer_S_LK", URLDecoder.decode(getRequest().getParameter("cardProposer_S_LK"),"utf-8"));
		filter.addFilter("Q_dept.depName_S_LK", URLDecoder.decode(getRequest().getParameter("dept.depName_S_LK"),"utf-8"));

		List<CardApply> list = cardApplyService.getAll(filter);
		Map<Object,Object> parmsMap = new HashMap<Object,Object>();
        ExportUtil.export(list, jasperName,fileName, format,parmsMap);
		return null;
	}
}
