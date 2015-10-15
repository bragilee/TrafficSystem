
public class IntersectionOracle {
	final public static char CAR = 'C';
	final public static char EMPTY = 'E';
	//initialize the intersection to test
	private Intersection intersection;
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
	private LightColour [][] lightPatternMix = 
		{	{LightColour.GREEN, LightColour.RED, LightColour.RED},
			{LightColour.AMBER, LightColour.RED, LightColour.RED},
			{LightColour.AMBER, LightColour.RED, LightColour.RED},
			{LightColour.RED, LightColour.GREEN, LightColour.RED},
			{LightColour.RED, LightColour.GREEN, LightColour.RED},
			{LightColour.RED, LightColour.GREEN, LightColour.RED},
			{LightColour.RED, LightColour.GREEN, LightColour.RED},
			{LightColour.RED, LightColour.AMBER, LightColour.RED},
			{LightColour.RED, LightColour.AMBER, LightColour.RED},
			{LightColour.RED, LightColour.RED, LightColour.GREEN},
			{LightColour.RED, LightColour.RED, LightColour.GREEN},
			{LightColour.RED, LightColour.RED, LightColour.GREEN},
			{LightColour.RED, LightColour.RED, LightColour.GREEN},
			{LightColour.RED, LightColour.RED, LightColour.AMBER},
			{LightColour.RED, LightColour.RED, LightColour.AMBER},
		};
	private int indexLight = 0;
	private int indexLightMinor = 0;
	//constructors
	public IntersectionOracle(Intersection intersection)
	{
		this.intersection = intersection;
	}

	/*
	 * check the expected light pattern is matched with actual light pattern
	 */
	public boolean verifyMajorPattern(char input)
	{
		//if it has reached the end of the lights pattern, go back to the start
		if (indexLight == lightPatternMajor.length) {
			indexLight = 0; 
			}
		
		LightColour northLightLeft = intersection.getNorthLightLeft().getColour();
		LightColour northLightStraight = intersection.getNorthLightStraight().getColour();
		LightColour northLightRight = intersection.getNorthLightRight().getColour();
		
		LightColour southLightLeft = intersection.getSouthLightLeft().getColour();
		LightColour southLightStraight = intersection.getSouthLightStraight().getColour();
		LightColour southLightRight = intersection.getSouthLightRight().getColour();		
		
		LightColour minorLightLeft = intersection.getMinorRoadLightLeft().getColour();
		LightColour minorLightStright = intersection.getMinorRoadLightLeft().getColour();
		LightColour minorLightRight = intersection.getMinorRoadLightLeft().getColour();
		
		boolean result = ( (northLightLeft == lightPatternMajor[indexLight][0]) && (northLightStraight == lightPatternMajor[indexLight][0]) &&
				(northLightRight == lightPatternMajor[indexLight][1]) && (southLightLeft == lightPatternMajor[indexLight][0]) && 
				(southLightStraight == lightPatternMajor[indexLight][0]) && (southLightRight == lightPatternMajor[indexLight][1]) &&
				(minorLightLeft == lightPatternMajor[indexLight][2]) && (minorLightStright == lightPatternMajor[indexLight][2]) &&
				(minorLightRight == lightPatternMajor[indexLight][2]) );
		
		if (input != EMPTY || indexLight > 0) {
			indexLight++; 
		}
		return result;
	}
	
	public boolean verifyMinorPattern(char input) {
		if (indexLightMinor == lightPatternMinor.length) {
			indexLightMinor = 0; 
			}
		LightColour northLightLeft = intersection.getNorthLightLeft().getColour();
		LightColour northLightStraight = intersection.getNorthLightStraight().getColour();
		LightColour northLightRight = intersection.getNorthLightRight().getColour();
		
		LightColour southLightLeft = intersection.getSouthLightLeft().getColour();
		LightColour southLightStraight = intersection.getSouthLightStraight().getColour();
		LightColour southLightRight = intersection.getSouthLightRight().getColour();		
		
		LightColour minorLightLeft = intersection.getMinorRoadLightLeft().getColour();
		LightColour minorLightStright = intersection.getMinorRoadLightLeft().getColour();
		LightColour minorLightRight = intersection.getMinorRoadLightLeft().getColour();
		
		boolean result = ( (northLightLeft == lightPatternMinor[indexLightMinor][0]) && (northLightStraight == lightPatternMinor[indexLightMinor][0]) &&
				(northLightRight == lightPatternMinor[indexLightMinor][1]) && (southLightLeft == lightPatternMinor[indexLightMinor][0]) && 
				(southLightStraight == lightPatternMinor[indexLightMinor][0]) && (southLightRight == lightPatternMinor[indexLightMinor][1]) &&
				(minorLightLeft == lightPatternMinor[indexLightMinor][2]) && (minorLightStright == lightPatternMinor[indexLightMinor][2]) &&
				(minorLightRight == lightPatternMinor[indexLightMinor][2]) );
		
		if (input != EMPTY || indexLightMinor > 0) {
			indexLightMinor++; 
		}
		return result;
	}
}
