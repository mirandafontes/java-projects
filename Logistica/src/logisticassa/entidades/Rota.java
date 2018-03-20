package logisticassa.entidades;

public abstract class Rota {
	
	protected int id;
	
	protected String nome;
	protected String destino;
	protected String origem;
	
	protected int tempoEntrega;
	
	protected double custoGrama;
	protected double capacidadeTotal;
	protected double capacidadeAlocada = 0;
	
	
	public Rota( int id, String nome ) {

		this.setId( id );
		this.setNome( nome );
		this.setDestino( destino );
		this.setOrigem( origem );

	}
	
	
	public Rota( int id, String nome, String destino, String origem ) {

		this.setId( id );
		this.setNome( nome );
		this.setDestino( destino );
		this.setOrigem( origem );

	}
	
	public Rota( int id, String nome, String destino, String origem, int tempoEntrega, double custoGrama, double capacidadeTotal ) {

		this.setId( id );
		this.setNome( nome );
		this.setDestino( destino );
		this.setOrigem( origem );
		this.setTempoEntrega( tempoEntrega );
		this.setCustoGrama( custoGrama );
		this.setCapacidadeTotal( capacidadeTotal );
		
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + id;
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Rota other = (Rota) obj;
		
		if (id != other.id)
			return false;
		
		return true;
	}

	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}


	public String getDestino() {
		return this.destino;
	}


	public String getOrigem() {
		return this.origem;
	}


	public int getTempoEntrega() {
		return this.tempoEntrega;
	}


	public double getCustoGrama() {
		return this.custoGrama;
	}


	public double getCapacidadeTotal() {
		return this.capacidadeTotal;
	}


	public double getCapacidadeAlocada() {
		return this.capacidadeAlocada;
	}


	protected void setId(int id) {
		this.id = id;
	}
	
	protected void setNome(String nome) {
		this.nome = nome;
	}


	protected void setDestino(String destino) {
		this.destino = destino;
	}


	protected void setOrigem(String origem) {
		this.origem = origem;
	}


	protected void setTempoEntrega(int tempoEntrega) {
		this.tempoEntrega = tempoEntrega;
	}


	protected void setCustoGrama(double custoGrama) {
		this.custoGrama = custoGrama;
	}


	protected void setCapacidadeTotal(double capacidadeTotal) {
		this.capacidadeTotal = capacidadeTotal;
	}


	protected void setCapacidadeAlocada(double capacidadeAlocada) {
		this.capacidadeAlocada = capacidadeAlocada;
	}
	
	public abstract double calcularCapacidadeTransporte();
	
	public abstract void alocar( double peso );
	
	public abstract void desalocar( double peso );
	//Verificar se isso fica aqui mesmo
	
	public String toString() {
		
		return this.getId() + " - " + " (" + this.getOrigem() + " - " + this.getDestino() + ")";
		
	}
	
	

}
