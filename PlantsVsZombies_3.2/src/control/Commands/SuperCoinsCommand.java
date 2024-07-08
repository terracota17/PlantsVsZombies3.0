package control.Commands;

import exceptions.*;
import logic.Game;

public class SuperCoinsCommand extends Command{
	
	private static String name="coins";
	private static String shortcut="c";
	private static String details = "[c]oins";
	private static String help = "coins Command";


	public SuperCoinsCommand() {
		super(name, shortcut, details, help);
		
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.superCoinsC();
		return true;

	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		 
		 if(commandWords[0].equals("c") || commandWords[0].equals("coins")){
			 
			if(commandWords.length == 1) {
				
				 SuperCoinsCommand coins= new SuperCoinsCommand();
				 return coins;	 
				 
			}else if(super.parseNoParamsCommand(commandWords) == null) return null;
			
			else return null; 
		 }
		 else return null;
	}

}
