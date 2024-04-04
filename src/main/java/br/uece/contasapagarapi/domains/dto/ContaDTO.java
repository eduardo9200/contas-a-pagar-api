package br.uece.contasapagarapi.domains.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.uece.contasapagarapi.domains.enums.PeriodoJuros;
import br.uece.contasapagarapi.domains.enums.StatusConta;
import br.uece.contasapagarapi.domains.utils.ContaUtils;

public class ContaDTO {
	private Long id;
	private String conta;
	private BigDecimal valor;
	private LocalDate vencimento;
	private BigDecimal juros;
	private PeriodoJuros periodoJuros;
	private StatusConta status;
	private BigDecimal valorAtualizado;
	private Boolean paga;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String descricao) {
		this.conta = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public LocalDate getVencimento() {
		return vencimento;
	}
	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}
	public BigDecimal getJuros() {
		return juros;
	}
	public void setJuros(BigDecimal juros) {
		this.juros = juros;
	}
	public PeriodoJuros getPeriodoJuros() {
		return periodoJuros;
	}
	public void setPeriodoJuros(PeriodoJuros periodoJuros) {
		this.periodoJuros = periodoJuros;
	}
	public StatusConta getStatus() {
		return status;
	}
	public void setStatus(StatusConta status) {
		this.status = status;
	}

	public Boolean getPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}

	public BigDecimal getValorAtualizado() {
		this.valorAtualizado = ContaUtils.getValorAtualizado(this);
		return this.valorAtualizado;
	}
	public void setValorAtualizado(BigDecimal valorAtualizado) {
		this.valorAtualizado = valorAtualizado;
	}
}
