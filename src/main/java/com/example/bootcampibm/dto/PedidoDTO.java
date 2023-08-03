package com.example.bootcampibm.dto;

import com.example.bootcampibm.domain.Cliente;
import com.example.bootcampibm.domain.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class PedidoDTO {
    private  Integer id;
    private Date data;
    private Cliente cliente_id;

    public PedidoDTO(Pedido obj) {
        id = obj.getId();
        data = obj.getData();
        cliente_id = obj.getCliente();
    }
}
