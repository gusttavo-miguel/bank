//package com.accenture.bank.controller;
//
//import com.accenture.bank.model.AgenciaModel;
//import com.accenture.bank.repository.AgenciaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//
//@RequestMapping("/agencia")
//@RestController
//public class AgenciaController {
//
//    @Autowired
//    private AgenciaRepository agenciaRepository;
//
//    @PostMapping("/cadastro")
//    public ResponseEntity<AgenciaModel> save(@Valid @RequestBody AgenciaModel agencia) {
//        AgenciaModel contaSalva = agenciaRepository.save(agencia);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(contaSalva);
//    }
//
//    @PutMapping(path = "/atualizar")
//    public AgenciaModel update(@RequestBody AgenciaModel body) {
//        return agenciaRepository.save(body);
//    }
//
//    @DeleteMapping(path = "/deletar/{agencia}")
//    public ResponseEntity<AgenciaModel> delete(@PathVariable("agencia") Long agencia) {
//        AgenciaModel agenciaModel = agenciaRepository.delete(agencia);
//        return ResponseEntity.ok(agenciaModel);
//    }
//
//    @GetMapping("/all")
//    public List<AgenciaModel> findAll() {
//        List<AgenciaModel> list = agenciaRepository.findAll();
//        return list;
//    }
//
//    @GetMapping("/agencia/{agencia}")
//    public ResponseEntity<AgenciaModel> findCliente(@PathVariable("agencia") Long agencia) {
//        Optional<AgenciaModel> agenciaModel = agenciaRepository.findAgencia(agencia);
//
//        if (agenciaModel.isPresent()) {
//            return ResponseEntity.ok(agenciaModel.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//
//    }
//
//
//}
