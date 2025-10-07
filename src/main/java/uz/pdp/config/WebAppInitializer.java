package uz.pdp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // Root context = non-web beans like DataSource, Security, Repositories, Services
        return new Class[]{
                DatasourceConfig.class,
                SecurityConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Servlet context = Web (controllers, view resolvers, etc.)
        return new Class[]{
                WebAppConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        // ✅ Only handle web requests
        return new String[]{"/"};
    }
}
