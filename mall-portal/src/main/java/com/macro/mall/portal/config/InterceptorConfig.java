package com.macro.mall.portal.config;

import com.macro.mall.portal.interceptor.ImgInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 添加拦截请求，注册拦截器
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private ImgInterceptor imgInterceptor;

    @Value("${minio.bucketName}")
    private String BUCKET_NAME;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截路径
        StringBuilder path = new StringBuilder("/");
        path.append(BUCKET_NAME);
        path.append("/**");
        registry.addInterceptor(imgInterceptor).addPathPatterns("/img/**");
    }
}
