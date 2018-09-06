import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
* Author: Ilnaz Daghighian
* 
* ReplacementAlgorithms
* public class ReplacementAlgorithms 
* The ReplacementAlgorithms class implements the FIFO, LRU, and Optimal page replacement algorithms. 
* It records the number of page faults incurred by each algorithm with each given page frame
* 1 through 7. It assumes that demand paging is used and uses a random page-reference string that is
* 10 entries long with page numbers ranging from 0 to 9. It also uses two given page-reference strings.   
*/
public class ReplacementAlgorithms {

	private int numOfPageFrames; 

	public ReplacementAlgorithms(int numOfPageFrames) {
		this.numOfPageFrames = numOfPageFrames;
		
	}
	
	public int FIFO(int[] referenceStr) {
		
		int numberOfPageFaults = 0; 
		//linkedList for page frames with values set to invalid page number -1
		LinkedList <Integer> pagesInMemory = new LinkedList<Integer>();
		for (int i = 0; i < numOfPageFrames; i++) {
			pagesInMemory.add(-1);
		}	
		
		//iterate through the given reference string
		for(int i = 0; i < referenceStr.length; i++) {
			//if page frames in memory does not contain the current reference string page number
			//remove the page in memory (at head/front of queue) then add current reference string page number
			//to the pages in memory (at the rear/end of queue) then increase page faults
			if(!pagesInMemory.contains(referenceStr[i])) {
				pagesInMemory.remove();
				pagesInMemory.add(referenceStr[i]);
				numberOfPageFaults++;			
			}
		}
		return numberOfPageFaults; 
	}
		
	public int LRU(int[] referenceStr) {
		
		int numberOfPageFaults = 0; 
		//linkedList for page frames with values set to invalid page number -1
		LinkedList <Integer> pagesInMemory = new LinkedList<Integer>();	
		for (int i = 0; i < numOfPageFrames; i++) {
			pagesInMemory.add(-1);
		}	
		
		//iterate through the given reference string
		for(int i = 0; i < referenceStr.length; i++) {
			
			//if page frames in memory does not contain the current reference string page number
			//increase page faults, then remove the page in memory (at head/front of queue) 
			//then add current reference string page number to the pages in memory (at the rear/end of queue)
			if(!pagesInMemory.contains(referenceStr[i])) {
				numberOfPageFaults++;
				pagesInMemory.remove();
				pagesInMemory.add(referenceStr[i]);
				
			}
			else {
				//iterate through the pages in memory and remove all current reference string page number from list
				//then add the current reference string page number to the head of the list
				Iterator<Integer> iterator = pagesInMemory.iterator();
				while (iterator.hasNext()) {
				    Integer integer = iterator.next();
				    if (integer == referenceStr[i]) {
				        iterator.remove();
				    }
				}
				pagesInMemory.add(referenceStr[i]);
			}
		}
		return numberOfPageFaults; 
	}
	
	public int optimal(int[] referenceStr) {
		
		int numberOfPageFaults = 0; 
		
		//linkedList for page frames with values set to invalid page number -1
		LinkedList<Integer> pagesInMemory = new LinkedList<Integer>();	
		for (int i = 0; i < numOfPageFrames; i++) {
			pagesInMemory.add(-1);
		}	
		
		//create a linked list of Integer from given int array
		LinkedList<Integer> referenceStrCopy = new LinkedList<Integer>();
		for(int i : referenceStr){
			referenceStrCopy.add(i);
		}
		
		//create a linked list of Integer to hold the index of the pages that remain 
		//to determine which will not be used for the longest time
		LinkedList<Integer> longestPageNotUsed = new LinkedList<Integer>();
	        
		//iterate through the given reference string
		for(int i = 0; i < referenceStr.length; i++) {
			
			referenceStrCopy.remove();//remove the head of the reference string copy
			
			//if page frames in memory does not contain the current reference string page number
			//increase page faults
			if(!pagesInMemory.contains(referenceStr[i])) {
				numberOfPageFaults++;
				
				//iterate through pages in memory list
				//if pages in memory has -1 values remove and replace with 
				//valid page number, else add all pages in memory to the longest page not used list
				Iterator<Integer> iterator = pagesInMemory.iterator();
				while (iterator.hasNext()) {
				    Integer integer = iterator.next();
				    if (integer == -1) {
				        iterator.remove();
				        pagesInMemory.add(referenceStr[i]);
				        break;
				    }
				    else {
				    	longestPageNotUsed.add(referenceStrCopy.indexOf(integer)); 			    	
				    }
				}//end while
				
				if(longestPageNotUsed != null && !longestPageNotUsed.isEmpty()) {
					Integer max = -1;
					//iterate through longest page not used list to find the page to remove
					Iterator<Integer> iterator1 = longestPageNotUsed.iterator();
					while(iterator1.hasNext()){
						Integer integer = iterator1.next();
						if (integer == -1) {
							int indexOfFrameToReplace = longestPageNotUsed.indexOf(integer);
							pagesInMemory.remove(indexOfFrameToReplace);
							pagesInMemory.add(indexOfFrameToReplace, referenceStr[i]);	
							max = -1;					
							break;
						}
						else {
							if(integer > max){
				    		max = integer;
							}
						}
					}//end while
					
					//replace frame and clear longest page not used list
					int indexOfFrameToReplace = longestPageNotUsed.indexOf(max);				
					pagesInMemory.remove(indexOfFrameToReplace);
					pagesInMemory.add(indexOfFrameToReplace, referenceStr[i]);
					longestPageNotUsed.clear();
				}
			}
		}
		return numberOfPageFaults; 
	}
	
	//method that generates a random page reference of 20 entries
	//where page numbers range from 0-9
	public int[] getRandomReferenceStr() {
		Random generator = new Random();
		int[] referenceStr = new int[20];
		
		for(int i = 0; i < referenceStr.length; i++) {
			referenceStr[i] = generator.nextInt(10);
		}
		return referenceStr;
	}

}//end class
