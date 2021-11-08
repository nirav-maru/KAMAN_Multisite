package com.Utilities;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.testcases.driverscripts.Execute_MainScript;



public class TakeFullScreenCapture {


	String scr_output_dir;
	Path scr_screenshotfile;
	Robot robot;

	public void Screenshots(WebDriver driver,String path,String dirname) throws Throwable {

		robot = new Robot();
		if (scr_screenshotfile==null){
			CreateScreenshotDir(path,dirname);
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			String formattedDate = dateFormat.format(date);
			robot.keyPress(KeyEvent.VK_PRINTSCREEN);
			robot.keyRelease(KeyEvent.VK_PRINTSCREEN);
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			File file =  new File(scr_screenshotfile+"/"+formattedDate +".jpg");
			ImageIO.write(image, "jpg", file);
		}
		else{
			UpdateScreenshotDir(scr_screenshotfile, driver);
		}

	}

	public  Path CreateScreenshotDir(String path,String dirname) throws IOException{

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String formattedDate = dateFormat.format(date);
		scr_output_dir=path+dirname+"/All_screenshots";


		scr_screenshotfile = Paths.get(scr_output_dir);
		Files.createDirectories(scr_screenshotfile);

		return scr_screenshotfile;

	}
	public void UpdateScreenshotDir(Path file,WebDriver driver) throws IOException, HeadlessException, AWTException{

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String formattedDate = dateFormat.format(date);
		robot.keyPress(KeyEvent.VK_PRINTSCREEN);
		robot.keyRelease(KeyEvent.VK_PRINTSCREEN);
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		File file1 =  new File(file+"/"+formattedDate +".jpg");
		ImageIO.write(image, "jpg", file1);
	}
}
