import java.io.*;
import java.util.ArrayList;

public class BacktrackSearch {
	
	/**
	 * DO NOT MODIFY THIS METHOD. It should copy the ConnectionMap argument and, on the copy, attempt to assign different values to the variable at position "index."
	 *   The method should then call itself recursively until it either produces a full solution or determines that no solution is possible for the map it was originally given.
	 *   Example: For a three-node problem, you might initially call backtrack(map, 0), which would attempt various values
	 *   for the 0th node; for each possible value, it would make several calls to backtrack(map, 1). Each of those would
	 *   make calls to backtrack(map, 2), finally returning either success or failure.
	 * @param map The original connection map
	 * @param index The variable to be assigned a value.
	 * @return A solved ConnectionMap, if a solution is possible from this starting point, or null otherwise.
	 */
	public static ConnectionMap backtrack(final ConnectionMap map, int index) {
		//if index is equal to the size of the map 
		if (index == getSize(map)) {
			//return success map
			return map;
		}
		//else if not success map
		else {
			//clone map
			ConnectionMap newMap = new ConnectionMap(map);
			//find number of connections at that index
			int numConnections = numConnections(newMap, index);
			
			//for every color in the domain
			for (int color : getDomain(newMap).get(index)) {
				newMap = new ConnectionMap(map);
				//checker for if we can go down the color choice
				int doesWork = 0;
				
				//for all connections
				for (int i = 0; i < getConnection(newMap)[index].length; i++) {
					//if we are at a connection
					if (getConnection(newMap)[index][i] == 1) {
						//if the domain of connections is greater than one or not the color we are on
						if ((getDomain(newMap).get(i).size() > 1) || (getDomain(newMap).get(i).get(0) != color)) {
							//increment doesWork
							doesWork++;
						}
						//else the color is not possible
						else {
							break;
						}
						
					}
				}
				
				//if the number of successful connections is equal to the number of connections we have
				if (doesWork == numConnections) {
					//"pick" this color
					pickEl(newMap, index, color);
					//backtrack
					newMap = backtrack(newMap, index+1);
					//if that maps recursion was successful
					if (newMap != null) {
						//return the map
						return newMap;
					}
				}

			}
		}
		
		//else no possible solution
		return null;
	
	}
	
	/**
	 * getter for getting rid of other els in domain other than target
	 * @param index is the index
	 * @param target is the el we want to keep
	 * @param map a connection map
	 * @return the connections array
	 */
	public static void pickEl(final ConnectionMap map, int index, int target) {
		//clear
		getDomain(map).get(index).clear();
		//add target
		getDomain(map).get(index).add(target);
	}

	/**
	 * getter for size
	 * @param map a connection map
	 * @return the size of domains
	 */
	public static int getSize(final ConnectionMap map) {
		return map.domains.size();
	}
	

	/**
	 * getter for domains
	 * @param map a connection map
	 * @return the domains ArrayList
	 */
	public static ArrayList<ArrayList<Integer>> getDomain(final ConnectionMap map){
		//return domains
		return map.domains;
	}
	
	/**
	 * getter for connections
	 * @param map a connection map
	 * @return the connections array
	 */
	public static int[][] getConnection(final ConnectionMap map){
		//return connections
		return map.connections;
	}
	

	/**
	 * @param map a connection map
	 * @param index The variable to be assigned a value.
	 * @return the number of connection
	 */
	public static int numConnections(final ConnectionMap map, int index) {
		//number of connections
		int numCon = 0;
		
		//count number of connections
		for (int i = 0; i < getConnection(map)[index].length; i++) {
			//if connection is there
			if (getConnection(map)[index][i] == 1) {
				//increment number of connections 
				numCon++;
			}
		}
		
		//return number of connections
		return numCon;
	}


}
