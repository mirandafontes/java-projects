package receiver;

public class Calculadora {
	
	private double total;
	
	public Calculadora() {
		
		this.setTotal( 0 );
		
	}
	
	public void somar( double valor ) {
	
		this.total += valor;
		
	}
	
	public void subtrair( double valor ) {
		
		this.total -= valor;
		
	}
	
	public void multiplicar( double valor ) {
		
		this.total *= valor;
		
	}
	
	public void dividir( double valor ) {
		
		this.total /= valor;
		
	}
		
	private void setTotal( double total ) {
		
		this.total = total;
		
	}
	
	public double getTotal() {
		
		return this.total;
		
	}

}
