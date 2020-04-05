
public class ColoringProblem {
	
	public static void main(String[] args) {
		
		ConnectionMap map = new ConnectionMap(8, 3, "data.txt");
		System.out.println(map);
		
		ConnectionMap result = BacktrackSearch.backtrack(map, 0);
		if (result != null) {
			System.out.println(result);
		}
		else {
			System.out.println("No solution");
		}
		
		
		
	}

}
