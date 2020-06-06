package com.test.controller;

import com.test.entity.Worker;
import com.test.log.L;
import com.test.service.AvatarService;
import com.test.service.EntityService;
import com.test.utils.Check;
import com.test.utils.Conversion;
import com.test.utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class EntityController {
    @Autowired
    private EntityService service;
    @Autowired
    private AvatarService avatarService;

    @GetMapping({"saveOne","saveAll","deleteMany","signInUp","getOne","getAll","cellphoneExists","retrievePassword"})
    public @ResponseBody String pageNotFound()
    {
        L.pageNotFound();
        return ReturnCode.PAGE_NOT_FOUND.name();
    }

    @PostMapping(path = "saveOne")
    public @ResponseBody String saveOne(@RequestParam String worker)
    {
        if(Check.isEmpty(worker))
        {
            L.emptyWorker();
            return toStr(ReturnCode.EMPTY_WORKER);
        }
        ReturnCode s = service.saveOne(worker);
        if(s == ReturnCode.SAVE_ONE_SUCCESS)
            L.saveOneSuccess(worker);
        else
            L.saveOneFailed(worker,s.message());
        return toStr(s);
    }

    @PostMapping(path = "saveAll")
    public @ResponseBody String saveAll(@RequestParam String workers)
    {
        if(Check.isEmpty(workers))
        {
            L.emptyWorker();
            return toStr(ReturnCode.EMPTY_WORKER);
        }
        List<Worker> workerList = Conversion.JSONToWorkers(workers);
        if(Check.isEmpty(workerList))
        {
            L.emptyWorker();
            return toStr(ReturnCode.EMPTY_WORKER);
        }
        StringBuilder cellphones = new StringBuilder();
        workerList.forEach(w-> cellphones.append("[").append(w.getCellphone()).append("] "));
        ReturnCode s = service.saveAll(workerList);
        if(s == ReturnCode.SAVE_ALL_SUCCESS)
            L.saveAllSuccess(cellphones.toString());
        else
            L.saveAllFailed(cellphones.toString(),s.message());
        return toStr(s);
    }

    @PostMapping(path = "deleteMany")
    public @ResponseBody String deleteMany(@RequestParam String cellphone)
    {
        if(Check.isEmpty(cellphone))
        {
            L.emptyCellphone();
            return toStr(ReturnCode.EMPTY_CELLPHONE);
        }
        List<String> cellphones = Conversion.stringToList(cellphone);
        String allCellphones = Conversion.listToString(cellphones);
        ReturnCode s = service.deleteMany(cellphones);
        if(s == ReturnCode.DELETE_MANY_ENTITIES_SUCCESS)
        {
            L.deleteManyAvatarsSuccess(allCellphones);
            s = avatarService.deleteMany(cellphones);
            if(s == ReturnCode.DELETE_MANY_AVATARS_SUCCESS)
                L.deleteManyAvatarsSuccess(allCellphones);
            else
                L.deleteMantAvatarsFailed(allCellphones,s.message());
        }
        else
            L.deleteManyEntitiesSuccess(allCellphones);
        return toStr(s);
    }

    @PostMapping(path = "signInUp")
    public @ResponseBody String signInUp(@RequestParam String cellphone,@RequestParam String password)
    {
        if(Check.isEmpty(cellphone))
        {
            L.emptyCellphone();
            return toStr(ReturnCode.EMPTY_CELLPHONE);
        }
        if(Check.isEmpty(password))
        {
            L.emptyPassword();
            return toStr(ReturnCode.EMPTY_PASSWORD);
        }
        if(service.isAdmin(cellphone,password))
        {
            L.adminLogin();
            return toStr(ReturnCode.ADMIN);
        }
        if(Check.isInvalidCellphone(cellphone))
        {
            L.invalidCellphone(cellphone);
            return toStr(ReturnCode.INVALID_CELLPHONE);
        }
        ReturnCode s;
        if(service.existsByCellphone(cellphone))
        {
            if(service.existsByCellphoneAndPassword(cellphone,password))
            {
                L.loginSuccess(cellphone, password);
                s = ReturnCode.LOGIN_SUCCESS;
            }
            else
            {
                L.loginFailed(cellphone, password,"Password not matches.");
                s = ReturnCode.PASSWORD_NOT_MATCH;
            }
        }
        else
        {
            Worker worker = new Worker(cellphone,password);
            String strWorker = Conversion.workerToJSON(worker);
            s = service.saveOne(strWorker);
            if(s == ReturnCode.SAVE_ONE_SUCCESS)
                L.saveOneSuccess(strWorker);
            else
                L.saveOneFailed(strWorker,s.message());
        }
        return toStr(s);
    }

    @PostMapping(path = "getOne")
    public @ResponseBody String getOne(@RequestParam String cellphone)
    {
        if(Check.isEmpty(cellphone))
        {
            L.emptyCellphone();
            return toStr(ReturnCode.EMPTY_CELLPHONE);
        }
        if(Check.isInvalidCellphone(cellphone))
        {
            L.invalidCellphone(cellphone);
            return toStr(ReturnCode.INVALID_CELLPHONE);
        }
        if(!service.existsByCellphone(cellphone))
        {
            L.getOneFailed(cellphone,"Cellphone not matches");
            return toStr(ReturnCode.CELLPHONE_NOT_MATCH);
        }
        ReturnCode s = service.getOne(cellphone);
        if(s == ReturnCode.GET_ONE_SUCCESS)
            L.getOneSuccess(cellphone);
        else
            L.getOneFailed(cellphone,s.message());
        return toStr(s);
    }

    @PostMapping(path = "getAll")
    public @ResponseBody String getAll()
    {
        ReturnCode s = service.getAll();
        if(s == ReturnCode.GET_ALL_SUCCESS)
            L.getAllSuccess();
        else
            L.getAllFailed(s.message());
        return toStr(s);
    }

    @PostMapping(path = "cellphoneExists")
    public @ResponseBody String cellphoneExists(@RequestParam String cellphone)
    {
        if(Check.isEmpty(cellphone))
        {
            L.emptyCellphone();
            return toStr(ReturnCode.EMPTY_CELLPHONE);
        }
        return toStr(service.existsByCellphone(cellphone) ?
                ReturnCode.CELLPHONE_EXISTS :
                ReturnCode.CELLPHONE_NOT_EXISTS);
    }

    @PostMapping(path = "retrievePassword")
    public @ResponseBody String retrievePassword(@RequestParam String cellphone,@RequestParam String password)
    {
        if(Check.isEmpty(cellphone))
        {
            L.emptyCellphone();
            return toStr(ReturnCode.EMPTY_CELLPHONE);
        }
        if(Check.isEmpty(password))
        {
            L.emptyPassword();
            return toStr(ReturnCode.EMPTY_CELLPHONE);
        }
        ReturnCode s = service.saveOne(new Worker(cellphone,password));
        if(s == ReturnCode.RETRIEVE_PASSWORD_SUCCESS)
            L.retrievePasswordSuccess(cellphone,password);
        else
            L.retrievePasswordFailed(cellphone,password,s.message());
        return toStr(s);
    }

    private static String toStr(ReturnCode s)
    {
        return Conversion.returnCodeToString(s);
    }
}
