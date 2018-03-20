package logisticassa.exception;

public class RotaNaoEncontradaOrigemException extends Exception {
	
	private String origem;

	
	public RotaNaoEncontradaOrigemException( String origem ) {
		
		this.setOrigem( origem );

	}
	

	

	public String getOrigem() {
		return this.origem;
	}



	private void setOrigem( String origem ) {
		this.origem = origem;
	}
	
	
	public String toString() {
		
		return "RotaNaoEncontradaOrigemException. Origem = " + this.getOrigem();
		
	}

}
