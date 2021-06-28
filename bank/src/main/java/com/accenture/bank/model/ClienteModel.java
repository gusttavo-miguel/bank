package com.accenture.bank.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"contaCorrente"})
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    private Long id;

    @NotEmpty(message = "Campo obrigatório.")
    @Column
    @Length(min = 3, max = 50, message = "O tamanho deve ser entre 3 a 50 caracteres.")
    private String nome;

    @NotEmpty(message = "Campo obrigatório.")
    @Column(unique = true)
    @Length(min = 11, max = 14, message = "O tamanho deve ser entre 11 a 14 caracteres.")
    private String cpf;

    @Column
    @Length(min = 11, max = 15, message = "O tamanho deve ser entre 11 a 15 caracteres.")
    private String telefone;

}
