package com.yangst.cloud.demo.config.properties.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 
 * 【请在此写上此类功能描述文字】
 *
 * @version 
 * @author Administrator  2017年8月3日 下午2:44:45
 *ignoreUnknownFields = false告诉Spring Boot在有属性不能匹配到声明的域的时候抛出异常
 */
@ConfigurationProperties(prefix = DataSourceMasterConfig.DS, ignoreUnknownFields = true)
public class DataSourceMasterConfig {
	
	//对应配置文件里的配置键
    public final static String DS="spring.master.datasource";
	
	// master
    private String url;

	private String username;

	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

}
