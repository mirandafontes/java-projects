package factory;

import interfaces.*;

import products.Product1B;
import products.Product2B;

public class FactoryB implements IFactory {

	@Override
	public Object createProduct1() {

		return new Product1B();
		
	}

	@Override
	public Object createProduct2() {

		return new Product2B();
		
	}

}
