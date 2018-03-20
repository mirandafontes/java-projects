package negocio;

public class Service {
	
	private float massa;
	
	private float altura;

	public float getMassa() {
		
		return massa;
		
	}

	public float getAltura() {
		
		return altura;
		
	}

	public void setMassa( float massa ) {
		
		this.massa = massa;
	}
	

	public void setAltura( float altura ) {
		
		this.altura = altura;
		
	}
	
	public String getIMC() {
		
		float imc = ( this.massa / ( this.altura * this.altura ) );
		
		if( imc < 18.5f ) {
			
			return imc + ". Abaixo do peso.";
			
		}
		
		else if ( imc >= 18.5f && imc <= 24.9f ) {
			
			return imc + ". No peso ideal.";
			
		}
		
		return imc + ". Acima do peso.";
		
	}
	
	

}
