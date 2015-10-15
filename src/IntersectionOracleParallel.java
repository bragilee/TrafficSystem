
public class IntersectionOracleParallel {
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
	
	//have not been used yet
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
	public IntersectionOracleParallel(IntersectionParallel intersectionParallel)
	{
		this.intersectionParallel = intersectionParallel;
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
		LightColour minorLightStrightA = intersectionParallel.getMinorRoadLightStraightA().getColour();
		LightColour minorLightStrightB = intersectionParallel.getMinorRoadLightStraightB().getColour();
		LightColour minorLightRightA = intersectionParallel.getMinorRoadLightRightA().getColour();
		LightColour minorLightRightB = intersectionParallel.getMinorRoadLightRightB().getColour();
		
		boolean result = (  ((northLightLeftA == lightPatternMajor[indexLight][0]) || (northLightLeftB == lightPatternMajor[indexLight][0])) && 
				((northLightStraightA == lightPatternMajor[indexLight][0]) || (northLightStraightB == lightPatternMajor[indexLight][0])) &&
				((northLightRightA == lightPatternMajor[indexLight][1]) || (northLightRightB == lightPatternMajor[indexLight][1])) && 
				((southLightLeftA == lightPatternMajor[indexLight][0]) || (southLightLeftB == lightPatternMajor[indexLight][0])) && 
				((southLightStraightA == lightPatternMajor[indexLight][0]) && (southLightStraightB == lightPatternMajor[indexLight][0])) &&
				((southLightRightA == lightPatternMajor[indexLight][1]) || (southLightRightB == lightPatternMajor[indexLight][1])) &&
				((minorLightLeftA == lightPatternMajor[indexLight][2]) || (minorLightLeftB == lightPatternMajor[indexLight][2]) ) && 
				((minorLightStrightA == lightPatternMajor[indexLight][2]) || (minorLightStrightB == lightPatternMajor[indexLight][2])) &&
				((minorLightRightA == lightPatternMajor[indexLight][2]) || (minorLightRightA == lightPatternMajor[indexLight][2]))  );
		
		if (input != EMPTY || indexLight > 0) {
			indexLight++; 
		}
		return result;
	}
	
	public boolean verifyMinorPattern(char input) {
		if (indexLightMinor == lightPatternMinor.length) {
			indexLightMinor = 0; 
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
		LightColour minorLightStrightA = intersectionParallel.getMinorRoadLightStraightA().getColour();
		LightColour minorLightStrightB = intersectionParallel.getMinorRoadLightStraightB().getColour();
		LightColour minorLightRightA = intersectionParallel.getMinorRoadLightRightA().getColour();
		LightColour minorLightRightB = intersectionParallel.getMinorRoadLightRightB().getColour();
		
		boolean result = (  ((northLightLeftA == lightPatternMinor[indexLight][0]) || (northLightLeftB == lightPatternMinor[indexLight][0])) && 
				((northLightStraightA == lightPatternMinor[indexLight][0]) || (northLightStraightB == lightPatternMinor[indexLight][0])) &&
				((northLightRightA == lightPatternMinor[indexLight][1]) || (northLightRightB == lightPatternMinor[indexLight][1])) && 
				((southLightLeftA == lightPatternMinor[indexLight][0]) || (southLightLeftB == lightPatternMinor[indexLight][0])) && 
				((southLightStraightA == lightPatternMinor[indexLight][0]) || (southLightStraightB == lightPatternMinor[indexLight][0])) &&
				((southLightRightA == lightPatternMinor[indexLight][1]) || (southLightRightB == lightPatternMinor[indexLight][1])) &&
				((minorLightLeftA == lightPatternMinor[indexLight][2]) || (minorLightLeftB == lightPatternMinor[indexLight][2]) ) && 
				((minorLightStrightA == lightPatternMinor[indexLight][2]) || (minorLightStrightB == lightPatternMinor[indexLight][2])) &&
				((minorLightRightA == lightPatternMinor[indexLight][2]) || (minorLightRightA == lightPatternMinor[indexLight][2]))  );
		
		if (input != EMPTY || indexLightMinor > 0) {
			indexLightMinor++; 
		}
		return result;
	}
}
