
import java.util.Arrays;

/**
* Author: Ilnaz Daghighian
* 
* TestDriver
* public class TestDriver
* The TestDriver class tests all of the functions of the ReplacementAlgorithms class
*/
public class TestDriver {

	public static void main(String[] args) {
		
		ReplacementAlgorithms r = new ReplacementAlgorithms(0);
		
		int[] randomRefStr = r.getRandomReferenceStr();
		int[] referenceStr1 = {0,7,0,1,2,0,8,9,0,3,0,4,5,6,7,0,8,9,1,2};
		int[] referenceStr2 = {7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1}; 
			
		System.out.println("***************** SET 1: RANDOM REFERENCE STRING *********************\n");	
		for(int i = 1; i < 8; i++) {
			ReplacementAlgorithms re = new ReplacementAlgorithms(i);
			System.out.println("For " + i + " page frames, and using page random reference string\n" 
								+ Arrays.toString(randomRefStr) + ":");
			System.out.println("FIFO had " + re.FIFO(randomRefStr) + " page faults" );
			System.out.println("LRU had " + re.LRU(randomRefStr) + " page faults" );
			System.out.println("Optimal had " + re.optimal(randomRefStr) + " page faults\n" );
		}
		
		System.out.println("***************** SET 2: GIVEN REFERENCE STRING 1 *********************\n");
		for(int i = 1; i < 8; i++) {
			ReplacementAlgorithms re = new ReplacementAlgorithms(i);
			System.out.println("For " + i + " page frames, and using page reference string\n" 
								+ Arrays.toString(referenceStr1) + ":");
			System.out.println("FIFO had " + re.FIFO(referenceStr1) + " page faults" );
			System.out.println("LRU had " + re.LRU(referenceStr1) + " page faults" );
			System.out.println("Optimal had " + re.optimal(referenceStr1) + " page faults\n" );
		}
		
		System.out.println("***************** SET 3: GIVEN REFERENCE STRING 2 *********************\n");
		for(int i = 1; i < 8; i++) {
			ReplacementAlgorithms re = new ReplacementAlgorithms(i);
			System.out.println("For " + i + " page frames, and using page reference string\n" 
								+ Arrays.toString(referenceStr2) + ":");
			System.out.println("FIFO had " + re.FIFO(referenceStr2) + " page faults" );
			System.out.println("LRU had " + re.LRU(referenceStr2) + " page faults" );
			System.out.println("Optimal had " + re.optimal(referenceStr2) + " page faults\n" );
		}
		
	}//end main
}//end class
