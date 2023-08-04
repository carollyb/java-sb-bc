package com.example.bootcampibm.service;

import com.example.bootcampibm.domain.Produto;
import com.example.bootcampibm.repository.ProdutoRepository;
import com.example.bootcampibm.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    public void testInsertProduto() {
        Produto produto = new Produto(1, "garrafa", 2.1);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto produtoInserido = produtoService.insert(produto);

        assertNotNull(produtoInserido);
        assertEquals(produto.getNome(), produtoInserido.getNome());
        assertEquals(produto.getPreco(), produtoInserido.getPreco());
        assertEquals(produto.getId(), produtoInserido.getId());

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    public void testFindProdutoByIdExistente() {
        Integer produtoId = 2;
        Produto produto = new Produto(produtoId, "lápis", 2.2);

        when(produtoRepository.findById(produtoId)).thenReturn(Optional.of(produto));
        Produto produtoEncontrado = produtoService.find(produtoId);

        assertNotNull(produtoEncontrado);
        assertEquals(produto.getId(), produtoEncontrado.getId());
        assertEquals(produto.getNome(), produtoEncontrado.getNome());
        assertEquals(produto.getPreco(), produtoEncontrado.getPreco());

        verify(produtoRepository, times(1)).findById(produtoId);
    }


    @Test
    public void testFindProdutoByIdNaoExistente() {
        Integer produtoId = 99;
        when(produtoRepository.findById(produtoId)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> produtoService.find(produtoId));
        verify(produtoRepository, times(1)).findById(produtoId);
    }

    @Test
    public void testDeleteProduto() {
        Integer produtoId = 1;
        produtoService.delete(produtoId);
        verify(produtoRepository, times(1)).deleteById(produtoId);
    }

    @Test
    public void testeUpdateProduto() {
        Integer produtoId = 1;
        Produto produtoAtualizado = new Produto(produtoId, "teclado", 5);
        Produto produtoExistente = new Produto(produtoId, "monitor", 699);

        when(produtoRepository.findById(produtoId)).thenReturn(Optional.of(produtoExistente));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoAtualizado);

        Produto resultado = produtoService.update(produtoAtualizado);

        assertNotNull(resultado);
        assertEquals(produtoId, resultado.getId());
        assertEquals(produtoAtualizado.getNome(), resultado.getNome());
        assertEquals(produtoAtualizado.getPreco(), resultado.getPreco());

        verify(produtoRepository, times(1)).findById(produtoId);
        verify(produtoRepository, times(1)).save(any(Produto.class));

    }
}
