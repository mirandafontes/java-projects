package client;

import interfaces.*;
import factory.*;

public class MainClient {
	
	public static void main(String[] args) {
		
		IFactory fabricaA = new FactoryA();
		IFactory fabricaB = new FactoryB();

		IProduct1 produto1;
		IProduct2 produto2;
		
		
		System.out.println("Inicio fabrica A\n\n");
		
		produto1 = ( IProduct1 ) fabricaA.createProduct1();
		produto2 = ( IProduct2 ) fabricaA.createProduct2();
		
		produto1.sayHai();
		produto2.sayHi();
		
		produto1.sayBye();
		produto2.sayBai();
		
		System.out.println("\n\nFim fabrica A\n\n");
		
		
		System.out.println("Inicio fabrica B\n\n");
		
		produto1 = ( IProduct1 ) fabricaB.createProduct1();
		produto2 = ( IProduct2 ) fabricaB.createProduct2();
		
		produto1.sayHai();
		produto2.sayHi();
		
		produto1.sayBye();
		produto2.sayBai();
		
		System.out.println("\n\nFim fabrica B\n\n");
		
	}

}
