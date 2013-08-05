package com.xpsoft.oa.dao.customer;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.customer.Customer;

public abstract interface CustomerDao extends BaseDao<Customer>
{
  public abstract boolean checkCustomerNo(String paramString);
}

