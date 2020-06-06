package com.test.utils;

import com.google.gson.Gson;
import com.test.Main;
import com.test.log.L;
import com.test.view.MessageBox;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Base64;
import java.util.HashMap;

public class AvatarUtils {
    public static final Path TEMP_PATH;

    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    public static final String DEFAULT_AVATAR_PATH;

    static
    {
        TEMP_PATH = Paths.get("temp");
        DEFAULT_AVATAR_PATH = Main.class.getResource("/image/DefaultAvatar.png").toString();
        deletePathIfExists();
        try
        {
            Files.createDirectory(TEMP_PATH);
        }
        catch (IOException e)
        {
            MessageBox.createTempFolderFailed();
            L.error(e);
        }
    }

    public static void deletePathIfExists()
    {
        try
        {
            if(Files.exists(TEMP_PATH)) {
                Files.walkFileTree(TEMP_PATH, new FileVisitor<>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                        Files.delete(path);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path path, IOException e) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path path, IOException e) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }
                });
                Files.delete(TEMP_PATH);
            }
        }
        catch (IOException e)
        {
            MessageBox.deleteTempFileOrFolderFailed();
            L.error(e);
        }
    }

    public static Path getPath(String cellphone)
    {
        return TEMP_PATH.resolve(cellphone);
    }

    public static void createPath(String cellphone)
    {
        Path path = getPath(cellphone);
        try
        {
            if(Files.exists(TEMP_PATH))
            {
                if(!Files.exists(path))
                    Files.createFile(path);
            }
            else
            {
                Files.createDirectory(TEMP_PATH);
                Files.createFile(path);
            }
        }
        catch (IOException e)
        {
            MessageBox.createTempFileFailed();
            L.error(e);
        }
    }

    public static void writeAvatar(File file, String cellphone)
    {
        try
        {
            Files.copy(file.toPath(), getPath(cellphone),StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e)
        {
            MessageBox.writeTempImageFailed();
            L.error(e);
        }
    }

    public static String avatarToString(Path path)
    {
        try
        {
            return new String(encoder.encode(Files.readAllBytes(path)));
        }
        catch (IOException e)
        {
            MessageBox.avatarToStringFailed();
            L.error(e);
            return null;
        }
    }

    public static String avatarsToString(HashMap<String,String> map)
    {
        return new Gson().toJson(map);
    }

    public static void stringToAvatar(String base64Code, String cellphone){
        try
        {
            if(!Files.exists(TEMP_PATH))
                Files.createDirectory(TEMP_PATH);
            if(!Files.exists(getPath(cellphone)))
                Files.createFile(getPath(cellphone));
            Files.write(getPath(cellphone), decoder.decode(base64Code));
        }
        catch (IOException e) {
            MessageBox.stringToAvatarFailed();
            L.error(e);
        }
    }

    public static HashMap<String,String> stringToAvatars(String str)
    {
        HashMap<?, ?> map = new Gson().fromJson(str, HashMap.class);
        if(Check.isEmpty(map))
            return null;
        HashMap<String,String> temp = new HashMap<>();
        map.forEach((k, v)->temp.put((String)k,(String)v));
        return temp;
    }

    public static String getImageViewPath(String cellphone)
    {
        return "file:"+ getPath(cellphone);
    }

    public static boolean exists(String cellphone)
    {
        return Files.exists(TEMP_PATH.resolve(cellphone));
    }

    public static void writeTempImage(File file,String cellphone)
    {
        if(!exists(cellphone))
            createPath(cellphone);
        try(FileChannel in = FileChannel.open(file.toPath(), StandardOpenOption.READ);
            FileChannel out = FileChannel.open(getPath(cellphone),StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE))
        {
            in.transferTo(0,in.size(),out);
        }
        catch (IOException e)
        {
            L.error("Can not write temp image.");
            L.error(e);
            MessageBox.show("临时图片写入失败");
        }
    }
}
