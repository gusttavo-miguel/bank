package com.accenture.bank.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.accenture.bank.model.ContaCorrenteModel;
import com.accenture.bank.model.TransferenciaDTO;
import com.accenture.bank.service.ContaCorrenteService;
import javax.validation.Valid;

@RequestMapping("/conta")
@RestController
public class ContaCorrenteController {

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    @PostMapping("/novo")
    public ResponseEntity<ContaCorrenteModel> save(@Valid @RequestBody ContaCorrenteModel conta) {
        ContaCorrenteModel contaSalva = contaCorrenteService.save(conta);

        return ResponseEntity.status(HttpStatus.CREATED).body(contaSalva);
    }

    @GetMapping("/all")
    public List<ContaCorrenteModel> findAll() {
        List<ContaCorrenteModel> list = contaCorrenteService.listarContas();
        return list;

    }

    @PutMapping("/saque/{valor}/idCliente/{idCliente}")
    public ResponseEntity<ContaCorrenteModel> saque(@Valid @PathVariable("valor") BigDecimal valor, @PathVariable("idCliente") Long idCliente) {
        ContaCorrenteModel contaCorrente = contaCorrenteService.saque(valor, idCliente);
        return ResponseEntity.ok(contaCorrente);

    }

    @PutMapping("/deposito/{valor}/idCliente/{idCliente}")
    public ResponseEntity<ContaCorrenteModel> deposito(@PathVariable("valor") BigDecimal valor, @PathVariable("idCliente") Long idCliente) {
        ContaCorrenteModel contaCorrente = contaCorrenteService.deposito(valor, idCliente);
        return ResponseEntity.ok(contaCorrente);

    }

    @PostMapping("/transferencia")
    public ResponseEntity<TransferenciaDTO> transferencia(@Valid @RequestBody TransferenciaDTO transferenciaDTO) {
        TransferenciaDTO transferenciaDTOFinal = contaCorrenteService.transferencia(transferenciaDTO);

        return ResponseEntity.ok(transferenciaDTOFinal);
    }

    @DeleteMapping("/agencia/{agencia}/conta/{conta}")
    public ResponseEntity<ContaCorrenteModel> delete(@PathVariable("agencia") Long agencia, @PathVariable("conta") Long conta) {
        ContaCorrenteModel contaCorrente = contaCorrenteService.delete(agencia, conta);

        return ResponseEntity.ok(contaCorrente);
    }



}