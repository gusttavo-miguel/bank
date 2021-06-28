package com.accenture.bank.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class ExtratoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExtrab;

    @Column(nullable = false)
    private String dataHoraMavimento;

    @Column(nullable = false)
    @Length(min = 1,max = 10, message = "O tamanho deve ser no minimo 3 e no máximo 10 caracteres.")
    private String operacao;

    @Column(nullable = false)
    private BigDecimal valorOperacao;

    @ManyToOne
    @JoinColumn(name = "ContaCorrente", nullable = false)
    private ContaCorrenteModel contaCorrenteModel;

}
