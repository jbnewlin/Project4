public class Song {
	// TODO Task 2
	// define any fields needed
	// to represent a song
	// and the getters, setters and constructors
	
	private int name, n, l, maxPos, minPos;
	
	public Song(int name, int n, int l) {
		this.name = name;
		this.n = n;
		this.l = l;
	}
	
	public void changeN(int change) {
		this.n += change;
		if (this.n < 0) 
			this.n = 0;
	}
	
	public void changeL(int change) {
		this.l += change;
		if (this.l < 0) 
			this.l = 0;
	}
	
	public void changeMinPos(int minPos) {
		this.minPos = minPos;
	}
	
	public void changeMaxPos(int maxPos) {
		this.maxPos = maxPos;
	}
	
	public int getMaxPos() {
		return maxPos;
	}
	
	public int getMinPos() {
		return minPos;
	}
	
	public int getName() {
		return name;
	}
	
	public int getN() {
		return n;
	}
	
	public int getL() {
		return l;
	}
	
	public int getP() {
		return n + 2*l;
	}
	
}
