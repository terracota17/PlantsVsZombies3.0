package logic.GameObjects;

public class GameObjectBoard {

//ATRIBUTOS
	
	private GameObjectList list;
	
// CONSTRUCTORES
	public GameObjectBoard() {
		this.list = new GameObjectList();
	}
	
// METODOS
	
	public void addObject (GameObject o) {
		list.addObjectBoard(o);
	}
	public boolean isCellEmpty(int x, int y) {
		return list.isCellEmpty(x, y);
	}
	
	public int checkEnd() {
		return list.checkEnd();
	}
	
	public String drawObject(int x, int y) {
		return list.drawObject(x, y);
	}
	public String serialize() {
		return list.serialize();
	}
	public void addObjectBoard(GameObject object){
		list.addObjectBoard(object);
	}
	
	public void removeDeadObjects() {
		list.removeDeadObjects();
	}
	
	public void objectAttack(){
		list.objectAttack();
	}
	
	public int monedasBank() {
		return list.monedasBank();
	}
	
	
	public void cicloObjetos() {
		list.cicloObjetos();
	}
	
	public void movimientoObjetos() {
		list.movimientoObjetos();
	}

	
	public IAttack attackable(int x, int y) {
		return list.attackable(x, y);
	}
	public void garlicPush() {
		list.garlicPush();
		
	}
	public void lightFlash() {
		list.lightFlash();
	}
}

