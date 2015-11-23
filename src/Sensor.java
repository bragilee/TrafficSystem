
public class Sensor
{
//	the types of input; either the car triggers the sensor or not
	final static protected char EMPTY = 'E';
	final static protected char CAR = 'C';

//	the probability of the sensor dropping out at each tick
	final protected static double FAILURE_PROBABILITY = 0.1D;

	//true if and only if this was activated in the previous tick
	protected boolean activatedState;

	//the index of the current input
	protected int index_;

	public Sensor()
	{
		activatedState = false;
	}

	//one tick of the clock. Detect whether a signal has been received
	public void tick(char in)
	{
		detectSignal(in);
	}

	protected void detectSignal(char input)
	{
		activatedState = false;
		if (input == CAR && !Util.fail(FAILURE_PROBABILITY)) {
			activatedState = true;
		}
	}

	//return true if and only if the sensor was activated this tick
	public boolean isActivated()
	{
		boolean result = activatedState;
		return result;
	}
}
