package co.id.ferry.phonebook.config;

import co.id.ferry.phonebook.controller.Controller;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class Config {
    static Logger logger = LoggerFactory.getLogger(Config.class);

    static Path path = null;
    static byte[] configBytes = null;
    static String configString = "";
    static JSONObject configJson = null;

    static {

        // TODO Auto-generated constructor stub
        try {
            Path configPath = Paths.get("","appconfig.json");
            configBytes = Files.readAllBytes(configPath);
            configString = new String(configBytes, StandardCharsets.UTF_8);
            configJson = new JSONObject(configString);
            logger.info("Config appconfig loaded");
        }
        catch (Exception e)
        {
            logger.info("unable to load config");
        }
    }
    public static JSONObject getResponseCodeMapping()
    {
        return (JSONObject)configJson.get("responseCodeMapping");
    }
    public static String getInformationRC(String key)
    {
        try {
            JSONObject responseMessage = getResponseCodeMapping();
            String responseMessages = (String) responseMessage.get(key);
            return responseMessages;
        }
        catch (Exception e)
        {
            return "";
        }
    }
}
