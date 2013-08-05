package com.xpsoft.oa.service.admin.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.core.util.DateUtil;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.dao.admin.BookDao;
import com.xpsoft.oa.dao.admin.BookSnDao;
import com.xpsoft.oa.dao.admin.BookTypeDao;
import com.xpsoft.oa.model.admin.Book;
import com.xpsoft.oa.model.admin.BookSn;
import com.xpsoft.oa.model.admin.BookType;
import com.xpsoft.oa.service.admin.BookService;

public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService{
	private BookDao dao;
	@Autowired
	private BookTypeDao bookTypeDao ;
	@Autowired
	private BookSnDao bookSnDao;
	public BookServiceImpl(BookDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<Book> findByTypeId(Long typeId, PagingBean pb) {
		return dao.findByTypeId(typeId, pb);
	}

	@Override
	public String getNextID(Long typeId) {
		return dao.getNextID(typeId);
	}
	public void readerExcel(String[] filePath) throws Exception{
		List<BookType> bookTypelist = bookTypeDao.getAll();
		if(filePath != null && filePath.length > 0){
			for (int k = 0; k < filePath.length; k++) {
				Workbook workbook = null;
				workbook = Workbook.getWorkbook(new File(filePath[k]));
				Sheet[] sheets = workbook.getSheets();
				if(sheets != null && sheets.length> 0){
					List<Book> list = new ArrayList<Book>();
					List<Book> list1 = new ArrayList<Book>();
					for (int i = 0; i < sheets.length; i++) {
						Sheet sheet = sheets[i];
						int rsColumns = sheet.getColumns();
						int rsRows = sheet.getRows();
						for (int j = 0; j < rsRows; j++) {
							Book book = new Book();
							row:
							for (int j2 = 0; j2 < rsColumns; j2++) {
								Cell cell = sheet.getCell(j2,j);
								String cellStr = cell.getContents();
								switch (j2) {
								case 0:
									if(StringUtils.isBlank(cellStr) || cellStr.trim().equals("档案编号")){
										break row;
									}else{
										book.setIsbn(cellStr);
									}
									break;
								case 1:
									book.setBookName(cellStr);
									break;
								case 2:
									//判读是否存在该类型
									long typeIdLong = this.compareWithName(bookTypelist, cellStr);
									if(typeIdLong == -1l){
										book.setTypeId(null);
										book.setRemark(cellStr);
									}else{
										book.setTypeId(typeIdLong);
									}
									break;
								case 3:
									try {
										book.setAmount(Integer.parseInt(cellStr));
										book.setLeftAmount(Integer.parseInt(cellStr));
									} catch (NumberFormatException e) {
										book.setAmount(1);
										book.setLeftAmount(1);
									}
									break;
								case 4:
									try {
										book.setPageCount(Integer.parseInt(cellStr));
									} catch (NumberFormatException e) {
										book.setPageCount(1);
									}
									break;
								case 5:
									book.setSecretLevel(0);
									break;
								case 6:
									if(cellStr != null && cellStr.trim() != ""){
										Calendar calendar = Calendar.getInstance(); 
										String[] DateStrs = cellStr.split("\\.");
										if(DateStrs.length == 1){
											try {
												int year = Integer.parseInt(DateStrs[0]);
												if(year > DateUtil.year()){
													year = DateUtil.year();
												}
												calendar.set(year, 1, 1);
											} catch (NumberFormatException e) {
												book.setCreateDate(new Date());
												break;
											}
										}else if(DateStrs.length == 2){
											try {
												int year = Integer.parseInt(DateStrs[0]);
												int month = Integer.parseInt(DateStrs[1]);
												if(year > DateUtil.year()){
													year = DateUtil.year();
												}
												if(month > 12){
													month = DateUtil.month();
												}
												calendar.set(year, month, 1);
											} catch (NumberFormatException e) {
												book.setCreateDate(new Date());
												break;
											}
										}else if(DateStrs.length == 3){
											try {
												int year = Integer.parseInt(DateStrs[0]);
												int month = Integer.parseInt(DateStrs[1]);
												int day = Integer.parseInt(DateStrs[2]);
												if(year > DateUtil.year()){
													year = DateUtil.year();
												}
												if(month > 12){
													month = DateUtil.month();
												}
												if(day > 31){
													day = DateUtil.day();
												}
												calendar.set(year, month, day);
											} catch (NumberFormatException e) {
												book.setCreateDate(new Date());
												break;
											}
										}
										book.setCreateDate(calendar.getTime());
									}else{
										book.setCreateDate(new Date());
									}
									break;
								case 7:
									if(StringUtils.isEmpty(cellStr)){
										book.setAuthor("系统管理员");
									}else{
										book.setAuthor(cellStr);
									}
									break;
								case 8:
									if(StringUtils.isBlank(cellStr)){
										book.setDepartment("系统管理员");
									}else{
										book.setDepartment(cellStr);
									}
									break;
								case 9:
									if("是".equals(cellStr)){
										book.setComplete(true);
									}else{
										book.setComplete(false);
									}
									break;
								case 10:
									if("有".equals(cellStr)){
										book.setPcFile(true);
									}else{
										book.setPcFile(false);
									}
									break;
								case 11:
									if("有".equals(cellStr)){
										book.setPaperFile(true);
									}else{
										book.setPaperFile(false);
									}
									break;
								case 12:
									//book.setLocation(cellStr);
									break;
								case 13:
									if(StringUtils.isNotBlank(cellStr) && cellStr.trim() != "无"){
										Calendar calendar = Calendar.getInstance(); 
										String[] DateStrs = cellStr.split("\\.");
										if(DateStrs.length == 1){
											try {
												int year = Integer.parseInt(DateStrs[0]);
												if(year > DateUtil.year()){
													year = DateUtil.year();
												}
												calendar.set(year, 1, 1);
											} catch (NumberFormatException e) {
												book.setExpirationDate(new Date());
												break;
											}
										}else if(DateStrs.length == 2){
											try {
												int year = Integer.parseInt(DateStrs[0]);
												int month = Integer.parseInt(DateStrs[1]);
												if(year > DateUtil.year()){
													year = DateUtil.year();
												}
												if(month > 12){
													month = DateUtil.month();
												}
												calendar.set(year, month, 1);
											} catch (NumberFormatException e) {
												book.setExpirationDate(new Date());
												break;
											}
										}else if(DateStrs.length == 3){
											try {
												int year = Integer.parseInt(DateStrs[0]);
												int month = Integer.parseInt(DateStrs[1]);
												int day = Integer.parseInt(DateStrs[2]);
												if(year > DateUtil.year()){
													year = DateUtil.year();
												}
												if(month > 12){
													month = DateUtil.month();
												}
												if(day > 31){
													day = DateUtil.day();
												}
												calendar.set(year, month, day);
											} catch (NumberFormatException e) {
												book.setExpirationDate(new Date());
												break;
											}
										}
										book.setExpirationDate(calendar.getTime());
									}else{
										book.setExpirationDate(null);
									}
									break;
								case 14:
									System.out.println(cellStr);
									break;
								case 15:
									if(book.getTypeId() != null){
										book.setRemark(cellStr);
									}
									break;
								default:
									break;
								}
								if(j2 > 16){
									break;
								}
							}
							if(book.getTypeId() != null){
								book.setState(1);
								list.add(book);
							}else{
								list1.add(book);
							}
							//column end;
						}
						// row end;
					}
					// sheets end;
					
					for(int i = 0 ; i< list.size(); i++){
						dao.save(list.get(i));
					}
					workbook.close();
//					System.out.println("****************"+list.size());
//					System.out.println("****************"+list1.size());
					//导出
//					WritableWorkbook writableWorkbook=Workbook.createWorkbook(new File("D:\\2.xls")); 
//					WritableSheet sheet=writableWorkbook.createSheet("sheet1",0);
//					for (int row = 0; row < list1.size(); row++) {
//						for(int column = 0; column < 17;column++){
//							Book book1 = list1.get(row);
//							Label label = null;
//							switch (column) {
//							case 0:
//								label = new Label(column, row, book1.getIsbn()==null?"":book1.getIsbn());
//								sheet.addCell(label);
//								break;
//							case 1:
//								label = new Label(column, row, book1.getBookName()==null?"":book1.getBookName());
//								sheet.addCell(label);
//								break;
//							case 2:
//								label = new Label(column, row, book1.getRemark());
//								sheet.addCell(label);
//								break;
//							case 3:
//								label = new Label(column, row, book1.getAmount()==null?"":book1.getAmount()+"");
//								sheet.addCell(label);
//								break;
//							case 4:
//								label = new Label(column, row, book1.getPageCount()==null?"":book1.getPageCount()+"");
//								sheet.addCell(label);
//								break;
//							case 5:
//								label = new Label(column, row, book1.getSecretLevel()==null?"":book1.getSecretLevel()+"");
//								sheet.addCell(label);
//								break;
//							case 6:
//								label = new Label(column, row, book1.getCreateDate()==null?"":DateUtil.formatDate(book1.getCreateDate()));
//								sheet.addCell(label);
//								break;
//							case 7:
//								label = new Label(column, row, book1.getAuthor()==null?"":book1.getAuthor());
//								sheet.addCell(label);
//								break;
//							case 8:
//								label = new Label(column, row, book1.getDepartment()==null?"":book1.getDepartment());
//								sheet.addCell(label);
//								break;
//							case 9:
//								label = new Label(column, row, book1.getState()==null?"":book1.getState()+"");
//								sheet.addCell(label);
//								break;
//							case 10:
//								label = new Label(column, row, book1.getPcFile()==null?"":book1.getPcFile()==true?"有":"无");
//								sheet.addCell(label);
//								break;
//							case 11:
//								label = new Label(column, row, book1.getPaperFile()==null?"":book1.getPaperFile()==true?"有":"无");
//								sheet.addCell(label);
//								break;
//							case 12:
//								label = new Label(column, row, book1.getLocation()==null?"":book1.getLocation());
//								sheet.addCell(label);
//								break;
//							case 13:
//								label = new Label(column, row, book1.getExpirationDate()==null?"无":DateUtil.formatDate(book1.getExpirationDate()));
//								sheet.addCell(label);
//								break;
//							case 14:
//								label = new Label(column, row, "");
//								sheet.addCell(label);
//								break;
//							case 15:
//								label = new Label(column, row, book1.getRemark()==null?"":"");
//								sheet.addCell(label);
//								break;
//							default:
//								break;
//							}
//						}
//					}
//					writableWorkbook.write();
//					writableWorkbook.close();
				}
			}
		}
	}
	
	private Long compareWithName(List<BookType> bookTypelist,String typeName){
		if(bookTypelist != null && !bookTypelist.isEmpty()){
			for (int i = 0; i < bookTypelist.size(); i++) {
				if(bookTypelist.get(i).getTypeName().trim().equals(typeName.trim())){
					return bookTypelist.get(i).getTypeId();
				}
			}
		}
		return -1l;
	}

	@Override
	public void updateBookData() {
		// TODO Auto-generated method stub
		List<Book> bookList = dao.findByHql("from Book where bookId > 1717",null);
		String bookSnNumber = "";
		for(Book book:bookList){
			for (int i = 1; i <= book.getLeftAmount(); i++) {
				// 添加图书成功后，根据ISBN和图书数量自动生成每本图书SN号
				BookSn bookSn = new BookSn();
				// 每本书的bookSn号根据书的ISBN号和数量自动生成,
				// 如书的ISBN是123,数量是3,则每本书的bookSn分别是：123-1,123-2,123-3
				bookSnNumber = "-" + i;//book.getIsbn() + "-" + i;
				bookSn.setBookId(book.getBookId());
				bookSn.setBookSN(bookSnNumber);
				// 默认书的状态是0表示未借出
				bookSn.setStatus(new Short((short) 0));
				// 添加bookSn信息
				bookSnDao.save(bookSn);
			}
		}
	}
	
	
}