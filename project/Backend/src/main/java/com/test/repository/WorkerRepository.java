package com.test.repository;
import com.test.entity.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends CrudRepository<Worker,String> {
    boolean existsByCellphoneAndPassword(String cellphone,String password);
}
