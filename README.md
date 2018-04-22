# spring-cloud-demo
based on: springboot 2.0.0.release, springcloud Finchley.M9, Groovy the microservices

-- start sequence:   
1.cloud-server-eureka               registration center(cluster)   
2.cloud-server-config               configuration center  
3.cloud-service-feign               service provider     
4.cloud-service-api service         interface        
5.cloud-service-feignconsumer       service consumers, contain: feign（ribbon,hystrix）        
6.cloud-server-hystrix-dashboard    fuse monitoring application   
7.cloud-server-zuul                 Gateway Routing/Verification    
8.cloud-server-turbine              Fuse monitoring cluster application   
9.cloud-service-common              public class
10.cloud-server-admin               service monitoring

-- quick start:
1. Add Spring boot support
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <!-- Add support for redis -->
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
        <version>2.9.0</version>
    </dependency>
		
2.Add support for sharding-jdbc and JPA  
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>commons-beanutils</groupId>
        <artifactId>commons-beanutils</artifactId>
    </dependency>
    <!-- druid connection pool -->
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
    