package com.test;

import com.test.log.L;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@SpringBootTest
class TestApplicationTests {
    @Autowired
    private Environment environment;

    @Autowired
    private StringEncryptor encryptor;

    @Test
    void contextLoads() {
        L.info(encryptor.encrypt(environment.getProperty("spring.datasource.username")));
        L.info(encryptor.encrypt(environment.getProperty("spring.datasource.password")));
        L.info(encryptor.encrypt(environment.getProperty("spring.datasource.url")));

//        L.info(encryptor.encrypt(environment.getProperty("tencent.secret.id")));
//        L.info(encryptor.encrypt(environment.getProperty("tencent.secret.key")));
//        L.info(encryptor.encrypt(environment.getProperty("tencent.app.id")));
//        L.info(encryptor.encrypt(environment.getProperty("tencent.sign")));
//        L.info(encryptor.encrypt(environment.getProperty("tencent.template.id")));

        L.info(encryptor.encrypt(environment.getProperty("admin.cellphone")));
        L.info(encryptor.encrypt(encrypt(Objects.requireNonNull(environment.getProperty("admin.password")))));
    }

    public static String encrypt(final String password)
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(password.getBytes());
            byte[] byteBuffer = messageDigest.digest();
            StringBuilder strHexString = new StringBuilder();
            for (byte b : byteBuffer) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    strHexString.append('0');
                strHexString.append(hex);
            }
            return strHexString.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            L.error(e.getMessage());
        }
        return null;
    }

}
