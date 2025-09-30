package uz.pdp.config;

import org.jspecify.annotations.Nullable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class DispatcherServletInitializer extends AbstractDispatcherServletInitializer {

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebAppConfig.class);
        context.register(SecurityConfiguration.class);
        return context;
    }

    @Override
    protected @Nullable WebApplicationContext createRootApplicationContext() {

        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }


}
