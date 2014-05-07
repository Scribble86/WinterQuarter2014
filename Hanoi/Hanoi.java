
public class Hanoi {

	public static void main(String[] args) {
		hanoi(10);
	}
	
	public static void hanoi (int n) {
	
		hanoi(n,'A', 'C', 'B');
	
	}
	
	private static void hanoi(int n, char source, char dest, char via) {
		
		if(n == 1) {
			System.out.println("Move top disc from " + source + " to " + dest + ".");
			return;
		}
		
		hanoi(n-1, source, via, dest);
		System.out.println("Move top disc from " + source + " to " + dest + ".");
		hanoi(n-1, via, dest, source);
		
	}
	
	
}
