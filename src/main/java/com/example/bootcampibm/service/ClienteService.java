package com.example.bootcampibm.service;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.dto.ClienteDTO;
import com.example.bootcampibm.repository.ClienteRepository;
import com.example.bootcampibm.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repository.save(obj);
        return obj;
    }

    public Cliente find(Integer id) {
         Optional<Cliente> obj = repository.findById(id);
         return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " " + Cliente.class.getName()));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Cliente update(Cliente obj) {
        Cliente att = find(obj.getId());
        updateData(att, obj);
        return repository.save(att);
    }

    private void updateData(Cliente novo, Cliente antigo) {
        novo.setNome(antigo.getNome());
        novo.setEmail(antigo.getEmail());
    }

    public Cliente fromDto(ClienteDTO objeto) {
        return new Cliente((objeto.getId()), objeto.getNome(), objeto.getEmail());
    }


}
