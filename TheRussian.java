import robocode.*;
import java.awt.Color;
/**
 * Robo - a robot by (your name here)
 */
public class TheRussian extends Robot
{
 /**
  * run: Robo's default behavior
  */
 int radar = 0;
 public void run() {
  // Initialization of the robot should be put here

  // After trying out your robot, try uncommenting the import at the top,
  // and the next line:
  radar = 0;
  setColors(Color.blue,Color.red,Color.white); // body,gun,radar
  turnLeft(getHeading() % 90);
  radar = 1;
  ahead(Double.POSITIVE_INFINITY);
  
  // Robot main loop
  
  do
  {
  turnGunRight(Double.POSITIVE_INFINITY);
  scan();

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
  
  int shootCount = 0;
  double angleToEnemy = getHeading() + scanner.getBearing(); //enemy angle from origin
  double gunTurn = angleToEnemy - getGunHeading(); 
  turnGunRight(gunTurn);
  fire(3);
  
  shootCount++;
  
  if(shootCount >= 5)
  {
    int additional = 0;
    
    if(getHeading() < scanner.getBearing())
    {
      additional = 5;
    }
    else
    {
      additional = -5;
    }
    
    angleToEnemy = getHeading() + scanner.getBearing() + additional;
    gunTurn = angleToEnemy - getGunHeading();
    turnGunRight(gunTurn);
    fire(3);
  }
  
  ahead(Double.POSITIVE_INFINITY);
  
  
 }
 //public void onHitByBullet(HitByBulletEvent hit)
 //{
  //back(100);
 //}
 public void onHitWall(HitWallEvent wall)
 {
   if(radar == 1)
   {
     turnRight(90);
   }
   else
   {
     stop();
   }
 }
}