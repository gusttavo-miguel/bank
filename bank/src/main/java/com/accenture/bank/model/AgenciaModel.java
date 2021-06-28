//package com.accenture.bank.model;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import lombok.Data;
//import org.hibernate.validator.constraints.Length;
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@Entity(name = "agencia")
//public class AgenciaModel {
//
////    @ManyToOne
////    @JoinColumn(name = "idCliente", nullable = false)
////    private ClienteModel cliente;
//
//    @OneToMany
//    @JoinColumn(name = "idConta", nullable = false)
//    private List <ContaCorrenteModel> contas = new ArrayList<>();
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "agencia")
//    @Length(min = 3, max = 45, message = "O tamanho deve ser no entre 3 e 45 caracteres.")
//    private Long agencia;
//
//    @Column
//    @Length(min = 3, max = 45, message = "O tamanho deve ser entre 3 a 45 caracteres.")
//    private String nome;
//
//    @Column
//    @Length(min = 10, max = 45, message = "O tamanho deve ser entre 10 a 45 caracteres.")
//    private String endereco;
//
//    @Column
//    @Length(min = 11, max = 15, message = "O tamanho deve ser entre 11 a 15 caracteres.")
//    private String telefone;
//
//}
