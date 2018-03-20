package logisticassa.exception;

public class RotaNaoEncontradaDestinoException extends Exception {
	
	private String Destino;

	
	public RotaNaoEncontradaDestinoException( String Destino ) {
		
		this.setDestino( Destino );

	}
	


	public String getDestino() {
		return this.Destino;
	}



	private void setDestino( String Destino ) {
		this.Destino = Destino;
	}
	
	
	public String toString() {
		
		return "RotaNaoEncontradaDestinoException. Origem = " + this.getDestino();
		
	}

}