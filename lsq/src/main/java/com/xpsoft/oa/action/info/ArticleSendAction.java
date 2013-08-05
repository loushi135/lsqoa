package com.xpsoft.oa.action.info;


import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.Constants;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.info.ArticleSend;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.info.ArticleSendService;
import com.xpsoft.oa.service.system.FileAttachService;

import flexjson.DateTransformer;
import flexjson.JSONSerializer;
/**
 * 
 * @author 
 *
 */
public class ArticleSendAction extends BaseAction{
	@Resource
	private ArticleSendService articleSendService;
	private ArticleSend articleSend;
	@Resource
	private FileAttachService fileAttachService;
	private List<ArticleSend> list;
	private Long noticeId;


	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public ArticleSend getArticleSend() {
		return articleSend;
	}

	public void setArticleSend(ArticleSend articleSend) {
		this.articleSend = articleSend;
	}

	public List<ArticleSend> getList() {
		return list;
	}

	public void setList(List<ArticleSend> list) {
		this.list = list;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<ArticleSend> list = this.articleSendService.getAll(filter);

		Type type = new TypeToken<List<ArticleSend>>() {
		}
		.getType();
		StringBuffer buff = new StringBuffer(
				"{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(
				",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.articleSendService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ArticleSend ArticleSend = (ArticleSend) this.articleSendService
				.get(this.noticeId);

		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer(
				"yyyy-MM-dd HH:mm:ss"), new String[] { "effectiveDate" });
		serializer.transform(new DateTransformer(
				"yyyy-MM-dd HH:mm:ss"), new String[] { "expirationDate" });

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(serializer.exclude(new String[] { "class" }).include("treeType","attachFiles")
				.serialize(ArticleSend));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		String attachIds = getRequest().getParameter("attachIds");
		if (StringUtils.isNotEmpty(attachIds)) {
		       this.articleSend.getAttachFiles().clear();
		       String[] fIds = attachIds.split(",");
		       for (int i = 0; i < fIds.length; i++) {
		         FileAttach fileAttach = (FileAttach)this.fileAttachService.get(new Long(fIds[i]));
		         this.articleSend.getAttachFiles().add(fileAttach);
		       }
	     }
		this.articleSendService.save(this.articleSend);
		setJsonString("{success:true}");
		return "success";
	}

	public String search() {
		String typeId = getRequest().getParameter("typeId");
		PagingBean pb = getInitPagingBean();
		String searchContent = getRequest().getParameter(
				"searchContent");
		List<ArticleSend> list = this.articleSendService.findBySearch(
				searchContent,typeId, pb);
		Type type = new TypeToken<List<ArticleSend>>() {
		}
		.getType();
		StringBuffer buff = new StringBuffer(
				"{success:true,'totalCounts':")
		.append(pb.getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_YMD).create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String display() {
		String typeId = getRequest().getParameter("typeId");
		PagingBean pb = new PagingBean(0, 8);
		List list = this.articleSendService.findBySearch(null,typeId, pb);
		getRequest().setAttribute("articleSendList", list);
		return "display";
	}
}
