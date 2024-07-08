package control.Commands;

import exceptions.*;
import logic.Game;
 
public class SerializeCommand extends Command{
	
	private static String name = "serialize";
	private static String shortcut ="z";
	private static String details = "seriali[z]e";
	private static String serialize = "serialize Game";
	
	public SerializeCommand() {
		super(name, shortcut, details, serialize);
	}


	public boolean execute(Game game) throws CommandExecuteException {
		game.serialize();
		return false;
	}


	public Command parse(String[] commandWords) throws CommandParseException {
	
		 if(commandWords[0].equals("z") || commandWords[0].equals("serialize")){
			 if(commandWords.length == 1) {
				 
				 SerializeCommand serialize= new SerializeCommand();
				 return serialize; 
			 }else if(	 super.parseNoParamsCommand(commandWords)== null) return null;
			 
			 else return null;
			 
		 } else return null;
	}
	
	
}
