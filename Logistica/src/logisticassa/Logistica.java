package logisticassa;

import java.util.ArrayList;
import logisticassa.ui.vo.RotasVO;

public interface Logistica {

	public void registrarRotaFracionada(int id, String nome, int[] ids) throws Exception;
	public void registrarRotaDireta(int id, String nome, String destino, String origem, int tempoEntrega,	double custoGrama, double capacidadeTotal ) throws Exception;
	public ArrayList<String> gerarOrigem() throws Exception;
	public ArrayList<String> gerarDestino( String origem ) throws Exception;
	public boolean gerarContratacaoDeTransporte( int id, double peso ) throws Exception;
	public ArrayList<RotasVO> gerarRotas() throws Exception;
	public ArrayList<RotasVO> findByOrigemAndDestino( String origem, String destino ) throws Exception;
	

	
}
