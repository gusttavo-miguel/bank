package com.accenture.bank.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name = "ContaCorrente")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContaCorrenteModel {

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private ClienteModel cliente;

//    @ManyToOne
//    @JoinColumn(name = "idAgencia")
//    private AgenciaModel agencias;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "conta")
    @Length(min = 3, max = 45, message = "O tamanho deve ser no entre 3 e 45 caracteres.")
    private Long conta;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = false)
    @Length(min = 1, max = 3, message = "O tamanho deve ser no máximo 3 caracteres.")
    private Long agencia;

    @Column
    private BigDecimal saldo;


}
