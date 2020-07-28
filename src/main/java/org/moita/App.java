package org.moita;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static java.util.Objects.nonNull;

public class App
{
    private static final Logger LOGGER = LogManager.getLogger(App.class);
    private static final String FILE_NAME = "config.json";

    public static void main( String[] args ) {

        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = App.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            Config config = mapper.readValue(inputStream, Config.class);
            LOGGER.info("getResourceAsStream = {}", config.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            URL resource = App.class.getClassLoader().getResource(FILE_NAME);

            if (nonNull(resource)) {
                LOGGER.info("Path: {}", resource.getPath());

                Config config = mapper.readValue((InputStream) resource.getContent(), Config.class);
                LOGGER.info("getResource = {}", config.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
