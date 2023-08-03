package com.example.bootcampibm.dto;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.domain.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
public class ProdutoDTO implements Serializable {
    private  Integer id;
    private String nome;
    private double preco;

    public ProdutoDTO(Produto obj) {
        id = obj.getId();
        nome = obj.getNome();
        preco = obj.getPreco();
    }
}
