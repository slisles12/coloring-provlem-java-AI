import java.util.*;
import java.io.*;

public class ConnectionMap {
	public int[][] connections;
	public int numColors;
	public ArrayList<ArrayList<Integer>> domains;
	
	public ConnectionMap(final ConnectionMap map) {
		connections = map.connections.clone();
		domains = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < map.domains.size(); i++) {
			domains.add((ArrayList<Integer>) map.domains.get(i).clone());
		}
	}
	
	public ConnectionMap(final int size, int colors, final String filename) {
		connections = new int[size][];
		for (int i = 0; i < size; i++) {
			connections[i] = new int[size];
		}
		
		try {
			FileInputStream fin = new FileInputStream(filename);
			Scanner scn = new Scanner(fin);
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (scn.hasNextInt()) {
						connections[i][j] = scn.nextInt();
					}
				}
			}
			scn.close();
			
		} catch (Exception e) {
			System.out.println("Error; object creation failed.");
		}
		
		domains = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < size; i++) {
			ArrayList<Integer> nextVar = new ArrayList<Integer>();
			for (int j = 0; j < colors; j++) {
				nextVar.add(j);
			}
			domains.add(nextVar);
		}
	}
	
	@Override
	public String toString() {
		/*String result = "Connections: \n";
		for (int i = 0; i < connections.length; i++) {
			for (int j = 0; j < connections[i].length; j++) {
				result += connections[i][j] + " ";
			}
			result += "\n";
		}
		result += "\n";*/
		
		String result = "Variable\tPossible Values\n";
		for (int i = 0; i < domains.size(); i++) {
			result += i + "\t\t";
			result += domains.get(i);
			result += "\n";
		}
		return result;
	}
}
