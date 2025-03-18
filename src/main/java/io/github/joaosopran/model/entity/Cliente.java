package io.github.joaosopran.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150) //OBRIGATÓRIO O NOME E VAI TER ATÉ 150 CARACTERES
    private String nome;

    @Column(nullable = false, length = 11) //OBRIGATÓRIO O CPF E VAI TER 11 CARACTERES
    private String cpf;

    @Column(name = "data_cadastro") //NOME DA COLUNA NO BANCO DE DADOS
    @JsonFormat(pattern = "dd/MM/yyyy") //FORMATO DE DATA
    private LocalDate datacadastro;

    @PrePersist
    public void prePersist() {
        setDatacadastro(LocalDate.now());

    }
}
