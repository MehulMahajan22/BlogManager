package com.blog.UserBlogs;

import com.blog.UserBlogs.filters.LoginCheckFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class UserBlogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserBlogsApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterUrl()
	{
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new LoginCheckFilter());
		filterRegistrationBean.addUrlPatterns("/blog/addBlog");
		filterRegistrationBean.addUrlPatterns("/blog/updateBlog");
		filterRegistrationBean.addUrlPatterns("/blog/deleteBlog");
		filterRegistrationBean.addUrlPatterns("/blog/myBlogs");
		filterRegistrationBean.addUrlPatterns("/blog/getBlog/*");
		filterRegistrationBean.addUrlPatterns("/blog/getBlogComment/*");
		return filterRegistrationBean;
	}
}
