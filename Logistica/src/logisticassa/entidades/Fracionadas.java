package logisticassa.entidades;

import java.util.ArrayList;
import logisticassa.entidades.Rota;

public class Fracionadas extends Rota {

	
	public ArrayList<Rota> rotas = new ArrayList<Rota>();
	private String origem = "";
	private String destino = "";

	public Fracionadas( int id, String nome  ) {
		
		super( id, nome );
		
	}
	
	public Fracionadas( int id, String nome, String origem, String destino ) {
		
		super( id, nome );
		this.setOrigem( origem );
		this.setDestino( destino );
		
	}
	
	public void add( Rota nova ) {
		
		this.rotas.add( nova );
		
		this.setTempoEntrega( this.calcularTempoEntrega() );
		this.setCustoGrama( this.calcularCustoGrama() );
		this.setCapacidadeTotal( this.calcularCapacidadeTotal() );
		this.setCapacidadeAlocada( this.calcularCapacidadeAlocada() );
		
		origem = rotas.get(0).getOrigem();
		
		destino = rotas.get( rotas.size() - 1 ).getDestino();
		
		this.setOrigem( origem );
		
		this.setDestino( destino );
		
		
		
	}

	public double calcularCapacidadeTransporte() {
		
		double menor = this.rotas.get(0).calcularCapacidadeTransporte();
		
		for( int i = 0 ; i < this.rotas.size() ; i++ ) {
			
			if( this.rotas.get( i ).calcularCapacidadeTransporte() < menor ) {
				
				menor = this.rotas.get( i ).calcularCapacidadeTransporte();
				
			}
			
		}
		
		
		return menor;
		
	}


	private int calcularTempoEntrega() {
		
		int tempoEntregaTotal = 0;
		
		for( int i = 0 ; i < this.rotas.size() ; i++ ) {
			
			
			tempoEntregaTotal += this.rotas.get( i ).getTempoEntrega();
			
			
		}
		
		tempoEntregaTotal += this.rotas.size() - 1;
		
		return tempoEntregaTotal;
	}



	private double calcularCustoGrama() {
		
		double custoGramaTotal = 0;
		
		for( int i = 0 ; i < this.rotas.size() ; i++ ) {
			
			custoGramaTotal += this.rotas.get( i ).getCustoGrama();
			
		}
		
		return custoGramaTotal;
		
	}

	@Override
	protected void setCustoGrama( double custoGrama ) {
		
		this.custoGrama = ( custoGrama * 0.8 );
		
	}



	private double calcularCapacidadeTotal() {
		
		double capacidadeTotal = 0;
		
		for( int i = 0 ; i < this.rotas.size() ; i++ ) {
			
			capacidadeTotal += this.rotas.get( i ).getCapacidadeTotal();
			
		}
		
		return capacidadeTotal;
	}




	private double calcularCapacidadeAlocada() {
		
		double capacidadeAlocada = 0;
		
		for( int i = 0 ; i < this.rotas.size() ; i++ ) {
			
			capacidadeAlocada += this.rotas.get( i ).getCapacidadeAlocada();
			
		}
		
		return capacidadeAlocada;
		
	}


	@Override
	public void alocar( double peso ) {
		
		if(  peso <= this.getCapacidadeTotal() ) {
		
			for( int i = 0 ; i < this.rotas.size() ; i++ ) {
				
				this.rotas.get( i ).alocar( peso );
				
			}	
			
		}
		
	}


	@Override
	public void desalocar( double peso ) {
		
		if(  peso >= this.getCapacidadeTotal() )
		
			for( int i = 0 ; i < this.rotas.size() ; i++ ) {
				
				this.rotas.get( i ).desalocar( peso );
				
			}
		
	}


}
