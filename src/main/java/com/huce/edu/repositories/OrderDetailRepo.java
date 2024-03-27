package com.huce.edu.repositories;

import com.huce.edu.entities.OrderdetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderDetailRepo extends JpaRepository<OrderdetailEntity, Integer> {
}

