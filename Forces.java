
public enum Forces {
	
	GRAVITY(1), VELY(-15), FREEFALL(0), VELX(-4);
	
	private int val;

	Forces(int force) {
		// TODO Auto-generated constructor stub
		
		this.val= force;
	}
	
	public static int getForce(int i){
		return Forces.values()[i].val;
	}
	

}
