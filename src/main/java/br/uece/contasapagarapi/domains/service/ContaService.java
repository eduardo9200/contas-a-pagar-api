package br.uece.contasapagarapi.domains.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.contasapagarapi.domains.builder.ContasBuilder;
import br.uece.contasapagarapi.domains.dto.ContaDTO;
import br.uece.contasapagarapi.domains.enums.StatusConta;
import br.uece.contasapagarapi.domains.model.Conta;
import br.uece.contasapagarapi.domains.repository.ContaRepository;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	public List<ContaDTO> buscarTodasContas() {
		List<Conta> contas = contaRepository.findAll();
		List<ContaDTO> dtoList = new ArrayList<>();
		
		for (Conta c : contas) {
			ContaDTO dto = 
				new ContasBuilder()
				.setId(c.getId())
				.setDescricao(c.getDescricao())
				.setValor(c.getValor())
				.setVencimento(c.getVencimento())
				.setJuros(c.getJuros())
				.setPeriodoJuros(c.getPeriodoJuros())
				.setStatus(c.getStatus())
				.setPaga(c.getPaga())
				.build();
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	public ContaDTO buscarPorId(Long id) {
		Optional<Conta> contaOpt = contaRepository.findById(id);
		
		if (contaOpt.isPresent()) {
			Conta c = contaOpt.get();
			
			ContaDTO dto = 
				new ContasBuilder()
				.setId(c.getId())
				.setDescricao(c.getDescricao())
				.setValor(c.getValor())
				.setVencimento(c.getVencimento())
				.setJuros(c.getJuros())
				.setPeriodoJuros(c.getPeriodoJuros())
				.setStatus(c.getStatus())
				.setPaga(c.getPaga())
				.build();
			return dto;
		} else {
			return null;
		}
	}
	
	public ContaDTO salvarConta(ContaDTO dto) {
		Conta c =
			new ContasBuilder()
			.setDescricao(dto.getConta())
			.setValor(dto.getValor())
			.setVencimento(dto.getVencimento())
			.setJuros(dto.getJuros())
			.setPeriodoJuros(dto.getPeriodoJuros())
			.setPaga(false)
			.buildModel();
		this.contaRepository.save(c);
		return dto;
	}
	
	public ContaDTO alterarDadosConta(ContaDTO dto) {
		Optional<Conta> contaOpt = contaRepository.findById(dto.getId());
		
		if (contaOpt.isEmpty()) {
			return null;			
		}
		
		Conta c =
			new ContasBuilder()
			.setId(dto.getId())
			.setDescricao(dto.getConta())
			.setValor(dto.getValor())
			.setVencimento(dto.getVencimento())
			.setJuros(dto.getJuros())
			.setPeriodoJuros(dto.getPeriodoJuros())
			.setPaga(dto.getPaga())
			.buildModel();
		Conta conta = this.contaRepository.save(c);
		
		ContaDTO novoDto = 
			new ContasBuilder()
			.setId(conta.getId())
			.setDescricao(conta.getDescricao())
			.setValor(conta.getValor())
			.setVencimento(conta.getVencimento())
			.setJuros(conta.getJuros())
			.setPeriodoJuros(conta.getPeriodoJuros())
			.setStatus(conta.getStatus())
			.setPaga(conta.getPaga())
			.build();
		return novoDto;
	}
	
	public Boolean pagarConta(Long id) {
		Optional<Conta> contaOpt = contaRepository.findById(id);
		
		if (contaOpt.isEmpty()) {
			return Boolean.FALSE;			
		}
		
		Conta c = contaOpt.get();
		c.setPaga(Boolean.TRUE);
		c.setStatus(StatusConta.PAGO);
		
		this.contaRepository.save(c);
		return Boolean.TRUE;
	}
	
	public void excluirConta(Long id) {
		this.contaRepository.deleteById(id);
	}
}
