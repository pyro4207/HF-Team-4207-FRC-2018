package HF2017Utility;

public class HFDistanceSensor {
	public static final int flagOnErrorValue = -4207;
	public String lastErrorMessage = "";
	public String debugStep;
	protected int feedbackLength;
	protected double  rejectedValues;
	protected double curSmartAverage;
	protected boolean smartInitialized = false;
	protected int curIndex=0;
	protected boolean sensorEnabled = true;
	
	public void enable(boolean setEnabled) {
		sensorEnabled = setEnabled;
	}
	
	public boolean getEnabled() {
		return sensorEnabled;
	}
    public double getDistance(boolean debugMessages) {
    	return flagOnErrorValue;
    }

    public double checkDistance(boolean debugMessages) {
    	return flagOnErrorValue;
    }
    
    public double smartInitialize() {
    	return flagOnErrorValue;
    }
}
