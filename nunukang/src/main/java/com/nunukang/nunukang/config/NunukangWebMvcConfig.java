package com.nunukang.nunukang.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class NunukangWebMvcConfig implements WebMvcConfigurer {

    @Value("${fishPicturePath}")
    String fishPicturePath;

    @Value("${postPicturePath}")
    String postPicturePath;




    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/fish/images/**")
                .addResourceLocations(fishPicturePath);
        registry.addResourceHandler("/post/images/**")
                .addResourceLocations(postPicturePath);
    }

}


