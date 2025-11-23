package org.example.medical_clinic_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.Source;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class JaxbConfig implements WebMvcConfigurer
{

    private static final String JAXB_CONTEXT_PATH = "org.example.medical_clinic_management_system.dto.xml";

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller m = new Jaxb2Marshaller();
        m.setContextPath(JAXB_CONTEXT_PATH);
        m.setSupportJaxbElementClass(true);
        return m;
    }

    @Bean
    public MarshallingHttpMessageConverter jaxbHttpMessageConverter(Jaxb2Marshaller marshaller)
    {
        return new MarshallingHttpMessageConverter(marshaller, marshaller);
    }


}
