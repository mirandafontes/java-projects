package logisticassa.entidades;

public class Diretas extends Rota {


	public Diretas( int id, String nome, String destino, String origem, int tempoEntrega, double custoGrama, double capacidadeTotal ) {
		
		super( id, nome, destino, origem, tempoEntrega, custoGrama, capacidadeTotal );
		
	}

	@Override
	public double calcularCapacidadeTransporte() {
		return this.getCapacidadeTotal() - this.getCapacidadeAlocada();
	}

	@Override
	public void alocar( double peso ) {
		
		this.setCapacidadeAlocada( this.getCapacidadeAlocada() + peso );
		
	}//EXCEPTION SOBRE O PESO ACIMA DO MAXIMO

	@Override
	public void desalocar( double peso ) {
		
		this.setCapacidadeAlocada( this.getCapacidadeAlocada() - peso );
		
	}


}
