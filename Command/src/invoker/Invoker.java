package invoker;

import java.util.Stack;

import interfaces.ICommand;

public class Invoker {

	
	private Stack<ICommand> commands = new Stack<ICommand>();

	public void storeAndExecute( ICommand c ) {
		
		commands.add( c );
		c.execute();
		
	} 
	
	public void removeAndUnexecute() {
		
		if( commands.isEmpty() == false ) {
		
			commands.pop().unexecute();
			
		}
		
	}
	


}
