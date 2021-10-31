package pl.fullsoft.soap.server;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/countries/*", "/ws/hours/*");
    }

    // WSDL dostępny pod adresem: localhost:8080/ws/countries/countries.wsdl
    @Bean(name = "countries")
    public DefaultWsdl11Definition wsdl11DefinitionCountries(@Qualifier("countriesSchema") XsdSchema
                                                                     countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws/countries");
        wsdl11Definition.setTargetNamespace("http://www.fullsoft.pl/soap-server/countries");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
    }

    // WSDL dostępny pod adresem: localhost:8080/ws/hours/hours.wsdl
    @Bean(name = "hours")
    public DefaultWsdl11Definition wsdl11DefinitionHours(@Qualifier("hoursSchema") XsdSchema hoursSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("HoursPort");
        wsdl11Definition.setLocationUri("/ws/hours");
        wsdl11Definition.setTargetNamespace("http://www.fullsoft.pl/soap-server/hours");
        wsdl11Definition.setSchema(hoursSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema hoursSchema() {
        return new SimpleXsdSchema(new ClassPathResource("hours.xsd"));
    }

}
