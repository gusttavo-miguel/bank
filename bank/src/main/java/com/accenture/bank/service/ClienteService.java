package com.accenture.bank.service;

import java.util.List;
import java.util.Optional;

import com.accenture.bank.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.accenture.bank.model.ClienteModel;
import com.accenture.bank.model.ContaCorrenteModel;
import com.accenture.bank.repository.ClienteRepository;
import com.accenture.bank.repository.ContaCorrenteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    //buscar todos os clientes
    public List<ClienteModel> findAll (){

        return clienteRepository.findAll();
    }

    //buscar clientes por ID
    public ClienteModel findId (Long id) {
        Optional<ClienteModel> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Código não encontrado! Id: " + id ));
    }

    //salvanod cliente.
    @Transactional
    public ClienteModel save (ClienteModel cliente) {

        return clienteRepository.save(cliente);
    }

    //realizando update do cliente
    @Transactional
    public ClienteModel update (ClienteModel clienteModel){
        ClienteModel newCliente = findId(clienteModel.getId());
        updateData(newCliente, clienteModel);
        return clienteRepository.save(newCliente);
    }

    //deletando cliente
    @Transactional
    public ClienteModel delete (Long id){
        ClienteModel cliente = findId(id);

//        ContaCorrente contaCorrente = contaCorrenteRepository.findByCliente(cliente);
//        contaCorrenteRepository.delete(contaCorrente);
//>>>>>>> 1b5cde87205d6ca14361ab179f932b8c7dae0e38
        clienteRepository.deleteById(cliente.getId());

        return cliente;

    }

    //Metodo para atualizar os campos que serão realizado o update.
    private void updateData (ClienteModel newCliente, ClienteModel cliente){
        newCliente.setNome(cliente.getNome());
        newCliente.setTelefone(cliente.getTelefone());
        newCliente.setCpf(cliente.getCpf());
    }

    public Optional<ClienteModel> findAgenciaConta(Long agencia, Long conta) {
        ContaCorrenteModel contaCorrente = contaCorrenteRepository.findByAgenciaAndConta(agencia, conta);

        return clienteRepository.findById(contaCorrente == null ? -1 : contaCorrente.getCliente().getId());
    }

    public Optional<ClienteModel> findCliente(long id) {
        return clienteRepository.findById(id);
    }
}

