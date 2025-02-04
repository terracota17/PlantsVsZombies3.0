package control.Commands;

import logic.Game;

import java.io.IOException;

import exceptions.*;

public abstract class Command {

	  protected final String name;
	  protected final String shortcut;
	  private final String details; 
	  private final String help;

	  protected static final String incorrectNumberOfArgsMsg = "Incorrect number of arguments";
	  protected static final String incorrectArgsMsg = "Incorrect arguments format";
	  
	  public Command(String name,  String shortcut, String details, String help){    
	    this.name = name;
	    this.shortcut = shortcut;
	    this.details = details;
	    this.help = help;
	  }
	  
	  public abstract boolean execute(Game game) throws CommandExecuteException, IOException;

	  public abstract Command parse(String[] commandWords) throws CommandParseException;
	  
	  protected boolean matchCommandName(String name) {
		    return this.shortcut.equalsIgnoreCase(name) || 
		        this.name.equalsIgnoreCase(name);
	  }
	  
	  protected Command parseNoParamsCommand(String[] words)throws CommandParseException {
		  
		  if (matchCommandName(words[0])) {
			  if (words.length != 1)
				  throw new CommandParseException("[ERROR]: Command "+ name+" :"+incorrectNumberOfArgsMsg);
			  else return this;
		  }
		 return null;  
			 
	 }

	  public String helpText(){
	    return details + ": " + help + "\n";
	  }
}