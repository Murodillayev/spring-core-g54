package uz.pdp.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.jspecify.annotations.Nullable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
import uz.pdp.props.AppProps;

public class DispatcherServletInitializer extends AbstractDispatcherServletInitializer {

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebAppConfig.class);
        context.register(AppProps.class);
        context.register(DatasourceConfig.class);
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


    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("");
        registration.setMultipartConfig(multipartConfigElement);
    }
}
