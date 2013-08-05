package com.xpsoft.oa.workflow.event;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.oa.model.admin.AssetsApplycontent;
import com.xpsoft.oa.model.admin.Book;
import com.xpsoft.oa.model.admin.BookBorRet;
import com.xpsoft.oa.model.admin.BookSn;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.admin.BookBorRetService;
import com.xpsoft.oa.service.admin.BookService;
import com.xpsoft.oa.service.admin.BookSnService;
import com.xpsoft.oa.service.flow.ProcessRunService;

public class BookBorrowFinishListener implements EventListener{
	private Log logger=LogFactory.getLog(BookBorrowFinishListener.class);
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		
		BookBorRetService bookBorRetService = (BookBorRetService) AppUtil.getBean("bookBorRetService");
		BookSnService bookSnService = (BookSnService) AppUtil.getBean("bookSnService");
		BookService bookService = (BookService) AppUtil.getBean("bookService");
		ProcessRunService processRunService=(ProcessRunService)AppUtil.getBean("processRunService");
		//获取流程发起者对象
		AppUser flowStartUser=(AppUser)execution.getProcessInstance().getVariable("flowStartUser");
		//获取表单参数
		OpenProcessInstance pi=execution.getProcessInstance();
		
		ProcessRun processRun=processRunService.getByExeId(pi.getId());
		
//		String bookId=(String)pi.getVariable("bookId");//档案Id
//		String bookName=(String)pi.getVariable("bookName");//档案名称
		Object resultData=pi.getVariable("bookresultGridData");//资产列表
//		Date returnTime=(Date)pi.getVariable("returnTime");//归还时间
		
		
		String[] resultArray={};
		if(null!=resultData)
			resultArray=resultData.toString().split(",");
		
//		Set<AssetsApplycontent> dataList=new HashSet<AssetsApplycontent>();
		BookBorRet bookBorRet=null;
		
		int height=resultArray.length/7;
		int width=7;
		int index=0;
		
		lableA://行的循环的标签 
		for (int i = 0; i < height; i++) {
			bookBorRet=new BookBorRet();
			String bookId="";
			String bookName="";
			String boorowAmount="";
			String returnTime="";
			for (int j = 0; j < width; j++) {	
				String temp=resultArray[index++];
				switch(j){
					case 0:
						if("".equals(temp.substring(temp.indexOf(":")+1))){
							break lableA;
						}else{
							bookId=temp.substring(temp.indexOf(":")+1);
							break;
						}
					case 2:bookName=temp.substring(temp.indexOf(":")+1);break;
					case 5:boorowAmount=temp.substring(temp.indexOf(":")+1);break;
					case 6:returnTime=temp.substring(temp.indexOf(":")+1,temp.indexOf(";"));break;
					
					default : break;
				}
			}
			
			List<BookSn> bookSnList=bookSnService.findBorrowByBookId(Long.parseLong(bookId));
			Book book = bookService.get(Long.parseLong(bookId));
			
			if(bookSnList.size()>0){
				BookSn bookSn=bookSnList.get(0);
				//用系统日期保存图书的借出日期
				Long snId=bookSn.getBookSnId();
				Long recordId=bookBorRetService.getBookBorRetId(snId);
				if(recordId!=null){
					bookBorRetService.remove(recordId);
				}
				bookBorRet.setBookSn(bookSn);
				bookBorRet.setBorrowTime(new Date());
				bookBorRet.setReturnTime(DateUtil.parse(returnTime,"yyyy-MM-dd"));
				bookBorRet.setRegisterName(flowStartUser.getFullname());
				bookBorRet.setRegisterId(flowStartUser.getUserId());
				bookBorRet.setFullname(flowStartUser.getFullname());
				bookBorRet.setUserId(flowStartUser.getUserId());
				bookBorRet.setStatus("已借出");
				bookBorRet.setBorrowIsbn(bookSn.getBookSN());
				bookBorRet.setBookName(bookName);
				bookBorRet.setProcessRunId(processRun.getRunId());
				bookBorRet.setBoorowAmount(Integer.parseInt(boorowAmount));
				bookBorRetService.save(bookBorRet);
				
				//执行借出操作时将图书的状态改为1（表示借出状态）
				bookSn.setStatus(new Short((short) 1));
				bookSnService.save(bookSn);
				
				
				//当图书借出操作成功后将未借出的图书数量-1
				book.setLeftAmount(book.getLeftAmount()-Integer.parseInt(boorowAmount));
				bookService.save(book);
			}
		}
		
		
		
		
		
		
		
		
		//所有参与流程的都发消息
		AppUtil.sendSysShortMsg(execution.getProcessInstance().getId(), flowStartUser);

	}

}
