package control.Commands;

import logic.Game;
import exceptions.*;
import exceptions.NumberFormatException;

public class AddBloodBankCommand extends Command {

	private static String name="bank";
	private static String shortcut="b";
	private static String details= "[b]ank <x> <y> <z>";
	private static String bloodbank= "add BloodBank in position x, y with cost z";
	private int x;
	private int y;
	private int z;
	
	public AddBloodBankCommand(int x, int y, int z) {
		super(name, shortcut, details, bloodbank);
		this.x=x;
		this.y=y;
		this.z=z;
	}

	public  AddBloodBankCommand (/*String name, String shortcut, String details, String help*/) {
		super(name, shortcut, details, bloodbank);
	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.addBloodBank(x, y, z);
		}catch(CommandExecuteException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException("[ERROR]: Failed to add bank", e.getCause());
		}
		
		return true;
	}
	
	 public  Command parse(String[] commandWords) throws CommandParseException {
		
		 char one, two,three;
		 
		 if(commandWords[0].equals("b") || commandWords[0].equals("bank")){
			 
			 if(commandWords.length == 4) {
				 
				 one=commandWords[1].charAt(0);
				 two=commandWords[2].charAt(0);
				 three=commandWords[3].charAt(0);
				 
				 if(Character.isDigit(one) && Character.isDigit(two) && Character.isDigit(three)) {
					 
					 this.x= Integer.parseInt(commandWords[1]);
					 this.y= Integer.parseInt(commandWords[2]);
					 this.z= Integer.parseInt(commandWords[3]);
					 
					 AddBloodBankCommand addBlood= new AddBloodBankCommand(this.x, this.y, this.z);
					 return addBlood;
					 
				 }else throw new NumberFormatException("[ERROR]: Unvalid number of arguments: expected [b]ank <x><y><z> must be Numbers");
				 
			 }else if(super.parseNoParamsCommand(commandWords) == null )  return null;
			  else return null;

		 }else return null;
	 }
}
		 
			
