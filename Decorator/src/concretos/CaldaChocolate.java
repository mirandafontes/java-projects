package concretos;

import abstratos.Decorator;

public class CaldaChocolate extends Decorator {

	@Override
	public void assar() {
		
		super.assar();
		System.out.println("A calda e de chocolate");
		
	}

}
