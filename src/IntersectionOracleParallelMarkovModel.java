/*
 * This is defines the oracle for the markov model in parallel system.
 * Specific pattern is identified to work collaboratively with drive tests
 * in verification.
 */

public class IntersectionOracleParallelMarkovModel {
	final public static char CAR = 'C';
	final public static char EMPTY = 'E';
	//initialize the intersection to test
	private IntersectionParallel intersectionParallel;
	/*
	 * the light pattern that the intersection should operate as followed,
	 * the first element is the major road light,
	 * the second element is the major turning road light,
	 * the third element is the minor road light.
	 */
	private LightColour [][] lightPatternMajor = 
		{	{LightColour.GREEN, LightColour.RED, LightColour.RED},
			{LightColour.AMBER, LightColour.RED, LightColour.RED},
			{LightColour.AMBER, LightColour.RED, LightColour.RED},
			{LightColour.RED, LightColour.GREEN, LightColour.RED},
			{LightColour.RED, LightColour.GREEN, LightColour.RED},
			{LightColour.RED, LightColour.GREEN, LightColour.RED},
			{LightColour.RED, LightColour.GREEN, LightColour.RED},
			{LightColour.RED, LightColour.AMBER, LightColour.RED},
			{LightColour.RED, LightColour.AMBER, LightColour.RED},
		};
	
	private LightColour [][] lightPatternMinor = 
		{	{LightColour.GREEN, LightColour.RED, LightColour.RED},
			{LightColour.AMBER, LightColour.RED, LightColour.RED},
			{LightColour.AMBER, LightColour.RED, LightColour.RED},
			{LightColour.RED, LightColour.RED, LightColour.GREEN},
			{LightColour.RED, LightColour.RED, LightColour.GREEN},
			{LightColour.RED, LightColour.RED, LightColour.GREEN},
			{LightColour.RED, LightColour.RED, LightColour.GREEN},
			{LightColour.RED, LightColour.RED, LightColour.AMBER},
			{LightColour.RED, LightColour.RED, LightColour.AMBER},
		};
	private int index = 0;
	//constructors
	public IntersectionOracleParallelMarkovModel(IntersectionParallel intersectionParallel) {
		// TODO Auto-generated constructor stub
		this.intersectionParallel = intersectionParallel;
	}

	/*
	 * check the expected light pattern is matched with actual light pattern
	 */
	public String verify(char input)
	{
		//if it has reached the end of the lights pattern, go back to the start
		if (index == lightPatternMajor.length) {
			index = 0; 
			}
		
		LightColour northLightLeftA = intersectionParallel.getNorthLightLeftA().getColour();
	    LightColour northLightLeftB = intersectionParallel.getNorthLightLeftB().getColour();

	    LightColour northLightStraightA = intersectionParallel.getNorthLightStraightA().getColour();
	    LightColour northLightStraightB = intersectionParallel.getNorthLightStraightB().getColour();

	    LightColour northLightRightA = intersectionParallel.getNorthLightRightA().getColour();
	    LightColour northLightRightB = intersectionParallel.getNorthLightRightB().getColour();

	    LightColour southLightLeftA = intersectionParallel.getSouthLightLeftA().getColour();
	    LightColour southLightLeftB = intersectionParallel.getSouthLightLeftB().getColour();

	    LightColour southLightStraightA = intersectionParallel.getSouthLightStraightA().getColour();
	    LightColour southLightStraightB = intersectionParallel.getSouthLightStraightB().getColour();

	    LightColour southLightRightA = intersectionParallel.getSouthLightRightA().getColour();
	    LightColour southLightRightB = intersectionParallel.getSouthLightRightB().getColour();

	    LightColour minorLightLeftA = intersectionParallel.getMinorRoadLightLeftA().getColour();
	    LightColour minorLightLeftB = intersectionParallel.getMinorRoadLightLeftB().getColour();

	    LightColour minorLightStraightA = intersectionParallel.getMinorRoadLightStraightA().getColour();
	    LightColour minorLightStraightB = intersectionParallel.getMinorRoadLightStraightB().getColour();

	    LightColour minorLightRightA = intersectionParallel.getMinorRoadLightRightA().getColour();
	    LightColour minorLightRightB = intersectionParallel.getMinorRoadLightRightB().getColour();
	    
	    boolean fullyOperated = (((northLightLeftA == lightPatternMajor[index][0]) && (northLightLeftB == lightPatternMajor[index][0]) && 
        		(northLightStraightB == lightPatternMajor[index][0]) && (northLightStraightB == lightPatternMajor[index][0]) &&
        		(northLightRightA == lightPatternMajor[index][1]) && (northLightRightB == lightPatternMajor[index][1]) && 
        		(southLightLeftA == lightPatternMajor[index][0]) && (southLightLeftB == lightPatternMajor[index][0]) &&
    			(southLightStraightA == lightPatternMajor[index][0]) && (southLightStraightB == lightPatternMajor[index][0]) &&
    			(southLightRightA == lightPatternMajor[index][1]) && (southLightRightB == lightPatternMajor[index][1]) &&
    			(minorLightLeftA == lightPatternMajor[index][2]) && (minorLightLeftB == lightPatternMajor[index][2]) &&
    			(minorLightStraightA == lightPatternMajor[index][2]) && (minorLightStraightB == lightPatternMajor[index][2]) &&
    			(minorLightRightA == lightPatternMajor[index][2]) && (minorLightRightB == lightPatternMajor[index][2])) || 
   				((northLightLeftA == lightPatternMinor[index][0]) && (northLightLeftB == lightPatternMinor[index][0]) && 
   		        (northLightStraightB == lightPatternMinor[index][0]) && (northLightStraightB == lightPatternMinor[index][0]) &&
   		    	(northLightRightA == lightPatternMinor[index][1]) && (northLightRightB == lightPatternMinor[index][1]) && 
   		        (southLightLeftA == lightPatternMinor[index][0]) && (southLightLeftB == lightPatternMajor[index][0]) &&
   		    	(southLightStraightA == lightPatternMinor[index][0]) && (southLightStraightB == lightPatternMajor[index][0]) &&
   		    	(southLightRightA == lightPatternMinor[index][1]) && (southLightRightB == lightPatternMajor[index][1]) &&
   		    	(minorLightLeftA == lightPatternMinor[index][2]) && (minorLightLeftB == lightPatternMinor[index][2]) &&
   		    	(minorLightStraightA == lightPatternMinor[index][2]) && (minorLightStraightB == lightPatternMinor[index][2]) &&
   		    	(minorLightRightA == lightPatternMinor[index][2]) && (minorLightRightB == lightPatternMinor[index][2])));
	    
	    boolean northDegraded = (((((northLightLeftA != lightPatternMajor[index][0]) && (northLightLeftB == lightPatternMajor[index][0])) || 
        		((northLightLeftA == lightPatternMajor[index][0]) && (northLightLeftB != lightPatternMajor[index][0])) ||
        		((northLightStraightA != lightPatternMajor[index][0]) && (northLightStraightB == lightPatternMajor[index][0])) || 
    	        ((northLightStraightA == lightPatternMajor[index][0]) && (northLightStraightB != lightPatternMajor[index][0])) ||
    	        ((northLightRightA != lightPatternMajor[index][1]) && (northLightRightB == lightPatternMajor[index][1])) || 
    	    	((northLightRightA == lightPatternMajor[index][1]) && (northLightRightB != lightPatternMajor[index][1]))) &&
        		((southLightLeftA == lightPatternMajor[index][0]) && (southLightLeftB == lightPatternMajor[index][0]) &&
    			(southLightStraightA == lightPatternMajor[index][0]) && (southLightStraightB == lightPatternMajor[index][0]) &&
    			(southLightRightA == lightPatternMajor[index][1]) && (southLightRightB == lightPatternMajor[index][1]) &&
    			(minorLightLeftA == lightPatternMajor[index][2]) && (minorLightLeftB == lightPatternMajor[index][2]) &&
    			(minorLightStraightA == lightPatternMajor[index][2]) && (minorLightStraightB == lightPatternMajor[index][2]) &&
    			(minorLightRightA == lightPatternMajor[index][2]) && (minorLightRightB == lightPatternMajor[index][2]))) || 
    			((((northLightLeftA != lightPatternMajor[index][0]) && (northLightLeftB == lightPatternMajor[index][0])) || 
    	        ((northLightLeftA == lightPatternMajor[index][0]) && (northLightLeftB != lightPatternMajor[index][0])) ||
    	       	((northLightStraightA != lightPatternMajor[index][0]) && (northLightStraightB == lightPatternMajor[index][0])) || 
    	    	((northLightStraightA == lightPatternMajor[index][0]) && (northLightStraightB != lightPatternMajor[index][0])) ||
    	    	((northLightRightA != lightPatternMajor[index][1]) && (northLightRightB == lightPatternMajor[index][1])) || 
    	    	((northLightRightA == lightPatternMajor[index][1]) && (northLightRightB != lightPatternMajor[index][1]))) &&
    	        ((southLightLeftA == lightPatternMajor[index][0]) && (southLightLeftB == lightPatternMajor[index][0]) &&
    	    	(southLightStraightA == lightPatternMajor[index][0]) && (southLightStraightB == lightPatternMajor[index][0]) &&
    	    	(southLightRightA == lightPatternMajor[index][1]) && (southLightRightB == lightPatternMajor[index][1]) &&
    	    	(minorLightLeftA == lightPatternMajor[index][2]) && (minorLightLeftB == lightPatternMajor[index][2]) &&
    	    	(minorLightStraightA == lightPatternMajor[index][2]) && (minorLightStraightB == lightPatternMajor[index][2]) &&
    	    	(minorLightRightA == lightPatternMajor[index][2]) && (minorLightRightB == lightPatternMajor[index][2]))));
	    
	    boolean southDegraded = (((((southLightLeftA != lightPatternMajor[index][0]) && (southLightLeftB == lightPatternMajor[index][0])) || 
	        		((southLightLeftA == lightPatternMajor[index][0]) && (southLightLeftB != lightPatternMajor[index][0])) ||
	        		((southLightStraightA != lightPatternMajor[index][0]) && (southLightStraightB == lightPatternMajor[index][0])) || 
	    	        ((southLightStraightA == lightPatternMajor[index][0]) && (southLightStraightB != lightPatternMajor[index][0])) ||
	    	        ((southLightRightA != lightPatternMajor[index][1]) && (southLightRightB == lightPatternMajor[index][1])) || 
	    	    	((southLightRightA == lightPatternMajor[index][1]) && (southLightRightB != lightPatternMajor[index][1]))) &&
	        		((northLightLeftA == lightPatternMajor[index][0]) && (northLightLeftB == lightPatternMajor[index][0]) &&
	    			(northLightStraightA == lightPatternMajor[index][0]) && (northLightStraightB == lightPatternMajor[index][0]) &&
	    			(northLightRightA == lightPatternMajor[index][1]) && (northLightRightB == lightPatternMajor[index][1]) &&
	    			(minorLightLeftA == lightPatternMajor[index][2]) && (minorLightLeftB == lightPatternMajor[index][2]) &&
	    			(minorLightStraightA == lightPatternMajor[index][2]) && (minorLightStraightB == lightPatternMajor[index][2]) &&
	    			(minorLightRightA == lightPatternMajor[index][2]) && (minorLightRightB == lightPatternMajor[index][2]))) || 
	    			((((southLightLeftA != lightPatternMinor[index][0]) && (southLightLeftB == lightPatternMinor[index][0])) || 
	    	        ((southLightLeftA == lightPatternMinor[index][0]) && (southLightLeftB != lightPatternMinor[index][0])) ||
	    	       	((southLightStraightA != lightPatternMinor[index][0]) && (southLightStraightB == lightPatternMinor[index][0])) || 
	    	    	((southLightStraightA == lightPatternMinor[index][0]) && (southLightStraightB != lightPatternMinor[index][0])) ||
	    	    	((southLightRightA != lightPatternMinor[index][1]) && (southLightRightB == lightPatternMinor[index][1])) || 
	    	    	((southLightRightA == lightPatternMinor[index][1]) && (southLightRightB != lightPatternMinor[index][1]))) &&
	    	        ((northLightLeftA == lightPatternMinor[index][0]) && (northLightLeftB == lightPatternMinor[index][0]) &&
	    	    	(northLightStraightA == lightPatternMinor[index][0]) && (northLightStraightB == lightPatternMinor[index][0]) &&
	    	    	(northLightRightA == lightPatternMinor[index][1]) && (northLightRightB == lightPatternMinor[index][1]) &&
	    	    	(minorLightLeftA == lightPatternMinor[index][2]) && (minorLightLeftB == lightPatternMinor[index][2]) &&
	    	    	(minorLightStraightA == lightPatternMinor[index][2]) && (minorLightStraightB == lightPatternMinor[index][2]) &&
	    	    	(minorLightRightA == lightPatternMinor[index][2]) && (minorLightRightB == lightPatternMinor[index][2]))));
	    
	    boolean minorDegraded = (((((minorLightLeftA != lightPatternMajor[index][2]) && (minorLightLeftB == lightPatternMajor[index][2])) || 
        		((minorLightLeftA == lightPatternMajor[index][2]) && (minorLightLeftB != lightPatternMajor[index][2])) ||
        		((minorLightStraightA != lightPatternMajor[index][2]) && (minorLightStraightB == lightPatternMajor[index][2])) || 
    	        ((minorLightStraightA == lightPatternMajor[index][2]) && (minorLightStraightB != lightPatternMajor[index][2])) ||
    	        ((minorLightRightA != lightPatternMajor[index][2]) && (minorLightRightB == lightPatternMajor[index][2])) || 
    	    	((minorLightRightA == lightPatternMajor[index][2]) && (minorLightRightB != lightPatternMajor[index][2]))) &&
    	    	
        		((northLightLeftA == lightPatternMajor[index][0]) && (northLightLeftB == lightPatternMajor[index][0]) &&
    			(northLightStraightA == lightPatternMajor[index][0]) && (northLightStraightB == lightPatternMajor[index][0]) &&
    			(northLightRightA == lightPatternMajor[index][1]) && (northLightRightB == lightPatternMajor[index][1]) &&
    			
    			(southLightLeftA == lightPatternMajor[index][0]) && (southLightLeftB == lightPatternMajor[index][0]) &&
    			(southLightStraightA == lightPatternMajor[index][0]) && (southLightStraightB == lightPatternMajor[index][0]) &&
    			(southLightRightA == lightPatternMajor[index][1]) && (southLightRightB == lightPatternMajor[index][1]))) || 
    			
    			
    			((((minorLightLeftA != lightPatternMinor[index][2]) && (minorLightLeftB == lightPatternMinor[index][2])) || 
    	        ((minorLightLeftA == lightPatternMinor[index][2]) && (minorLightLeftB != lightPatternMinor[index][2])) ||
    	       	((minorLightStraightA != lightPatternMinor[index][2]) && (minorLightStraightB == lightPatternMinor[index][2])) || 
    	    	((minorLightStraightA == lightPatternMinor[index][2]) && (minorLightStraightB != lightPatternMinor[index][2])) ||
    	    	((minorLightRightA != lightPatternMinor[index][2]) && (minorLightRightB == lightPatternMinor[index][2])) || 
    	    	((minorLightRightA == lightPatternMinor[index][2]) && (minorLightRightB != lightPatternMinor[index][2]))) &&
    	    	
    	        ((northLightLeftA == lightPatternMinor[index][0]) && (northLightLeftB == lightPatternMinor[index][0]) &&
    	    	(northLightStraightA == lightPatternMinor[index][0]) && (northLightStraightB == lightPatternMinor[index][0]) &&
    	    	(northLightRightA == lightPatternMinor[index][1]) && (northLightRightB == lightPatternMinor[index][1]) &&
    	    	
    	    	(southLightLeftA == lightPatternMinor[index][0]) && (southLightLeftB == lightPatternMinor[index][0]) &&
    	    	(southLightStraightA == lightPatternMinor[index][0]) && (southLightStraightB == lightPatternMinor[index][0]) &&
    	    	(southLightRightA == lightPatternMinor[index][1]) && (southLightRightB == lightPatternMinor[index][1]))));
	    
	    boolean northSouthDegraded = (((((southLightLeftA != lightPatternMajor[index][0]) && (southLightLeftB == lightPatternMajor[index][0])) || 
        		((southLightLeftA == lightPatternMajor[index][0]) && (southLightLeftB != lightPatternMajor[index][0])) ||
        		((southLightStraightA != lightPatternMajor[index][0]) && (southLightStraightB == lightPatternMajor[index][0])) || 
    	        ((southLightStraightA == lightPatternMajor[index][0]) && (southLightStraightB != lightPatternMajor[index][0])) ||
    	        ((southLightRightA != lightPatternMajor[index][1]) && (southLightRightB == lightPatternMajor[index][1])) || 
    	    	((southLightRightA == lightPatternMajor[index][1]) && (southLightRightB != lightPatternMajor[index][1]))) &&
    	    	
    	    	(((northLightLeftA != lightPatternMajor[index][0]) && (northLightLeftB == lightPatternMajor[index][0])) || 
    	        ((northLightLeftA == lightPatternMajor[index][0]) && (northLightLeftB != lightPatternMajor[index][0])) ||
    	        ((northLightStraightA != lightPatternMajor[index][0]) && (northLightStraightB == lightPatternMajor[index][0])) || 
    	    	((northLightStraightA == lightPatternMajor[index][0]) && (northLightStraightB != lightPatternMajor[index][0])) ||
    	    	((northLightRightA != lightPatternMajor[index][1]) && (northLightRightB == lightPatternMajor[index][1])) || 
    	    	((northLightRightA == lightPatternMajor[index][1]) && (northLightRightB != lightPatternMajor[index][1]))) &&
    	    	    	
    			((minorLightLeftA == lightPatternMajor[index][2]) && (minorLightLeftB == lightPatternMajor[index][2]) &&
    			(minorLightStraightA == lightPatternMajor[index][2]) && (minorLightStraightB == lightPatternMajor[index][2]) &&
    			(minorLightRightA == lightPatternMajor[index][2]) && (minorLightRightB == lightPatternMajor[index][2]))) || 
    			
    			((((southLightLeftA != lightPatternMinor[index][0]) && (southLightLeftB == lightPatternMinor[index][0])) || 
    	        ((southLightLeftA == lightPatternMinor[index][0]) && (southLightLeftB != lightPatternMinor[index][0])) ||
    	       	((southLightStraightA != lightPatternMinor[index][0]) && (southLightStraightB == lightPatternMinor[index][0])) || 
    	    	((southLightStraightA == lightPatternMinor[index][0]) && (southLightStraightB != lightPatternMinor[index][0])) ||
    	    	((southLightRightA != lightPatternMinor[index][1]) && (southLightRightB == lightPatternMinor[index][1])) || 
    	    	((southLightRightA == lightPatternMinor[index][1]) && (southLightRightB != lightPatternMinor[index][1]))) &&
    	    	(((northLightLeftA != lightPatternMinor[index][0]) && (northLightLeftB == lightPatternMinor[index][0])) || 
    	        ((northLightLeftA == lightPatternMinor[index][0]) && (northLightLeftB != lightPatternMinor[index][0])) ||
    	        ((northLightStraightA != lightPatternMinor[index][0]) && (northLightStraightB == lightPatternMinor[index][0])) || 
    	    	((northLightStraightA == lightPatternMinor[index][0]) && (northLightStraightB != lightPatternMinor[index][0])) ||
    	    	((northLightRightA != lightPatternMinor[index][1]) && (northLightRightB == lightPatternMinor[index][1])) || 
    	    	((northLightRightA == lightPatternMinor[index][1]) && (northLightRightB != lightPatternMinor[index][1]))) &&
    	    	((minorLightLeftA == lightPatternMinor[index][2]) && (minorLightLeftB == lightPatternMinor[index][2]) &&
    	    	(minorLightStraightA == lightPatternMinor[index][2]) && (minorLightStraightB == lightPatternMinor[index][2]) &&
    	    	(minorLightRightA == lightPatternMinor[index][2]) && (minorLightRightB == lightPatternMinor[index][2]))));
	    
	    boolean northMinorDegraded = (((((minorLightLeftA != lightPatternMajor[index][2]) && (minorLightLeftB == lightPatternMajor[index][2])) || 
        		((minorLightLeftA == lightPatternMajor[index][2]) && (minorLightLeftB != lightPatternMajor[index][2])) ||
        		((minorLightStraightA != lightPatternMajor[index][2]) && (minorLightStraightB == lightPatternMajor[index][2])) || 
    	        ((minorLightStraightA == lightPatternMajor[index][2]) && (minorLightStraightB != lightPatternMajor[index][2])) ||
    	        ((minorLightRightA != lightPatternMajor[index][2]) && (minorLightRightB == lightPatternMajor[index][2])) || 
    	    	((minorLightRightA == lightPatternMajor[index][2]) && (minorLightRightB != lightPatternMajor[index][2]))) &&
    	    	
    	    	(((northLightLeftA != lightPatternMajor[index][0]) && (northLightLeftB == lightPatternMajor[index][0])) || 
    	        ((northLightLeftA == lightPatternMajor[index][0]) && (northLightLeftB != lightPatternMajor[index][0])) ||
    	        ((northLightStraightA != lightPatternMajor[index][0]) && (northLightStraightB == lightPatternMajor[index][0])) || 
    	    	((northLightStraightA == lightPatternMajor[index][0]) && (northLightStraightB != lightPatternMajor[index][0])) ||
    	    	((northLightRightA != lightPatternMajor[index][1]) && (northLightRightB == lightPatternMajor[index][1])) || 
    	    	((northLightRightA == lightPatternMajor[index][1]) && (northLightRightB != lightPatternMajor[index][1]))) &&
    	    	    	
    			((southLightLeftA == lightPatternMajor[index][0]) && (southLightLeftB == lightPatternMajor[index][0]) &&
    			(southLightStraightA == lightPatternMajor[index][0]) && (southLightStraightB == lightPatternMajor[index][0]) &&
    			(southLightRightA == lightPatternMajor[index][1]) && (southLightRightB == lightPatternMajor[index][1]))) || 
    			
    			((((minorLightLeftA != lightPatternMinor[index][2]) && (minorLightLeftB == lightPatternMinor[index][2])) || 
    	        ((minorLightLeftA == lightPatternMinor[index][2]) && (minorLightLeftB != lightPatternMinor[index][2])) ||
    	       	((minorLightStraightA != lightPatternMinor[index][2]) && (minorLightStraightB == lightPatternMinor[index][2])) || 
    	    	((minorLightStraightA == lightPatternMinor[index][2]) && (minorLightStraightB != lightPatternMinor[index][2])) ||
    	    	((minorLightRightA != lightPatternMinor[index][2]) && (minorLightRightB == lightPatternMinor[index][2])) || 
    	    	((minorLightRightA == lightPatternMinor[index][2]) && (minorLightRightB != lightPatternMinor[index][2]))) &&
    	    	(((northLightLeftA != lightPatternMinor[index][0]) && (northLightLeftB == lightPatternMinor[index][0])) || 
    	        ((northLightLeftA == lightPatternMinor[index][0]) && (northLightLeftB != lightPatternMinor[index][0])) ||
    	        ((northLightStraightA != lightPatternMinor[index][0]) && (northLightStraightB == lightPatternMinor[index][0])) || 
    	    	((northLightStraightA == lightPatternMinor[index][0]) && (northLightStraightB != lightPatternMinor[index][0])) ||
    	    	((northLightRightA != lightPatternMinor[index][1]) && (northLightRightB == lightPatternMinor[index][1])) || 
    	    	((northLightRightA == lightPatternMinor[index][1]) && (northLightRightB != lightPatternMinor[index][1]))) &&
    	    	((southLightLeftA == lightPatternMinor[index][0]) && (southLightLeftB == lightPatternMinor[index][0]) &&
    	    	(southLightStraightA == lightPatternMinor[index][0]) && (southLightStraightB == lightPatternMinor[index][0]) &&
    	    	(southLightRightA == lightPatternMinor[index][1]) && (southLightRightB == lightPatternMinor[index][1]))));
	    
	    boolean southMinorDegraded = (((((minorLightLeftA != lightPatternMajor[index][2]) && (minorLightLeftB == lightPatternMajor[index][2])) || 
        		((minorLightLeftA == lightPatternMajor[index][2]) && (minorLightLeftB != lightPatternMajor[index][2])) ||
        		((minorLightStraightA != lightPatternMajor[index][2]) && (minorLightStraightB == lightPatternMajor[index][2])) || 
    	        ((minorLightStraightA == lightPatternMajor[index][2]) && (minorLightStraightB != lightPatternMajor[index][2])) ||
    	        ((minorLightRightA != lightPatternMajor[index][2]) && (minorLightRightB == lightPatternMajor[index][2])) || 
    	    	((minorLightRightA == lightPatternMajor[index][2]) && (minorLightRightB != lightPatternMajor[index][2]))) &&
    	    	
    	    	(((southLightLeftA != lightPatternMajor[index][0]) && (southLightLeftB == lightPatternMajor[index][0])) || 
    	        ((southLightLeftA == lightPatternMajor[index][0]) && (southLightLeftB != lightPatternMajor[index][0])) ||
    	        ((southLightStraightA != lightPatternMajor[index][0]) && (southLightStraightB == lightPatternMajor[index][0])) || 
    	    	((southLightStraightA == lightPatternMajor[index][0]) && (southLightStraightB != lightPatternMajor[index][0])) ||
    	    	((southLightRightA != lightPatternMajor[index][1]) && (southLightRightB == lightPatternMajor[index][1])) || 
    	    	((southLightRightA == lightPatternMajor[index][1]) && (southLightRightB != lightPatternMajor[index][1]))) &&
    	    	    	
    			((northLightLeftA == lightPatternMajor[index][0]) && (northLightLeftB == lightPatternMajor[index][0]) &&
    			(northLightStraightA == lightPatternMajor[index][0]) && (northLightStraightB == lightPatternMajor[index][0]) &&
    			(northLightRightA == lightPatternMajor[index][1]) && (northLightRightB == lightPatternMajor[index][1]))) || 
    			
    			((((minorLightLeftA != lightPatternMinor[index][2]) && (minorLightLeftB == lightPatternMinor[index][2])) || 
    	        ((minorLightLeftA == lightPatternMinor[index][2]) && (minorLightLeftB != lightPatternMinor[index][2])) ||
    	       	((minorLightStraightA != lightPatternMinor[index][2]) && (minorLightStraightB == lightPatternMinor[index][2])) || 
    	    	((minorLightStraightA == lightPatternMinor[index][2]) && (minorLightStraightB != lightPatternMinor[index][2])) ||
    	    	((minorLightRightA != lightPatternMinor[index][2]) && (minorLightRightB == lightPatternMinor[index][2])) || 
    	    	((minorLightRightA == lightPatternMinor[index][2]) && (minorLightRightB != lightPatternMinor[index][2]))) &&
    	    	(((southLightLeftA != lightPatternMinor[index][0]) && (southLightLeftB == lightPatternMinor[index][0])) || 
    	        ((southLightLeftA == lightPatternMinor[index][0]) && (southLightLeftB != lightPatternMinor[index][0])) ||
    	        ((southLightStraightA != lightPatternMinor[index][0]) && (southLightStraightB == lightPatternMinor[index][0])) || 
    	    	((southLightStraightA == lightPatternMinor[index][0]) && (southLightStraightB != lightPatternMinor[index][0])) ||
    	    	((southLightRightA != lightPatternMinor[index][1]) && (southLightRightB == lightPatternMinor[index][1])) || 
    	    	((southLightRightA == lightPatternMinor[index][1]) && (southLightRightB != lightPatternMinor[index][1]))) &&
    	    	((northLightLeftA == lightPatternMinor[index][0]) && (northLightLeftB == lightPatternMinor[index][0]) &&
    	    	(northLightStraightA == lightPatternMinor[index][0]) && (northLightStraightB == lightPatternMinor[index][0]) &&
    	    	(northLightRightA == lightPatternMinor[index][1]) && (northLightRightB == lightPatternMinor[index][1]))));
	    
	    boolean northSouthMinorDegraded = (((((southLightLeftA != lightPatternMajor[index][0]) && (southLightLeftB == lightPatternMajor[index][0])) || 
        		((southLightLeftA == lightPatternMajor[index][0]) && (southLightLeftB != lightPatternMajor[index][0])) ||
        		((southLightStraightA != lightPatternMajor[index][0]) && (southLightStraightB == lightPatternMajor[index][0])) || 
    	        ((southLightStraightA == lightPatternMajor[index][0]) && (southLightStraightB != lightPatternMajor[index][0])) ||
    	        ((southLightRightA != lightPatternMajor[index][1]) && (southLightRightB == lightPatternMajor[index][1])) || 
    	    	((southLightRightA == lightPatternMajor[index][1]) && (southLightRightB != lightPatternMajor[index][1]))) &&
    	    	
    	    	(((northLightLeftA != lightPatternMajor[index][0]) && (northLightLeftB == lightPatternMajor[index][0])) || 
    	        ((northLightLeftA == lightPatternMajor[index][0]) && (northLightLeftB != lightPatternMajor[index][0])) ||
    	        ((northLightStraightA != lightPatternMajor[index][0]) && (northLightStraightB == lightPatternMajor[index][0])) || 
    	    	((northLightStraightA == lightPatternMajor[index][0]) && (northLightStraightB != lightPatternMajor[index][0])) ||
    	    	((northLightRightA != lightPatternMajor[index][1]) && (northLightRightB == lightPatternMajor[index][1])) || 
    	    	((northLightRightA == lightPatternMajor[index][1]) && (northLightRightB != lightPatternMajor[index][1]))) &&
    	    	    	
    	    	(((minorLightLeftA != lightPatternMajor[index][2]) && (minorLightLeftB == lightPatternMajor[index][2])) || 
    	        		((minorLightLeftA == lightPatternMajor[index][2]) && (minorLightLeftB != lightPatternMajor[index][2])) ||
    	        		((minorLightStraightA != lightPatternMajor[index][2]) && (minorLightStraightB == lightPatternMajor[index][2])) || 
    	    	        ((minorLightStraightA == lightPatternMajor[index][2]) && (minorLightStraightB != lightPatternMajor[index][2])) ||
    	    	        ((minorLightRightA != lightPatternMajor[index][2]) && (minorLightRightB == lightPatternMajor[index][2])) || 
    	    	    	((minorLightRightA == lightPatternMajor[index][2]) && (minorLightRightB != lightPatternMajor[index][2])))) &&
    			
    			((((southLightLeftA != lightPatternMajor[index][0]) && (southLightLeftB == lightPatternMajor[index][0])) || 
    	        ((southLightLeftA == lightPatternMajor[index][0]) && (southLightLeftB != lightPatternMajor[index][0])) ||
    	       	((southLightStraightA != lightPatternMajor[index][0]) && (southLightStraightB == lightPatternMajor[index][0])) || 
    	    	((southLightStraightA == lightPatternMajor[index][0]) && (southLightStraightB != lightPatternMajor[index][0])) ||
    	    	((southLightRightA != lightPatternMajor[index][1]) && (southLightRightB == lightPatternMajor[index][1])) || 
    	    	((southLightRightA == lightPatternMajor[index][1]) && (southLightRightB != lightPatternMajor[index][1]))) &&
    	    	(((northLightLeftA != lightPatternMajor[index][0]) && (northLightLeftB == lightPatternMajor[index][0])) || 
    	        ((northLightLeftA == lightPatternMajor[index][0]) && (northLightLeftB != lightPatternMajor[index][0])) ||
    	        ((northLightStraightA != lightPatternMajor[index][0]) && (northLightStraightB == lightPatternMajor[index][0])) || 
    	    	((northLightStraightA == lightPatternMajor[index][0]) && (northLightStraightB != lightPatternMajor[index][0])) ||
    	    	((northLightRightA != lightPatternMajor[index][1]) && (northLightRightB == lightPatternMajor[index][1])) || 
    	    	((northLightRightA == lightPatternMajor[index][1]) && (northLightRightB != lightPatternMajor[index][1]))) &&
    	    	(((minorLightLeftA != lightPatternMajor[index][2]) && (minorLightLeftB == lightPatternMajor[index][2])) || 
    	    			((minorLightLeftA == lightPatternMajor[index][2]) && (minorLightLeftB != lightPatternMajor[index][2])) ||
    	    	       	((minorLightStraightA != lightPatternMajor[index][2]) && (minorLightStraightB == lightPatternMajor[index][2])) || 
    	    	    	((minorLightStraightA == lightPatternMajor[index][2]) && (minorLightStraightB != lightPatternMajor[index][2])) ||
    	    	    	((minorLightRightA != lightPatternMajor[index][2]) && (minorLightRightB == lightPatternMajor[index][2])) || 
    	    	    	((minorLightRightA == lightPatternMajor[index][2]) && (minorLightRightB != lightPatternMajor[index][2])))));
	    
		if (input != EMPTY || index > 0) {
			index++; 
		}
		
		if (fullyOperated) {
			return "FullyOperated";
		}
		else if (northDegraded) {
			return "NorthDegraded";
		}
		else if (southDegraded) {
			return "SouthDegraded";
		}
		else if (minorDegraded) {
			return "MinorDegraded";
		}
		else if (northSouthDegraded) {
			return "NorthSouthDegraded";
		}
		else if (northMinorDegraded) {
			return "NorthMinorDegraded";
		}
		else if (southMinorDegraded) {
			return "SouthMinorDegraded";
		}
		else if (northSouthMinorDegraded) {
			return "NorthSouthMinorDegraded";
		}
		else {
			return "Failed";
		}
	}
}
