package logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import view.GamePrinter;
import logic.Player;
import view.IPrintable;
import logic.GameObjects.*;
import exceptions.*;
import control.*;

public class Game implements IPrintable, Serializable {

//ATRIBUTOS
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameObjectBoard board;
	private Level level;
	private GamePrinter printer;
	private Player player;
	private Random random;
	private int cicle;
	private long seed;
	private int numberOfCoinsPlayer=50;
	IAttack attack;

	public Game(long seed, Level level) {
		initGame(seed, level);
	}
	
// METODOS
	public void initGame(long seed, Level level) {
		this.seed=seed;
		this.level=level;
		this.random = new Random(seed);
		this.board = new GameObjectBoard();
		this.printer = new GamePrinter(this, level.getDimX(), level.getDimY());
		this.player= new Player(numberOfCoinsPlayer);
		this.cicle=0;
	}
	
	public boolean outOfMatrix(int x, int y) {
		boolean out=false;
		if(x<0 || y<0 || x>= level.getDimX() || y>= level.getDimY()) out=true;
		return out;
	}
	
	public boolean cellEmpty(int x, int y) {
		return board.isCellEmpty(x, y);
	}
	
	public boolean isFinished() {
		boolean finish=false;
		
		if(board.checkEnd()!=-1) {
			finish = true;
		}
		
		return finish;
	}
	
	public String getWinnerMessage() {
		
		String  msg="";
		if(this.isFinished()) {
			if(board.checkEnd()==1) msg= "Vampires Win!";
			else if(board.checkEnd()==0) msg="Player Wins!";
			
		}
		return msg;
	}
	
	public 	String getPositionToString(int x, int y) {
		return board.drawObject(x, y);
	}
	
	public String toString() {
		return printer.toString();
	}
	
	public String getInfo() {
		
		String inf = String.format
				(
						"\n"+
						"Number of cycles:  " + this.cicle + "\n"
						+ "Coins:  " + player.getCoins() + "\n"
						+ "Remaining vampires:  " + Vampire.remainingVampires + "\n"
						+ "Vampires on board:  " + Vampire.vampiresOnBoard + "\n"
						
				);
		
		return inf;
	}
	
	public void addObject(GameObject object){
		board.addObjectBoard(object);	
	}
	
	public void addSlayerIn(int x, int y) throws CommandExecuteException {
		if(player.getCoins() >= 50) {
			if(this.cellEmpty(x, y)) {
				if(!this.outOfMatrix(x, y)) {
					this.addObject(new Slayer(x, y, this));
					player.setCoins(player.getCoins() - 50);
					
				}else throw new UnvalidPositionException("[ERROR]: Position("+x+","+y+") " + Controller.unvalidPositionMsg);
			}
			
		}
		else{
			throw new NotEnoughCoinsException("[ERROR]: Not enough coins");
		}
		
	}
	
	public void addVampireIn(int x, int y) throws CommandExecuteException {
		if(random.nextDouble() < level.getVampireFrequency()) {
			x=level.getDimX() - 1;
			y= random.nextInt(level.getDimY());
			
			if(board.isCellEmpty(x, y))
			{
				int aleatorio= random.nextInt(9);
				if(aleatorio < 3) this.addObject(new Vampire(x, y, this));
				else if(2<aleatorio && 6< aleatorio ) {
					if(!Dracula.draculaAlive) this.addObject(new Dracula(x, y, this));
				}
				else this.addObject(new ExplosiveVampire(x, y, this));
			}
		}
	}
	
	public void update() throws CommandExecuteException {
		float coins = (random.nextFloat()); 
		
	    board.removeDeadObjects();
		board.objectAttack();
		
		player.recibirMonedas(coins);
		player.setCoins(player.getCoins() + board.monedasBank());
		
		int x= level.getDimX();
		int y= random.nextInt(level.getDimY());
		
		board.cicloObjetos();
		board.movimientoObjetos();

		if(board.isCellEmpty(x, y)) 
			if(Vampire.remainingVampires > 0) addVampireIn(x, y);
		
	
		board.removeDeadObjects();
		this.cicle++;
	}
	
	public void reset() {
		this.initGame(seed, level);
	}
	
	public void iniciar() {
		Vampire.init(level.getNumberOfVampires());
	}
	
	public void addBloodBank(int x, int y, int z) throws CommandExecuteException {
		
		if(board.isCellEmpty(x, y)  && x != level.getDimX() - 1 && z>0  ){
			if(!this.outOfMatrix(x, y)) {
				if(player.getCoins() >= z && z > 0) {
					player.setCoins(player.getCoins()-z);
					this.addObject(new BloodBank(x, y, this, z));
				}
				else throw new NotEnoughCoinsException("[ERROR]: Defender cost is "+z+": Not enough coins");
		
			}else throw new UnvalidPositionException("[ERROR]: Position("+x+","+y+") " + Controller.unvalidPositionMsg);		
		}	
		
	}
	
	public IAttack getAttackableInPosition(int x, int y) {
		return board.attackable(x, y);
	}
	
	public int getDimLevel() {
		return level.getDimX();
	}
	
	public void garlicPushC() throws CommandExecuteException{
		if(player.getCoins()>=10) {
			board.garlicPush();
			player.setCoins(player.getCoins() - 10);
			
		}else throw new NotEnoughCoinsException("[ERROR]: Cost Garlic Push 10: Not enough Coins");
	}
	
	public void lightFlashC() throws CommandExecuteException {
		if(player.getCoins()>=50) {
			board.lightFlash();
			player.setCoins(player.getCoins() - 50);
		}
		else{
			throw new NotEnoughCoinsException("[ERROR]: Light Flash Cost: 50 Not enough Coins");
		}
	}
	
	public void superCoinsC() {
		player.setCoins(player.getCoins() + 1000);
	}
	
	public String serialize() {
		
		String serialize="";
		String inf = String.format
				(
						"\n"+
						"Buffy the Vampire Slayer v3.0"+ "\n"+
						"\n"
						+"Cycles:  " + this.cicle + "\n"
						+ "Coins:  " + player.getCoins() + "\n"
						+"Level: "+this.level +"\n"
						+ "Remaining vampires:  " + Vampire.remainingVampires + "\n"
						+ "Vampires on  board:  " + Vampire.vampiresOnBoard + "\n"
						+"\n"
						+"Game Object list:"+"\n"
						
				);
		serialize = board.serialize();
		System.out.println(inf+serialize);
		
		return inf + serialize;
		
	}
	
	public void save(String filename) throws IOException {
		
		String  ruta = String.format("C:/Users/34634/");
		
		BufferedWriter outChars = null;
		
		 try {
			 outChars = new BufferedWriter(new FileWriter(ruta+filename));
			 
			 String l; 
			 
			 while ((l = this.serialize())  != null){
				 outChars.write(l); 
				 outChars.newLine();
			 }
		 }finally {
			 System.out.println("Game successfully saved in file"+filename);
			 if (outChars != null) { outChars.close(); }
		 } 
			
	}
	
	public void addVampireC(int x, int y, String tipoV) throws CommandExecuteException{
		tipoV.toLowerCase();
		if(board.isCellEmpty(x, y)) {
			if(!this.outOfMatrix(x, y)) {
				
				if(Vampire.remainingVampires > 0) {
					
					if(tipoV.equals("v")) this.addObject(new Vampire(x, y, this));
					else if(tipoV.equals("e")) this.addObject(new ExplosiveVampire(x, y, this));
					else if(tipoV.equals("d") ) {
						
						if(!Dracula.draculaAlive) {
							this.addObject(new Dracula(x, y, this));
							
						}else throw new DraculaIsAliveException("[ERROR]: Dracula is on the board");
						
					}		
								
				}else throw new NoMoreVampiresException("[ERROR]: No more remaining vampires left ");
				
			}else throw new UnvalidPositionException("[ERROR]: Position("+x+","+ y+") " + Controller.unvalidPositionMsg);
		}	
	}	
			
}


