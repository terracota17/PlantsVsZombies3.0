package logic.GameObjects;

import logic.Game;

public class BloodBank extends GameObject  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int coste;
	
	public BloodBank(int x, int y, Game game, int coste) {
		super(x, y, game);
		this.coste=coste;
		
	}
	
	public String objectString() {
		return "B["+this.coste+"]";
	}
	
	public int getCosteBankBlood() {
		return this.coste;
	}
	
	
	public int winner() {
		return -1;
	}
	
	public int receiveCoins() {
		
		return (int)(this.coste*0.1);
	}
	
	public boolean receiveDraculaAttack() {
		boolean attack=false;
		this.coste-=this.coste;
		return attack;
	}
	
	public boolean receiveVampireAttack(int damage) {
		boolean attack=false;
		this.coste-=this.coste;
		return attack;
	}

 	
	public void avanzar() {};
	
	public void attack() {}; 
	
	public boolean isAlive() {
		
		boolean BankBloodisAlive=false;
		if(this.coste > 0) BankBloodisAlive=true;
		return BankBloodisAlive;
	}

	public String serialize() {
		return "B;"+this.getX()+";"+this.getY()+";"+this.getVida()+this.coste+"\n";
	}
	
}
