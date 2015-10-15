public class IntersectionParallel {
	// the traffic lights
	protected TrafficLight northLightLeftA;
	protected TrafficLight northLightLeftB;
	protected TrafficLight northLightStraightA;
	protected TrafficLight northLightStraightB;
	protected TrafficLight northLightRightA;
	protected TrafficLight northLightRightB;
	
	protected TrafficLight southLightLeftA;
	protected TrafficLight southLightLeftB;
	protected TrafficLight southLightStraightA;
	protected TrafficLight southLightStraightB;
	protected TrafficLight southLightRightA;
	protected TrafficLight southLightRightB;
	
	protected TrafficLight minorRoadLightLeftA;
	protected TrafficLight minorRoadLightLeftB;
	protected TrafficLight minorRoadLightStraightA;
	protected TrafficLight minorRoadLightStraightB;
	protected TrafficLight minorRoadLightRightA;
	protected TrafficLight minorRoadLightRightB;

	// the left and right car sensors respectively
	protected Sensor northRightSensor;
	protected Sensor southRightSensor;
	protected Sensor minorRightSensor;

	public IntersectionParallel() {
		// TODO Auto-generated constructor stub

		northLightLeftA = new MajorRoadTrafficLight(LightColour.GREEN);
		northLightLeftB = new MajorRoadTrafficLight(LightColour.GREEN);
		northLightStraightA = new MajorRoadTrafficLight(LightColour.GREEN);
		northLightStraightB = new MajorRoadTrafficLight(LightColour.GREEN);
		northLightRightA = new MajorRoadTrafficLightTurn(LightColour.RED);
		northLightRightB = new MajorRoadTrafficLightTurn(LightColour.RED);

		southLightLeftA = new MajorRoadTrafficLight(LightColour.GREEN);
		southLightLeftB = new MajorRoadTrafficLight(LightColour.GREEN);
		southLightStraightA = new MajorRoadTrafficLight(LightColour.GREEN);
		southLightStraightB = new MajorRoadTrafficLight(LightColour.GREEN);
		southLightRightA = new MajorRoadTrafficLightTurn(LightColour.RED);
		southLightRightB = new MajorRoadTrafficLightTurn(LightColour.RED);

		minorRoadLightLeftA = new MinorRoadTrafficLight();
		minorRoadLightLeftB = new MinorRoadTrafficLight();
		minorRoadLightStraightA = new MinorRoadTrafficLight();
		minorRoadLightStraightB = new MinorRoadTrafficLight();
		minorRoadLightRightA = new MinorRoadTrafficLight();
		minorRoadLightRightB = new MinorRoadTrafficLight();

		northRightSensor = new Sensor();
		southRightSensor = new Sensor();
		minorRightSensor = new Sensor();
	}

	// one tick of the intersection. If one of the sensors is activated,
	// change the signals on the lights.
	public void tick(char input) {
		northLightLeftA.tick();
		northLightLeftB.tick();
		northLightStraightA.tick();
		northLightStraightB.tick();
		northLightRightA.tick();
		northLightRightB.tick();

		southLightLeftA.tick();
		southLightLeftB.tick();
		southLightStraightA.tick();
		southLightStraightB.tick();
		southLightRightA.tick();
		southLightRightB.tick();

		minorRoadLightLeftA.tick();
		minorRoadLightLeftB.tick();
		minorRoadLightStraightA.tick();
		minorRoadLightStraightB.tick();
		minorRoadLightRightA.tick();		
		minorRoadLightRightB.tick();

		northRightSensor.tick(input);
		southRightSensor.tick(input);
		minorRightSensor.tick(input);

		if (northRightSensor.isActivated() || southRightSensor.isActivated()) {

			northLightLeftA.signalChange();
			northLightLeftB.signalChange();
			northLightStraightA.signalChange();
			northLightStraightB.signalChange();
			northLightRightA.signalChange();
			northLightRightB.signalChange();

			southLightLeftA.signalChange();
			southLightLeftB.signalChange();
			southLightStraightA.signalChange();
			southLightStraightB.signalChange();
			southLightRightA.signalChange();
			southLightRightB.signalChange();
		}

		else if (minorRightSensor.isActivated()) {

			if ( (northLightStraightA.getColour() == LightColour.GREEN) || (northLightStraightB.getColour() == LightColour.GREEN) ||
					(northLightLeftA.getColour() == LightColour.GREEN) || (northLightLeftB.getColour() == LightColour.GREEN)  ||
					(southLightStraightA.getColour() == LightColour.GREEN) || (southLightStraightB.getColour() == LightColour.GREEN) ||
					(southLightLeftA.getColour() == LightColour.GREEN) || (southLightLeftB.getColour() == LightColour.GREEN) ) {

				northLightLeftA.signalChange();
				northLightLeftB.signalChange();
				northLightStraightA.signalChange();
				northLightStraightB.signalChange();

				southLightLeftA.signalChange();
				southLightLeftB.signalChange();
				southLightStraightA.signalChange();
				southLightStraightB.signalChange();

				minorRoadLightLeftA.signalChange();
				minorRoadLightLeftB.signalChange();
				minorRoadLightStraightA.signalChange();
				minorRoadLightStraightB.signalChange();
				minorRoadLightRightA.signalChange();
				minorRoadLightRightB.signalChange();
			}

			else if (northLightRightA.getColour() == LightColour.GREEN || northLightRightB.getColour() == LightColour.GREEN ||
					southLightRightA.getColour() == LightColour.GREEN || southLightRightB.getColour() == LightColour.GREEN ) {

				northLightRightA.signalChange();
				northLightRightB.signalChange();
				southLightRightA.signalChange();
				southLightRightB.signalChange();

				minorRoadLightLeftA.signalChange();
				minorRoadLightLeftB.signalChange();
				minorRoadLightStraightA.signalChange();
				minorRoadLightStraightB.signalChange();
				minorRoadLightRightA.signalChange();
				minorRoadLightRightB.signalChange();
			}
		}
	}

	public TrafficLight getNorthLightLeftA() {
		return northLightLeftA;
	}

	public void setNorthLightLeftA(TrafficLight northLightLeftA) {
		this.northLightLeftA = northLightLeftA;
	}

	public TrafficLight getNorthLightLeftB() {
		return northLightLeftB;
	}

	public void setNorthLightLeftB(TrafficLight northLightLeftB) {
		this.northLightLeftB = northLightLeftB;
	}

	public TrafficLight getNorthLightStraightA() {
		return northLightStraightA;
	}

	public void setNorthLightStraightA(TrafficLight northLightStraightA) {
		this.northLightStraightA = northLightStraightA;
	}

	public TrafficLight getNorthLightStraightB() {
		return northLightStraightB;
	}

	public void setNorthLightStraightB(TrafficLight northLightStraightB) {
		this.northLightStraightB = northLightStraightB;
	}

	public TrafficLight getNorthLightRightA() {
		return northLightRightA;
	}

	public void setNorthLightRightA(TrafficLight northLightRightA) {
		this.northLightRightA = northLightRightA;
	}

	public TrafficLight getNorthLightRightB() {
		return northLightRightB;
	}

	public void setNorthLightRightB(TrafficLight northLightRightB) {
		this.northLightRightB = northLightRightB;
	}

	public TrafficLight getSouthLightLeftA() {
		return southLightLeftA;
	}

	public void setSouthLightLeftA(TrafficLight southLightLeftA) {
		this.southLightLeftA = southLightLeftA;
	}

	public TrafficLight getSouthLightLeftB() {
		return southLightLeftB;
	}

	public void setSouthLightLeftB(TrafficLight southLightLeftB) {
		this.southLightLeftB = southLightLeftB;
	}

	public TrafficLight getSouthLightStraightA() {
		return southLightStraightA;
	}

	public void setSouthLightStraightA(TrafficLight southLightStraightA) {
		this.southLightStraightA = southLightStraightA;
	}

	public TrafficLight getSouthLightStraightB() {
		return southLightStraightB;
	}

	public void setSouthLightStraightB(TrafficLight southLightStraightB) {
		this.southLightStraightB = southLightStraightB;
	}

	public TrafficLight getSouthLightRightA() {
		return southLightRightA;
	}

	public void setSouthLightRightA(TrafficLight southLightRightA) {
		this.southLightRightA = southLightRightA;
	}

	public TrafficLight getSouthLightRightB() {
		return southLightRightB;
	}

	public void setSouthLightRightB(TrafficLight southLightRightB) {
		this.southLightRightB = southLightRightB;
	}

	public TrafficLight getMinorRoadLightLeftA() {
		return minorRoadLightLeftA;
	}

	public void setMinorRoadLightLeftA(TrafficLight minorRoadLightLeftA) {
		this.minorRoadLightLeftA = minorRoadLightLeftA;
	}

	public TrafficLight getMinorRoadLightLeftB() {
		return minorRoadLightLeftB;
	}

	public void setMinorRoadLightLeftB(TrafficLight minorRoadLightLeftB) {
		this.minorRoadLightLeftB = minorRoadLightLeftB;
	}

	public TrafficLight getMinorRoadLightStraightA() {
		return minorRoadLightStraightA;
	}

	public void setMinorRoadLightStraightA(TrafficLight minorRoadLightStraightA) {
		this.minorRoadLightStraightA = minorRoadLightStraightA;
	}

	public TrafficLight getMinorRoadLightStraightB() {
		return minorRoadLightStraightB;
	}

	public void setMinorRoadLightStraightB(TrafficLight minorRoadLightStraightB) {
		this.minorRoadLightStraightB = minorRoadLightStraightB;
	}

	public TrafficLight getMinorRoadLightRightA() {
		return minorRoadLightRightA;
	}

	public void setMinorRoadLightRightA(TrafficLight minorRoadLightRightA) {
		this.minorRoadLightRightA = minorRoadLightRightA;
	}

	public TrafficLight getMinorRoadLightRightB() {
		return minorRoadLightRightB;
	}

	public void setMinorRoadLightRightB(TrafficLight minorRoadLightRightB) {
		this.minorRoadLightRightB = minorRoadLightRightB;
	}

	public Sensor getNorthRightSensor() {
		return northRightSensor;
	}

	public void setNorthRightSensor(Sensor northRightSensor) {
		this.northRightSensor = northRightSensor;
	}

	public Sensor getSouthRightSensor() {
		return southRightSensor;
	}

	public void setSouthRightSensor(Sensor southRightSensor) {
		this.southRightSensor = southRightSensor;
	}

	public Sensor getMinorRightSensor() {
		return minorRightSensor;
	}

	public void setMinorRightSensor(Sensor minorRightSensor) {
		this.minorRightSensor = minorRightSensor;
	}

	@Override
	public String toString() {
		return "IntersectionParallel [northLightLeftA=" + northLightLeftA
				+ ", northLightLeftB=" + northLightLeftB
				+ ", northLightStraightA=" + northLightStraightA
				+ ", northLightStraightB=" + northLightStraightB
				+ ", northLightRightA=" + northLightRightA
				+ ", northLightRightB=" + northLightRightB
				+ ", southLightLeftA=" + southLightLeftA + ", southLightLeftB="
				+ southLightLeftB + ", southLightStraightA="
				+ southLightStraightA + ", southLightStraightB="
				+ southLightStraightB + ", southLightRightA="
				+ southLightRightA + ", southLightRightB=" + southLightRightB
				+ ", minorRoadLightLeftA=" + minorRoadLightLeftA
				+ ", minorRoadLightLeftB=" + minorRoadLightLeftB
				+ ", minorRoadLightStraightA=" + minorRoadLightStraightA
				+ ", minorRoadLightStraightB=" + minorRoadLightStraightB
				+ ", minorRoadLightRightA=" + minorRoadLightRightA
				+ ", minorRoadLightRightB=" + minorRoadLightRightB
				+ ", northRightSensor=" + northRightSensor
				+ ", southRightSensor=" + southRightSensor
				+ ", minorRightSensor=" + minorRightSensor + "]";
	}

}
