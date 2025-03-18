package io.github.joaosopran.rest;

import io.github.joaosopran.model.entity.Cliente;
import io.github.joaosopran.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // ABORDAGEM DE CÓDIGO LIMPOR E ORGANIZADO
    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

}
