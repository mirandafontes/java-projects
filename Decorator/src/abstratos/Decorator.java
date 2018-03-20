package abstratos;

public abstract class Decorator implements IComponent {
	
	private IComponent decorated;

	public void setDecorated( IComponent decorated ) {
		
		this.decorated = decorated;
		
	}
	
	public void assar() {
		
		if( decorated != null ) {
			
			this.decorated.assar();
			
		}
		
	}

}
