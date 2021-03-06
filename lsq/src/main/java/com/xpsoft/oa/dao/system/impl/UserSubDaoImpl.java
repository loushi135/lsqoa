package com.xpsoft.oa.dao.system.impl;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.UserSubDao;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.UserSub;
import java.util.ArrayList;
import java.util.List;

public class UserSubDaoImpl extends BaseDaoImpl<UserSub> implements UserSubDao {
	public UserSubDaoImpl() {
		super(UserSub.class);
	}

	public List<Long> upUser(Long userId) {
		String hql = "from UserSub vo where vo.subAppUser.userId=?";
		Object[] objs = { userId };
		List<UserSub> list = findByHql(hql, objs);
		List idList = new ArrayList();
		for (UserSub sb : list) {
			idList.add(sb.getUserId());
		}
		return idList;
	}

	public List<Long> subUsers(Long userId) {
		String hql = "from UserSub vo where vo.userId=?";
		Object[] objs = { userId };
		List<UserSub> list = findByHql(hql, objs);
		List idList = new ArrayList();
		for (UserSub sb : list) {
			idList.add(sb.getSubAppUser().getUserId());
		}
		return idList;
	}
}
