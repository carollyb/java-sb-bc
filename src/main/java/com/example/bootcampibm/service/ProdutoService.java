package com.example.bootcampibm.service;

import com.example.bootcampibm.domain.Produto;
import com.example.bootcampibm.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto insert(Produto obj) {
        obj = repository.save(obj);
        return obj;
    }
}
