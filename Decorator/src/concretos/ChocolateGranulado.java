package concretos;

import abstratos.Decorator;

public class ChocolateGranulado extends Decorator {

	@Override
	public void assar() {
		
		super.assar();
		System.out.println("O granulado e de chocolate");
		
	}

}
