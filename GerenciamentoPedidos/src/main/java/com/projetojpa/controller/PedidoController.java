package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Pedido;
import com.projetojpa.services.PedidoServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Pedido", description = "API REST DE GERENCIAMENTO SE USUÁRIOS")
@RestController
@RequestMapping("/pedido")
public class PedidoController {
	private final PedidoServices pedidoServices;

	@Autowired
	public PedidoController (PedidoServices pedidoServices) {
		this.pedidoServices = pedidoServices;
	}
	@GetMapping("/{id}")
	@Operation(summary = "Localiza pedido por ID")
	public ResponseEntity <Pedido> buscaPedidoIdControlId(@PathVariable Long id){
		Pedido pedido = pedidoServices.buscaPedidoId(id);
		if(pedido!= null) {
			return ResponseEntity.ok(pedido);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/")
	@Operation(summary = "Apresenta todos os pedidos")
	public ResponseEntity<List<Pedido>> buscaTodosPedidoControl() {
		List<Pedido> Pedido = pedidoServices.buscaTodosPedidos();

		return ResponseEntity.ok(Pedido);
	}
	@PostMapping("/")
	@Operation(summary = "Cadastra um livro")
	public ResponseEntity<Pedido> salvaLivroControl(@RequestBody @Valid Pedido pedido){
		Pedido salvaPedido = pedidoServices.salvaPedido(pedido);

		return ResponseEntity.status(HttpStatus.CREATED).body(salvaPedido);

	}
	@PutMapping ("/{id}")
	@Operation(summary = "altera as informações do id")
	public ResponseEntity<Pedido> alterarPedido(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
		Pedido alterarPedido = pedidoServices.alterarPedido(id,pedido);
		if (alterarPedido  != null) {
			return ResponseEntity.ok(alterarPedido);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation(summary = "Apagar o id selecionado")
	public ResponseEntity<String> apagaPedidoControl(@PathVariable Long id) {
		boolean apagar = pedidoServices.apagarPedido(id);
		if(apagar) {
			return ResponseEntity.ok().body("O produto foi excluído com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}



