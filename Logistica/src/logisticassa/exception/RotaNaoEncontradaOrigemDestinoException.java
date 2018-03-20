package logisticassa.exception;

public class RotaNaoEncontradaOrigemDestinoException extends Exception {
	
	private String origem;
	private String destino;
	
	public RotaNaoEncontradaOrigemDestinoException( String origem, String destino ) {
		
		this.setOrigem(origem);
		this.setDestino(destino);
		
	}

	private String getOrigem() {
		return origem;
	}

	private String getDestino() {
		return destino;
	}

	private void setOrigem(String origem) {
		this.origem = origem;
	}

	private void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String toString() {
		
		return "RotaNaoEncontradaOrigemDestinoException. Origem = " + this.getOrigem() + " Destino = " + this.getDestino();
		
	}

}
