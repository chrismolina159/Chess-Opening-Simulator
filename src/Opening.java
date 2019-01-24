import java.util.ArrayList;
import java.util.Scanner;

public class Opening {

	protected ArrayList<ArrayList<String> > openings = new ArrayList<ArrayList<String>>();
	protected ArrayList<String> queensGambAccept = new ArrayList<String>();
	protected ArrayList<String> ruyLopezMorClos = new ArrayList<String>();
	protected ArrayList<String> scotchGameSchMieVar = new ArrayList<String>();
	protected ArrayList<String> fkgScotchVarA = new ArrayList<String>();
	public Opening() {
		initiateRuyLopezMorClos();
		initiateScotchGameSMV();
		initateFkgScotchVarA();
		initiateQueensGambitAccepted();
		openings.add(ruyLopezMorClos);
		openings.add(queensGambAccept);
		openings.add(scotchGameSchMieVar);
		openings.add(fkgScotchVarA);
	}
	
	//will check openings and eliminate the ones that do not follow.
	//will remove openings that do not match current input and print out the next move for each possible opening.
	public boolean checkOpeningsW(String chessMove, int counter) {
		
		System.out.println("Possible openings and the very next move:");
		ArrayList<ArrayList<String>> retryList = new ArrayList<ArrayList<String>>();
		retryList.addAll(openings);

		for(int i = 0; i < openings.size();) {
			if(openings.get(i).get(2*counter - 1).equals(chessMove)) {
				System.out.println(openings.get(i).get(0) + ": " + openings.get(i).get(2*counter));
				i++;
				continue;
			}
			else {
				openings.remove(i);
			}
		}
		if(openings.size() == 0) {
			System.out.println("Not the correct move please retry.");
			openings.addAll(retryList);
			return true;
		}
		return false;
	}
	
	//Because different openings may start off similar but diverge at some point, users will be able
	//to choose how they wish for their opponent to respond if there are multiple openings to choose from
	//so that the user has a way of choosing which opening they wish to simulate
	//unlike the checkOpenings(String, int) method, this will not tell you the next move for the opening
	//(since you are practicing this), but will tell you openings still available
	public boolean oppRespondW(String chessMove, int counter) throws IndexOutOfBoundsException {
		int size = openings.size();
		ArrayList<ArrayList<String>> retryList = new ArrayList<ArrayList<String>>();
		retryList.addAll(openings);
		
		if(size == 1) {
			System.out.println("Simulating " + openings.get(0).get(0));
			openings.get(0).get(2*counter+1);
		}
		else{
			System.out.println("Possible openings still available: ");
			
			for(int i = 0; i < size;) {
				if(openings.get(i).get(2*counter).equals(chessMove)) {
					System.out.println(openings.get(i).get(0) );
					i++;
					continue;
				}
				else {
					openings.remove(i);
					size--;
				}
			}
		}
		if(openings.size() == 0) {
			System.out.println("Not the correct move please retry.");
			openings.addAll(retryList);
			return true;
		}
		
		return false;
	}
	
	//will check openings and eliminate the ones that do not follow.
	//will remove openings that do not match current input and print out the next move for each possible opening.
	//Current state will not allow one to retry after messing up.  
	public boolean checkOpeningsB(String chessMove, int counter) {
		System.out.println("Possible openings:");
		ArrayList<ArrayList<String>> retryList = new ArrayList<ArrayList<String>>();
		retryList.addAll(openings);
		
		for(int i = 0; i < openings.size();) {
			if(openings.get(i).get(2*counter).equals(chessMove)) {
				try {
					System.out.println(openings.get(i).get(0) + ": " + openings.get(i).get(2*counter+1));
				}catch(Exception e) {
					System.out.println("You have reached the end of simulating this opening.  Good job!");
				}
				i++;
				continue;
			}
			else {
				openings.remove(i);
			}
		}
		
		if(openings.size() == 0) {
			System.out.println("Not the correct move, please retry");
			openings.addAll(retryList);
			return true;
		}
		return false;
	}
		
	//Similar to oppRespondW, this method is used if the user is playing as black and reads and
	//processes the move inputed for the CPU
	public boolean oppRespondB(String chessMove, int counter) {
		int size = openings.size();
		ArrayList<ArrayList<String>> retryList = new ArrayList<ArrayList<String>>();
		retryList.addAll(openings);
		
		if(size == 1) {
			System.out.println("Simulating " + openings.get(0).get(0));
		}
		else {
			System.out.println("Possible openings still available: ");
			
			for(int i = 0; i < size;) {
				if(openings.get(i).get(2*counter-1).equals(chessMove)) {
					System.out.println(openings.get(i).get(0) );
					i++;
					continue;
				}
				else {
					openings.remove(i);
					size--;
				}
			}
		}
		
		if(size == 0) {
			System.out.println("Not the correct move, please retry");
			openings.addAll(retryList);
			return true;
		}
		return false;
	}
	
	private void initiateRuyLopezMorClos() {
		ruyLopezMorClos.add("Ruy Lopez: Morphy Defense, Closed Defense");
		ruyLopezMorClos.add("Pe4");
		ruyLopezMorClos.add("Pe5");
		ruyLopezMorClos.add("Nf3");
		ruyLopezMorClos.add("Nc6");
		ruyLopezMorClos.add("Bb5");
		ruyLopezMorClos.add("Pa6");
		ruyLopezMorClos.add("Ba4");
		ruyLopezMorClos.add("Nf6");
		ruyLopezMorClos.add("O-O");
		ruyLopezMorClos.add("Be7");
		ruyLopezMorClos.add("Re1");
		ruyLopezMorClos.add("Re1");
		ruyLopezMorClos.add("Pb5");
		ruyLopezMorClos.add("Bb3");
		ruyLopezMorClos.add("Pd6");
		ruyLopezMorClos.add("Pc3");
		ruyLopezMorClos.add("O-O");
	}
	
	private void initiateScotchGameSMV() {
		scotchGameSchMieVar.add("Scotch Game: Schmidt, Mieses Variation");
		scotchGameSchMieVar.add("Pe4");
		scotchGameSchMieVar.add("Pe5");
		scotchGameSchMieVar.add("Nf3");
		scotchGameSchMieVar.add("Nc6");
		scotchGameSchMieVar.add("Pd4");
		scotchGameSchMieVar.add("Pe5xd4");
		scotchGameSchMieVar.add("Nf3xd4");
		scotchGameSchMieVar.add("Nf6");
		scotchGameSchMieVar.add("Nd4xc6");
		scotchGameSchMieVar.add("Pb7xc6");
		scotchGameSchMieVar.add("Pe5");
		scotchGameSchMieVar.add("Qe7");
		
	}
	
	private void initateFkgScotchVarA() {
		fkgScotchVarA.add("Four Knights Game: Scotch Variation Accepted");
		fkgScotchVarA.add("Pe4");
		fkgScotchVarA.add("Pe5");
		fkgScotchVarA.add("Nf3");
		fkgScotchVarA.add("Nc6");
		fkgScotchVarA.add("Pd4");
		fkgScotchVarA.add("Pe5xd4");
		fkgScotchVarA.add("Nf3xd4");
		fkgScotchVarA.add("Nf6");
		fkgScotchVarA.add("Nc3");
		fkgScotchVarA.add("Bb4");
	}
	
	private void initiateQueensGambitAccepted() {
		queensGambAccept.add("Queen's Gambit Accepted");
		queensGambAccept.add("Pd4");
		queensGambAccept.add("Pd5");
		queensGambAccept.add("Pc4");
		queensGambAccept.add("Pd5xc4");
		//queensGambAccept.add("Nf3");
		
	}
}
