package io.github.joaosopran.rest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {

    @NotEmpty(message = "Descrição é obrigatória.")
    private String descricao;

    @NotEmpty(message = "Data é obrigatória.")
    // Validação customizada de formato seria mais complexa, tratamos isso no controller
    private String data;

    @NotNull(message = "ID do cliente é obrigatório.")
    private Integer idCliente;

    @NotNull(message = "Preço é obrigatório.")
    @DecimalMin(value = "0.00", inclusive = false, message = "Preço deve ser maior que zero.")
    private BigDecimal preco;
}
