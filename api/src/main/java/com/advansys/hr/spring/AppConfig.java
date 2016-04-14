package com.advansys.hr.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.advansys.hr" })
public class AppConfig extends WebMvcConfigurerAdapter {

    // ========= Overrides ===========

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }

    // ========= Beans ===========

    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver() {
        return new CookieLocaleResolver();
    }

    @Bean(name = "messageSource")
    public MessageSource getMessageSources() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("/WEB-INF/resources/properties/clientMessages");
        messageSource.setCacheSeconds(0);

        return messageSource;
    }
    
    @Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

}
