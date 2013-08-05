package com.xpsoft.oa.action.info;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.info.InMessage;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.info.InMessageService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.system.AppUserService;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class ShortMessageAction extends BaseAction {
	static short NOT_DELETE = 0;
	private ShortMessage shortMessage;
	private Date from;
	private Date to;
	private List<InMessage> inList = new ArrayList();

	@Resource
	private ShortMessageService shortMessageService;

	@Resource
	private InMessageService inMessageService;

	@Resource
	private AppUserService appUserService;

	public List<InMessage> getInList() {
		return this.inList;
	}

	public void setInList(List<InMessage> inList) {
		this.inList = inList;
	}

	public Date getFrom() {
		return this.from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return this.to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public ShortMessage getShortMessage() {
		return this.shortMessage;
	}

	public void setShortMessage(ShortMessage shortMessage) {
		this.shortMessage = shortMessage;
	}

	public String list() {
		PagingBean pb = getInitPagingBean();
		AppUser appUser = ContextUtil.getCurrentUser();
		List list = this.shortMessageService.searchShortMessage(
				appUser.getUserId(), this.shortMessage, this.from, this.to, pb);
		StringBuffer buff = new StringBuffer(
				"{success:true,'totalCounts':" + pb.getTotalItems()
						+ ",result:");
		List<InMessage> inList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			InMessage inMessage = (InMessage) ((Object[]) list.get(i))[0];
			inList.add(inMessage);
		}
		Gson gson = new Gson();
		Type type = new TypeToken<List<InMessage>>() {
		}
		.getType();
		buff.append(gson.toJson(inList, type));
		buff.append("}");
		setJsonString(buff.toString());
		return "success";
	}

	public String send() {
		String reId = getRequest().getParameter("userId");
		String content = getRequest().getParameter("content");

		AppUser appUser = ContextUtil.getCurrentUser();
		if ((StringUtils.isNotEmpty(reId))
				&& (StringUtils.isNotEmpty(content))) {
			String[] st = reId.split(",");
			ShortMessage message = new ShortMessage();
			message.setContent(content);
			message.setMsgType(Short.valueOf((short) 1));
			message.setSenderId(appUser.getUserId());
			message.setSender(appUser.getFullname());
			message.setSendTime(new Date());
			this.shortMessageService.save(message);
			for (int i = 0; i < st.length; i++) {
				InMessage in = new InMessage();
				in.setUserId(Long.valueOf(Long.parseLong(st[i])));
				AppUser user = (AppUser) this.appUserService.get(Long
						.valueOf(Long.parseLong(st[i])));
				in.setUserFullname(user.getFullname());
				in.setDelFlag(Short.valueOf(NOT_DELETE));
				in.setReadFlag(Short.valueOf((short) 0));
				in.setShortMessage(message);
				this.inMessageService.save(in);
			}
			setJsonString("{success:true}");
		} else {
			setJsonString("{success:false}");
		}
		return "success";
	}
}
