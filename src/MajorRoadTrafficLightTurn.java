
public class MajorRoadTrafficLightTurn extends TrafficLight {

	public MajorRoadTrafficLightTurn(LightColour colour) {
		super(LightColour.RED);
		// TODO Auto-generated constructor stub
	}

	//one tick of the clock; move the lights through the pattern if necessary
	public void tick()
	{
	  if (timer_ >= TIMEOUT) {
	    timer_--;
	  }
	  super.tick();
	}
	  
	@Override
	//change the signal due to a sensor being triggered
	public void signalChange()
	{
		//if we are already iterating through a light change, then ignore
		//the signal
		if (colour_ == LightColour.RED && timer_ <= 0) {
			//+ 1 to account for the current tick
			timer_ = AMBER_TIME + 1;
		}
	}
	
	protected void goGreen()
	{
		super.goGreen();
		timer_ = GREEN_TIME;
	}

	protected void goAmber()
	{
		super.goAmber();
		timer_ = AMBER_TIME;
	}

	protected void goRed()
	{
		super.goRed();
		timer_ = -1;
	}
}
