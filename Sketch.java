import processing.core.PApplet;
import processing.core.PVector;

public class Sketch extends PApplet {
	
  PVector ballCoords;
  PVector velocity;

  //number of levels that will be laoded in a game, if the game is in progress or should be in menu, and the score and stroke count
  boolean isGameInProgress = false;
  boolean isBallMoving = false;
  boolean isLevelComplete = true;
  boolean onScoreScreen = false;
  int intHoleCount = 0;
  int intHoleLimit = 3; 
  int intScore = 0;
  int intCount = 0;
  int intPar;
  int intTimer = 180;
  
  //random box, sand, and water variables.

  float fltBox1X;
  float fltBox1Y;
  float fltBox1Width;
  float fltBox1Height;
  float fltBox2X;
  float fltBox2Y;
  float fltBox2Width;
  float fltBox2Height;

  float fltHoleX;

  float fltTrapX;
  float fltTrapY;
  float fltTrapWidth;
  float fltTrapHeight;

  int intTrapType;

  boolean isSandTrap = false;
  boolean isWaterTrap = false;

  //Golf balls size
  float fltBallSize = 20;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    size(600, 900);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(215,255,194);
    ballCoords = new PVector(300, 750);
    velocity = new PVector(0,0);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(215,255,194);
    
    //grass pattern
    for(int i = 0; i < width; i += (width / 5)){
      for(int j = 0; j < height; j += (height / 10)){
        noStroke();
        fill(60,106,73);
        rect(i, j, width / 10, height / 20);
      }

    }

    for(int i = width / 10; i < width; i += (width / 5)){
      for(int j = height / 20; j < height; j += (height / 10)){
        noStroke();
        fill(60,106,73);
        rect(i, j, width / 10, height / 20);
      }

    }

    //title screen - choose holeLimit.
    if(isGameInProgress == false){
      fill(0);
      textSize(100);
      text("Mini Golf!", 100, 100 );
      textSize(50);
      text("Created By", 175, 700 );
      text("John Matthew Kassapian", 50, 800 );
      
      fill(169,208,153);
      rect(100, 200, 400, 100);
      rect(100, 350, 400, 100);
      rect(100, 500, 400, 100);

      fill(0);
      textSize(75);
      text("3 Holes", 175, 275);
      text("9 Holes", 175, 425);
      text("18 Holes", 175, 575);
    }


    //game IN PROGRESS
    if(isGameInProgress == true){
      if(intHoleCount < intHoleLimit){

        //make level - randomize boxes
        if(isLevelComplete){
          randomizeLevel();
          intTrapType = coinFlip();
          intPar = randomPar();
          isLevelComplete = false;
        }
        
        // border 
        noStroke();
        fill(125);
        rect(0, 0, 20, height);
        rect(0, 0, width, 20);
        rect(580, 0, 20, height);
        rect(0, 880, width, 20);
        
        // hole (make a random hold somewhere near the top)
        strokeWeight(2);
        stroke(0);
        fill(100);
        ellipse(fltHoleX, 50, 40, 40);
        
        // sandtrap or water ( do the physics first)
        noStroke();
        if(intTrapType == 0){
          isSandTrap = true;
          fill(234,204,167);
        }
        else{
          isWaterTrap = true;
          fill(128,213,242);
        }
        ellipse(fltTrapX, fltTrapY, fltTrapWidth, fltTrapHeight);

        // blocks (make two blocks, random size, needs to have one gap twice the size of the ball to go through) w 600 h 800
        strokeWeight(2);
        stroke(0);
        fill(124,60,30);
        rect(fltBox1X, fltBox1Y, fltBox1Width, fltBox1Height);
        rect(fltBox2X, fltBox2Y, fltBox2Width, fltBox2Height);
  
        

        // golf ball
        fill(255);
        ellipse(ballCoords.x, ballCoords.y, fltBallSize, fltBallSize);


        // show what par is
        if(intTimer > 0){
          textSize(50);
          text("par is " + intPar, 220, 100);
          intTimer -= 1;
        }

        //stroke count
        fill(255);
        textSize(60);
        text(intCount, 520, 50);
        textSize(30);
        text("strokes", 500, 70);

        //total score bc of par
        textSize(60);
        text(intScore, 20, 50);
        textSize(30);
        text("total", 10, 70);
        
        fill(0);
        stroke(3); 
        if(isBallMoving != true && mousePressed){
          line(ballCoords.x, ballCoords.y, mouseX, mouseY);
        } 
        

        //if ball collides with walls
          if(ballCoords.y < 20 + 10){
            velocity.y *= -1;
          }
          if(ballCoords.y > 880 - 10){
            velocity.y *= -1;
          }
          if(ballCoords.x > 580 - 10){
            velocity.x *= -1;
          }
          if(ballCoords.x < 20 + 10){
            velocity.x *= -1;
          }

        //if ball collides with box1
        if(ballCoords.y < fltBox1Y + fltBox1Height + 10 && ballCoords.y > fltBox1Y - 10 && ballCoords.x > fltBox1X && ballCoords.x < fltBox1X + fltBox1Width){
          velocity.y *= -1;
        }
        if(ballCoords.x > fltBox1X - 10 && ballCoords.x < fltBox1X + fltBox1Width + 10 && ballCoords.y >  fltBox1Y && ballCoords.y < fltBox1Y + fltBox1Height){
          velocity.x *= -1;
        }
        
        //if ball collides with box2
        if(ballCoords.y < fltBox2Y + fltBox2Height + 10 && ballCoords.y > fltBox2Y - 10 && ballCoords.x > fltBox2X && ballCoords.x < fltBox2X + fltBox2Width){
          velocity.y *= -1;
        }
        if(ballCoords.x > fltBox2X - 10 && ballCoords.x < fltBox2X + fltBox2Width + 10 && ballCoords.y >  fltBox2Y && ballCoords.y < fltBox2Y + fltBox2Height){
          velocity.x *= -1;
        }

        //if ball collides with sand
        if(intTrapType == 0 && ballCoords.x > fltTrapX - (fltTrapWidth / 2) && ballCoords.x < fltTrapX + (fltTrapWidth / 2) && ballCoords.y > fltTrapY - (fltTrapHeight / 2) && ballCoords.y < fltTrapY + (fltTrapHeight / 2)){
          
          if(velocity.x != 0 || velocity.y != 0){
            int randomSpeedChange = round(random(30,45));
            
            if(velocity.x > 50){
              isBallMoving = true;
              velocity.x -= randomSpeedChange;
            }
            if(velocity.y > 50){
              isBallMoving = true;
              velocity.y -= randomSpeedChange;
            }
            if(velocity.x < -50){
              isBallMoving = true;
              velocity.x += randomSpeedChange;
            }
            if(velocity.y < -50){
              isBallMoving = true;
              velocity.y += randomSpeedChange;
            }
          }

        }

        //if ball collides with water
        
        if(intTrapType == 1 && ballCoords.x > fltTrapX - (fltTrapWidth / 2) && ballCoords.x < fltTrapX + (fltTrapWidth / 2) && ballCoords.y > fltTrapY - (fltTrapHeight / 2) && ballCoords.y < fltTrapY + (fltTrapHeight / 2)){
          
          ballCoords = new PVector(300, 750);
          velocity.x = 0;
          velocity.y = 0;
          intCount += 1;
        }
        

        //if ball collides with hole
          if(ballCoords.x > fltHoleX - 20 && ballCoords.x < fltHoleX + 20 && ballCoords.y < 50 + 20 && ballCoords.y > 50 - 20 && fltBallSize > 0){
            ballCoords.x = fltHoleX;
            ballCoords.y = 50;
            fltBallSize -= 1;
          }
        
    
        //controls where the ball goes
          ballCoords.sub(velocity);
          if(velocity.x > 0){
            isBallMoving = true;
            velocity.x -= 1;
          }
          if(velocity.y > 0){
            isBallMoving = true;
            velocity.y -= 1;
          }
          if(velocity.x < 0){
            isBallMoving = true;
            velocity.x += 1;
          }
          if(velocity.y < 0){
            isBallMoving = true;
            velocity.y += 1;
          }

          //changes isBallMoving to false when both velocity variables = 0
          if(velocity.x == 0 && velocity.y == 0){
            isBallMoving = false;
          }

        //ball in hole - reset score - next level - make levelComplete = true again - add 1 to holeCount - make ballSize 20 again
        if(ballCoords.x > fltHoleX - 20 && ballCoords.x < fltHoleX + 20 && ballCoords.y < 50 + 20 && ballCoords.y > 50 - 20 && fltBallSize == 0){
          ballCoords = new PVector(300, 750);
          velocity = new PVector(0,0);
          
          //calculating the par score total thing
          intScore += (intPar - intCount) * -1;

          intCount = 0;
          intHoleCount += 1;
          fltBallSize = 20;
          isSandTrap = false;
          isWaterTrap = false;
          isLevelComplete = true;
          intTimer = 180;
          
        }
        
      }
      else{
        // score screen with menu button to go back to menu
        onScoreScreen = true;
        fill(0);
        textSize(75);
        text("Game Finished!", 50, 100 );
        text("Score " + intScore, 140, 175);
        fill(169,208,153);
        rect(100, 350, 400, 100);
        fill(0);
        textSize(50);
        text("Back To Menu", 155, 410);
        
      }
    }
  }

  /*
  * Description: when the mouse is clicked. In this event there is only code for when the player is on the main menu or score screen. This is so
  * they can select either the holeLimit when they're on the main menu, or to go back to the main menu when on the score screen
  * @param: void (uses global variables)
  */
  public void mouseClicked(){
    //main menu
    if(isGameInProgress != true){

      if(mouseX >= 100 && mouseX <= 500 && mouseY > 200 && mouseY < 300){
        isGameInProgress = true;
        intHoleLimit = 3;
      }
      if(mouseX >= 100 && mouseX <= 500 && mouseY > 350 && mouseY < 450){
        isGameInProgress = true;
        intHoleLimit = 9;
      }
      if(mouseX >= 100 && mouseX <= 500 && mouseY > 500 && mouseY < 600){
        isGameInProgress = true;
        intHoleLimit = 18;
      }

    }

    //score screen
    if(onScoreScreen){
      if(mouseX >= 100 && mouseX <= 500 && mouseY > 350 && mouseY < 450){
        isGameInProgress = false;
        intScore = 0;
        onScoreScreen = false;
        intHoleCount = 0;

        ballCoords = new PVector(300, 750);
        velocity = new PVector(0,0);
        fltBallSize = 20;
        intTimer = 180;
        isSandTrap = false;
        isWaterTrap = false;
      }
    }

  }

  /*
  * Description: when the mouse is released while a game is in progress, and the ball isn't moving, a velocity PVector will be modified based on 
  * the distance between the golf ball and the players mouse. This allows for the player to move the ball around.
  * @param: void (uses global variables)
  */

  public void mouseReleased(){
    if(isGameInProgress == true && isBallMoving != true){
      velocity.x = round((mouseX - ballCoords.x) / 5);
      velocity.y = round((mouseY - ballCoords.y) / 5);
      intCount += 1;
    }
  }

  /*
  * Description: method that simulates a coin flip (50/50) odds
  * @param: none. returns an integer.
  */

  public int coinFlip(){
    return round(random(1));
  }

  /*
  * Description: method that choses a random whole number from 2-4 to act as the par for each hole.
  * @param: none. returns an integer.
  */

  public int randomPar(){
    return round(random(2, 4));
  }

  /*
  * Description: method that generates random values for the location of the two boxes, the sand/water trap, and the x value for the hole.
  * @param: returns nothing needs nothing (uses global variables).
  */

  public void randomizeLevel(){
  fltBox1X = random(0, 200);
  fltBox1Y = random(100, 400);
  fltBox1Width = random(200, 300);
  fltBox1Height = random(200, 300);
  fltBox2X = random(300, 400);
  fltBox2Y = random(200, 400);
  fltBox2Width = random(200, 300);
  fltBox2Height = random(200, 300);
  fltHoleX = random(50, 550);

  fltTrapX = random(0, 400);
  fltTrapY = random(100, 400);
  fltTrapWidth = random(50, 150);
  fltTrapHeight = random(50, 150);
  }

}