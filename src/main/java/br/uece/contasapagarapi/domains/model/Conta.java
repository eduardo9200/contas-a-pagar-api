package br.uece.contasapagarapi.domains.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.uece.contasapagarapi.domains.enums.PeriodoJuros;
import br.uece.contasapagarapi.domains.enums.StatusConta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Conta")
public class Conta {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String descricao;
	private BigDecimal valor;
	private LocalDate vencimento;
	private BigDecimal juros;
	
	@Column(name = "periodo_juros")
	@Enumerated(EnumType.STRING)
	private PeriodoJuros periodoJuros;
	
	@Enumerated(EnumType.STRING)
	private StatusConta status;

	private Boolean paga;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
}
