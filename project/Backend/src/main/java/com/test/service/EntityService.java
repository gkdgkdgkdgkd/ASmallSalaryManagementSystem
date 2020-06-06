package com.test.service;

import com.google.common.collect.Lists;
import com.test.entity.Worker;
import com.test.log.L;
import com.test.repository.WorkerRepository;
import com.test.utils.Check;
import com.test.utils.Conversion;
import com.test.utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class EntityService {
    @Autowired
    private WorkerRepository workerRepository;

    public ReturnCode saveOne(String json) {
        ReturnCode s = ReturnCode.SAVE_ONE_SUCCESS;
        Worker worker = Conversion.JSONToWorker(json);
        if (Check.isEmpty(worker)) {
            L.emptyWorker();
            s = ReturnCode.EMPTY_WORKER;
        }
        else
            workerRepository.save(worker);
        return s;
    }

    public ReturnCode saveOne(Worker worker)
    {
        workerRepository.save(worker);
        return ReturnCode.RETRIEVE_PASSWORD_SUCCESS;
    }

    public ReturnCode deleteMany(List<String> cellphone)
    {
        cellphone.forEach(t->workerRepository.deleteById(t));
        return ReturnCode.DELETE_MANY_ENTITIES_SUCCESS;
    }

    public boolean existsByCellphone(String cellphone)
    {
        return workerRepository.existsById(cellphone);
    }

    public boolean existsByCellphoneAndPassword(String cellphone,String password)
    {
        return workerRepository.existsByCellphoneAndPassword(cellphone,password);
    }

    public ReturnCode getOne(String cellphone)
    {
        ReturnCode s = ReturnCode.EMPTY_WORKER;
        String str = Conversion.workerToJSON(workerRepository.findById(cellphone).orElse(null));
        if(!Check.isEmpty(str))
        {
            s = ReturnCode.GET_ONE_SUCCESS;
            s.body(str);
        }
        return s;
    }

    public ReturnCode getAll()
    {
        ReturnCode s = ReturnCode.EMPTY_DATABASE;
        String str = Conversion.workerToJSON(Lists.newArrayList(workerRepository.findAll()));
        if(!Check.isEmpty(str))
        {
            s = ReturnCode.GET_ALL_SUCCESS;
            s.body(str);
        }
        else
            s.message("Database is empty");
        return s;
    }

    public boolean isAdmin(String cellphone,String password)
    {
        return Check.isAdmin(cellphone,password);
    }

    public ReturnCode saveAll(List<Worker> workers)
    {
        workerRepository.saveAll(workers);
        return ReturnCode.SAVE_ALL_SUCCESS;
    }
}
