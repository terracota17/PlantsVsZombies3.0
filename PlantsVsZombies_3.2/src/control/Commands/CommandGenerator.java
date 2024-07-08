package control.Commands;

import exceptions.*;
import control.*;

public class CommandGenerator {

	public static String helpMsg;
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new AddBloodBankCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),
			new SuperCoinsCommand(),
			new AddVampireCommand(),
			new SerializeCommand()
			};

	public static Command parse(String[] commandWords) throws CommandParseException {
		int i=0;
		boolean encontrado=false;
		
		while(i<availableCommands.length && !encontrado){
			
			if(availableCommands[i].parse(commandWords) != null){
				encontrado=true;
			}
			i++;
		}
		i--;
		
		if(encontrado){
			
			return availableCommands[i].parse(commandWords);
			
		}else throw new CommandParseException("[ERROR]: "+ Controller.unknownCommandMsg);
		
	}
	
	public static String commandHelp() {
		 helpMsg="Available commands:\n";

		for(int i=0; i< availableCommands.length; i++)
		{
			 helpMsg= helpMsg + availableCommands[i].helpText();
			 
		}
		System.out.println("\n");
		return helpMsg;
	}
}