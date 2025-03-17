package io.github.joaosopran.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150) //OBRIGATÓRIO O NOME E VAI TER ATÉ 150 CARACTERES
    private String nome;

    @Column(nullable = false, length = 11) //OBRIGATÓRIO O CPF E VAI TER 11 CARACTERES
    private String cpf;

    @Column
    private LocalDate datacadastro;


}
