package products;

import interfaces.IProduct1;

public class Product1B implements IProduct1 {
	
	@Override
	public void sayHai() {
		
		System.out.println("HAI! PRODUCT 1B DES!");
		
	}
	
	@Override
	public void sayBye() {
		
		System.out.println("BYE! PRODUCT 1B DES!");
		
	} 

}
