package control.Commands;


import exceptions.*;
import logic.Game;

public class UpdateCommand extends Command {
	private static String name="none";
	private static String shortcut="n";
	private static String details = "[n]one";
	private static String help = "update";
	
	public UpdateCommand() {
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		game.update();
		return true;
	}
	
	 public  Command parse(String[] commandWords) throws CommandParseException{
		 
		 
		 if(commandWords[0].equals("n") || commandWords[0].equals("none") || commandWords[0].equals("")){
			 if(commandWords.length == 1) {
				 
				 UpdateCommand update= new UpdateCommand();
				 return update;
				 
			 }else if(super.parseNoParamsCommand(commandWords) == null) return null;
			 
			 else return null;
			 
		 } else return null;

	 }
}