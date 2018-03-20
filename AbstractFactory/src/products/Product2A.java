package products;

import interfaces.IProduct2;

public class Product2A implements IProduct2 {
	
	@Override	
	public void sayHi() {
		
		System.out.println("HI! PRODUCT2A DES!");
		
	}
	
	@Override	
	public void sayBai() {
		
		System.out.println("BAI! PRODUCT2A DES!");
		
	}

}
