package commands;

import interfaces.*;
import receiver.*;

public class SomaSubCommand implements ICommand {
	
	private Calculadora calculadora;
	
	private double valor;
	
	public SomaSubCommand( Calculadora calculadora, double valor ) {
		
		this.calculadora = calculadora;
		this.valor = valor;
		
	}

	@Override
	public void execute() {
		
		this.calculadora.somar( this.valor );

	}

	@Override
	public void unexecute() {
		
		this.calculadora.subtrair( this.valor );
		
	}

}
