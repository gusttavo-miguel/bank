package com.accenture.bank.repository;

import com.accenture.bank.model.ClienteModel;
import com.accenture.bank.model.ContaCorrenteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrenteModel, Long> {


    public ContaCorrenteModel findByAgenciaAndConta(Long agencia, Long conta);

    public ContaCorrenteModel findByCliente(ClienteModel cliente);


}
