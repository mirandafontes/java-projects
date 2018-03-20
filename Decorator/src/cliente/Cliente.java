package cliente;

import abstratos.*;
import concretos.*;

public class Cliente {

	public static void main(String[] args) {
		
		IComponent bolo = new BoloSimples();
		
		Decorator mc = new MassaChocolate();
		mc.setDecorated( bolo );
		
		Decorator cc = new CaldaChocolate();
		cc.setDecorated( mc );
		
		Decorator cg = new ChocolateGranulado();
		cg.setDecorated( cc );
		
		cg.assar();
		
		

	}

}
