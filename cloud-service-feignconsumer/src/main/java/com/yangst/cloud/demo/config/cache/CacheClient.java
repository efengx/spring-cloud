package com.yangst.cloud.demo.config.cache;

import java.util.List;

/**
 * 
 * 缓存客户端接口类
 *
 * @version 
 * @author liuyi  2016年4月25日 下午1:48:15
 *
 */
public interface CacheClient {
	
	/**
	 * 
	 * 设置对象
	 * 
	 * @author liuyi 2016年4月20日
	 * @param key
	 * @param object
	 * @param clazz
	 * @return
	 */
    public <T> boolean set(String prefix, String key, T t);
    
    /**
     * 
     * 【设置小键的值】
     * 
     * @author Hermit 2017年4月21日
     * @param realKey
     * @param hashKey
     * @param t
     * @return
     */
    public <T> boolean hashSet(String realKey, String hashKey, T t);
    
    /**
     * 
     * 【得到最外层的大键的值】
     * 
     * @author Hermit 2017年4月21日
     * @param realKey
     * @return
     */
    public <T> T hashGet(String realKey);
    
    /**
     * 
     * 【根据大键和小键，得到小键的值】
     * 
     * @author Hermit 2017年4月21日
     * @param realKey
     * @param hashKey
     * @return
     */
    public <T> T hashGet(String realKey, String hashKey);
    
    /**
     * 
     * 【根据最外层大键删除所有的值】
     * 
     * @author Hermit 2017年4月21日
     * @param realKey
     * @return
     */
    public <T> boolean hashDelete(String realKey);
    
    /**
     * 
     * @Title: hashDelete 
     * @Description: 【根据最外层大键和小键删除所有的值】
     * @param realKey
     * @param hashKey
     * @return    设定文件 
     * boolean    返回类型 
     * @author Yangst
     * @throws
     */
    public <T> boolean hashDelete(String realKey, String hashKey);
    
	/**
	 * 
	 * 设置对象
	 * 
	 * @author liuyi 2016年4月20日
	 * @param key
	 * @param object
	 * @param timeout
	 * @param clazz
	 * @return
	 */
    public <T> boolean set(String prefix, String key, T t, Long timeout);
    
    /**
     * 
     * 设置列表缓存
     * 
     * @author liuyi 2016年4月25日
     * @param prefix
     * @param key
     * @param t
     * @param timeout
     * @return
     */
    public <T> boolean setList(String prefix, String key, List<T> t);
    
    /**
     * 
     * 获取对象
     * 
     * @author liuyi 2016年4月20日
     * @param key
     * @return
     */
    public <T> T get(String prefix, String key);
    
    /**
     * 设置对象
     * @param prefix
     * @param key
     * @param t
     * @return
     */
    public <T> boolean setBound(String prefix, String key, T t);
    
    /**
     * 
     * 获取列表缓存
     * 
     * @author liuyi 2016年4月25日
     * @param prefix
     * @param key
     * @param t
     * @return
     */
    public <T> List<T> getList(String prefix, String key);
    
    /**
     * 
     * 删除缓存
     * 
     * @author liuyi 2016年4月20日
     * @param key
     */
    public void delete(String prefix, String key);
    
    
    /******************************************/
    /**
	 * 压栈
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long push(String key, String value);

	/**
	 * 出栈
	 * 
	 * @param key
	 * @return
	 */
	public String pop(String key);

	/**
	 * 入队
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long in(String key, String value);

	/**
	 * 出队
	 * 
	 * @param key
	 * @return
	 */
	public String out(String key);

	/**
	 * 栈/队列长
	 * 
	 * @param key
	 * @return
	 */
	public Long length(String key);

	/**
	 * 范围检索
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> range(String key, int start, int end);

	/**
	 * 移除
	 * 
	 * @param key
	 * @param i
	 * @param value
	 */
	public void remove(String key, long i, String value);

	/**
	 * 检索
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String index(String key, long index);

	/**
	 * 置值
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void set(String key, long index, String value);

	/**
	 * 裁剪
	 * 
	 * @param key
	 * @param start
	 * @param end
	 */
	public void trim(String key, long start, int end);
}
