package HF2017Utility;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HFCameraPairSwitchable2018 {
	CameraServer server;
	int curCamera = 0;
	UsbCamera camera1;
	UsbCamera camera2;
	public HFCameraPairSwitchable2018() {
		server = CameraServer.getInstance();
		 camera2 = server.startAutomaticCapture(1);
		camera1 = server.startAutomaticCapture(0);
	

//		camera1 = new UsbCamera("cam0",0);
 //    	camera2 = new UsbCamera("cam1",1);
/*    	CvSink cvSink1 = new CvSink("csCamera1");
    	cvSink1.setSource(camera1);
    	cvSink1.setEnabled(true);

    	CvSink cvSink2 = new CvSink("csCamera2");
    	cvSink2.setSource(camera2);
    	cvSink2.setEnabled(true);

    	setCamera(1);
    	*/
	}
	
	@SuppressWarnings("deprecation")
	public void setCamera(int camIndex) {
		if(camIndex == 1) {
			SmartDashboard.putString("Camera","cam 1");
			NetworkTable.getTable("").putString("CameraSelection", camera1.getName());
		} else {
			SmartDashboard.putString("Camera","cam 2");
			NetworkTable.getTable("").putString("CameraSelection", camera2.getName());

		}
	}
}
