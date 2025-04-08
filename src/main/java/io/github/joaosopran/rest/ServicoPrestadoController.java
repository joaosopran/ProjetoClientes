package io.github.joaosopran.rest;

import io.github.joaosopran.model.entity.Cliente;
import io.github.joaosopran.model.entity.ServicoPrestado;
import io.github.joaosopran.model.repository.ClienteRepository;
import io.github.joaosopran.model.repository.ServicoPrestadoRepository;
import io.github.joaosopran.rest.dto.ServicoPrestadoDTO;
import io.github.joaosopran.util.BigDecimalConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository repository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {

        String dataStr = dto.getData();

        // Verifica se a string está nula ou vazia
        if (dataStr == null || dataStr.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data do serviço não pode estar vazia.");
        }

        LocalDate data;
        try {
            // Tenta fazer o parse da data
            data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            // Se der erro, lança uma exceção com status 400
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de data inválido. Use dd/MM/yyyy.");
        }

        Integer idCliente = dto.getIdCliente();

        Cliente cliente =
                clienteRepository
                        .findById(idCliente)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(dto.getPreco());

        return repository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ) {
        return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }

}



