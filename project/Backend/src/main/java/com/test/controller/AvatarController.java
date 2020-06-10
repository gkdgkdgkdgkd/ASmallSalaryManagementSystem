package com.test.controller;

import com.test.log.L;
import com.test.service.AvatarService;
import com.test.utils.Check;
import com.test.utils.Conversion;
import com.test.utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AvatarController {
    private final AvatarService service;

    @GetMapping({"uploadOneAvatar","downloadOneAvatar","uploadAllAvatars","downloadAllAvatars"})
    public @ResponseBody String pageNotFound()
    {
        L.pageNotFound();
        return ReturnCode.PAGE_NOT_FOUND.name();
    }

    @PostMapping("uploadOneAvatar")
    public @ResponseBody String uploadOneAvatar(@RequestParam String avatar, @RequestParam String cellphone)
    {
        if(Check.isEmpty(avatar))
        {
            L.emptyAvatar();
            return toStr(ReturnCode.EMPTY_AVATAR);
        }
        if(Check.isEmpty(cellphone))
        {
            L.emptyCellphone();
            return toStr(ReturnCode.EMPTY_CELLPHONE);
        }
        ReturnCode s = service.uploadOne(avatar,cellphone);
        if(s == ReturnCode.UPLOAD_ONE_SUCCESS)
            L.uploadOneSuccess(cellphone);
        else
            L.uploadOneFailed(cellphone,s.message());
        return toStr(s);
    }

    @PostMapping("downloadOneAvatar")
    public @ResponseBody String downloadOneAvatar(@RequestParam String cellphone)
    {
        if(Check.isEmpty(cellphone))
        {
            L.emptyCellphone();
            return toStr(ReturnCode.EMPTY_CELLPHONE);
        }
        if(service.exists(cellphone))
        {
            ReturnCode s = service.downloadOne(cellphone);
            if(s == ReturnCode.DOWNLOAD_ONE_SUCCESS)
                L.downloadOneSuccess(cellphone);
            else
                L.downloadOneFailed(cellphone,s.message());
            return toStr(s);
        }
        L.downloadOneFailed(cellphone,"Image not found.");
        return toStr(ReturnCode.IMAGE_NOT_FOUND);
    }

    @PostMapping("downloadAllAvatars")
    public @ResponseBody String downloadAllAvatars()
    {
        ReturnCode s = service.downloadAll();
        if(s == ReturnCode.DOWNLOAD_ALL_SUCCESS)
            L.downloadAllSuccess();
        else
            L.downloadAllFailed(s.message());
        return toStr(s);
    }

    @PostMapping("uploadAllAvatars")
    public @ResponseBody String uploadAllAvatars(@RequestParam String avatars)
    {
        ReturnCode s = service.uploadAll(avatars);
        if(s == ReturnCode.UPLOAD_ALL_SUCCESS)
            L.uploadAllSuccess(s.cellphone());
        else
            L.uploadAllFailed(s.message());
        return toStr(s);
    }

    private static String toStr(ReturnCode s)
    {
        return Conversion.returnCodeToString(s);
    }
}
