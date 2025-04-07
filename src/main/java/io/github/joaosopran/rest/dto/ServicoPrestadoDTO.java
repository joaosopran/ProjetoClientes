package io.github.joaosopran.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {
    private String descricao;
    private BigDecimal preco;
    private String data;
    private Integer idCliente;
}
