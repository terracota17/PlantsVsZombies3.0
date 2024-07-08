package control.Commands;


import exceptions.*;
import logic.Game;

public class LightFlashCommand extends Command {

	private static String name="light";
	private static String shortcut="l";
	private static String details= "[l]ight";
	private static String light= "light flash";
	
	public  LightFlashCommand () {
		super(name, shortcut, details, light);
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.lightFlashC();
		}catch(CommandExecuteException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException("[ERROR]: Failed to light Flash ", e.getCause());
		}
	
		return true;
	}
	
	 public  Command parse(String[] commandWords) throws CommandParseException{
		
		 if(commandWords[0].equals("l") || commandWords[0].equals("light")){
			  if(commandWords.length == 1) {
				  
				  LightFlashCommand exit= new LightFlashCommand();
					 return exit;
			  }else if(super.parseNoParamsCommand(commandWords) == null) return null;
			  
			  else return null;
			 
			 
		 }else return null;
	 }
}