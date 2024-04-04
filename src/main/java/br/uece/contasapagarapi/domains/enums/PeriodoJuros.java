package br.uece.contasapagarapi.domains.enums;

public enum PeriodoJuros {
	AD("a.d."),
	AM("a.m."),
	AA("a.a.");
	
	private String periodo;
	
	private PeriodoJuros(String periodo) {
		this.periodo = periodo;
	}
	
	public String getPeriodo() {
		return periodo;
	}
	
	public static PeriodoJuros from(String codigo) {
		for (PeriodoJuros status : PeriodoJuros.values()) {
            if (status.getPeriodo().equals(codigo)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
	}
}
