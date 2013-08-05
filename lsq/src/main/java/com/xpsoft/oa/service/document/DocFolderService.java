package com.xpsoft.oa.service.document;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.document.DocFolder;
import java.util.List;

public abstract interface DocFolderService extends BaseService<DocFolder>
{
  public abstract List<DocFolder> getUserFolderByParentId(Long paramLong1, Long paramLong2);

  public abstract List<DocFolder> getFolderLikePath(String paramString);

  public abstract List<DocFolder> getPublicFolderByParentId(Long paramLong);
}

