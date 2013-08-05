package com.xpsoft.oa.action.admin;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.util.ExportUtil;
import com.xpsoft.core.util.JsonUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.admin.Book;
import com.xpsoft.oa.model.admin.BookBorRet;
import com.xpsoft.oa.model.admin.BookSn;
import com.xpsoft.oa.model.info.ShortMessage;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.admin.BookBorRetService;
import com.xpsoft.oa.service.admin.BookService;
import com.xpsoft.oa.service.admin.BookSnService;
import com.xpsoft.oa.service.info.ShortMessageService;
import com.xpsoft.oa.service.system.AppUserService;

import flexjson.JSONSerializer;

public class BookBorRetAction extends BaseAction{
	@Resource
	private BookBorRetService bookBorRetService;
	private BookBorRet bookBorRet;
	@Resource
	private BookSnService bookSnService;
	@Resource
	private BookService bookService;
	@Resource
	private AppUserService appUserService;
	private Long recordId;
	private Long bookSnId;
	

	public Long getBookSnId() {
		return bookSnId;
	}

	public void setBookSnId(Long bookSnId) {
		this.bookSnId = bookSnId;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public BookBorRet getBookBorRet() {
		return bookBorRet;
	}

	public void setBookBorRet(BookBorRet bookBorRet) {
		this.bookBorRet = bookBorRet;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<BookBorRet> list= bookBorRetService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer=JsonUtil.getJSONSerializer("borrowTime","returnTime","lastReturnTime");
		buff.append(serializer.exclude(new String[]{"class"}).serialize(list));
		buff.append("}");
		jsonString=buff.toString();
		return SUCCESS;
	}
	/**
	 * 显示已借出图书列表
	 */
	public String listBorrow(){
		PagingBean pb = getInitPagingBean();
		List<BookBorRet> list = bookBorRetService.getBorrowInfo(pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(pb.getTotalItems()).append(",result:");
		JSONSerializer serializer=JsonUtil.getJSONSerializer("borrowTime","returnTime","lastReturnTime");
		buff.append(serializer.exclude(new String[]{"class"}).serialize(list));
		buff.append("}");
		jsonString=buff.toString();
		return SUCCESS;
	}
	/**
	 * 显示已归还图书列表
	 */
	public String listReturn(){
		PagingBean pb = getInitPagingBean();
		List<BookBorRet> list = bookBorRetService.getReturnInfo(pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(pb.getTotalItems()).append(",result:");
		JSONSerializer serializer=JsonUtil.getJSONSerializer("borrowTime","returnTime","lastReturnTime");
		buff.append(serializer.exclude(new String[]{"class"}).serialize(list));
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
				bookBorRetService.remove(new Long(id));
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
		BookBorRet bookBorRet=bookBorRetService.get(recordId);
		JSONSerializer serializer=JsonUtil.getJSONSerializer("borrowTime","returnTime","lastReturnTime");
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(serializer.exclude(new String[]{"class"}).serialize(bookBorRet));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
		
	}
	/**
	 * 添加及保存图书借阅操作
	 */
	public String saveBorrow(){
		//用系统日期保存图书的借出日期
		Long snId=bookBorRet.getBookSn().getBookSnId();
		Long recordId=bookBorRetService.getBookBorRetId(snId);
		if(recordId!=null){
			bookBorRetService.remove(recordId);
		}
		bookBorRet.setBorrowTime(new Date());
		AppUser user=ContextUtil.getCurrentUser();
		bookBorRet.setRegisterName(user.getFullname());
		bookBorRet.setRegisterId(user.getUserId());
		bookBorRet.setStatus("待领用确认");
		bookBorRetService.save(bookBorRet);
		BookSn bookSn = bookSnService.get(bookBorRet.getBookSnId());
		//执行借出操作时将图书的状态改为1（表示借出状态）
		bookSn.setStatus(new Short((short) 1));
		bookSnService.save(bookSn);
		Book book = bookService.get(bookSn.getBookId());
		//当图书借出操作成功后将未借出的图书数量-1
		book.setLeftAmount(book.getLeftAmount()-1);
		bookService.save(book);
		ShortMessageService smService = (ShortMessageService) AppUtil.getBean("shortMessageService");
		smService.save(AppUser.SYSTEM_USER, bookBorRet.getUserId().toString(), "您已经借阅了档案，拿到了借阅的档案之后，请进行领用确认。", ShortMessage.MSG_TYPE_SYS);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	/**
	 * 添加及保存图书归还操作
	 */
	public String saveReturn(){
		//用系统日期保存图书的归还日期
		bookBorRet.setLastReturnTime(new Date());
		AppUser user=ContextUtil.getCurrentUser();
		bookBorRet.setRegisterName(user.getFullname());
		bookBorRet.setRegisterId(user.getUserId());
		bookBorRet.setStatus("已归还");
		bookBorRetService.save(bookBorRet);
		BookSn bookSn = bookSnService.get(bookBorRet.getBookSnId());
		//执行借出操作时将图书的状态改为0（表示已归还）
		bookSn.setStatus(new Short((short) 0));
		bookSnService.save(bookSn);
		Book book = bookService.get(bookSn.getBookId());
		//当图书归还操作成功后将未借出的图书数量+1
		book.setLeftAmount(book.getLeftAmount()+bookBorRet.getBoorowAmount());
		bookService.save(book);
		setJsonString("{success:true}");
		return SUCCESS;
	}
	/**
	 * 根据bookSnId查出所借图书的借出时间和应还时间
	 */
	public String getBorRetTime(){
		//得到传来的bookSnId
		Long bookSnId = Long.valueOf(getRequest().getParameter("bookSnId"));
		BookBorRet bookBorRet = bookBorRetService.getByBookSnId(bookSnId);
		JSONSerializer serializer=JsonUtil.getJSONSerializer("borrowTime","returnTime");
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(serializer.exclude(new String[]{"class"}).serialize(bookBorRet));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	
	/**
	 * 领用确认
	 */
	public String multiConfirm(){
		BookBorRet bookBorRet = new BookBorRet();
		String[] ids = getRequest().getParameterValues("ids");
		String status = getRequest().getParameter("status");
		
		if (ids != null) {
			for (String recordId : ids) {
				bookBorRet = bookBorRetService.get(new Long(recordId));
				bookBorRet.setStatus(status);
				bookBorRetService.save(bookBorRet);
			}
			ShortMessageService smService = (ShortMessageService) AppUtil.getBean("shortMessageService");
			smService.save(AppUser.SYSTEM_USER, bookBorRet.getRegisterId().toString(), bookBorRet.getFullname()+"已经确认领用了借阅的档案。", ShortMessage.MSG_TYPE_SYS);
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	
	/**
	 * 显示列表
	 */
	public String listForPublic(){
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUser().getUserId().toString());
		List<BookBorRet> list= bookBorRetService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer=JsonUtil.getJSONSerializer("borrowTime","returnTime","lastReturnTime");
		buff.append(serializer.exclude(new String[]{"class"}).serialize(list));
		buff.append("}");
		jsonString=buff.toString();
		return SUCCESS;
	}
	
	public String export()throws Exception{
		String format = getRequest().getParameter("format");
		String quarter = getRequest().getParameter("quarter");
		String exportYear = getRequest().getParameter("exportYear");
		
		String fileName="朗捷通智能";
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("reporter", ContextUtil.getCurrentUser().getFullname());
		String deptName = ContextUtil.getCurrentUser().getDepartment().getDepName();
		List<AppUser> userList = appUserService.findByRoleNameFZ(deptName);
		String reportLeader = "";
		if(userList!=null&&userList.size()>0){
			reportLeader = userList.get(0).getFullname();
		}
		dataMap.put("reportLeader", reportLeader);
		Map<Object,Object> parmsMap = new HashMap<Object,Object>();
		parmsMap.put("dataMap", dataMap);
		//季节
		String season = "";
		String beginDate = "";
		String endDate = "";
		String reportTime = "";
		String reportNo = "";
		int month;
		try {
			month=Integer.valueOf(quarter);
		} catch (Exception e) {
			month = DateUtil.month();
		}
		String year=null;
		if(StringUtils.isEmpty(exportYear)){
			year= DateUtil.year()+"";
		}else{
			year=exportYear;
		}
		
		
		switch(month){
			case 1:
			case 2:
			case 3:
				season = String.valueOf(1);
				beginDate = year+"-01-01";
				endDate = year+"-03-31";
				reportTime = year+"/1/1"+"-"+ year+"/3/31";
				reportNo = "ZLGL"+year+"—01 号";
				fileName+=year+"年第1季度";
				break;
			case 4:
			case 5:
			case 6:
				season = String.valueOf(2);
				beginDate = year+"-04-01";
				endDate = year+"-06-30";
				reportTime = year+"/4/1"+"-"+ year+"/6/30";
				reportNo = "ZLGL"+year+"—02 号";
				fileName+=year+"年第2季度";
				break;
			case 7:
			case 8:
			case 9:
				season = String.valueOf(3);
				beginDate = year+"-07-01";
				endDate = year+"-09-30";
				reportTime = year+"/7/1"+"-"+ year+"/9/30";
				reportNo = "ZLGL"+year+"—03 号";
				fileName+=year+"年第3季度";
				break;
			case 10:
			case 11:
			case 12:
				season = String.valueOf(4);
				beginDate = year+"-10-01";
				endDate = year+"-12-31";
				reportTime = year+"/10/1"+"-"+ year+"/12/31";
				reportNo = "ZLGL"+year+"—04 号";
				fileName+=year+"年第4季度";
				break;
		}
		fileName+="资料管理报告";
		parmsMap.put("season",season);
		parmsMap.put("beginDate",beginDate);
		parmsMap.put("endDate",endDate);
		dataMap.put("reportTime", reportTime);
		dataMap.put("reportNo", reportNo);
		parmsMap.put("logo","goldMantis.jpg"); 
		List<String> jasperNameList = new ArrayList<String>();
		jasperNameList.add("bookReport");
		jasperNameList.add("bookReport_subreport1");
		jasperNameList.add("bookReport_subreport2");
		jasperNameList.add("bookReport_subreport3");
		ExportUtil.exportMultiByJdbc(jasperNameList, fileName, format, parmsMap);
		return SUCCESS;
	}
	
}
