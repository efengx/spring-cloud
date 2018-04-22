package com.yangst.cloud.demo.repository.queryFilter;


import com.yangst.cloud.demo.entity.User;
import com.yangst.cloud.demo.common.quary.Where;
import com.yangst.cloud.demo.common.quary.annotation.QBindAttrField;
import com.yangst.cloud.demo.common.quary.annotation.QBindEntity;
import com.yangst.cloud.demo.common.quary.core.BaseQuery;

@QBindEntity(entityClass = User.class)
public class UserQueryFilter extends BaseQuery {
	
	@QBindAttrField(fieldName = "name", where = Where.equal)
	private String name;
	
	@QBindAttrField(fieldName = "userId", where = Where.equal)
    private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
