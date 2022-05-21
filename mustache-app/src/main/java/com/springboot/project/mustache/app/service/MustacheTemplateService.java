package com.springboot.project.mustache.app.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class MustacheTemplateService {

    @Autowired
    private Mustache.TemplateLoader templateLoader;
    @Autowired
    private Mustache.Compiler compiler;
    private static final String CLASS_PATH_TEMPALTE = "person";

    @Autowired
    private ObjectMapper objectMapper;

    private static final ConcurrentHashMap<String, Template> TEMPLATE_CACHE = new ConcurrentHashMap<>();


    public String transform(String inputData) {
        try {
            System.out.println(inputData);
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Object obj = mapper.readValue(inputData, Object.class);
            Template template = TEMPLATE_CACHE.computeIfAbsent(CLASS_PATH_TEMPALTE, s -> {
                try {
                    return compiler.defaultValue("null").withFormatter(new Mustache.Formatter() {
                        @Override
                        public String format(Object value) {
                            if(!"null".equalsIgnoreCase(value.toString())){
                                if(value instanceof  String){
                                    return '\"'+value.toString()+'\"';
                                }
                            }
                            return value.toString();
                        }
                    }).compile(templateLoader.getTemplate(CLASS_PATH_TEMPALTE));
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            });
            String result = template.execute(obj);
            System.out.println(result);
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
