package control.Commands;

import logic.Game;
import exceptions.*;

public class GarlicPushCommand extends Command {

	private static String name="garlic";
	private static String shortcut="g";
	private static String details= "[g]arlic";
	private static String garlic= "garlic push";
	
	public  GarlicPushCommand (/*String name, String shortcut, String details, String help*/) {
		super(name, shortcut, details, garlic);
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			
			game.garlicPushC();
		}catch(CommandExecuteException e) {
			
			System.out.println(e.getMessage());
			throw new CommandExecuteException("[ERROR]: Failed to Garlic push ",e.getCause());
		}
		return true;
	}
	
	 public  Command parse(String[] commandWords) throws CommandParseException{
		 
		 if(commandWords[0].equals("g") || commandWords[0].equals("garlic")) {
			 
			 if(commandWords.length == 1) {
				 
				 GarlicPushCommand exit= new GarlicPushCommand();
				 return exit;
				 
			 }else if(super.parseNoParamsCommand(commandWords) == null) return null;
			 
			 else return null;
			 
		 }
		 else return null;
	 }
}