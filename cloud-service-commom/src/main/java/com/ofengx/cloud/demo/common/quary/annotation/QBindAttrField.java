package com.yangst.cloud.demo.common.quary.annotation;
import com.yangst.cloud.demo.common.quary.Where;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * 查询绑定属性
 *
 * @version 
 * @author zengjq  2016年10月28日 下午6:20:57
 * 
 */
@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QBindAttrField {
	public abstract String fieldName();
	public abstract Where where();
}
