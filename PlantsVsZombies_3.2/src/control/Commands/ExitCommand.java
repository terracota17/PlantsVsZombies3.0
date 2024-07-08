package control.Commands;


import exceptions.*;
import logic.Game;

public class ExitCommand extends Command {

	private static String name="exit";
	private static String shortcut="e";
	private static String details= "[e]xit:";
	private static String help= "exit game";
	
	public ExitCommand() {
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		System.out.println("Nobody Wins...");
		System.exit(0);
		return false;
	}
	
	 public  Command parse(String[] commandWords) throws CommandParseException{
		
		 if(commandWords[0].equals("e") || commandWords[0].equals("exit")){
			 
			 if(commandWords.length == 1) {
				 
				 ExitCommand exit= new ExitCommand();
				 return exit;
				 
			 }else if(super.parseNoParamsCommand(commandWords)== null) return null;
			 
			 else return null;
		 }
		 else return null;
		 
	 }
}