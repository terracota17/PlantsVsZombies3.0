package logic.GameObjects;

import logic.Game;
import java.io.*;

public abstract class GameObject implements IAttack, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int x;
	private int y;
	protected int vida;
	protected static int harm = 1;
	protected Game game;
	
// CONSTRUCTORES 
	
	public GameObject(int x, int y, Game game) {
		this.x=x;
		this.y=y;
		this.game=game;
	}

// METODOS
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int i) {
		this.x=i;
	}
	
	public int getVida() {
		return this.vida;
	}
	
	public boolean isAlive() {
		
		boolean alive=true;
		if(this.vida<=0) alive=false;
		return alive;
	}
	
	public int getCiclo() {
		return -1;
	}
	
	public abstract String serialize();
	
	public void actualizarCicloObjeto() {};
	
	public abstract String objectString();
	
	public abstract void avanzar();
	
	public abstract int winner();
	
	public abstract int receiveCoins();

}

