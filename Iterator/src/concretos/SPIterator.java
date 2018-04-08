package concretos;

import java.lang.reflect.Field;
import java.util.Collection;

public class SPIterator<T> {
	
	private String operator;
	
	private String attribute;
	
	private Collection<?> collection;
	
	private int value;
	
	private int position;
		
	
	public SPIterator( String operator, String attribute, Collection<?> collection, int value ) {
		
		this.setOperator( operator );
		
		this.attribute = attribute;
		
		this.collection = collection;
				
		this.value = value;
		
		this.position = 0;
		
	}
	
	private void setOperator( String operator ) {
		
		if( operator.equals(">") ) {
			
			this.operator = operator;
			
		}
		
		else if( operator.equals(">=") ) {
			
			this.operator = operator;
			
		}
		
		
		else if( operator.equals("<") ) {
			
			this.operator = operator;
			
		}
		
		
		else if( operator.equals("<=") ) {
			
			this.operator = operator;
			
		}
		
		
		else if( operator.equals("==") ) {
			
			this.operator = operator;
			
		}
		
		
		else if ( operator.equals("!=")) {
			
			this.operator = operator;
			
		}
 		
		
	}


	public T next() throws NoSuchFieldException, SecurityException, NullPointerException {
		
		//T obj = ( T ) collection.toArray()[position];
		
		Field field = collection.getClass().getField( attribute );
		
		if( this.operator.equals(">") ) {
			
			//field.
			
		}
		
		else if( this.operator.equals(">=") ) {
			
			//this.operator = operator;
			
		}
		
		
		else if( this.operator.equals("<") ) {
			
			//this.operator = operator;
			
		}
		
		
		else if( this.operator.equals("<=") ) {
			
			//this.operator = operator;
			
		}
		
		
		else if( this.operator.equals("==") ) {
			
			//this.operator = operator;
			
		}
		
		
		else if ( this.operator.equals("!=")) {
			
			//this.operator = operator;
			
		}
		
		position++;
		
		return null;
		
	}

	public boolean hasNext() {
		
		if( position >= collection.toArray().length || collection.toArray()[position] == null ) {
			
			return false;
			
		}

		return true;
	}

}
