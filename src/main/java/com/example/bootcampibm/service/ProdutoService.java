package com.example.bootcampibm.service;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.domain.Produto;
import com.example.bootcampibm.dto.ClienteDTO;
import com.example.bootcampibm.dto.ProdutoDTO;
import com.example.bootcampibm.repository.ProdutoRepository;
import com.example.bootcampibm.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto insert(Produto obj) {
        obj = repository.save(obj);
        return obj;
    }

    public Produto find(Integer id) {
        Optional<Produto> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " " + Cliente.class.getName()));
    }



    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Produto update(Produto obj) {
        Produto att = find(obj.getId());
        updateData(att, obj);
        return repository.save(att);
    }

    private void updateData(Produto novo, Produto antigo) {
        novo.setNome(antigo.getNome());
        novo.setPreco(antigo.getPreco());
    }

    public Produto fromDto(ProdutoDTO objeto) {
        return new Produto((objeto.getId()), objeto.getNome(), objeto.getPreco());
    }
}
