package co.id.ferry.phonebook.utils;

import co.id.ferry.phonebook.config.Constant;
import co.id.ferry.phonebook.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationUtils {
    Logger logger = LoggerFactory.getLogger(ValidationUtils.class);

    public boolean emailValidation(String email){
        Pattern pattern = Pattern.compile(Constant.regexPatternEmail);
        Matcher validation = pattern.matcher(email);
        boolean result = validation.matches();
        logger.info("Validation Email : {}",String.valueOf(result));
        return result;
    }
}
