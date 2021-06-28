package com.accenture.bank.service;

import com.accenture.bank.model.ClienteModel;
import com.accenture.bank.model.ContaCorrenteModel;
import com.accenture.bank.model.ExtratoModel;
import com.accenture.bank.model.TransferenciaDTO;
import com.accenture.bank.repository.ClienteRepository;
import com.accenture.bank.repository.ContaCorrenteRepository;
import com.accenture.bank.repository.ExtratoRepository;
import com.accenture.bank.service.exception.BalanceException;
import com.accenture.bank.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ContaCorrenteService {

	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ExtratoRepository extratoRepository;

	// Salvar contas
	@Transactional
	public ContaCorrenteModel save(ContaCorrenteModel conta) {
//		conta.setSaldo(new BigDecimal(0));
		return contaCorrenteRepository.save(conta);
	}
	
	// Lista todas as contas
	public List<ContaCorrenteModel> listarContas() {
		return contaCorrenteRepository.findAll();
	}

	// buscar conta por ID
	public ContaCorrenteModel findId(Long id) {
		Optional<ContaCorrenteModel> conta = contaCorrenteRepository.findById(id);
		return conta.orElseThrow(() -> new ObjectNotFoundException("Código não encontrado! Id: " + id));
	}

	public ContaCorrenteModel saque(BigDecimal valor, Long idCliente) {
		Optional<ClienteModel> cliente = clienteRepository.findById(idCliente);
		ContaCorrenteModel contaCorrente = new ContaCorrenteModel();
		if (cliente.isPresent()) {
			contaCorrente = contaCorrenteRepository.findByCliente(cliente.get());
		}

		if (contaCorrente.getSaldo().subtract(valor).doubleValue() < 0) {
			throw new BalanceException("Saldo insuficiente para o saque.");
		}
		
		contaCorrente.setSaldo(contaCorrente.getSaldo().subtract(valor));

		return contaCorrenteRepository.save(contaCorrente);

	}

	public ContaCorrenteModel deposito(BigDecimal valor, long idCliente) {
		Optional<ClienteModel> cliente = clienteRepository.findById(idCliente);
		ContaCorrenteModel contaCorrente = new ContaCorrenteModel();
		if (cliente.isPresent()) {
			contaCorrente = contaCorrenteRepository.findByCliente(cliente.get());
		}

		contaCorrente.setSaldo(contaCorrente.getSaldo().add(valor));

		return contaCorrenteRepository.save(contaCorrente);

	}

	@Transactional
	public TransferenciaDTO transferencia(TransferenciaDTO transferenciaDTO) {
		ContaCorrenteModel contaOrigem = contaCorrenteRepository.findByAgenciaAndConta(transferenciaDTO.getContaOrigem().getAgencia(), transferenciaDTO.getContaOrigem().getConta());
		ContaCorrenteModel contaDestino = contaCorrenteRepository.findByAgenciaAndConta(transferenciaDTO.getContaDestino().getAgencia(), transferenciaDTO.getContaDestino().getConta());
		ExtratoModel extrato = new ExtratoModel();
		transferenciaDTO.setContaOrigem(saque(transferenciaDTO.getValor(), contaOrigem.getCliente().getId()));
		transferenciaDTO.setContaDestino(deposito(transferenciaDTO.getValor(), contaDestino.getCliente().getId()));
		LocalDateTime data = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		extrato.setDataHoraMavimento(data.format(formatter));
		extrato.setValorOperacao(transferenciaDTO.getValor());
		extrato.setContaCorrenteModel(transferenciaDTO.getContaOrigem());
		extratoRepository.save(extrato);
		return transferenciaDTO;
	}

	public ContaCorrenteModel delete(Long agencia, Long conta) {
		ContaCorrenteModel contaCorrente = contaCorrenteRepository.findByAgenciaAndConta(agencia, conta);
		contaCorrenteRepository.delete(contaCorrente);
		return contaCorrente;
	}

}