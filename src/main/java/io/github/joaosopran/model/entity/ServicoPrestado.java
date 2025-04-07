package io.github.joaosopran.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class ServicoPrestado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String descricao;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(precision = 10, scale = 2) // Exemplo para BigDecimal
    private BigDecimal valor;

    @Column
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate data;

    // Getters e setters são gerados pelo @Data do Lombok
}