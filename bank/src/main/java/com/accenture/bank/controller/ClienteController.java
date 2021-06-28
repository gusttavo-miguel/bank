package com.accenture.bank.controller;

import com.accenture.bank.model.ClienteModel;
import com.accenture.bank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastro")
    public ResponseEntity<ClienteModel> cadastro(@RequestBody ClienteModel body) {
        ClienteModel clienteSalvo = clienteService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @PutMapping(path = "/atualizar")
    public ClienteModel update(@RequestBody ClienteModel body) {
        return clienteService.save(body);
    }

    @DeleteMapping(path = "/deletar/{cpf}")
    public ResponseEntity<ClienteModel> delete(@PathVariable("cpf") Long cpf) {
        ClienteModel cliente = clienteService.delete(cpf);
        return ResponseEntity.ok(cliente);    }

    @GetMapping("/todos")
    public ResponseEntity<List<ClienteModel>> buscarTodos() {
        List<ClienteModel> list = clienteService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ClienteModel> findCliente(@PathVariable("id") long id) {
        Optional<ClienteModel> cliente = clienteService.findCliente(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/agencia/{agencia}/conta/{conta}")
    public ResponseEntity<ClienteModel> findAgenciaConta(@PathVariable("agencia") Long agencia, @PathVariable("conta") Long conta){
        Optional<ClienteModel> cliente = clienteService.findAgenciaConta(agencia, conta);

        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
