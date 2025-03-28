package io.github.joaosopran.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "O nome não pode estar vazio!")
    private String nome;

    @Column(unique = true, nullable = false, length = 11)
    @NotBlank(message = "O CPF é obrigatório!")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos!")
    private String cpf;


    @Column(name = "data_cadastro", updatable = false) //NOME DA COLUNA NO BANCO DE DADOS
    @JsonFormat(pattern = "dd/MM/yyyy") //FORMATO DE DATA
    private LocalDate datacadastro;

    @PrePersist
    public void prePersist() {
        setDatacadastro(LocalDate.now());

    }
}
