
package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.print("拦截一下");
       //静态文件不拦截
        http.authorizeRequests()
                .antMatchers("/**").permitAll();
        //后台拦截
        http.authorizeRequests()
                .antMatchers( "/admin/**", "/about").permitAll();//所有用户都尅都可以访问
       //首页不拦截
        //http.addFilter(new FilterConfig());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        System.out.print("拦截一下2");
        //解决静态资源被拦截的问题
    web.ignoring().antMatchers("/static/**");
    }

}

