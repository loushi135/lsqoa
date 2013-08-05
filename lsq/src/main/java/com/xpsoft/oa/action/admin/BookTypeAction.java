package com.xpsoft.oa.action.admin;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.web.action.BaseAction;
import com.xpsoft.oa.model.admin.Book;
import com.xpsoft.oa.model.admin.BookType;
import com.xpsoft.oa.service.admin.BookService;
import com.xpsoft.oa.service.admin.BookTypeService;

public class BookTypeAction extends BaseAction {
	
	@Resource
	private BookTypeService bookTypeService;
	private BookType bookType;
	@Resource
	private BookService bookService;

	private Long typeId = -1l;

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

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<BookType> list = bookTypeService.getAll(filter);

		for (BookType bookType : list) {

			if (bookType.getPtypeId() == null || bookType.getPtypeId() <= 0)
				bookType.setPath("无父分类");
			else
				bookType.setPath(bookTypeService.sPath(bookType.getTypeId()));

		}

		Type type = new TypeToken<List<BookType>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		jsonString = buff.toString();

		return SUCCESS;
	}

	/**
	 * 显示图书类别下拉选框
	 * 
	 * @return
	 */
	public String tree() {
		String method = getRequest().getParameter("method");

		//		
		// List<BookType> list = bookTypeService.getByPtypeId(typeId);
		// StringBuffer sb = new StringBuffer();
		// int i = 0;
		// if (StringUtils.isNotEmpty(method)) {
		// sb.append("[");
		// } else {
		// i++;
		// sb.append("[{id:'" + 0 + "',text:'图书类别',expanded:true,children:[");
		// }
		// for (BookType bookType : list) {
		// sb.append("{id:'" + bookType.getTypeId() + "',text:'"
		// + bookType.getTypeName() + "',leaf:true},");
		// }
		// if (list.size() > 0) {
		// sb.deleteCharAt(sb.length() - 1);
		// }
		// if (i == 0) {
		// sb.append("]");
		// } else {
		// sb.append("]}]");
		// }
		// setJsonString(sb.toString());

		setJsonString(bookTypeService.getTree(typeId));

		return SUCCESS;
	}

	/**
	 * 删除图书类别
	 * 
	 * @return
	 */
	public String remove() {
		Long typeId = Long.valueOf(getRequest().getParameter("typeId"));
		setBookType(bookTypeService.get(typeId));
		if (bookType != null) {
			QueryFilter filter = new QueryFilter(getRequest());
			filter.addFilter("Q_bookType.typeId_L_EQ", typeId.toString());
			List<Book> list = bookService.getAll(filter);
			if (list.size() > 0) {
				jsonString = "{success:false,message:'该类型下还有图书，请将图书移走后再删除！'}";
				return SUCCESS;
			}
			bookTypeService.remove(typeId);
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		jsonString = bookTypeService.multiDel(ids);
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		BookType bookType = bookTypeService.get(typeId);

		if (null == bookType) {
			bookType = new BookType();
			bookType.setPtypeId(-1l);
			bookType.setTypeId(-1l);
			bookType.setTypeName("无父类别");
		}

		String spath = bookTypeService.sPath(typeId);
		bookType.setPath(spath.equals("") ? "无父类别" : spath);

		Gson gson = new Gson();
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bookType));
		sb.append("}");
		setJsonString(sb.toString());

		System.out.println(sb.toString());
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {

		bookTypeService.save(bookType);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 根据当前的类型ID 获取从该类型向上的所有类型的
	 */
	public String path() {

		StringBuffer sb = new StringBuffer("{success:true,directSubTypeCount:");
		int directSubTypeCount = bookTypeService.directSubTypeCount(typeId);
		sb.append(directSubTypeCount);
		sb.append(",data:");
		if (directSubTypeCount != 0) {
			BookType[] list = bookTypeService.path(typeId);
			Type type = new TypeToken<BookType[]>() {
			}.getType();

			Gson gson = new Gson();
			sb.append(gson.toJson(list, type));
		} else {
			sb.append("null");
		}
		sb.append("}");
		jsonString = sb.toString();
		System.out.println(jsonString);
		return SUCCESS;
	}
}
