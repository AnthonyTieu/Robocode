//SPECIFICALLY USED TO COUNTER RAMFIRE

//IMPORT PACKAGES
import robocode.*; //Robocode
import java.awt.Color; //Colouring tank
import java.util.*; //For arraylists

//CLASS
public class Husarii extends Robot
{
  //ASSIGNMENTS STATEMENTS
  ArrayList <String> names = new ArrayList <String>(); //Store enemy names
  ArrayList <Double> energies = new ArrayList <Double>(); //Store enemy energy
  int direction = 1; /*Variable used to denote direction of motion, moving forward and backwards
                      *should be in context with each situation and not hardcoded*/
 
 //MAIN METHOD
 public void run() {

  setColors(Color.red,Color.white,Color.white); //Setting colours: body, gun, radar
  
  //WHILE LOOP
  while(true)
  {
    turnRadarRight(Double.POSITIVE_INFINITY); //Spin radar infinitely
    scan(); //Scan for enemy bots
  }
 }

 //SCANNED ROBOT
 public void onScannedRobot(ScannedRobotEvent s) 
 {
   String name = s.getName(); //Get enemy name
   double energy = s.getEnergy(); //Get enemy energy
   
   //AIMING
   turnGunRight((getHeading() + s.getBearing()) - getGunHeading()); //Turn gun towards the target
   
   //FIRING
   
   //*Credit to Anthony Maocheia-Ricci and Liliana Austin*
   //Firepower is dependent on the velocity of the opponent
   
   //Max speed of tank is 8 turns/tick
   double velocity = s.getVelocity(); //Get speed of enemy...
   
   if(velocity <= 3 || s.getDistance() < 100) //If slow or close...
   {
     fire(3); //Blast them full force
   }
   else if(velocity <= 6 && velocity > 3) //If medium...
   {
     fire(2); //Fire
   }
   else //If going balls to the wall...
   {
     fire(1); //Fire conservatively
   }
   
   setAdjustGunForRobotTurn(true); //Maintain gun heading when tank is turning
   turnRight((getHeading() + s.getBearing()) - 90 - getHeading()); /*Turning tank perpendicular to the enemy's bearing
                                                                    *this is to avoid charging toward the enemy's
                                                                    *bullets*/
   
   //EVASIVE MANEUVERS
   //MEMORY AND STORAGE
   if(!names.contains(name)) //If this robot was not previously seen...
   {
     names.add(name); //Add its name to the list
     energies.add(energy); //Add its current energy to the list
   }
   else //Otherwise...
   {
     //Compare its current energy with its previous, if it has fired (loss of energy from 0.1 to 3)...
     if((energies.get(names.indexOf(name)) - energy) <= 3 && (energies.get(names.indexOf(name)) - energy) != 0) 
     {
       ahead(200 * direction); //Get out of the bullet's way!
     }
     energies.set(names.indexOf(name), energy); //Update the enemy tank's energy
   }
   
   out.println(names + " " + energies); //Used to verify that the arraylist was working properly
   
   if(s.getDistance() <= 100) //If the enemy is getting to close...
   {
     ahead(200 * direction); //Get the heck out of the tank's way!
   }
 }
 
 //HITTING WALL
 public void onHitWall(HitWallEvent wall)
 {
   direction = direction * (-1); //Change direction
   back(200 * direction); //Move away
 }
}