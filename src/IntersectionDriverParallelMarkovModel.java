
/*
 * This class provides functions to work collaboratively with oracle 
 * to implement markov model on the parallel system.
 * Corresponding transition matrix and probobality will be generated.
 */
public class IntersectionDriverParallelMarkovModel {
	final public static char CAR = 'C';
	final public static char EMPTY = 'E';
	//the number of times to run a simulation
	final public static int NUMBER_RUNS = 10000;
	//the operational profile of the intersection
	final public static float CAR_PROB = (float) 0.2;
	//define arrays to record state transit
	private static int[] fullyOperated;
	private static int[] northDegraded;
	private static int[] southDegraded;
	private static int[] minorDegraded;
	private static int[] northSouthDegreaded;
	private static int[] northMinorDegreded;
	private static int[] southMinorDegraded;
	private static int[] northSouthMinorDegraded;
	private static int[] failed;

	//final markov transit matrix
	private static float [][] operationalMatrix;
	
	//constructors
	public IntersectionDriverParallelMarkovModel() {
		fullyOperated = new int[9];
		southDegraded = new int[9];
		northDegraded = new int[9];
		minorDegraded = new int[9];
		northSouthDegreaded = new int[9];
		northMinorDegreded = new int[9];
		southMinorDegraded = new int[9];
		northSouthMinorDegraded = new int[9];
		failed = new int[9];
		operationalMatrix = new float[9][9];
	}
	
//	method for generating markov matrix
	public void generateMatrix(int numberTicks) {
		System.out.println("===============The transition matrix==============");
		System.out.println();
		drive(numberTicks);
		printMatrix();
		System.out.println();
	}
	
	//print matrix generated
	public void printMatrix() {
		// TODO Auto-generated method stub
		sum(fullyOperated,0);
		sum(northDegraded,1);
		sum(southDegraded,2);
		sum(minorDegraded,3);
		sum(northSouthDegreaded,4);
		sum(northMinorDegreded,5);
		sum(southMinorDegraded,6);
		sum(northSouthMinorDegraded,7);
		sum(failed,8);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(String.format("%.4f", operationalMatrix[i][j]) + "\t");
//				System.out.print(operationalMatrix[i][j] +"\t\t");

			}
			System.out.println();
		}
	}
	
	//help method of matrix generation
	private void sum(int[] array,int index) {
		// TODO Auto-generated method stub
		float sum =0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		for (int i = 0; i < array.length; i++) {
			operationalMatrix[index][i] = array[i] / sum;
		}
	}
	
	//accept input and simulate the system
		public void driveTest(int numberTicks) {
			// TODO Auto-generated method stub
			//count failing 
//			for (int i = 0; i < 100; i++) {
//				int fail = 0;
//				fail = drive(numberTicks);
//				double failure = fail/(double)NUMBER_RUNS;
//				System.out.println(failure);
//			}
			int fail = 0;
			System.out.println("====================Number of ticks: " + numberTicks + "====================\n");
			fail = drive(numberTicks);
			System.out.println("Failures = " + fail + " out of " + NUMBER_RUNS + " runs");
			System.out.println("Failure rate = " + fail/(double)NUMBER_RUNS);
			System.out.println();
			System.out.println("The probability vector is: ");
			printProbability(numberTicks,operationalMatrix);
			System.out.println();
		}
		
		//simulate the intersection system
		private int drive(int numberTicks) {
			// TODO Auto-generated method stub
			String previousStatus = "";
			int failCount = 0;
			for (int run = 0; run < NUMBER_RUNS; run++) {
				//randomly create the input according to the operational profile
				char [] input = new char[numberTicks];
				for (int i = 0; i < numberTicks; i++) {
					double nextRandom = Math.random();
					if (nextRandom < CAR_PROB) {
						input[i] = CAR;
					}
					else {
						input[i] = EMPTY;
					}
				}
				failCount += runTicks(input,previousStatus);
				previousStatus = "";
			}
			return failCount;
		}
		
		public int runTicks(char [] input, String previousStatus)
		{
			int fail = 0;
			IntersectionParallel intersectionParallel = new IntersectionParallel();
			IntersectionOracleParallelMarkovModel oracleMarkovModel = new IntersectionOracleParallelMarkovModel(intersectionParallel);
			
//			iterate over the input, use the oracle to verify the correctness
//			of the intersection. 
			for (int tick = 0; tick < input.length; tick++) {
				intersectionParallel.tick(input[tick]);
				String verify = oracleMarkovModel.verify(input[tick]);
				
				
				if (!previousStatus.isEmpty()) {
					if (previousStatus.equals("FullyOperated")) {
						fullyOperated[Transition(verify)]++;
					}
					else if (previousStatus.equals("NorthDegraded")) {
						southDegraded[Transition(verify)]++;
					}
					else if (previousStatus.equals("SouthDegraded")) {
						northDegraded[Transition(verify)]++;
					}
					else if (previousStatus.equals("MinorDegraded")) {
						minorDegraded[Transition(verify)]++;
					}
					else if (previousStatus.equals("NorthSouthDegraded")) {
						northSouthDegreaded[Transition(verify)]++;
					}
					else if (previousStatus.equals("NorthMinorDegraded")) {
						northMinorDegreded[Transition(verify)]++;
					}
					else if (previousStatus.equals("SouthMinorDegraded")) {
						southMinorDegraded[Transition(verify)]++;
					}
					else if (previousStatus.equals("NorthSouthMinorDegraded")) {
						northSouthMinorDegraded[Transition(verify)]++;
					}
					else if(previousStatus.equals("Failed")) {
						failed[Transition(verify)]++;
					}
				}
				
				if (verify.equals("Failed")){ 
					fail = 1;
				}
				previousStatus = verify;
			}
			return fail == 1 ? 1: 0;
		}
		
		/*
		 * transit method helps to count frequency one state to another state
		 */
		private int Transition(String verify) {
			// TODO Auto-generated method stub
			int index = 0;
			switch(verify) {
				case "FullyOperated": 	
					index = 0;
					break;
				case "NorthDegraded":  
					index = 1;
					break;
				case "SouthDegraded":  
					index = 2;
					break;
				case "MinorDegraded":  
					index = 3;
					break;
				case "NorthSouthDegraded":  
					index = 4;
					break;
				case "NorthMinorDegraded":
					index = 5;
					break;
				case "SouthMinorDegraded":
					index = 6;
					break;
				case "NorthSouthMinorDegraded":
					index = 7;
					break;
				case "Failed":
					index = 8;
					break;
				default:    
					System.out.println("Error!");
					break;
			}
			return index;
		}
		
		// print final probability of each state
		private void printProbability(int numberTicks, float[][] matrix) {
			// TODO Auto-generated method stub		
			float[][] tick = new float[9][9];
			tick[0][0] = (float) 1.0;
			for (int i = 1; i < 9; i++) {
				tick[0][i] = (float) 0.0;
			}
			for (int i = 1; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					tick[i][j] = (float) 0.0;
				}
			}
			float[][] transition = new float[9][9];
			for (int i = 0; i < 9; i++) {
			    for (int j = 0; j < 9; j++) {
			    	transition[i][j] = matrix[i][j];
			    }
			}
			for (int i = 0; i < numberTicks-1; i++) {
				tick = multiply(tick,transition);
			}
			for (int i = 0; i < 9; i++) {
				System.out.print(tick[0][i] + "\t");
			}
		}
		
//		help method of multiplying two matrix
		public static float[][] multiply(float[][] A, float[][] B) {
	        int mA = A.length;
	        int nA = A[0].length;
	        int mB = B.length;
	        int nB = B[0].length;
	        if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
	        float[][] C = new float[mA][nB];
	        for (int i = 0; i < mA; i++)
	            for (int j = 0; j < nB; j++)
	                for (int k = 0; k < nA; k++)
	                    C[i][j] += A[i][k] * B[k][j];
	        return C;
	    }

		/*
		 * main 
		 */
		public static void main(String[] args) {
			IntersectionDriverParallelMarkovModel intersectionDriver = new IntersectionDriverParallelMarkovModel();
			intersectionDriver.generateMatrix(10000);
			intersectionDriver.driveTest(10);
			intersectionDriver.driveTest(100);
			intersectionDriver.driveTest(1000);
			intersectionDriver.driveTest(10000);
		}
}
