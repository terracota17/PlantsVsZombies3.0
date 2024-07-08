package logic.GameObjects;

import logic.Game;

public class Slayer extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Slayer(int x, int y, Game game) {
		super(x, y, game);
		this.vida=3;
	}
	
// METODOS

	public String objectString() {
		return "S["+vida+"]";
	}
	
	public void avanzar() {}
	
	public int winner() {
		if(Vampire.remainingVampires== 0 && Vampire.vampiresOnBoard==0) {
			return 0;
		}
		else
		{
			return -1;
		}
	}
	
	public boolean receiveVampireAttack(int damage) {
		boolean attack=false;
		this.vida-=damage;
		return attack;
	}
	
	public boolean receiveDraculaAttack() {
		boolean attack=false;
		
		this.vida -= this.vida;
		
		return attack;
		
	}
	
	public void attack() {
		int i=this.getX() + 1;
		boolean encontrado=false;
		
		if (isAlive ()) {
			while (i<game.getDimLevel() && !encontrado) {
				IAttack other = game.getAttackableInPosition(i, this.getY());
					if (other != null ) {
						other.receiveSlayerAttack(harm);
						encontrado=true;
					}
				i++;
			}
		}
		
	}
	
		
	public int receiveCoins(){return 0;}

	@Override
	public String serialize() {
		return "S;"+this.getX()+";"+this.getY()+";"+this.getVida()+ "\n";
	}
	
}