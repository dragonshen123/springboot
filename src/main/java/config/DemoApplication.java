package config;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.Properties;

@SpringBootApplication(scanBasePackages = {"com.admin.*"})
@ComponentScan(basePackages = "com.admin.controller," +
		                        "com.admin.service," +
		                        "com.admin.pojo,config")
@EnableCaching //开启缓存
@MapperScan("com.admin.dao")//扫描dao 包
@EnableTransactionManagement // 开启事务管理
@EnableScheduling
@EnableAsync(proxyTargetClass = true)
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
      public PageHelper pageHelper(){
		       PageHelper pageHelper = new PageHelper();
		       Properties properties = new Properties();
		        properties.setProperty("offsetAsPageNum","true");
		        properties.setProperty("rowBoundsWithCount","true");
		        properties.setProperty("reasonable","true");
		        properties.setProperty("dialect","oracle");    //配置mysql数据库的方言
		       pageHelper.setProperties(properties);
		         return pageHelper;
		    }

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}
