package logic.GameObjects;

import logic.Game;

public class ExplosiveVampire extends Vampire {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExplosiveVampire(int x, int y, Game game) {
		super(x, y, game);
		
	}
	
	public String serialize() {
		return "EV;"+this.getX()+";"+this.getY()+";"+this.getVida()+";"+this.ciclo+"\n";
	}
	
	public String objectString() {
		return "EV["+this.vida+"]";
	}
	
	public void explosion() {
		for(int i=-1; i<2 ; i++) {
			for(int j= -1 ; j<2; j++) {
				
				if(i!=0 || j!=0) {
					IAttack other = game.getAttackableInPosition(this.getX() + i, this.getY() + j);
					if (other != null ) {
						other.receiveExplosiveVampireAttack(harm);
				
					}
				}
			}
		}
	}

	public void attack() {
		if (isAlive ()) {
			if(this.vida>1) {
				IAttack other = game.getAttackableInPosition(this.getX()-1, this.getY());
				if (other != null ) {
					other.receiveVampireAttack(harm);
				}
			}
			else {
				this.explosion();
			}
			
		}
	}
}