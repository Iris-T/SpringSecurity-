package cn.iris.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Iris 2022/1/8
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 【重写方法】授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1.首页所有人可访问
        // 2.功能页面对应权限可进入
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("normal")
                .antMatchers("/level2/**").hasRole("vip")
                .antMatchers("/level3/**").hasRole("root");

        // 无访问权限跳转至登录页【需要开启登录页面】
        http.formLogin().loginPage("/tologin").loginProcessingUrl("/login");

        // 防止网页工具：get&post
        http.csrf().disable();//关闭csrf功能，不允许登录失败进行其他操作（不进行其他跳转）
        http.logout().logoutSuccessUrl("/");

        //记住我==>Cookie
        http.rememberMe().rememberMeParameter("remember");
    }

    /**
     * 【重写方法】认证
     * 问题1：PasswordEncoder
     * 解决1：给密码使用SpringSecurity加密
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("iris")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("root","normal","vip")
                .and()
                .withUser("molly")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("normal","vip")
                .and()
                .withUser("guest")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("normal");
    }
}
