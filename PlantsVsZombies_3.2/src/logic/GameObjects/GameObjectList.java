package logic.GameObjects;

import java.util.ArrayList;
import logic.Game;

public class GameObjectList {
	
//ATRIBUTOS	
	
	private ArrayList<GameObject> gameobjects;
	
	public GameObjectList() {
		this.gameobjects = new ArrayList<GameObject>();
	}
	
//METODOS
	
	public void addObjectBoard(GameObject e) {
		gameobjects.add(e);
	}
	

	public void addBloodBank(int x, int y, Game game, int z) {
		gameobjects.add(new BloodBank(x, y, game, z));
	}
	
	public void addExplosiveVampire(int x, int y, Game game) {
		gameobjects.add(new ExplosiveVampire(x, y, game));
	}
	
	public void garlicPush() {
		for(int i=0; i<gameobjects.size(); i++) {
			if(this.isCellEmpty(gameobjects.get(i).getX() + 1, gameobjects.get(i).getY())) {
				gameobjects.get(i).receiveGarlicPush();
			}
		}
	}
	
	public void lightFlash() {
		for(int i=0; i<gameobjects.size(); i++) {
			gameobjects.get(i).receiveLightFlash();
		}
		
		this.removeDeadObjects();
	}
	
	public boolean isCellEmpty(int x, int y) {
		int i=0; 
		boolean empty=true;
		
		while(i<gameobjects.size() && empty) {
			if(gameobjects.get(i)!=null && gameobjects.get(i).getX()==x && gameobjects.get(i).getY() == y) {
				empty=false;
			}
			i++;
		}
		
		return empty;
	}
	
	public String drawObject(int x, int y) {
		int i=0; 
		boolean encontrado= false;
		
		if(!this.isCellEmpty(x, y))
		{
			while(i<gameobjects.size() && !encontrado) {
				if(gameobjects.get(i)!=null && gameobjects.get(i).getX()==x && gameobjects.get(i).getY() == y)
				{
					encontrado=true;
				}
				i++;
			}
			i--;
			return gameobjects.get(i).objectString();
		}
		else
		{
			return "";
		}
	}
	
	public void movimientoObjetos() {
		for(int i=0; i< gameobjects.size(); i++) {
			if(this.isCellEmpty(gameobjects.get(i).getX() - 1, gameobjects.get(i).getY()) && gameobjects.get(i).getCiclo()%2 == 0)
			gameobjects.get(i).avanzar();
		}
	}
	
	public int checkEnd() {
		int end=-1;
		int i=0;
		
		while(i<gameobjects.size() && end==-1) {
			end = gameobjects.get(i).winner();
			i++;
		}
		
		return end;
	}
	
	public String serialize() {
		String objectSerialized="";
		for(int i=0; i<gameobjects.size();i++)
			objectSerialized += gameobjects.get(i).serialize();
		return objectSerialized;
	}
	
	
	public void objectAttack() {
		for(int i=0; i < gameobjects.size(); i++) {
			gameobjects.get(i).attack();
		}
	}
	
	public void removeDeadObjects() {
		for(int i=0;i<gameobjects.size();i++) {
			if(!gameobjects.get(i).isAlive()) {	
				gameobjects.remove(i);
				Vampire.vampiresOnBoard--;
			}
		}
	}
	
	public IAttack attackable(int x, int y) {
		IAttack other=null;
		int i=0;
		boolean encontrado=false;
		
		while(i< gameobjects.size() && !encontrado) {
			if(x == gameobjects.get(i).getX() && y == gameobjects.get(i).getY()) {
				other = gameobjects.get(i);
				encontrado=true;
			}
			i++;
		}
		
		return other;
	}
	
	public int monedasBank() {
		
		int coins=0;
		for(int i=0; i< gameobjects.size(); i++) {
			coins += gameobjects.get(i).receiveCoins();
		}
		return coins;
	}
	

	
	public void cicloObjetos() {
		for(int i=0; i<gameobjects.size(); i++) {
			gameobjects.get(i).actualizarCicloObjeto();
		}
	}

}