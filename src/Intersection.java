
public class Intersection {
	  //the traffic lights
	  protected TrafficLight northLightLeft;
	  protected TrafficLight northLightStraight;
	  protected TrafficLight northLightRight;
	  protected TrafficLight southLightLeft;
	  protected TrafficLight southLightStraight;
	  protected TrafficLight southLightRight;
	  protected TrafficLight minorRoadLightLeft;
	  protected TrafficLight minorRoadLightStraight;
	  protected TrafficLight minorRoadLightRight;

	  //the left and right car sensors respectively
	  protected Sensor northRightSensor;
	  protected Sensor southRightSensor;
	  protected Sensor minorRightSensor;

	  public Intersection()
	  {
	    northLightLeft = new MajorRoadTrafficLight(LightColour.GREEN);
	    northLightStraight = new MajorRoadTrafficLight(LightColour.GREEN);
	    northLightRight = new MajorRoadTrafficLightTurn(LightColour.RED);
	    
	    southLightLeft = new MajorRoadTrafficLight(LightColour.GREEN);
	    southLightStraight = new MajorRoadTrafficLight(LightColour.GREEN);
	    southLightRight = new MajorRoadTrafficLightTurn(LightColour.RED);
	    
	    minorRoadLightLeft = new MinorRoadTrafficLight();
	    minorRoadLightStraight = new MinorRoadTrafficLight();
	    minorRoadLightRight = new MinorRoadTrafficLight();
	    
	    northRightSensor = new Sensor();
	    southRightSensor = new Sensor();
	    minorRightSensor = new Sensor();
	  }

	  //one tick of the intersection. If one of the sensors is activated,
	  //change the signals on the lights.
	  public void tick(char input)
	  {
	    northLightLeft.tick();
	    northLightStraight.tick();
	    northLightRight.tick();
	    
	    southLightLeft.tick();
	    southLightStraight.tick();
	    southLightRight.tick();
	    
	    minorRoadLightLeft.tick();
	    minorRoadLightStraight.tick();
	    minorRoadLightRight.tick();
	    
	    northRightSensor.tick(input);
	    southRightSensor.tick(input);
	    minorRightSensor.tick(input);

	    if (northRightSensor.isActivated() || southRightSensor.isActivated()) {
	    	
	          northLightLeft.signalChange();
	          northLightStraight.signalChange();
	          northLightRight.signalChange();
	          
	          southLightLeft.signalChange();
	          southLightStraight.signalChange();
	          southLightRight.signalChange();
	        }
	        
	    else if (minorRightSensor.isActivated()) {

	    	if(northLightStraight.getColour() == LightColour.GREEN){

	    		northLightLeft.signalChange();
	    		northLightStraight.signalChange();

	    		southLightLeft.signalChange();
	    		southLightStraight.signalChange();

	    		minorRoadLightLeft.signalChange();
	    		minorRoadLightStraight.signalChange();
	    		minorRoadLightRight.signalChange();
	    	}

	    	else if(northLightRight.getColour() == LightColour.GREEN){

	    		northLightRight.signalChange();
	    		southLightRight.signalChange();

	    		minorRoadLightLeft.signalChange();
	    		minorRoadLightStraight.signalChange();
	    		minorRoadLightRight.signalChange();
	    	}
	    }

	  }

	public TrafficLight getNorthLightLeft() {
		return northLightLeft;
	}

	public void setNorthLightLeft(TrafficLight northLightLeft) {
		this.northLightLeft = northLightLeft;
	}

	public TrafficLight getNorthLightStraight() {
		return northLightStraight;
	}

	public void setNorthLightStraight(TrafficLight northLightStraight) {
		this.northLightStraight = northLightStraight;
	}

	public TrafficLight getNorthLightRight() {
		return northLightRight;
	}

	public void setNorthLightRight(TrafficLight northLightRight) {
		this.northLightRight = northLightRight;
	}

	public TrafficLight getSouthLightLeft() {
		return southLightLeft;
	}

	public void setSouthLightLeft(TrafficLight southLightLeft) {
		this.southLightLeft = southLightLeft;
	}

	public TrafficLight getSouthLightStraight() {
		return southLightStraight;
	}

	public void setSouthLightStraight(TrafficLight southLightStraight) {
		this.southLightStraight = southLightStraight;
	}

	public TrafficLight getSouthLightRight() {
		return southLightRight;
	}

	public void setSouthLightRight(TrafficLight southLightRight) {
		this.southLightRight = southLightRight;
	}

	public TrafficLight getMinorRoadLightLeft() {
		return minorRoadLightLeft;
	}

	public void setMinorRoadLightLeft(TrafficLight minorRoadLightLeft) {
		this.minorRoadLightLeft = minorRoadLightLeft;
	}

	public TrafficLight getMinorRoadLightStraight() {
		return minorRoadLightStraight;
	}

	public void setMinorRoadLightStraight(TrafficLight minorRoadLightStraight) {
		this.minorRoadLightStraight = minorRoadLightStraight;
	}

	public TrafficLight getMinorRoadLightRight() {
		return minorRoadLightRight;
	}

	public void setMinorRoadLightRight(TrafficLight minorRoadLightRight) {
		this.minorRoadLightRight = minorRoadLightRight;
	}

	@Override
	public String toString() {
		return "Intersection [northLightLeft=" + northLightLeft
				+ ", northLightStraight=" + northLightStraight
				+ ", northLightRight=" + northLightRight + ", southLightLeft="
				+ southLightLeft + ", southLightStraight=" + southLightStraight
				+ ", southLightRight=" + southLightRight
				+ ", minorRoadLightLeft=" + minorRoadLightLeft
				+ ", minorRoadLightStraight=" + minorRoadLightStraight
				+ ", minorRoadLightRight=" + minorRoadLightRight + "]";
	}
}
