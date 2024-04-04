package br.uece.contasapagarapi.domains.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

import br.uece.contasapagarapi.domains.dto.ContaDTO;
import br.uece.contasapagarapi.domains.enums.PeriodoJuros;
import br.uece.contasapagarapi.domains.enums.StatusConta;

public class ContaUtils {

	public static StatusConta getStatusAtualizado(boolean contaPaga, LocalDate vencimento) {
		LocalDate hoje = LocalDate.now();
		StatusConta status;
		
		if (contaPaga) {
			status = StatusConta.PAGO;
		} else if (hoje.isAfter(vencimento)) {
			status = StatusConta.VENCIDA;
		} else if (hoje.isEqual(vencimento)) {
			status = StatusConta.ABERTA_PERTO_VENCER;
		} else if (hoje.isBefore(vencimento) && hoje.isAfter(vencimento.minusDays(7))) {
			status = StatusConta.ABERTA_PERTO_VENCER;
		} else {
			status = StatusConta.ABERTA;
		}
		
		return status;
	}
	
	public static BigDecimal getValorAtualizado(ContaDTO dto) {
		return getValorAtualizado(
			dto.getValor(),
			dto.getVencimento(),
			dto.getJuros(),
			dto.getPeriodoJuros(),
			dto.getPaga()
		);
	}
	
	public static BigDecimal getValorAtualizado(
		BigDecimal valorAtual,
		LocalDate vencimento,
		BigDecimal juros,
		PeriodoJuros periodoJuros,
		boolean paga
	) {
		StatusConta status = getStatusAtualizado(paga, vencimento);
		
		if (status == StatusConta.VENCIDA) {
			int tempoAtraso = 0;
			LocalDate hoje = LocalDate.now();
			
			switch(periodoJuros) {
			case AD:
				tempoAtraso = Period.between(vencimento, hoje).getDays();
				break;
			case AM:
				tempoAtraso = Period.between(vencimento, hoje).getMonths();
				break;
			case AA:
				tempoAtraso = Period.between(vencimento, hoje).getYears();
				break;
			}
			
			BigDecimal taxa = juros.divide(new BigDecimal("100"), RoundingMode.HALF_UP);
			BigDecimal um = new BigDecimal("1");
			BigDecimal taxaJurosDiariaFracionada = um.add(taxa);
			BigDecimal valorAtualizado = valorAtual.multiply(taxaJurosDiariaFracionada.pow(Math.abs(tempoAtraso)));
			return valorAtualizado;
			
		} else {
			return valorAtual;
		}
	}
}
