package com.example.bootcampibm.service;

import com.example.bootcampibm.domain.Pedido;
import com.example.bootcampibm.dto.PedidoDTO;
import com.example.bootcampibm.repository.PedidoRepository;
import com.example.bootcampibm.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setData(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj = pedidoRepository.save(obj);
        return obj;
    }

    public Pedido find(Integer id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " " + Pedido.class.getName()));
    }

    public Pedido update(Pedido obj) {
        Pedido att = find(obj.getId());
        updateData(att, obj);
        return pedidoRepository.save(att);
    }

    private void updateData(Pedido novo, Pedido antigo) {
        novo.setId(antigo.getId());
        novo.setData(new Date());
    }

    public Pedido fromDto(PedidoDTO objeto) {
        return new Pedido((objeto.getId()), objeto.getData(), objeto.getCliente_id());
    }

}
