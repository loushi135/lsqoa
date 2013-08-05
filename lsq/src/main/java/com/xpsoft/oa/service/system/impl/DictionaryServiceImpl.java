 package com.xpsoft.oa.service.system.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.system.DictionaryDao;
 import com.xpsoft.oa.model.system.Dictionary;
 import com.xpsoft.oa.service.system.DictionaryService;
 import java.util.List;
 
 public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary>
   implements DictionaryService
 {
   private DictionaryDao dao;
 
   public DictionaryServiceImpl(DictionaryDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<String> getAllItems()
   {
     return this.dao.getAllItems();
   }
 
   public List<String> getAllByItemName(String itemName)
   {
     return this.dao.getAllByItemName(itemName);
   }
 }

