package com.example.bootcampibm.service;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.repository.ClienteRepository;
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

    public Optional<Cliente> find(Integer id) {
        return repository.findById(id);
    }
}
