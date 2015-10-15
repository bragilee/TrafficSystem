
public class IntersectionDriverParallel {
	final public static char CAR = 'C';
	final public static char EMPTY = 'E';
	//the number of times to run a simulation
	final public static int NUMBER_RUNS = 10000;
	//the operational profile of the intersection
	final public static float CAR_PROB = (float) 0.2;
	
	public void driveTest(int numberTicks) {
		// TODO Auto-generated method stub
		//count failing 
		int fail = 0;
		System.out.println("====================Number of ticks: " + numberTicks + "====================\n");
		fail = drive(numberTicks);
		System.out.println("Failures = " + fail + " out of " + NUMBER_RUNS + " runs");
		System.out.println("Failure rate = " + fail/(double)NUMBER_RUNS);
	}
	
	private int drive(int numberTicks) {
		// TODO Auto-generated method stub
		int failCount = 0;
		for (int run = 0; run < NUMBER_RUNS; run++) {
			//randomly create the input according to the operational profile
			char [] inputMajor = new char[numberTicks];
			char [] inputMinor = new char[numberTicks];
			
			//Random ticks on the major road
			for (int i = 0; i < numberTicks; i++) {
				double nextRandom = Math.random();
				if (nextRandom < CAR_PROB) {
					inputMajor[i] = CAR;
				}
				else {
					inputMajor[i] = EMPTY;
				}
			}
			
			//Random ticks on the minor road
			for (int i = 0; i < numberTicks; i++) {
				double nextRandom = Math.random();
				if (nextRandom < CAR_PROB) {
					inputMinor[i] = CAR;
				}
				else {
					inputMinor[i] = EMPTY;
				}
			}	
			
			failCount += runTicks(inputMajor,inputMinor);
		}
		return failCount;
	}
	
	public int runTicks(char [] inputMajor, char [] inputMinor)
	{
		int fail = 0;
		IntersectionParallel intersectionParallel = new IntersectionParallel();
		IntersectionOracleParallel oracleParallel = new IntersectionOracleParallel(intersectionParallel);
		
		int index = 0;
		while (index < inputMajor.length) {
			int firstMajor = -1;
			int firstMinor = -1;
			
			//record the index of the first car at major road
			for (int i = 0; i < 9; i++) {
				if (inputMajor[i] == CAR) {
					firstMajor = i;
					break;
				}
			}
			
			//record the index of the first car at minor road
			for (int i = 0; i < 9; i++) {
				if (inputMinor[i] == CAR) {
					firstMinor = i;
					break;
				}
			}
			
			//decide which pattern to match currently
			if (firstMajor == -1) {
				if (firstMinor > -1) {
					fail = verifyTicksMinor(intersectionParallel,oracleParallel,inputMinor);
				}				
			}
			else {
				if (firstMinor == -1) {
					fail = verifyTicksMajor(intersectionParallel,oracleParallel,inputMajor);
				}
				else {
					if (firstMajor < firstMinor) {
						fail = verifyTicksMajor(intersectionParallel,oracleParallel,inputMajor);
					}
					else {
						fail = verifyTicksMinor(intersectionParallel,oracleParallel,inputMinor);
;					}
				}
			}
			
			if (fail > 0) {
				break;
			}
		
			index = index + 9;
		}
		
		return fail;
	}
	
	public int verifyTicksMajor(IntersectionParallel intersectionParallel,IntersectionOracleParallel oracleParallel, char [] inputMajor) {
		int failMajor = 0;
		for (int tick = 0; tick < inputMajor.length; tick++) {
			intersectionParallel.tick(inputMajor[tick]);
			boolean verifyMajorPattern = oracleParallel.verifyMajorPattern(inputMajor[tick]);
			if (!verifyMajorPattern) {
				failMajor = failMajor + 1;
				break;
			}
		}
		return failMajor;
		
	}
	
	public int verifyTicksMinor(IntersectionParallel intersectionParallel,IntersectionOracleParallel oracleParallel, char [] inputMinor) {
		int failMinor = 0;
		for (int tick = 0; tick < inputMinor.length; tick++) {
			intersectionParallel.tick(inputMinor[tick]);
			boolean verifyMinorPattern = oracleParallel.verifyMinorPattern(inputMinor[tick]);
			if (!verifyMinorPattern) {
				failMinor = failMinor + 1;
				break;
			}
		}
		return failMinor;
	}
	
	public static void main(String[] args) {
		IntersectionDriverParallel intersectionDriverParallel = new IntersectionDriverParallel();
//		intersectionDriver.generateMatrix(10000);
		intersectionDriverParallel.driveTest(20);
//		intersectionDriver.driveTest(100);
//		intersectionDriver.driveTest(1000);
	}
}
