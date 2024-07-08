package logic.GameObjects;

import logic.Game;

public class Vampire extends GameObject {

	// ATRIBUTOS

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static int remainingVampires;
	public static int vampiresOnBoard;
	protected int ciclo;
	
	// CONSTRUCTORES
	
	public Vampire(int x, int y, Game game) {
		super(x, y, game);
		this.vida=5;
		this.ciclo=0;
		vampiresOnBoard++;
		remainingVampires--;
	}
	
	// METODOS
	
	public static void init(int i) {
		vampiresOnBoard = 0;
		remainingVampires=(i - vampiresOnBoard);
	}
	
	
	
	public void avanzar() {
		this.ciclo = 0;
		this.setX(this.getX() - 1); 
	}
	
	public boolean receiveSlayerAttack(int damage) {
		boolean attack=false;
		this.vida-=damage;
		return attack;
	}
	
	public void attack() {
		
		if (isAlive ()) {
			IAttack other = game.getAttackableInPosition(this.getX()-1, this.getY());
				if (other != null ) {
					other.receiveVampireAttack(harm);
				}
		}
	}

	
	public String objectString() {
		return "V["+vida+"]";
	}
	
	public int winner() {
		
		if(this.getX() == 0) return 1;
		else return -1;
	}
	
	
	public boolean receiveGarlicPush() {
		boolean attack=false;
		
		if(game.outOfMatrix(super.getX() + 1, super.getY())) this.vida -= this.vida;
		this.setX(this.getX() + 1);
		this.ciclo=0;
		return attack;
	}
	
	public boolean receiveLightFlash() {
		
		boolean attack=false;
		this.vida -= this.vida;
		return attack;
	}
	
	public boolean isAlive() {
		boolean alive=false;
		if(vida>0) 
			alive=true;
		return alive;
	}
	
	public int receiveCoins(){return 0;}
	
	public boolean receiveExplosiveVampireAttack(int damage) {
		boolean attack=false;
		this.vida-=damage;
		return attack;
	}
	
	public void actualizarCicloObjeto() {
		this.ciclo++;
	}
	
	public int getCiclo() {
		return this.ciclo;
	}
	
	public String serialize() {
		return "V;"+this.getX()+";"+this.getY()+";"+this.getVida()+";"+this.ciclo+"\n";
	}
}