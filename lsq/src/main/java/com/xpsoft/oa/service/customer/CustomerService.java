package com.xpsoft.oa.service.customer;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.customer.Customer;

public abstract interface CustomerService extends BaseService<Customer>
{
  public abstract boolean checkCustomerNo(String paramString);
}

