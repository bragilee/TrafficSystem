
public class IntersectionDriverSingleTick {
	final public static char CAR = 'C';
	final public static char EMPTY = 'E';
	//the number of times to run a simulation
	final public static int NUMBER_RUNS = 10000;
	//the operational profile of the intersection
	final public static float CAR_PROB = (float) 0.2;
	//define arrays to record state transit
//	private static int[] fullyOperated;
//	private static int[] southDegraded;
//	private static int[] northDegraded;
//	private static int[] bioDirectionalDegraded;
//	private static int[] failed;
	//final markov transit matrix
//	private static float [][] operationalMatrix;
	
	//constructors
//	public IntersectionDriver(){
//		fullyOperated = new int[5];
//		southDegraded = new int[5];
//		northDegraded = new int[5];
//		bioDirectionalDegraded = new int[5];
//		failed = new int[5];
//		operationalMatrix = new float[5][5];
//	}
	//method for generating markov matrix
//	public void generateMatrix(int numberTicks) {
//		System.out.println("===============The transition matrix==============");
//		System.out.println();
//		drive(numberTicks);
//		printMatrix();
//		System.out.println();
//	}
	//print matrix generated
//	public void printMatrix() {
//		// TODO Auto-generated method stub
//		sum(fullyOperated,0);
//		sum(southDegraded,1);
//		sum(northDegraded,2);
//		sum(bioDirectionalDegraded,3);
//		sum(failed,4);
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 5; j++) {
//				System.out.print(operationalMatrix[i][j] + "\t\t");
//			}
//			System.out.println();
//		}
//	}
	//help method of matrix generation
//	private void sum(int[] array,int index) {
//		// TODO Auto-generated method stub
//		float sum =0;
//		for (int i = 0; i < array.length; i++) {
//			sum += array[i];
//		}
//		for (int i = 0; i < array.length; i++) {
//			operationalMatrix[index][i] = array[i] / sum;
//		}
//	}
	//accept input and simulate the system
	public void driveTest(int numberTicks) {
		// TODO Auto-generated method stub
		//count failing 
		int fail = 0;
		System.out.println("====================Number of ticks: " + numberTicks + "====================\n");
		fail = drive(numberTicks);
		System.out.println("Failures = " + fail + " out of " + NUMBER_RUNS + " runs");
		System.out.println("Failure rate = " + fail/(double)NUMBER_RUNS);
		System.out.println();
//		System.out.println("The probability vector is: ");
//		printProbability(numberTicks,operationalMatrix);
		System.out.println();
	}
	//simulate the intersection system
	private int drive(int numberTicks) {
		// TODO Auto-generated method stub
//		String previousStatus = "";
		int failCount = 0;
		for (int run = 0; run < NUMBER_RUNS; run++) {
			//randomly create the input according to the operational profile
			char [] input = new char[numberTicks];
//			char [] inputMajorNorth = new char[numberTicks];
//			char [] inputMinor = new char[numberTicks];
			
			for (int i = 0; i < numberTicks; i++) {
				double nextRandom = Math.random();
				if (nextRandom < CAR_PROB) {
					input[i] = CAR;
				}
				else {
					input[i] = EMPTY;
				}
//				System.out.println(input[i]);
			}
			
			failCount += runTicks(input);
//			previousStatus = "";
		}
		return failCount;
	}

	public int runTicks(char [] input)
	{

		int failMajor = 0;
		int failMinor = 0;
		IntersectionParallel intersectionP = new IntersectionParallel();
		IntersectionOracleParallel oracleSingleTick = new IntersectionOracleParallel(intersectionP);
		IntersectionParallel intersectionP2 = new IntersectionParallel();
		IntersectionOracleParallel oracleSingleTick2 = new IntersectionOracleParallel(intersectionP);
//		Intersection intersectionP = new Intersection();
//		IntersectionOracle oracleSingleTick = new IntersectionOracle(intersectionP);
//		Intersection intersectionP2 = new Intersection();
//		IntersectionOracle oracleSingleTick2 = new IntersectionOracle(intersectionP);
		for (int tick = 0; tick < input.length; tick++) {
			intersectionP.tick(input[tick]);
			boolean verify = oracleSingleTick.verifyMajorPattern(input[tick]);
			if (!verify) {
				failMajor = 1;
				break;
			}

		}
		if (failMajor == 1) {
			for (int tick = 0; tick < input.length; tick++) {
				intersectionP2.tick(input[tick]);
//				System.out.println(input[tick]);
				boolean verify2 = oracleSingleTick2.verifyMinorPattern(input[tick]);
				if (!verify2) {
					failMinor = 1;
					break;
				}
			}	
		}
		
		if ((failMajor == 1) && (failMinor ==1 )) {
			return 1;
		}
		else {
			return 0;
		}
	}
		//iterate over the input, use the oracle to verify the correctness
		//of the intersection. 
//		for (int tick = 0; tick < input.length; tick++) {
//			intersection.tick(input[tick]);
//			String verify = oracle.verify(input[tick]);
//			if (!previousStatus.isEmpty()) {
//				if (previousStatus.equals("FullyOperated")) {
//					fullyOperated[Transition(verify)]++;
//				}
//				else if (previousStatus.equals("SouthDegraded")) {
//					southDegraded[Transition(verify)]++;
//				}
//				else if (previousStatus.equals("NorthDegraded")) {
//					northDegraded[Transition(verify)]++;
//				}
//				else if (previousStatus.equals("BioDirectionalDegraded")) {
//					bioDirectionalDegraded[Transition(verify)]++;
//				}
//				else if(previousStatus.equals("Failed")) {
//					failed[Transition(verify)]++;
//				}
//			}
//			if (verify.equals("Failed")){ 
//				fails = 1;
//			}
//			previousStatus = verify;
//		}
//		return fails == 1 ? 1: 0;
//	}
	
	/*
	 * transit method helps to count frequency one state to another state
	 */
//	private int Transition(String verify) {
//		// TODO Auto-generated method stub
//		int index = 0;
//		switch(verify) {
//			case "FullyOperated": 	
//						index = 0;
//						break;
//			case "SouthDegraded":  
//						index = 1;
//						break;
//			case "NorthDegraded":  
//						index = 2;
//						break;
//			case "BioDirectionalDegraded":  
//						index = 3;
//						break;
//			case "Failed":  
//						index = 4;
//						break;
//			default:    System.out.println("Error!");
//						break;
//		}
//		return index;
//	}
	// print final probability of each state
//	private void printProbability(int numberTicks, float[][] matrix) {
//		// TODO Auto-generated method stub		
//		float[][] tick = new float[5][5];
//		tick[0][0] = (float) 1.0;
//		for (int i = 1; i < 5; i++) {
//			tick[0][i] = (float) 0.0;
//		}
//		for (int i = 1; i < 5; i++) {
//			for (int j = 0; j < 5; j++) {
//				tick[i][j] = (float) 0.0;
//			}
//		}
//		float[][] transition = new float[5][5];
//		for (int i = 0; i < 5; i++) {
//		    for (int j = 0; j < 5; j++) {
//		    	transition[i][j] = matrix[i][j];
//		    }
//		}
//		for (int i = 0; i < numberTicks-1; i++) {
//			tick = multiply(tick,transition);
//		}
//		for (int i = 0; i < 5; i++) {
//			System.out.print(tick[0][i] + "\t");
//		}
//	}
	//help method of multiplying two matrix
//	public static float[][] multiply(float[][] A, float[][] B) {
//        int mA = A.length;
//        int nA = A[0].length;
//        int mB = B.length;
//        int nB = B[0].length;
//        if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
//        float[][] C = new float[mA][nB];
//        for (int i = 0; i < mA; i++)
//            for (int j = 0; j < nB; j++)
//                for (int k = 0; k < nA; k++)
//                    C[i][j] += A[i][k] * B[k][j];
//        return C;
//    }

	/*
	 * main 
	 */
	public static void main(String[] args) {
		IntersectionDriverSingleTick intersectionDriverSingleTick = new IntersectionDriverSingleTick();
//		intersectionDriver.generateMatrix(10000);
		intersectionDriverSingleTick.driveTest(100);
//		intersectionDriver.driveTest(100);
//		intersectionDriver.driveTest(1000);
	}
	
}
