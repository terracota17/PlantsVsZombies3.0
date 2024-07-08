package logic.GameObjects;

import logic.Game;

public class Dracula extends Vampire{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean draculaAlive=false;

//CONSTRUCTORES
		public Dracula(int x, int y, Game game) {
			super(x, y, game);
			draculaAlive=true;
			System.out.println("Dracula is Alive");
		}
//METODOS
		public String objectString() {
			return "D["+this.vida+"]";
		}
		
		public String serialize() {
			return "D;"+this.getX()+";"+this.getY()+";"+this.getVida()+";"+this.ciclo+"\n";
		}
		
		public boolean isAlive() {
			boolean alive=false;
			if(vida>0) 
			{
				alive=true;
			}
			else
			{
				draculaAlive=false;
			}
				
			return alive;
		}
		
		public void attack() {
			if (isAlive ()) {
				IAttack other = game.getAttackableInPosition(this.getX()-1, this.getY());
					if (other != null ) {
						other.receiveDraculaAttack();
					}
			}
		}

		public boolean receiveLightFlash() {
			boolean attack=false;
			
			return attack;
		}
		
}


