package logisticassa.ui.vo;

public class RotasVO {

	private String nome;
	
	private int id;
	private int tempoEntrega;
	
	private double capacidadeTotal;
	private double capacidadeAlocada;
	private double custoGrama;
	
	private String origem;
	private String destino;
	
	private char tipo;
	
	public RotasVO( String nome , int id, int tempoEntrega, double capacidadeTotal, double capacidadeAlocada, double custoGrama, String origem, String destino, char tipo ){

		this.nome = nome;
		this.id = id;
		this.tempoEntrega = tempoEntrega;
		this.capacidadeTotal = capacidadeTotal;
		this.capacidadeAlocada = capacidadeAlocada;
		this.custoGrama = custoGrama;
		this.origem = origem;
		this.destino = destino;
		this.tipo = tipo;
		
	}
	
	public String getNome() {
		
		return this.nome;
		
	}
	
	public int getId() {
		
		return this.id;
		
	}
	
	public int getTempoEntrega() {
		
		return this.tempoEntrega;
		
	}
	
	public double getCapacidadeTotal() {
		
		return this.capacidadeTotal;
		
	}
	
	public double getCapacidadeAlocada() {
		
		return this.capacidadeAlocada;
		
	}
	
	public double getCustoGrama() {
		
		return this.custoGrama;
		
	}

	public String getOrigem() {
		
		return this.origem;
		
	}

	public String getDestino() {
		
		return this.destino;
		
	}

	public char getTipo() {
		
		return this.tipo;
		
	}
	
	public String toString() {
		
		return "Nome : [ " + this.getNome() + " ]" + "  Tempo Entrega : ( " + Integer.toString( this.getTempoEntrega() ) + " ) Dias";
		
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			
		
			return true;
			
		}
		
		if (obj == null) {
			
			return false;
			
		}
		
		if (!(obj instanceof RotasVO)) {
			
			return false;
			
		}
		
		RotasVO other = (RotasVO) obj;
		if (id != other.id) {
			
			return false;
			
		}
		
		return true;
	}
	
	
	public boolean podeAlocar( double peso ) {
		
		return ( ( peso <= this.getCapacidadeTotal() - this.getCapacidadeAlocada() ) );
		
	}
	
	public boolean alocar( double peso ) {
		
		if( this.podeAlocar( peso ) ) {
			
			this.setCapacidadeAlocada( this.getCapacidadeAlocada() + peso );
			return true;
			
		}
		
		else {
			
			return false;
			
		}
		
	}

	private void setCapacidadeAlocada( double capacidadeAlocada ) {
		this.capacidadeAlocada = capacidadeAlocada;
	}
	


	
	
}
