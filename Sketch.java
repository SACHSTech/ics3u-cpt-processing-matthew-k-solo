import processing.core.PApplet;
import processing.core.PVector;

public class Sketch extends PApplet {
	
  PVector ballCoords;
  PVector velocity;

  //number of levels that will be laoded in a game, if the game is in progress or should be in menu, and the score and stroke count
  int holeCount = 0;
  int holeLimit = 3; 
  boolean gameInProgress = false;
  boolean ballMoving = false;
  boolean levelComplete = true;
  boolean scoreScreen = false;
  int score = 0;
  int count = 0;
  int par;
  int timer = 180;

  
  //random box, sand, and water variables.

  float box1X;
  float box1Y;
  float box1Width;
  float box1Height;
  float box2X;
  float box2Y;
  float box2Width;
  float box2Height;
  float holeX;

  float trapX;
  float trapY;
  float trapWidth;
  float trapHeight;

  int trapType;
  boolean sandTrap = false;
  boolean waterTrap = false;

  //ball faces
  float ballFront;
  float ballDown;
  float ballRight;
  float ballLeft;

  //balls size
  float ballSize = 20;

  //track every position the ball has been in
  int indexPosition = 0;
  float[] pastBallCoordX = new float[10];
  float[] pastBallCoordY = new float[10];

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
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

    if(gameInProgress == false){
      //title screen - choose holeLimit.
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
    if(gameInProgress == true){
      if(holeCount < holeLimit){

        //make level - randomize boxes
        if(levelComplete){
          randomizeLevel();
          trapType = coinFlip();
          par = randomPar();
          levelComplete = false;
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
        ellipse(holeX, 50, 40, 40);
        
        // sandtrap or water ( do the physics first)
        noStroke();
        if(trapType == 0){
          sandTrap = true;
          fill(234,204,167);
        }
        else{
          waterTrap = true;
          fill(128,213,242);
        }
        ellipse(trapX, trapY, trapWidth, trapHeight);

        // blocks (make two blocks, random size, needs to have one gap twice the size of the ball to go through) w 600 h 800
        strokeWeight(2);
        stroke(0);
        fill(124,60,30);
        rect(box1X, box1Y, box1Width, box1Height);
        rect(box2X, box2Y, box2Width, box2Height);
  
        

        // golf ball
        fill(255);
        ellipse(ballCoords.x, ballCoords.y, ballSize, ballSize);


        // show what par is
        if(timer > 0){
          textSize(50);
          text("par is " + par, 220, 100);
          timer -= 1;
        }

        //stroke count
        fill(255);
        textSize(60);
        text(count, 520, 50);
        textSize(30);
        text("strokes", 500, 70);

        //total score bc of par
        textSize(60);
        text(score, 20, 50);
        textSize(30);
        text("total", 10, 70);
        
        fill(0);
        stroke(3); 
        if(ballMoving != true && mousePressed){
          line(ballCoords.x, ballCoords.y, mouseX, mouseY);
        } 
        
        
        //side of the ball variables for collission
        ballFront = ballCoords.y - 10;
        ballDown = ballCoords.y + 10;
        ballRight = ballCoords.x + 10;
        ballLeft =  ballCoords.x - 10;

        //if ball collides with walls
          if(ballFront < 20){
            velocity.y *= -1;
          }
          if(ballDown > 880){
            velocity.y *= -1;
          }
          if(ballRight > 580){
            velocity.x *= -1;
          }
          if(ballLeft < 20){
            velocity.x *= -1;
          }

        //if ball collides with box1
        /*
        if(ballFront < box1X + box1Width && ballFront > box1X){
          velocity.y *= -1;
        }
        if(ballDown > 899){
          velocity.y *= -1;
        }
        if(ballRight > 599){
          velocity.x *= -1;
        }
        if(ballLeft < 1){
          velocity.x *= -1;
        }
        */

        /* box1X = random(0, 200);
  box1Y = random(100, 400);
  box1Width = random(200, 300);
  box1Height = random(200, 300);
  */

        //if ball collides with box2

        //if ball collides with sand
        if(trapType == 0 && ballCoords.x > trapX - (trapWidth / 2) && ballCoords.x < trapX + (trapWidth / 2) && ballCoords.y > trapY - (trapHeight / 2) && ballCoords.y < trapY + (trapHeight / 2)){
          
          if(velocity.x != 0 && velocity.y != 0){
            velocity.x -= round(random(2));
            velocity.y -= round(random(2));
          }

        }

        //if ball collides with water
        
        if(trapType == 1 && ballCoords.x > trapX - (trapWidth / 2) && ballCoords.x < trapX + (trapWidth / 2) && ballCoords.y > trapY - (trapHeight / 2) && ballCoords.y < trapY + (trapHeight / 2)){
          
          ballCoords = new PVector(300, 750);
          velocity.x = 0;
          velocity.y = 0;
          count += 1;
        }
        

        //if ball collides with hole
          if(ballCoords.x > holeX - 20 && ballCoords.x < holeX + 20 && ballCoords.y < 50 + 20 && ballCoords.y > 50 - 20 && ballSize > 0){
            ballCoords.x = holeX;
            ballCoords.y = 50;
            ballSize -= 1;
          }
        
    
        //controls where the ball goes
          ballCoords.sub(velocity);
          if(velocity.x > 0){
            ballMoving = true;
            velocity.x -= 1;
          }
          if(velocity.y > 0){
            ballMoving = true;
            velocity.y -= 1;
          }
          if(velocity.x < 0){
            ballMoving = true;
            velocity.x += 1;
          }
          if(velocity.y < 0){
            ballMoving = true;
            velocity.y += 1;
          }

          if(velocity.x == 0 && velocity.y == 0){
            ballMoving = false;
          }


        //calculate score for par

        //ball in hole - reset score - next level - make levelComplete = true again - add 1 to holeCount - make ballSize 20 again
        if(ballCoords.x > holeX - 20 && ballCoords.x < holeX + 20 && ballCoords.y < 50 + 20 && ballCoords.y > 50 - 20 && ballSize == 0){
          ballCoords = new PVector(300, 750);
          velocity = new PVector(0,0);

          //calculating the par score total thing
          score += (par - count) * -1;

          count = 0;
          holeCount += 1;
          ballSize = 20;
          sandTrap = false;
          waterTrap = false;
          levelComplete = true;
          timer = 180;
          
        }
        
      }
      else{
        scoreScreen = true;
        fill(0);
        textSize(75);
        text("Game Finished!", 50, 100 );
        text("Score " + score, 140, 175);
        fill(169,208,153);
        rect(100, 350, 400, 100);
        fill(0);
        textSize(50);
        text("Back To Menu", 155, 410);
        
      }

      // score screen with menu button to go back to menu
    }
  }


  public void mouseClicked(){

    if(gameInProgress != true){

      if(mouseX >= 100 && mouseX <= 500 && mouseY > 200 && mouseY < 300){
        gameInProgress = true;
        holeLimit = 3;
      }
      if(mouseX >= 100 && mouseX <= 500 && mouseY > 350 && mouseY < 450){
        gameInProgress = true;
        holeLimit = 9;
      }
      if(mouseX >= 100 && mouseX <= 500 && mouseY > 500 && mouseY < 600){
        gameInProgress = true;
        holeLimit = 18;
      }

    }

    if(scoreScreen){
      if(mouseX >= 100 && mouseX <= 500 && mouseY > 350 && mouseY < 450){
        gameInProgress = false;
        score = 0;
        scoreScreen = false;
        holeCount = 0;

        ballCoords = new PVector(300, 750);
        velocity = new PVector(0,0);
        ballSize = 20;
        timer = 180;
        sandTrap = false;
        waterTrap = false;
      }
    }

  }

  public void mouseReleased(){
    if(gameInProgress == true && ballMoving != true){
      velocity.x = round((mouseX - ballCoords.x) / 5);
      velocity.y = round((mouseY - ballCoords.y) / 5);
      count += 1;
    }
  }

  public int coinFlip(){
    return round(random(1));
  }

  public int randomPar(){
    return round(random(2, 4));
  }

  public void randomizeLevel(){
  box1X = random(0, 200);
  box1Y = random(100, 400);
  box1Width = random(200, 300);
  box1Height = random(200, 300);
  box2X = random(300, 400);
  box2Y = random(200, 400);
  box2Width = random(200, 300);
  box2Height = random(200, 300);
  holeX = random(50, 550);

  trapX = random(0, 400);
  trapY = random(100, 400);
  trapWidth = random(50, 150);
  trapHeight = random(50, 150);
  }

}