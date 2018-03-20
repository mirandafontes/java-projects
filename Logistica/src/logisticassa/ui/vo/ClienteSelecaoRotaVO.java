package logisticassa.ui.vo;

public class ClienteSelecaoRotaVO {

	private String nome;
	private double peso;

	public ClienteSelecaoRotaVO( String nome, double peso ) {

		this.setNome( nome );
		this.setPeso( peso );
		
	}

	public String getNome() {
		
		return this.nome;
		
	}
	
	public double getPeso() {
		
		return this.peso;
		
	}

	private void setNome( String nome ) {
		
		this.nome = nome;
		
	}

	private void setPeso( double peso ) {
		
		this.peso = peso;
		
	}
	


	
}
