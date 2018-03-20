package concretos;

import abstratos.Decorator;

public class MassaChocolate extends Decorator {

	@Override
	public void assar() {
		
		super.assar();
		System.out.println("A massa e de chocolate");
		
	}

}
