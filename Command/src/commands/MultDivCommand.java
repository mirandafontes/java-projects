package commands;

import interfaces.*;
import receiver.*;

public class MultDivCommand implements ICommand {

	private Calculadora calculadora;
	
	private double valor;
	
	public MultDivCommand( Calculadora calculadora, double valor ) {
		
		this.calculadora = calculadora;
		this.valor = valor;
		
	}
	
	@Override
	public void execute() {
		
		this.calculadora.multiplicar( this.valor );
		
	}

	@Override
	public void unexecute() {
		
		this.calculadora.dividir( this.valor );
		
	}

}
