import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Robo - a robot by (your name here)
 */
public class TheCanadian extends Robot
{
 /**
  * run: Robo's default behavior
  */
 int shootCount = 0;
 public void run() {
  // Initialization of the robot should be put here

  // After trying out your robot, try uncommenting the import at the top,
  // and the next line:
  setColors(Color.red,Color.white,Color.red); // body,gun,radar

  // Robot main loop
  turnGunRight(Double.POSITIVE_INFINITY);
  do
  {
  turnGunRight(Double.POSITIVE_INFINITY);
  scan();
  ahead(((Math.random()*100) + 100) * (Math.pow(-1, (int)(Math.random() * 100))));
  ahead(((Math.random()*100) + 100) * (Math.pow(-1, (int)(Math.random() * 100))));
  //turnGunRight(Double.POSITIVE_INFINITY);
  //scan();
  }
  while(true);
   // Replace the next 4 lines with any behavior you would like
 }

 /**
  * onScannedRobot: What to do when you see another robot
  */
 public void onScannedRobot(ScannedRobotEvent scanner) {
  // Replace the next line with any behavior you would like
   if(shootCount < 5)
   {
     double angleToEnemy = getHeading() + scanner.getBearing();
     double gunTurn = angleToEnemy - getGunHeading();
     turnGunRight(gunTurn);
     fire(2);
     shootCount++;
   }
  
  else if(shootCount >= 5)
  {
    int additional = 0;
    
    if(getHeading() < (getHeading() + scanner.getBearing()))
    {
      additional = -20;
    }
    else
    {
      additional = 20;
    }
    
    double angleToEnemy = getHeading() + scanner.getBearing() + additional;
    double gunTurn = angleToEnemy - getGunHeading();
    turnGunRight(gunTurn);
    fire(3);
  }
 }
 public void onHitByBullet(HitByBulletEvent hit)
 {
  back(100);
 }
}