package logisticassa.persistencia.sql;

import java.util.ArrayList;
import logisticassa.entidades.*;

public class TestJDBC {
	
	public void run() throws Exception {
		
		RotaDAOSQL teste = new RotaDAOSQL();
		
		Rota rota = new Diretas( 20, "Teste", "Testelandia", "Origemlandia", 20, 50.00, 5000.0 );
		Rota rota2 = new Diretas( 25, "Teste2", "Testelandia2", "Origemlandia2", 20, 50.00, 5000.0 );
		
		teste.save(rota);
		teste.save(rota2);
		
		System.out.println( teste.findByName("Teste") );
		
		System.out.println( teste.findByName("Teste2").get( 0 ).getId() );
		
		
	}
	
	public void run2() throws Exception{
		
		
		RotaDAOSQL teste = new RotaDAOSQL();
		ArrayList<Rota> rotas;
		
		rotas = teste.findAll();
		
		for( Rota rota : rotas ) {
		
			System.out.println( rota + " ------ " + Double.toString( rota.getCapacidadeAlocada() ) );
			
		}


		
		
	}

	public static void main(String[] args) throws Exception {
		
		TestJDBC teste = new TestJDBC();
		
		teste.run2();

	}

}
