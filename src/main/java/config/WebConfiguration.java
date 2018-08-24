package config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


import javax.servlet.ServletContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author: ZJL
 * @Description: web配置类
 * @Date: 2018/6/26 10:00
 */

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
    @Value("${xwtec.web.static.version}")
    String webStaticVersion;

    /**
     * 添加解析方式
     * @param converters
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // 设置 response 日期格式
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        // 设置 request 日期格式
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        objectMapper.registerModule(javaTimeModule);
        // 设置不序列化null 值
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL );
        converter.setObjectMapper(objectMapper);
        converters.add(converter);
    }
    /**
     * 添加监控
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //开发阶段不记录日志
        //registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
    }
    @Override
    public void setServletContext(ServletContext servletContext) {
        // 初始化静态资源版本号，目前从配置文件获取需要每次发布修改配置文件静态资源版本号参数，后面可以考虑从数据库系统参数表中获取
       // servletContext.setAttribute("webStaticVersion", webStaticVersion);
        super.setServletContext(servletContext);
    }
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "redirect:/index/index" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.setOrder(Integer.MIN_VALUE);
       /* registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");*/
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        /*registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("/")
                .setCachePeriod(0);*/

        super.addResourceHandlers(registry);
    }

    /**
     * spring security thymeleaf 注册
     * @return
     */
   /* @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }*/
}
