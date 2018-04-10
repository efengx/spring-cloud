package com.yangst.cloud.demo.repository.slave;

import java.util.List;

import com.yangst.cloud.demo.entity.User;
import com.yangst.cloud.demo.quary.core.BaseRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * 
 * @author YST
 *
 */
public interface OrderRepositorySlave extends BaseRepository<User, Long> {
	
}
