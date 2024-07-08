package logic;

public class Player {

	private int numberOfCoins;
	//CONSTRUCTOR
	
	public Player(int numberOfCoins) {
		this.numberOfCoins=numberOfCoins;
	}
	
	// METODOS
	public void setCoins(int i) {
		this.numberOfCoins=i;
	}
	
	public void recibirMonedas(float coins) {
		if(coins > 0.5) {
			numberOfCoins+=10;
			this.setCoins(numberOfCoins);
		}
	}
	
	public int getCoins() {
		return this.numberOfCoins;
	}
}