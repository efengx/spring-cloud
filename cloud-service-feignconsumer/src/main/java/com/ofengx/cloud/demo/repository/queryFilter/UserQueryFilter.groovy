package com.ofengx.cloud.demo.repository.queryFilter

import com.ofengx.cloud.demo.common.quary.Where
import com.ofengx.cloud.demo.common.quary.annotation.QBindAttrField
import com.ofengx.cloud.demo.common.quary.annotation.QBindEntity
import com.ofengx.cloud.demo.common.quary.core.BaseQuery
import com.ofengx.cloud.demo.entity.User

@QBindEntity(entityClass = User.class)
class UserQueryFilter extends BaseQuery {

    @QBindAttrField(fieldName = "name", where = Where.equal)
    private String name;

    @QBindAttrField(fieldName = "userId", where = Where.equal)
    private Integer age;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    Integer getAge() {
        return age;
    }

    void setAge(Integer age) {
        this.age = age;
    }
}
