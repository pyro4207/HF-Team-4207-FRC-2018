package HF2017Utility;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HFDigitalDistanceSensor extends HFDistanceSensor {

	DigitalOutput digitalOutput;
	DigitalInput digitalInput;
    private double lastRawReading = -4207;
    
	
	public HFDigitalDistanceSensor(int pingChannel,int echoChannel) {
		digitalOutput = new DigitalOutput(pingChannel);
		digitalInput = new DigitalInput(echoChannel);
		this.feedbackLength = 1;
		rejectedValues = 0;
	}
	public HFDigitalDistanceSensor(int pingChannel,int echoChannel, int feedbackLength) {
		digitalOutput = new DigitalOutput(pingChannel);
		digitalInput = new DigitalInput(echoChannel);
		this.feedbackLength = feedbackLength;
		rejectedValues = 0;
	}
	
    private boolean sendTriggerSignal(){
    	try {
    		// Send 5V for 10 uS.
        	digitalOutput.set(false);
			Timer.delay(0.000010);
			digitalOutput.set(true);
			Timer.delay(0.000010);
  			digitalOutput.set(false);      	// Set signal back to 0V.
    	} catch(Exception ex)  {
    		lastErrorMessage = "T" + ex.getMessage();
    		 SmartDashboard.putString("statusdist", lastErrorMessage);
    		return false;
    	}
    	return true;
    }
	
    private double takeDistanceReading() {
		long startEcho =0;
    	try {
    		//   SmartDashboard.putString("DB/String 0", "start sense     " );
//    		SmartDashboard.putString("statusdist", "starting");

    		if(!sendTriggerSignal()) return flagOnErrorValue;
    		// Wait for Echo signal.
    		int cnt = 0;
    		while(!digitalInput.get()){
    			if (cnt>2000)  {    // purpose is to handle sensor not responding
    	    		SmartDashboard.putString("statusdist", "no echo b");
    				return flagOnErrorValue;
    			}
    			cnt++;
    		}
    		//start of return pulse
    		startEcho = System.nanoTime();   
    		cnt = 0;
    		while(digitalInput.get()){
    			if (cnt>35000)  {    // purpose is to handle sensor not responding
    	    		SmartDashboard.putString("statusdist", "no echo");
    	    		return flagOnErrorValue;
    			}
    			cnt++;
    		}
    		//end of return pulse
    	} catch(Exception ex) {
        	debugStep = "S5";
    		lastErrorMessage = ex.getMessage();    		
    		return flagOnErrorValue;
    	}
  		return convertPulseLengthToInches(System.nanoTime() - startEcho);
    }

    double convertPulseLengthToInches(long echoTime) {
    	if(echoTime == 38000) return flagOnErrorValue;
		return 1.0*(echoTime)/148000;
    }
    
    double convertPulseLengthToCM(long echoTime) {
		return 1.0*(echoTime)/148000; // wrong time for centimeters
    }
    
    double checkDistance() {  	
    	if(sensorEnabled) {
      		return takeDistanceReading();
    	}
    	return flagOnErrorValue;
    }


    public double smartInitialize() {
    	if(!sensorEnabled) return flagOnErrorValue;
    	double distance = checkDistance();
//    	for(int i = 0; i < feedbackLength; i++) {
//			distanceValues[i] = distance;
 //   	}
    	
    	smartInitialized = true;
    	curSmartAverage = distance;
    	lastRawReading = distance;
    	return distance;
    }

    
    public double getDistance(boolean debugStatus) {
    	double rTolerance = 3.0;  // max change allowed from previous reading
    	if(!sensorEnabled) return flagOnErrorValue;
    	if(!smartInitialized) smartInitialize();

    	double distance = checkDistance();

    	if(distance == flagOnErrorValue) return curSmartAverage;

//    	if(debugStatus) SmartDashboard.putNumber("DistanceRaw",distance);
		if(Math.abs(distance - lastRawReading) > rTolerance) {
//			if(debugStatus) SmartDashboard.putString("DistanceSafeStatus","rejecting " + distance);
			lastRawReading = distance;
			return curSmartAverage;
		}
//		if(debugStatus) SmartDashboard.putString("DistanceSafeStatus","accepting " + distance);
		lastRawReading = distance;

		curSmartAverage = distance;
		if(debugStatus) SmartDashboard.putString("DistanceSmart","" + curSmartAverage);
    	return curSmartAverage;
    }

}
