package com.yangst.cloud.demo.repository.queryFilter;


import com.yangst.cloud.demo.entity.User;
import com.yangst.cloud.demo.quary.Where;
import com.yangst.cloud.demo.quary.annotation.QBindAttrField;
import com.yangst.cloud.demo.quary.annotation.QBindEntity;
import com.yangst.cloud.demo.quary.core.BaseQuery;

@QBindEntity(entityClass = User.class)
public class OrderQueryFilter extends BaseQuery {
	
	@QBindAttrField(fieldName = "orderId", where = Where.equal)
	private Long orderId;
	
	@QBindAttrField(fieldName = "userId", where = Where.equal)
    private Long userId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}
