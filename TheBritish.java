//IMPORT PACKAGES
import robocode.*; //Robocode
import java.awt.Color; //Colour

//*Credit to Robocode*
//The idea of sticking to the walls was inspired by their robot sample.Walls

//CLASS
public class TheBritish extends Robot
{
  //MAIN METHOD
  public void run() 
  {
    setColors(Color.blue,Color.red,Color.white); //Colouring: body, gun, radar
    setAdjustGunForRobotTurn(true); //Maintain gun heading when tank is turning
    turnLeft(getHeading() % 90); //Turn so that it is aligned either parallel or perpendicular to the walls
    ahead(Double.POSITIVE_INFINITY); //Move forward (until wall is hit)
    
    //DO WHILE LOOP
    do
    {
      turnRadarRight(Double.POSITIVE_INFINITY); //Turn radar infinitely
      scan(); //Scan for enemies
    }
    while(true);
  }
  
  //SCANNING
  public void onScannedRobot(ScannedRobotEvent scanner)
  {
    turnGunRight((getHeading() + scanner.getBearing()) - getGunHeading()); //Turn gun to target
    
    //Max speed of tank is 8 turns/tick
    
    /**************************/
    //USE ONLY IN LARGE BATTLES
    fire(3);
    /**************************/
    
    /**************************
     //USE ONLY IN SMALL BATTLES
      
     //FIRING
      
     //*Credit to Anthony Maocheia-Ricci and Liliana Austin*
     //Firepower is dependent on the velocity of opponent
     double velocity = scanner.getVelocity();
     if(velocity <= 3) //If slow...
     {
      fire(3); //Fire full blast
     }
     else if(velocity <= 6 && velocity > 3) //If medium...
     {
      fire(2);
     }
     else //If going crazy...
     {
      fire(1);
     }
     **************************/
    
    ahead(200); //Move forward
    
  }
  
  //HITTING A WALL
  public void onHitWall(HitWallEvent wall)
  {
    setAdjustGunForRobotTurn(true); //Maintain gun heading when tank is turning
    turnRight(90); //Turn 90 degrees
  }
}