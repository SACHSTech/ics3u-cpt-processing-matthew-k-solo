import processing.core.PApplet;

public class Sketch extends PApplet {
	
  float[] mouseDrag = new float[4];
  float[] ballCoords = new float[2];	
  float[] speed = new float [2];
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 600);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(215,255,194);
    //setting up where the ball starts
    ballCoords[0] = width / 2;	
    ballCoords[1] = (float) (height / 1.25);
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

    

    // blocks

    // hole

    // golf ball
    strokeWeight(2);
    stroke(0);
    fill(255);
    ellipse(ballCoords[0], ballCoords[1], 20, 20);

    //controls where the ball goes
      ballCoords[0] -= speed[0];
      ballCoords[1] -= speed[1];
      
  }

  //save position when mousePressed
  public void mousePressed(){
    mouseDrag[0] = mouseX;
    mouseDrag[1] = mouseY;
  }
  
  //make line when dragged
  public void mouseDragged(){
    line(mouseDrag[0], mouseDrag[1], mouseX, mouseY);
  }

  //save position when mouse released
  public void mouseReleased(){
    mouseDrag[2] = mouseX;
    mouseDrag[3] = mouseY;

      //create speed variable based on how far it was dragged / also creates the direction to go towards
    speed[0] = mouseDrag[2] - mouseDrag[0];
    speed[1] = mouseDrag[3] - mouseDrag[1];
    
    stroke(0);
  }
}