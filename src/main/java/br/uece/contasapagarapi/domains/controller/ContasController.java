package br.uece.contasapagarapi.domains.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.contasapagarapi.domains.dto.ContaDTO;
import br.uece.contasapagarapi.domains.service.ContaService;

@RestController
@RequestMapping("contas")
public class ContasController {

	@Autowired
	private ContaService contaService;
	
	@GetMapping(value = "/todas")
	public ResponseEntity<List<ContaDTO>> getTodasContas() {
		return ResponseEntity.ok(this.contaService.buscarTodasContas());
	}
	
	@GetMapping(value = "/buscar/{id}")
	public ResponseEntity<ContaDTO> buscarPorId(@PathVariable Long id) {
		ContaDTO dto = this.contaService.buscarPorId(id);
		if (dto != null) {
			return ResponseEntity.ok(dto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<ContaDTO> salvarNovaConta(@RequestBody ContaDTO dto) {
		return ResponseEntity.ok(this.contaService.salvarConta(dto));
	}
	
	@PutMapping(value = "/alterar")
	public ResponseEntity<ContaDTO> alterarDadosConta(@RequestBody ContaDTO dto) {
		ContaDTO dtoAtualizado = this.contaService.alterarDadosConta(dto);
		if (dtoAtualizado == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(dtoAtualizado);
		}
	}
	
	@PatchMapping(value = "/pagar/{id}")
	public ResponseEntity<Boolean> pagarConta(@PathVariable Long id) {
		Boolean resultado = this.contaService.pagarConta(id);
		
		if (resultado) {
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<Boolean> excluirConta(@PathVariable Long id) {
		this.contaService.excluirConta(id);
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}
}
