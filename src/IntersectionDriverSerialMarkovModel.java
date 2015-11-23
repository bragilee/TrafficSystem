/*
 * This class calls intersection class and oracle class to generate the random testing outcomes 
 * of both serial and parallel system.
 */


public class IntersectionDriverSerialMarkovModel {
	final public static char CAR = 'C';
	final public static char EMPTY = 'E';
	//the number of times to run a simulation
	final public static int NUMBER_RUNS = 10000;
	//the operational profile of the intersection
	final public static float CAR_PROB = (float) 0.2;
	private static int[] fullyOperated;
	private static int[] failed;
	private static float [][] operationalMatrix;

	
	public IntersectionDriverSerialMarkovModel() {
		// TODO Auto-generated constructor stub
		fullyOperated = new int[2];
		failed = new int[2];
		operationalMatrix = new float[2][2];
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
		sum(failed,1);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(String.format("%.4f", operationalMatrix[i][j]) + "\t\t");
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
		int fail = 0;
		System.out.println("====================Number of ticks: " + numberTicks + "====================\n");
		fail = drive(numberTicks);
		System.out.println("Failures = " + fail + " out of " + NUMBER_RUNS + " runs");
		System.out.println("Failure rate = " + fail/(double)NUMBER_RUNS);
		System.out.println();
		System.out.println("The probability vector is: ");
		printProbability(numberTicks,operationalMatrix);
		System.out.println();
		System.out.println();
	}
	
	//simulate the intersection system
	private int drive(int numberTicks) {
		// TODO Auto-generated method stub
		int failCount = 0;
		String previousStatus = "";
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

	private int runTicks(char[] input,String previousStatus) {
		// TODO Auto-generated method stub
		int fail = 0;
		
		Intersection intersectionSerial = new Intersection();
		IntersectionOracleSerialMarkovModel oracleSerial = new IntersectionOracleSerialMarkovModel(intersectionSerial);
		
		for (int tick = 0; tick < input.length; tick++) {
			
			intersectionSerial.tick(input[tick]);
			String verify = oracleSerial.verify(input[tick]);
			
			if (!previousStatus.isEmpty()) {
				if (previousStatus.equals("FullyOperated")) {
					fullyOperated[Transition(verify)]++;
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
			case "Failed":
				index = 1;
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
				float[][] tick = new float[2][2];
				tick[0][0] = (float) 1.0;
				for (int i = 1; i < 2; i++) {
					tick[0][i] = (float) 0.0;
				}
				for (int i = 1; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						tick[i][j] = (float) 0.0;
					}
				}
				float[][] transition = new float[2][2];
				for (int i = 0; i < 2; i++) {
				    for (int j = 0; j < 2; j++) {
				    	transition[i][j] = matrix[i][j];
				    }
				}
				for (int i = 0; i < numberTicks-1; i++) {
					tick = multiply(tick,transition);
				}
				for (int i = 0; i < 2; i++) {
					System.out.print(tick[0][i] + "\t");
				}
			}
			
//			help method of multiplying two matrix
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
		IntersectionDriverSerialMarkovModel intersectionDriver = new IntersectionDriverSerialMarkovModel();
		intersectionDriver.generateMatrix(10000);
		intersectionDriver.driveTest(10);
		intersectionDriver.driveTest(100);
		intersectionDriver.driveTest(1000);
		intersectionDriver.driveTest(10000);
	}
	
}
