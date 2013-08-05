package com.xpsoft.oa.service.admin.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.xpsoft.core.service.impl.BaseServiceImpl;

import com.xpsoft.oa.dao.admin.BookTypeDao;
import com.xpsoft.oa.model.admin.BookType;
import com.xpsoft.oa.service.admin.BookTypeService;

public class BookTypeServiceImpl extends BaseServiceImpl<BookType> implements
		BookTypeService {
	private BookTypeDao dao;

	public BookTypeServiceImpl(BookTypeDao dao) {
		super(dao);
		this.dao = dao;
	}

	/**
	 * 根据当前的类型ID 获取从该类型向上的所有类型的
	 */
	public BookType[] path(Long typeId) {
		List<BookType> list = new ArrayList<BookType>();

		while (true) {
			BookType current = dao.get(typeId);
			if (current == null) {
				break;
			} else {
				list.add(current);
				typeId = current.getPtypeId();
			}
		}

		BookType[] byarray = new BookType[list.size()];

		for (int i = list.size(); i > 0; i--) {
			byarray[list.size() - i] = list.get(i - 1);
		}

		return byarray;
	}

	public String sPath(Long typeId) {
		StringBuffer sb = new StringBuffer();
		BookType[] btarray = path(typeId);

		for (int i = 0; i < btarray.length - 1; i++) {

			sb.append(btarray[i].getTypeName());

			if (i != btarray.length - 2) {

				sb.append(" > ");
			}
		}

		return sb.toString();
	}

	/**
	 * 获取直接子分类
	 * 
	 * @param typeId
	 * @return
	 */
	public int directSubTypeCount(Long typeId) {

		return getByPtypeId(typeId).size();
	}

	public List<BookType> getByPtypeId(Long typeId) {
		return dao.findByHql(
				"from BookType b where b.ptypeId = ? order by b.typeId",
				new Object[] { typeId });
	}

	private String getChildren(Long typeId) {

		List<BookType> clist = getByPtypeId(typeId);
		if (null == clist || clist.size() == 0) {
			return "[]";
		}

		StringBuffer sb = new StringBuffer();
		sb.append("[");

		for (BookType bookType : clist) {
			sb.append("{id:'");
			sb.append(bookType.getTypeId());
			sb.append("',expanded : true,text:'");
			sb.append(bookType.getTypeName());
			sb.append("',children:");
			sb.append(getChildren(bookType.getTypeId()));
			sb.append("},");
		}

		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");

		return sb.toString();
	}

	@Override
	public String getTree(Long typeId) {

		StringBuffer sb = new StringBuffer();
		sb.append("[{id:-1,text:'朗捷通智能',expanded:true,children:");
		sb.append(getChildren(typeId));
		sb.append("}]");
		return sb.toString();
	}

	@Override
	public BookType save(BookType bookType) {

		// 新增情况下 生成 SN 的过程
		if (bookType.getTypeId() == null || bookType.getTypeId() <= 0) {
			createSN(bookType);
			return super.save(bookType);
		} else {
			BookType bt = get(bookType.getTypeId());
			//bookType.setTypeSN(bt.getTypeSN());
			bookType.setTypeSN(bookType.getTypeSN());
			bookType.setIndex(bt.getIndex());
			return super.merge(bookType);
		}

	}

	private Object getObjectByHql(String hql, Object[] args) {
		List<BookType> btlist = dao.findByHql(hql, args);

		for (Object bt : btlist) {
			// if (bt != null) {
			// sn = bt.toString();
			// int ascii = (int) sn.charAt(0);
			// sn = ((char) (ascii + 1)) + "";
			// bookType.setTypeSN(sn);
			// }
			return bt;
		}
		return null;
	}

	private void createSN(BookType bookType) {

		// 如果不存在编号 则生成编号
		if (StringUtils.isBlank(bookType.getTypeSN())) {

			String sn = "A";

			// 如果当前的分类为最顶级分类 则 使用字母ABCD作为编号分类
			if (bookType.getPtypeId() <= 0) {
				Object prevSN = getObjectByHql(
						"select max(typeSN) from BookType b where b.ptypeId = -1 ",
						null);
				if (prevSN != null) {
					sn = prevSN.toString();
					int ascii = (int) sn.charAt(0);
					sn = ((char) (ascii + 1)) + "";

				}
			} else {

				BookType btp = get(bookType.getPtypeId());
				if (null != btp) {
					StringBuffer sb = new StringBuffer();
					sb.append(btp.getTypeSN());
					sb.append("-");
					Object nowCount = getObjectByHql(
							"select max(index) from BookType b where b.ptypeId = ? ",
							new Object[] { bookType.getPtypeId() });

					if (nowCount == null) {
						nowCount = 0;
					}

					int count = Integer.parseInt(nowCount.toString());
					bookType.setIndex(++count);
					sb.append(count);
					sn = sb.toString();

				}

			}
			bookType.setTypeSN(sn);
		}

	}

	@Override
	public String multiDel(String[] ids) {

		String jsonString = "{success:true}";

		if (ids != null) {
			for (String id : ids) {
				// QueryFilter filter = new QueryFilter(getRequest());
				// filter.addFilter("Q_bookType.typeId_L_EQ", id);
				// List<Book> list = bookService.getAll(filter);

				Object bookcount = getObjectByHql(
						"select count(*) from Book b where b.bookType.typeId = ?",
						new Object[] { new Long(id) });

				// 存在被引用的情节
				if (Integer.parseInt(bookcount.toString()) > 0) {

					BookType errorType = get(new Long(id));
					if (null != errorType) {
						jsonString = "{success:false,message:'类型["
								+ errorType.getTypeName()
								+ "]下还有档案，请将档案移走后再删除！'}";
					} else {
						jsonString = "{success:false,message:'该类型下还有档案，请将档案移走后再删除！'}";
					}
					return jsonString;
				}

				Object childrencount = getObjectByHql(
						"select count(*) from BookType b where b.ptypeId = ?",
						new Object[] { new Long(id) });

				// 分类下存在子类的情况
				if (Integer.parseInt(childrencount.toString()) > 0) {

					BookType errorType = get(new Long(id));
					if (null != errorType) {
						jsonString = "{success:false,message:'类型["
								+ errorType.getTypeName()
								+ "]下还有子分类，请将子分类移走后再删除！'}";
					} else {
						jsonString = "{success:false,message:'该类型下还有子分类，请将子分类移走后再删除！'}";
					}
					return jsonString;
				}

				super.remove(new Long(id));
			}
		}

		return jsonString;
	}
}