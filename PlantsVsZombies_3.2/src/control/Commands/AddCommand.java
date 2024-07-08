package control.Commands;

import exceptions.*;
import exceptions.NumberFormatException;
import logic.Game;

public class AddCommand extends Command {

	private static String name="add";
	private static String shortcut="a";
	private static String details = "[a]dd <x> <y>";
	private static String help= "add slayer in position x, y";
	private int x;
	private int y;
	
	public AddCommand(int x, int y) {
		super(name, shortcut, details, help);
		this.x=x;
		this.y=y;
	}
	
	public AddCommand() {
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		try{
			game.addSlayerIn(x, y);
		}catch(CommandExecuteException e){
			System.out.println(e.getMessage());
			throw new CommandExecuteException("[ERROR]: Failed to add this Slayer", e.getCause());
	 	}
		return true;
	}
	
	 public  Command parse(String[] commandWords) throws CommandParseException {
		 
		 char c ,  d;
		 if(commandWords[0].equals("a") || commandWords[0].equals("add")){
			 
			if(commandWords.length == 3) {
				c=commandWords[1].charAt(0);
				 d=commandWords[2].charAt(0);
				 
				 if(Character.isDigit(c) && Character.isDigit(d)) {
					 this.x= Integer.parseInt(commandWords[1]);
					 this.y= Integer.parseInt(commandWords[2]);
					 AddCommand add= new AddCommand(this.x, this.y);
					 return add;
					 
				 }else throw new NumberFormatException("[ERROR]: Unvalid argument for add slayer command | <x> <y> must be numbers");
				 
			}else if(super.parseNoParamsCommand(commandWords) == null) return null; 
			else return null;
			
		}else return null;
	 
			 

	 }
}
