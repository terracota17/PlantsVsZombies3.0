package control;

import java.io.IOException;
import java.util.Scanner;

import control.Commands.Command;
import control.Commands.CommandGenerator;
import logic.Game;
import exceptions.*;

public class Controller {
	
	public final String prompt = "Command > ";
	public static final String unknownCommandMsg ="Unknown command";
	public static final String unvalidPositionMsg="Unvalid Position";
	
    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void  printGame() {
   	 System.out.println(game);
   }
    
    public void run() {
	    	boolean refreshDisplay = true;
	    	game.iniciar();
	    	
	    while (!game.isFinished()){
	    		
	    	 if (refreshDisplay) printGame();
    		 refreshDisplay = false;
    		
			  System.out.println(prompt);	
			  String s = scanner.nextLine();
			  String[] parameters = s.toLowerCase().trim().split(" ");
			  System.out.println("[DEBUG] Executing: " + s);
			  
			  try {
				  Command command = CommandGenerator.parse(parameters);
				  refreshDisplay = command.execute(game);
			  }
			  catch (GameException | IOException ex) {
				  System.out.format(ex.getMessage() + " %n %n");
			  }	  
		}
        	if (refreshDisplay) printGame();
    		System.out.println ("[Game over] " + game.getWinnerMessage());

    }

}
