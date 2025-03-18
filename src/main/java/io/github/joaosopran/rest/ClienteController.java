package io.github.joaosopran.rest;

import io.github.joaosopran.model.entity.Cliente;
import io.github.joaosopran.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    // PARA SALVAR UM CLIENTE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // ABORDAGEM DE CÓDIGO LIMPO E ORGANIZADO
    public Cliente salvar(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }

    // PARA ACHAR UM CLIENTE
    @GetMapping("{id}")
    public Cliente acharPorId(@PathVariable Integer id){
        return repository.findById(id)
                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id)
                  .map(cliente -> {
                      repository.delete(cliente);
                      return Void.TYPE;
                  })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado){
        repository.findById(id)
                  .map(cliente -> {
                      clienteAtualizado.setId(cliente.getId());
                      return repository.save(clienteAtualizado);
                  })
                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
