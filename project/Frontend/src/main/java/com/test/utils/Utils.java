package com.test.utils;

import com.test.constant.ViewSize;
import com.test.log.L;
import com.test.view.GUI;
import com.test.view.MessageBox;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class Utils {
    private static String env = "dev";
    private static final Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
    private static final double width = screenRectangle.getWidth();
    private static final double height = screenRectangle.getHeight();

    //SHA-512加密,别人的轮子
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
            MessageBox.show("程序内部错误，密码加密失败");
            L.error(e);
        }
        return null;
    }

    public static boolean goWithDevEnvironment()
    {
        env = "dev";
        if(!Utils.networkAvaliable())
        {
            MessageBox.show("请检查网络连通!");
            return false;
        }
        if(!Utils.backendAvaliable())
        {
            MessageBox.show("无法访问后端服务器，请联系管理员！");
            return false;
        }
        return true;
    }

    public static boolean goWithTestEnvironment()
    {
        env = "test";
        if(!Utils.networkAvaliable())
        {
            MessageBox.show("请检查网络连通!");
            return false;
        }
        if(!Utils.backendAvaliable())
        {
            MessageBox.show("无法访问后端服务器，请联系管理员！");
            return false;
        }
        return true;
    }

    public static boolean goWithProdEnvironment()
    {
        env = "prod";
        if(!Utils.networkAvaliable())
        {
            MessageBox.show("请检查网络连通!");
            return false;
        }
        if(!Utils.backendAvaliable())
        {
            MessageBox.show("无法访问后端服务器，请联系管理员！");
            return false;
        }
        return true;
    }

    public static String getEnv()
    {
        return env;
    }

    public static boolean isDevOrTestEnvironment()
    {
        return !isProdEnvironment();
    }

    public static boolean isProdEnvironment()
    {
        return env.equals("prod");
    }

    public static Properties getProperties()
    {
        Properties properties = new Properties();
        String fileName = "properties/config_"+ getEnv() +".properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream inputStream = loader.getResourceAsStream(fileName))
        {
            if(inputStream != null)
            {
                properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                return properties;
            }
            L.error("Can not load properties properly.InputStream is null.");
            return null;
        }
        catch (IOException e)
        {
            L.error("Can not load properties properly.Message:"+e.getMessage());
            return null;
        }
    }

    public static double countCenterX(double nodeWidth)
    {
        return width/2 - nodeWidth/2;
    }

    public static double countCenterY(double nodeHeight)
    {
        return height/2 - nodeHeight/2;
    }

    public static boolean networkAvaliable()
    {
        try(Socket socket = new Socket())
        {
            socket.connect(new InetSocketAddress("www.baidu.com",443));
            return true;
        }
        catch (IOException e)
        {
            L.error("Can not connect network.");
            L.error(e);
        }
        return false;
    }

    public static boolean backendAvaliable()
    {
        try(Socket socket = new Socket())
        {
            if(isProdEnvironment())
                //自定义的测试后端连接的URL与端口
                socket.connect(new InetSocketAddress("www.test.com",443));
            else
                socket.connect(new InetSocketAddress("127.0.0.1",8080));
            return true;
        }
        catch (IOException e)
        {
            L.error("Can not connect back end server.");
            L.error(e);
        }
        return false;
    }

    public static void centerMainStage()
    {
        Stage stage = GUI.getStage();
        stage.setX(width/2 - ViewSize.MAIN_WIDTH/2);
        stage.setY(height/2 - ViewSize.MAIN_HEIGHT/2);
    }

    public static void centerMessgeBoxStage(Stage stage)
    {
        Stage mainStage = GUI.getStage();
        if(mainStage.getScene() == null)
        {
            stage.setX(width/2 - ViewSize.MESSAGE_BOX_WIDTH/2);
            stage.setY(height/2 - ViewSize.MESSAGE_BOX_HEIGHT/2);
        }
        else
        {
            stage.setX(mainStage.getX()+ ViewSize.MAIN_WIDTH/2- ViewSize.MESSAGE_BOX_WIDTH/2);
            stage.setY(mainStage.getY()+ ViewSize.MAIN_HEIGHT/2- ViewSize.MESSAGE_BOX_HEIGHT/2);
        }
    }

}
