package com.xpsoft.oa.dao.system;

import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.system.FileAttach;

public abstract interface FileAttachDao extends BaseDao<FileAttach>
{
  public abstract void removeByPath(String paramString);
  public abstract FileAttach getByPath(String paramString);
  public List<FileAttach> getFileAttachById(String fileIds);
}
