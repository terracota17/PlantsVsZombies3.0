package exceptions;

public class NoMoreVampiresException extends CommandExecuteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMoreVampiresException() {super();}
	
	public NoMoreVampiresException(String message) { super(message);}
	
	public NoMoreVampiresException(String message, Throwable cause) {	super(message, cause);}
	
	public NoMoreVampiresException(Throwable cause){ super(cause); } 

	NoMoreVampiresException (String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);

	}
}
