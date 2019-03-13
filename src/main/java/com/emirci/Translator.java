package com.emirci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import java.util.Locale;

/**
 * Created by serdar on 3/11/19.
 */

@Component
public class Translator {

    private static MessageSource messageSource;

    @Autowired
    Translator(MessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    public static String toLocale(String msg) {
        Locale locale = LocaleContextHolder.getLocale().stripExtensions();
        return messageSource.getMessage(msg, new Object[]{}, locale);
    }
}
