package com.xpsoft.oa.model.statistics;

import com.google.gson.annotations.Expose;

public class ModelTicket {
	    @Expose
	    protected Long id;
	    @Expose
		protected Long userId;
	    @Expose
		protected String userName;
	    @Expose
		protected String idCard;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getIdCard() {
			return idCard;
		}
		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}

}
