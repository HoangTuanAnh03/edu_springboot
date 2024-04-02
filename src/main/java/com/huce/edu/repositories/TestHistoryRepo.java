package com.huce.edu.repositories;

import com.huce.edu.entities.OrderdetailEntity;
import com.huce.edu.entities.TesthistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestHistoryRepo extends JpaRepository<TesthistoryEntity, Integer> {
    TesthistoryEntity findByThid(Integer thid);
    TesthistoryEntity findByUid(Integer uid);
    TesthistoryEntity findByNumques(Integer numQues);
    TesthistoryEntity findByNumcorrectques(Integer num);


}