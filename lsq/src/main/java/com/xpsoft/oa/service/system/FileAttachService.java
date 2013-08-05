package com.xpsoft.oa.service.system;

import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.FileAttach;

public abstract interface FileAttachService extends BaseService<FileAttach>
{
  public abstract void removeByPath(String paramString);

  public abstract FileAttach getByPath(String paramString);
  public List<FileAttach> getFileAttachById(String fileIds);
}

