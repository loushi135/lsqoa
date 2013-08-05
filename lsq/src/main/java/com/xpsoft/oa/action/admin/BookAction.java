package com.xpsoft.oa.action.admin;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.admin.Book;
import com.xpsoft.oa.model.admin.BookDel;
import com.xpsoft.oa.model.admin.BookSn;
import com.xpsoft.oa.model.admin.BookType;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.admin.BookDelService;
import com.xpsoft.oa.service.admin.BookService;
import com.xpsoft.oa.service.admin.BookSnService;
import com.xpsoft.oa.service.admin.BookTypeService;
import com.xpsoft.oa.service.system.FileAttachService;

import flexjson.JSONSerializer;

/**
 * 
 * @author csx
 * 
 */
public class BookAction extends BaseAction {
	@Resource
	private BookService bookService;
	@Resource
	private BookTypeService bookTypeService;
	@Resource
	private BookSnService bookSnService;
	@Resource
	private FileAttachService fileAttachService;
	@Resource
	private BookDelService bookDelService;

	private String bookAttachIDs;
	private Book book;

	private Long bookId;

	private Long typeId;
	private BookType bookType;

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getBookAttachIDs() {
		return bookAttachIDs;
	}

	public void setBookAttachIDs(String bookAttachIDs) {
		this.bookAttachIDs = bookAttachIDs;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<Book> list = bookService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = new JSONSerializer();
		buff.append(serializer.exclude(new String[] { "class" })
				.serialize(list));
		buff.append("}");
		jsonString = buff.toString();
		return SUCCESS;
	}

	public String nextID() {
		String id = bookService.getNextID(typeId);
		jsonString = "{success:true,id:'" + id + "'}";
		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {
		String ids = getRequest().getParameter("ids");
		String[] idArr = ids.split(",");
		String delReason = getRequest().getParameter("delReason");
		if (ids != null) {
			for (String id : idArr) {
				Book obj = bookService.get(new Long(id));
				obj.setState(2);//删除;
				bookService.save(obj);
				BookDel bookDel = new BookDel();
				bookDel.setDelReason(delReason);
				bookDel.setBook(obj);
				bookDel.setCreateDate(new Date());
				bookDel.setUser(ContextUtil.getCurrentUser());
				bookDelService.save(bookDel);
//				bookService.remove(new Long(id));
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		Book book = bookService.get(bookId);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = new JSONSerializer();
		sb.append(serializer.include("bookFiles","bookSns").serialize(book));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		String state = getRequest().getParameter("state");
		if ("on".equals(state)) {
			book.setState(1);
		}

		//附件
		String[] fileIDs = getBookAttachIDs().split(",");
		Set bookFiles = new HashSet();
		for (String id : fileIDs) {
			if (!id.equals("")) {
				bookFiles.add((FileAttach) this.fileAttachService.get(new Long(id)));
			}
		}
		this.book.setBookFiles(bookFiles);

		String bookSnNumber = "";
		// bookId为空，说明是新增加图书
		if (book.getBookId() == null) {

			bookService.save(book);
			for (int i = 1; i <= book.getLeftAmount(); i++) {
				// 添加图书成功后，根据ISBN和图书数量自动生成每本图书SN号
				BookSn bookSn = new BookSn();
				// 每本书的bookSn号根据书的ISBN号和数量自动生成,
				// 如书的ISBN是123,数量是3,则每本书的bookSn分别是：123-1,123-2,123-3
				bookSnNumber = book.getIsbn() + "-" + i;
				bookSn.setBookId(book.getBookId());
				bookSn.setBookSN(bookSnNumber);
				// 默认书的状态是0表示未借出
				bookSn.setStatus(new Short((short) 0));
				// 添加bookSn信息
				bookSnService.save(bookSn);
			}
		} else {
			bookService.save(book);
		}

		// String bookSnNumber = "";
		// // bookId为空，说明是新增加图书
		// if (book.getBookId() == null) {
		// // 添加图书时将未借出的数量设置为和图书数量一样
		// book.setLeftAmount(book.getAmount());
		// }
		// bookService.save(book);

		setJsonString("{success:true,bookSnNumber:'" + bookSnNumber + "'}");
		return SUCCESS;
	}

	/**
	 * 增加图书数量时修改图书数量
	 */
	public String updateAmount() {
		Long bookId = Long.valueOf(getRequest().getParameter("bookId"));
		book = bookService.get(bookId);
		int addAmount = Integer
				.parseInt(getRequest().getParameter("addAmount"));
		int amount = book.getAmount() + addAmount;
		BookSn bookSn = null;
		String bookSnNumber = "";
		for (int i = book.getAmount() + 1; i <= book.getAmount() + addAmount; i++) {
			// 添加图书成功后，根据ISBN和图书数量自动生成每本图书SN号
			bookSn = new BookSn();
			// 每本书的bookSn号根据书的ISBN号和数量自动生成,
			// 如书的ISBN是123,数量是3,则每本书的bookSn分别是：123-1,123-2,123-3
			bookSnNumber = book.getIsbn() + "-" + i;
			bookSn.setBookId(book.getBookId());
			bookSn.setBookSN(bookSnNumber);
			// 默认书的状态是0表示未借出
			bookSn.setStatus(new Short((short) 0));
			// 添加bookSn信息
			bookSnService.save(bookSn);
		}
		book.setAmount(amount);
		book.setLeftAmount(book.getLeftAmount() + addAmount);
		bookService.save(book);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = new JSONSerializer();
		sb.append(serializer.exclude(new String[] { "class" }).serialize(book));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 删除图书标签后修改图书数量和未借出数量
	 */
	public String updateAmountAndLeftAmount() {
		Long bookId = Long.valueOf(getRequest().getParameter("bookId"));
		book = bookService.get(bookId);
		int amount = book.getAmount() - 1;
		int leftAmount = book.getLeftAmount() - 1;
		book.setAmount(amount);
		book.setLeftAmount(leftAmount);
		bookService.save(book);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = new JSONSerializer();
		sb.append(serializer.exclude(new String[] { "class" }).serialize(book));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	public String upload() {
		String fileIds = getRequest().getParameter("fileIds");
		String[] ids = fileIds.split(",");
		String[] filePath = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			FileAttach fileAttach = fileAttachService.get(new Long(ids[i]));
			filePath[i] = AppUtil.getAppAbsolutePath() + "attachFiles/"
					+ fileAttach.getFilePath();
		}
		try {
			bookService.readerExcel(filePath);
		} catch (Exception e) {
			setJsonString("{success:false}");
			e.printStackTrace();
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

	public String deleteAttechFile() {
		String filesIndex = getRequest().getParameter("filesIndex");
		String bookId = getRequest().getParameter("bookId");

		Book book = bookService.get(Long.parseLong(bookId));
		String filenames = book.getFileName();
		String filePaths = book.getFilePath();
		if (StringUtils.isNotEmpty(filenames)) {
			StringBuffer sb = new StringBuffer();
			String[] namestrs = filenames.split(";");
			for (int i = 0; i < namestrs.length; i++) {
				if (i == Integer.parseInt(filesIndex)) {
					continue;
				}
				sb.append(namestrs[i] + ";");
			}
			book.setFileName(sb.toString());
		}
		if (StringUtils.isNotEmpty(filePaths)) {
			StringBuffer sb = new StringBuffer();
			String[] filestrs = filePaths.split(";");
			for (int i = 0; i < filestrs.length; i++) {
				if (i == Integer.parseInt(filesIndex)) {
					continue;
				}
				sb.append(filestrs[i] + ";");
			}
			book.setFilePath(sb.toString());
		}
		bookService.save(book);
		StringBuffer sb = new StringBuffer("{success:true,fileName:");
		sb.append("'" + book.getFileName() + "',filePath:");
		sb.append("'" + book.getFilePath() + "'}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
}
