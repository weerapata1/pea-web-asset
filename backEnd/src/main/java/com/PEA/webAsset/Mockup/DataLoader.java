package com.PEA.webAsset.Mockup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationArguments applicationArguments;

    @Override
    public void run(String... args) throws Exception {
        if (applicationArguments.containsOption("seed")) {
            System.out.println("Seeding data: Executing data.sql...");
            try (Connection connection = dataSource.getConnection()) {
                ScriptUtils.executeSqlScript(connection, resourceLoader.getResource("classpath:data.sql"));
            } catch (Exception e) {
                System.err.println("Error during seeding: " + e.getMessage());
            }
        } else {
            System.out.println("Skipping data seeding. Use --seed to enable.");
        }
    }
}
