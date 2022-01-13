import processing.core.PApplet;

public class Sketch extends PApplet {
	
  float[] speed = new float[2];
  float[] ballCoords = new float[2];	
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
    ballCoords[0] = width / 2;	
    ballCoords[1] = (float) (height / 1.25);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	 
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

  }

  public void mouseDragged(){
    
  }
  
}