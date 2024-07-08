package control.Commands;

import logic.Game;
import exceptions.*;
import exceptions.NumberFormatException;

public class AddVampireCommand extends Command {

	private static String name="vampire";
	private static String shortcut="v";
	private static String details = "[v]ampire [tipo] <x> <y>";
	private static String help= "add vampire in position x, y";
	private int x;
	private int y;
	private String tipoV;
	
	public AddVampireCommand(int x, int y, String tipoV) {
		super(name, shortcut, details, help);
		this.x=x;
		this.y=y;
		this.tipoV=tipoV;
	}
	
	public AddVampireCommand() {
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.addVampireC(x, y, this.tipoV);
			
		}catch(CommandExecuteException ex) {
			System.out.println(ex.getMessage());
			throw new CommandExecuteException("[ERROR]: Failed to add this Vampire", ex.getCause());
		}
		return true;
	}
	
	 public  Command parse(String[] commandWords) throws CommandParseException{
		 
		 char c, d;
		 if(commandWords[0].equals("v") || commandWords[0].equals("vampire")) {
			if(commandWords.length == 4) {
				
				c=commandWords[2].charAt(0);
				 d=commandWords[3].charAt(0);
				 commandWords[1].toLowerCase();
				 
				if(commandWords[1].equals(" ") || commandWords[1].equals("d") || commandWords[1].equals("e") || Character.isDigit(commandWords[1].charAt(0))) {
		
					this.tipoV= commandWords[1];
					if(Character.isDigit(c) && Character.isDigit(d) ) {
						
						 this.x= Integer.parseInt(commandWords[2]);
						 this.y= Integer.parseInt(commandWords[3]);
						 AddVampireCommand add= new AddVampireCommand(this.x, this.y, this.tipoV);
						 return add;

					 }else throw new NumberFormatException("[ERROR]: [v]ampire [tipo] <x> <y>: add vampire in position x, y | <x> <y> must be a numbers");
					
				}else throw new CommandParseException("[ERROR]:  Unvalid type: [v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"} ");
				 
			}else if(super.parseNoParamsCommand(commandWords)== null) return null;
			
			else return null;
			
			 
		 }
		 else return null;
		
	 }
}