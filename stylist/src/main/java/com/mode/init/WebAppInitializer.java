package com.mode.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.Log4jConfigListener;

/**
 * This is the equivalent to the web.xml for servlet container.
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
        implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        initLog4j(servletContext);
        initSecurity(servletContext);

        super.onStartup(servletContext);
    }

    // Config log4j that's used during app startup.
    protected void initLog4j(ServletContext servletContext) {
        final String log4jConfigLocationParam = "log4jConfigLocation";
        final String log4jConfigLocation = servletContext.getInitParameter
                (log4jConfigLocationParam);
        if (log4jConfigLocation == null) {
            servletContext.setInitParameter(log4jConfigLocationParam,
                    getDefaultLog4jConfigLocation());
        }

        servletContext.setInitParameter("log4jExposeWebAppRoot", "false");

        servletContext.addListener(Log4jConfigListener.class);
    }

    /* Add the Spring Security filter. */
    protected void initSecurity(ServletContext servletContext) {
        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy())
                .addMappingForUrlPatterns(null, false, "/*");
    }

    protected String getDefaultLog4jConfigLocation() {
        return "classpath:config/log4j.properties";
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{PropertiesConfig.class, MybatisConfig.class, SecurityConfig.class,
                ServiceConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{MvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        MultipartFilter mpf = new MultipartFilter();
        mpf.setMultipartResolverBeanName("multipartResolver");
        return new Filter[]{characterEncodingFilter, new HiddenHttpMethodFilter(), mpf};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/", 50000000,
                50000000, 50000000);

        registration.setMultipartConfig(multipartConfigElement);
    }
}