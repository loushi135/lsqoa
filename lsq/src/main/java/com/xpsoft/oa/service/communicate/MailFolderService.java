package com.xpsoft.oa.service.communicate;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.communicate.MailFolder;
import java.util.List;

public abstract interface MailFolderService extends BaseService<MailFolder>
{
  public abstract List<MailFolder> getUserFolderByParentId(Long paramLong1, Long paramLong2);

  public abstract List<MailFolder> getAllUserFolderByParentId(Long paramLong1, Long paramLong2);

  public abstract List<MailFolder> getFolderLikePath(String paramString);
}

