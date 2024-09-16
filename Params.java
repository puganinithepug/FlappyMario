
public enum Params {
	
	//height and width
	
	BW(530), BH(530), MW(81), MH(57), PW(64), PH(320);
	
	private int num; 

	Params(int num) {
		// TODO Auto-generated constructor stub
		this.num=num;
	} 
	
	public static int getBW() {
		return Params.values()[0].num;
	}
	
	public static int getBH() {
		return Params.values()[1].num;
	}
	
	public static int getMarioX() {
		return Params.values()[0].num/8;
	}
	
	public static int getMarioY() {
		return Params.values()[1].num/2;
	}
	
	public static int getMarioW() {
		return Params.values()[2].num;
	}
	
	public static int getMarioH() {
		return Params.values()[3].num;
	}
	
	public static int pipeX() {
		return BW.num;
	}
	
	public static int pipeY() {
		return 0;
	}
	
	public static int pipeW() {
		return PW.num;
	}
	
	public static int pipeH() {
		return PH.num;
	}
	
	

}
