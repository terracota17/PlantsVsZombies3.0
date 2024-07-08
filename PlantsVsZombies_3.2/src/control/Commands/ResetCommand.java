package control.Commands;


import exceptions.*;
import logic.Game;

public class ResetCommand extends Command {

	private static String name="reset";
	private static String shortcut="r";
	private static String details = "[r]eset";
	private static String help = "reset game";
	
	public ResetCommand(/*String name, String shortcut, String details, String help*/) {
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		game.reset();
		return true;
	}
	
	 public  Command parse(String[] commandWords) throws CommandParseException {
		 
		 if(commandWords[0].equals("r") || commandWords[0].equals("reset")){
			  if(commandWords.length == 1) {
				  
				 ResetCommand reset= new ResetCommand();
				 return reset;
				  
			  }else if(super.parseNoParamsCommand(commandWords)== null) return null;
			  
			  else return null;
		
		
		 }else return null;

	 }
}
