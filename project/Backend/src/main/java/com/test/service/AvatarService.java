package com.test.service;

import com.test.log.L;
import com.test.utils.Check;
import com.test.utils.Conversion;
import com.test.utils.ReturnCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AvatarService {

    private static Path path;

    @Value("${upload.path}")
    public void setPath(String strPath)
    {
        path = Paths.get(strPath);
        if(!Files.exists(path))
        {
            try
            {
                Files.createDirectory(path);
                L.createUploadDirSuccess(path.toString());
            }
            catch (IOException e)
            {
                L.createUploadDirFailed(path.toString());
                path = null;
            }
        }
    }

    public boolean exists(String cellphone)
    {
        return Files.exists(getPath(cellphone));
    }

    public static Path getPath(String cellphone)
    {
        return path.resolve(cellphone);
    }

    public ReturnCode uploadOne(String avatarStr, String cellphone)
    {
        ReturnCode s = ReturnCode.UPLOAD_ONE_SUCCESS;
        try
        {
            Conversion.stringToAvatar(avatarStr,cellphone);
        }
        catch (IOException e)
        {
            s = ReturnCode.STRING_TO_AVATAR_FAILED;
            s.message(e.getMessage());
        }
        return s;
    }

    public ReturnCode downloadOne(String cellphone)
    {
        ReturnCode s = ReturnCode.DOWNLOAD_ONE_SUCCESS;
        try
        {
            s.body(Conversion.avatarToString(getPath(cellphone)));
        }
        catch (IOException e)
        {
            L.avatarToStringFailed(e.getMessage());
            s = ReturnCode.AVATAR_TO_STRING_FAILED;
            s.message(e.getMessage());
        }
        return s;
    }

    public ReturnCode uploadAll(String str)
    {
        ReturnCode s = ReturnCode.UPLOAD_ALL_SUCCESS;
        try
        {
            HashMap<String,String> map = Conversion.stringToAvatars(str);
            AtomicReference<IOException> e = new AtomicReference<>(new IOException());
            StringBuilder cellphones = new StringBuilder();
            if(!Check.isEmpty(map)) {
                map.forEach((cellphone, avatarStr) -> {
                    try {
                        Conversion.stringToAvatar(avatarStr, cellphone);
                    } catch (IOException e1) {
                        L.stringToAvatarFailed(e1.getMessage());
                        e.set(e1);
                    }
                    cellphones.append(cellphone);
                });
            }
            if(!Check.isEmpty(e.get()))
                throw e.get();
            s.cellphone(cellphones.toString());
        }
        catch (IOException e)
        {
            L.stringToAvatarsFailed(e.getMessage());
            s = ReturnCode.STRING_TO_AVATARS_FAILED;
            s.message(e.getMessage());
        }
        return s;
    }

    public ReturnCode downloadAll()
    {
        ReturnCode s = ReturnCode.DOWNLOAD_ALL_SUCCESS;
        try
        {
            HashMap<String,String> avatars = new HashMap<>();
            Files.walkFileTree(path, new FileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes){
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                    avatars.put(path.getFileName().toString(), Conversion.avatarToString(path));
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path path, IOException e){
                    L.fileVisitFailed(e.getMessage());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path path, IOException e){
                    return FileVisitResult.CONTINUE;
                }
            });
            s.body(Conversion.avatarsToString(avatars));
        }
        catch (IOException e)
        {
            L.fileVisitFailed(e.getMessage());
            s = ReturnCode.FILE_VISIT_FAILED;
            s.message(e.getMessage());
        }
        return s;
    }

    public ReturnCode deleteMany(List<String> cellphones)
    {
        ReturnCode s = ReturnCode.DELETE_MANY_AVATARS_SUCCESS;
        String c = "";
        try
        {
            for (String cellphone : cellphones) {
                c = cellphone;
                if (Files.exists(getPath(c)))
                    Files.delete(getPath(c));
            }
        }
        catch (IOException e)
        {
            L.deleteMantEntitiesFailed(c,e.getMessage());
            s = ReturnCode.UNKNOWN_ERROR;
            s.message(e.getMessage());
        }
        return s;
    }
}

