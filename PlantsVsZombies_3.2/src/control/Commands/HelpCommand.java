package control.Commands;

import logic.Game;
import exceptions.*;

public class HelpCommand extends Command {

	private static String name="help";
	private static String shortcut="h";
	private static String details = "[h]elp";
	private static String help = "show this help";
	
	public HelpCommand() {
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game game)  {
		
		System.out.println(CommandGenerator.commandHelp());
		return true;
	}
	
	 public  Command parse(String[] commandWords) throws CommandParseException{
		 
	
		 if(commandWords[0].equals("h") || commandWords[0].equals("help")){
			 if(commandWords.length == 1) {
				 
				 HelpCommand helpC= new HelpCommand();
				 return helpC;
				 
			 }else if(super.parseNoParamsCommand(commandWords) == null) return null;
			 
			 else return null;
		 }
		 else return null;

	 }
}
