package factory;

import interfaces.*;

import products.Product1A;
import products.Product2A;

public class FactoryA implements IFactory {

	@Override
	public Object createProduct1() {
		
		return new Product1A();
		
	}

	@Override
	public Object createProduct2() {

		return new Product2A();
		
	}
	
	

}
