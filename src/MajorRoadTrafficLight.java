public class MajorRoadTrafficLight extends TrafficLight {

	public MajorRoadTrafficLight(LightColour color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	//one tick of the clock; move the lights through the pattern if necessary
	  public void tick()
	  {
	    if (timer_ > TIMEOUT) {
	      timer_--;
	    }

	    super.tick();
	  }

	  //change the signal due to a sensor being triggered
	  public void signalChange()
	  {
	    //if we are already iterating through a light change, then ignore
	    //the signal.
	    if (colour_ == LightColour.GREEN) {
	      timer_ = TIMEOUT;
	    }
	  }

	  protected void goGreen()
	  {
	    super.goGreen();
	    timer_ = -1;
	  }

	  protected void goAmber()
	  {
	    super.goAmber();
	    timer_ = AMBER_TIME;
	  }

	  protected void goRed()
	  {
	    super.goRed();
	    timer_ = RED_TIME;
	  }
	}
