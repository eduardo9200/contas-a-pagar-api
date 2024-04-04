package br.uece.contasapagarapi.domains.builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.uece.contasapagarapi.domains.dto.ContaDTO;
import br.uece.contasapagarapi.domains.enums.PeriodoJuros;
import br.uece.contasapagarapi.domains.enums.StatusConta;
import br.uece.contasapagarapi.domains.model.Conta;
import br.uece.contasapagarapi.domains.utils.ContaUtils;

public class ContasBuilder {
	private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate vencimento;
    private BigDecimal juros;
    private PeriodoJuros periodoJuros;
    private StatusConta status;
    private Boolean paga;
    private BigDecimal valorAtualizado;
    
	public ContasBuilder setId(Long id) {
		this.id = id;
		return this;
	}
	public ContasBuilder setDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	public ContasBuilder setValor(BigDecimal valor) {
		this.valor = valor;
		return this;
	}
	public ContasBuilder setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
		return this;
	}
	public ContasBuilder setJuros(BigDecimal juros) {
		this.juros = juros;
		return this;
	}
	public ContasBuilder setPeriodoJuros(PeriodoJuros periodoJuros) {
		this.periodoJuros = periodoJuros;
		return this;
	}
	public ContasBuilder setStatus(StatusConta status) {
		this.status = status;
		return this;
	}
	public ContasBuilder setPaga(Boolean paga) {
		this.paga = paga;
		return this;
	}
	public ContasBuilder setValorAtualizado(BigDecimal valorAtualizado) {
		this.valorAtualizado = valorAtualizado;
		return this;
	}
    
    public ContaDTO build() {
    	ContaDTO dto = new ContaDTO();
    	dto.setId(id);
    	dto.setConta(descricao);
    	dto.setValor(valor);
    	dto.setVencimento(vencimento);
    	dto.setJuros(juros);
    	dto.setPeriodoJuros(periodoJuros);
    	dto.setStatus(status);
    	dto.setPaga(paga);
    	dto.setValorAtualizado(ContaUtils.getValorAtualizado(dto));
    	return dto;
    }
    
    public Conta buildModel() {
    	Conta c = new Conta();
    	c.setId(id);
    	c.setDescricao(descricao);
    	c.setValor(valor);
    	c.setVencimento(vencimento);
    	c.setJuros(juros);
    	c.setPeriodoJuros(periodoJuros);
    	c.setPaga(paga);
    	c.setStatus(ContaUtils.getStatusAtualizado(paga, vencimento));
    	return c;
    }
}
