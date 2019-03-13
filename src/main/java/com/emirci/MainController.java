package com.emirci;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by Serdar on 18/12/2016.
 */
@Controller
public class MainController {

    @RequestMapping("/test1")
    @ResponseBody
    public String index() {
        return "Proudly handcrafted by " +
                "<a href='http://netgloo.com/en'>Netglooo</a> :)";
    }

    @RequestMapping(value = "/{lang}", method = RequestMethod.GET)
    public String changeSessionLanguage(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("lang") String lang) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        Locale locale = LocaleContextHolder.getLocale();
        if ("zh".equals(lang)) {
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        } else if ("jp".equals(lang)) {
            localeResolver.setLocale(request, response, new Locale("ja", "JP"));
        } else {
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        }
        return "redirect:/";
    }

}
