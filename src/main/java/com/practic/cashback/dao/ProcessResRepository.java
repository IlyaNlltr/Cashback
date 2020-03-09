package com.practic.cashback.dao;
import com.practic.cashback.dto.ProcessRes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessResRepository extends CrudRepository<ProcessRes, Long> {
}
