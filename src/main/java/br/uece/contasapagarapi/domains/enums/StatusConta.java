package br.uece.contasapagarapi.domains.enums;

public enum StatusConta {
	PAGO("PG"),
	VENCIDA("VC"),
	ABERTA("AB"),
	ABERTA_PERTO_VENCER("PV");
	
	private String status;
	
	private StatusConta(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public static StatusConta from(String codigo) {
		for (StatusConta status : StatusConta.values()) {
            if (status.getStatus().equals(codigo)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
	}
}
