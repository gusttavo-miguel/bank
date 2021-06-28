package com.accenture.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDTO {

	@NotNull(message = "Campo obrigatório..")
	private BigDecimal valor;

	@NotNull(message = "Campo obrigatório..")
	private char opercao;

	@Valid
	private ContaCorrenteModel contaOrigem;

	@Valid
	private ContaCorrenteModel contaDestino;
	
}
