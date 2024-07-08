package exceptions;

public class CommandExecuteException extends GameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommandExecuteException() {super();}
	
	public CommandExecuteException(String message) { super(message);}
	
	public CommandExecuteException(String message, Throwable cause) {	super(message, cause);}
	
	public CommandExecuteException(Throwable cause){ super(cause); } 

	 CommandExecuteException (String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);

	}


}
