 package com.xpsoft.oa.dao.system.impl;
 
 import java.util.List;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.FunUrlDao;
import com.xpsoft.oa.model.system.FunUrl;
 
 public class FunUrlDaoImpl extends BaseDaoImpl<FunUrl>
   implements FunUrlDao
 {
   public FunUrlDaoImpl()
   {
     super(FunUrl.class);
   }
 
   public FunUrl getByPathFunId(String path, Long funId)
   {
     String hql = "from FunUrl fu where fu.urlPath=? and fu.appFunction.functionId=? ";
     List<FunUrl> funUrlList = findByHql(hql, new Object[] { path, funId });
     FunUrl  funUrl = null;
     if(funUrlList!=null&&funUrlList.size()>0){
    	 funUrl = funUrlList.get(0);
     }
     return funUrl;
   }
 }

