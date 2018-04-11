# spring-cloud-demo
基于springboot 2.0.0.release和springcloud Finchley.M9的微服务demo  
****1.cloud-server-eureka 注册中心 注册中心集群   
****2.cloud-server-config 配置中心  
****3.cloud-service-feign 服务提供者     
****4.cloud-service-api 服务接口        
****5.cloud-service-feignconsumer 服务消费者 包含feign（ribbon,hystrix）        
****6.cloud-server-hystrix-dashboard 熔断监控单应用   
****7.cloud-server-zuul 网关 路由/校验    
****8.cloud-server-turbine 熔断监控集群应用   
****9.cloud-service-common 公共类
****10.cloud-server-admin 服务监控
****11.使用
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
		<dependency>
			<groupId>redis.clients</groupId>
			<artifactId>jedis</artifactId>
			<version>2.9.0</version>
		</dependency>
		添加对redis的支持

 **** 12.添加sharding-jdbc与JPA的支持  
     <dependency>
     			<groupId>org.springframework.boot</groupId>
     			<artifactId>spring-boot-starter-data-jpa</artifactId>
     		</dependency>
     
     		<dependency>
     			<groupId>commons-beanutils</groupId>
     			<artifactId>commons-beanutils</artifactId>
     		</dependency>
     
     		<!-- druid连接池 -->
     		<dependency>
     			<groupId>com.alibaba</groupId>
     			<artifactId>druid</artifactId>
     			<version>1.0.12</version>
     		</dependency>
     
     		<!-- mysql -->
     		<dependency>
     			<groupId>mysql</groupId>
     			<artifactId>mysql-connector-java</artifactId>
     			<version>5.1.41</version>
     		</dependency>
     
     		<!-- sharding-jdbc -->
     		<dependency>
     			<groupId>com.dangdang</groupId>
     			<artifactId>sharding-jdbc-core</artifactId>
     			<version>1.5.4</version>
     		</dependency>
     
     		<dependency>
     			<groupId>com.dangdang</groupId>
     			<artifactId>sharding-jdbc-config-common</artifactId>
     			<version>1.5.4</version>
     		</dependency>
     		<dependency>
     			<groupId>com.dangdang</groupId>
     			<artifactId>sharding-jdbc-transaction-storage</artifactId>
     			<version>1.5.4</version>
     		</dependency>
需增加 对/mq/mongoDB的支持