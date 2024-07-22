package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class RestServices
{
    public static void main(String[] args)
    {
        SpringApplication.run(RestServices.class, args);
    }

    @Bean(name = "props")
    public static Properties getProperties()
    {
        Properties properties = new Properties();
        try
        {
            properties.load(new FileReader("restservices.properties"));
        }
        catch (IOException exception)
        {
            System.err.println("Cannot find restservices.properties " + exception);
        }

        return properties;
    }
}