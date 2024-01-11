//package com.weng.openapiserver.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@Slf4j
//public class WebMvcConfig implements WebMvcConfigurer
//{
//    @Override
//    public void addInterceptors(InterceptorRegistry registry)
//    {
//        //添加的令牌校验拦截器
//
//
//    }
//
////    /**
////     * 扩展mvc中的消息转换器
////     * @param converters
////     */
////    @Override
////    public void extendMessageConverters(List<HttpMessageConverter<?>> converters)
////    {
////        MappingJackson2HttpMessageConverter messageConverter=new MappingJackson2HttpMessageConverter();
////        messageConverter.setObjectMapper(new JsonConfig());
////        converters.add(0,messageConverter);
////    }
//}
