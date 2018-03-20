package logisticassa.exception;

public class RotaNaoEncontradaIdNomeException extends Exception {
	
	private int id = -1;
	private String nome;
	
	public RotaNaoEncontradaIdNomeException( int id ) {
		
		this.setId( id );
		
	}
	
	public RotaNaoEncontradaIdNomeException( String nome ) {
		
		this.setNome( nome );
		
	}
	

	public int getId() {
		return this.id;
	}


	public String getNome() {
		return this.nome;
	}

	private void setId(int id) {
		this.id = id;
	}



	private void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		
		if ( this.id != -1 ) {
			
			return "RotaNaoEncontradaPorId Exception. ID = " + Integer.toString( this.id );
			
		}
		
		else if ( this.nome.isEmpty() ) {
			
			return "RotaNaoEncontradaPorNome Exception. Nome = " + this.getNome();
			
		}
		
		return "Rota Exception. ID = " + Integer.toString( this.id ) + "  Nome = " + this.getNome();
		
	}

}
