# spring-cloud
+ spring boot 2.0.0.release
+ spring cloud (Finchley.M9)
+ (~~Groovy~~) Deprecated, bad maven integration

# start sequence:   
1.  cloud-server-eureka               
    registration center (cluster)
    
2.  cloud-server-config               
    configuration center  
    
3.  cloud-service-feign               
    service provider     
    
4.  cloud-service-api service         
    interface        
    
5.  cloud-service-feignconsumer       
    service consumers, contain: feign（ribbon,hystrix）
            
6.  cloud-server-hystrix-dashboard    
    fuse monitoring application
       
7.  cloud-server-zuul                 
    Gateway Routing/Verification
        
8.  cloud-server-turbine              
    Fuse monitoring cluster application
       
9.  cloud-service-common              
    public class
    
10. cloud-server-admin                
    service monitoring

### quick start:
Add Spring boot support
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <!-- redis -->
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
        <version>2.9.0</version>
    </dependency>
    
    <!-- jpa -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>commons-beanutils</groupId>
        <artifactId>commons-beanutils</artifactId>
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
</dependencies>
```

## Error handling

### Packet conflict
