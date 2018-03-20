package logisticassa.persistencia;

import java.util.ArrayList;
import logisticassa.entidades.*;;


public interface RotasDAO {
	
	public void save( Rota rota ) throws Exception;
	public void updateAlocadaById( int id, double capacidadeAlocada ) throws Exception;
	
	public ArrayList<Rota> findByName( String id ) throws Exception;
	public ArrayList<Rota> findByOrigem( String origem ) throws Exception;
	public ArrayList<Rota> findByDestino( String destino ) throws Exception;
	public ArrayList<Rota> findByOrigemAndDestino ( String origem, String destino ) throws Exception;
	public ArrayList<Rota> findAll() throws Exception;	
	public ArrayList<Rota> findById( int id ) throws Exception;
	
	public ArrayList<String> findDestino() throws Exception;
	public ArrayList<String> findOrigem() throws Exception;
	public ArrayList<String> findDestinoByOrigem( String origem ) throws Exception;

}
