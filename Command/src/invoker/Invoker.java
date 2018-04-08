package invoker;

import java.util.ArrayList;

import interfaces.ICommand;

public class Invoker {

	
	private ArrayList<ICommand> commands = new ArrayList<ICommand>();
	
	private int current;
	
	public Invoker() {
		
		this.current = -1;
				
	}

	public void storeAndExecute( ICommand c ) {
		
		commands.add( c );
		c.execute();
		
		current = commands.size() - 1;

		
	} 
	
	public void unexecute() {
		
		if( commands.isEmpty() == false && current >= 0 ) {
		
			commands.get(current).unexecute();
			
			current--;
			
		}
		
	}
	
	public void reexecute() {
		
		if( current < commands.size() - 1 ) {
		
			if( current == - 1 ) {
				
				current++;
				
			}
						
			while( current < commands.size() ) {
	
				commands.get(current).execute();
					
				current++;
				
			}
				
			current--;
					
			}
		
	}
		

	}
