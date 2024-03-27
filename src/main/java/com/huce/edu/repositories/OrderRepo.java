package com.huce.edu.repositories;

import com.huce.edu.entities.OrdertableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrdertableEntity, Integer> {
}
