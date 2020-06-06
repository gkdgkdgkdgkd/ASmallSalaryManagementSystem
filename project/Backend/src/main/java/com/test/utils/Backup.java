package com.test.utils;

import com.test.log.L;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@EnableScheduling
public class Backup {
    private static final long INTERVAL = 1000 * 3600 * 12;
    @Value("${backup.command}")
    private String command;
    @Value("${backup.path}")
    private String strPath;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${backup.dataTimeFormat}")
    private String dateTimeFormat;

    @Scheduled(fixedRate = INTERVAL)
    public void startBackup()
    {
        try
        {
            String[] commands = command.split(",");
            String dbname = url.substring(url.lastIndexOf("/")+1);
            commands[2] = commands[2] + username + " --password=" + password + " " + dbname + " > " + strPath +
                    dbname + "_" + DateTimeFormatter.ofPattern(dateTimeFormat).format(LocalDateTime.now())+".sql";
            Path path = Paths.get(strPath);
            if(!Files.exists(path))
                Files.createDirectories(path);
            Process process = Runtime.getRuntime().exec(commands);
            process.waitFor();
            if(process.exitValue() != 0)
            {
                InputStream inputStream = process.getErrorStream();
                StringBuilder str = new StringBuilder();
                byte []b = new byte[2048];
                while(inputStream.read(b,0,2048) != -1)
                    str.append(new String(b));
                L.backupFailed(str.toString());
            }
            L.backupSuccess();
        }
        catch (IOException | InterruptedException e)
        {
            L.backupFailed(e.getMessage());
        }
    }
}
