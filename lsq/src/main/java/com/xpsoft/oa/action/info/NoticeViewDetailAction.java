package com.xpsoft.oa.action.info;


import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;


import com.xpsoft.oa.model.info.NoticeViewDetail;
import com.xpsoft.oa.service.info.NoticeViewDetailService;
/**
 * 
 * @author 
 *
 */
public class NoticeViewDetailAction extends BaseAction{
	@Resource
	private NoticeViewDetailService noticeViewDetailService;
	private NoticeViewDetail noticeViewDetail;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NoticeViewDetail getNoticeViewDetail() {
		return noticeViewDetail;
	}

	public void setNoticeViewDetail(NoticeViewDetail noticeViewDetail) {
		this.noticeViewDetail = noticeViewDetail;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<NoticeViewDetail> list= noticeViewDetailService.getAll(filter);
		
		Type type=new TypeToken<List<NoticeViewDetail>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_FULL).create();
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
				noticeViewDetailService.remove(new Long(id));
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
		NoticeViewDetail noticeViewDetail=noticeViewDetailService.get(id);
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_FULL).create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(noticeViewDetail));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		noticeViewDetailService.save(noticeViewDetail);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
