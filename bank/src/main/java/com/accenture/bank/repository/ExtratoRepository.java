package com.accenture.bank.repository;

import com.accenture.bank.model.ExtratoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtratoRepository extends JpaRepository<ExtratoModel, Integer> {

}
