package HF2017Utility;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;

//import com.ni.vision.NIVision;
//import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HFCameraPairSwitchable {
	public boolean cameraEnabled = true;
	public static int CAMERA_ONE=0;
	public static int CAMERA_TWO=1;
	
	private String cam1;
	private String cam2;

	UsbCamera camera1;
	UsbCamera camera2;
	
	
	public int activeCamera = CAMERA_TWO;

	CameraServer server;
//	Image frame;
	int sessionFront;
	int sessionBack;
	int curSession;
	
	CvSink camSink;
	CvSource outputStream;
	
	public HFCameraPairSwitchable(String camera1,String camera2, boolean startEnabled) {
		cameraEnabled = startEnabled;
		cam1 = camera1;
		cam2 = camera2;
		if(cameraEnabled) {
			init();
		}
	}
	public void init() {
        server = CameraServer.getInstance();
    //    server.setQuality(40);
        /*  server.setSize(200);
     		server.startAutomaticCapture("cam0")
         */  

		
		if(!cameraEnabled) return;
		SmartDashboard.putString("Camera","starting");

		try {
			camera1 = new UsbCamera(cam1,0);

			camera2 = new UsbCamera(cam2,1);
			server.addCamera(camera1);
			server.addCamera(camera2);
			server.startAutomaticCapture();
		//	server.startAutomaticCapture(1);
//			outputStream = CameraServer.getInstance().putVideo("Video1", 640, 480);
/*			
			frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
			SmartDashboard.putString("camerab","INIT Failed 1");
			sessionFront = NIVision.IMAQdxOpenCamera(cam1, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			SmartDashboard.putString("camerab","INIT Failed 2");
			sessionBack = NIVision.IMAQdxOpenCamera(cam2, NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			curSession = sessionFront;
			NIVision.IMAQdxConfigureGrab(curSession);
			
			
			//simpler camera
//			server.startAutomaticCapture(cam1);
//			server.startAutomaticCapture(cam2);
			
			SmartDashboard.putString("camera","initialized");
			SmartDashboard.putString("camerab","init success");
*/
		} catch(Exception ex) {
			SmartDashboard.putString("camera","INIT FAILED");
			cameraEnabled = false;
		}
	}
	
	public void setEnabled(boolean newValue) {
		cameraEnabled = newValue;
	}
	
	public void displayImages() {
		if(activeCamera == CAMERA_ONE) {
			camSink = server.getVideo(camera1); 
		} else {
			camSink = server.getVideo(camera2); 			
		}
		Mat source = new Mat();
		camSink.grabFrame(source);

		Mat output = new Mat();
		Imgproc.cvtColor(source,output,Imgproc.COLOR_BGR2RGB);
		outputStream.putFrame(output);
//		outputStream.putFrame(source);

		
		/*		if(!cameraEnabled) {
			SmartDashboard.putString("Camera", "disabled can't display");			
			return;
		}
		SmartDashboard.putString("Display", "ThatString");
		try {
			NIVision.IMAQdxGrab(curSession, frame, 1);
			server.setImage(frame);
			SmartDashboard.putString("Camera", "imageupdated");			
//	      	CameraServer.getInstance().setImage(frame);
		} catch (Exception e) {
			SmartDashboard.putString("Camera", "error");			
		}
		SmartDashboard.putString("Display", "ThisString");
*/
	}

	public void toggleCamera() {
//		camera1.
/*		if(!cameraEnabled) return;
		NIVision.IMAQdxStopAcquisition(curSession);
		if(activeCamera == CAMERA_ONE) {
			activeCamera = CAMERA_TWO;
			curSession = sessionBack;
			SmartDashboard.putString("Camera", "2");
		}
		else {
			activeCamera = CAMERA_ONE;
			curSession = sessionFront;
			SmartDashboard.putString("Camera", "0");
		}
		NIVision.IMAQdxConfigureGrab(curSession);		
		*/
	}
	
    public void setCamera(int newCamera) {
    	if(!cameraEnabled) return;
//    	NIVision.IMAQdxStopAcquisition(curSession);
    	if(newCamera == CAMERA_TWO) {
    		activeCamera = CAMERA_TWO;
    		curSession = sessionBack;
    		SmartDashboard.putString("Camera", "2");
    	}
    	else {
    		activeCamera = CAMERA_ONE;
    		curSession = sessionFront;
    		SmartDashboard.putString("Camera", "0");
    	}
//    	NIVision.IMAQdxConfigureGrab(curSession);
    		
    }


}
