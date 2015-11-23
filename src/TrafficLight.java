
abstract public class TrafficLight {

	final protected static int TIMEOUT = 0;

	//the time that a light should be amber
	final public static int AMBER_TIME = 2;

	//the time that the minor road light should be green
	final public static int GREEN_TIME = 4;

	//the time that the major road light should be red
	final public static int RED_TIME = AMBER_TIME + GREEN_TIME;

	final protected static double FAILURE_PROBABILITY = 0.01D;


	//true if and only if the light is working
	protected boolean workingState = true;

	//the colour of this light
	protected LightColour colour_;

	//the time remaining until a change is required
	protected int timer_;

	public TrafficLight(LightColour colour)
	{
		colour_ = colour;
		timer_ = -1;
	}

	//one tick of the block. Calculate the colour of the light for the
	//next time unit
	public void tick()
	{
		if (workingState) {
			//if it is time to change the colour, then do so.
			if (timer_ == TIMEOUT) {
				if (colour_ == LightColour.RED) {
					goGreen();
				}
				else if (colour_ == LightColour.AMBER) {
					goRed();
				}
				else if (colour_ == LightColour.GREEN) {
					goAmber();
				}
			}

			//at a certain probability, make the light 'fail'
			if (Util.fail(FAILURE_PROBABILITY)) {
				workingState = false;
				goNone();
			}
		}
	}

	abstract void signalChange();

	public LightColour getColour()
	{
		return colour_;
	}

	public String toString()
	{
		return colour_.toString();
	}

	protected void goGreen()
	{
		colour_ = LightColour.GREEN;
	}

	protected void goAmber()
	{
		colour_ = LightColour.AMBER;
	}

	protected void goRed()
	{
		colour_ = LightColour.RED;
	} 

	protected void goNone()
	{
		colour_ = LightColour.NONE;
	}
}

