import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class dropper extends PApplet {

int EDGE = 800;
int EDGES = 8;
int GRIDEDGE = EDGE / EDGES;
int[] LITX = {0};
int[] LITY = {0, 0};
int thisX, thisY;
boolean DROPPING = false;
int CURRENTCOLOR;

public void setup() {
  
  drawGrid();
  //noStroke();
  frameRate(10);
  CURRENTCOLOR = color(255);
}

public void draw() {
  //followMouse();
  //mouseClicked();
  
  for (int y = 0; y < LITY.length; y++) {
    thisY = LITY[y];
    for (int x = 0; x < LITX.length; x++) {
      thisX = LITX[x];
      lightLED(thisX, thisY, CURRENTCOLOR);
    }
  }
  
  if (DROPPING) {
    CURRENTCOLOR = color(255);
    LITY[1] = LITY[0];
    CURRENTCOLOR = color(random(0, 255), random(0, 255), random(0, 255));
    LITY[0]++;
    
    if (LITY[0] > 8) {
      DROPPING = false;
      LITY[0] = 0;
      CURRENTCOLOR = color(255);
      drawGrid();
      
    }
  } else {
    LITX[0] = PApplet.parseInt(random(0, 8));
    DROPPING = true;
  }
  
  //LITX[0] = int(random(0, 9));
  //LITY[0] = int(random(0, 9));
}

public void followMouse() {
  int gridX = getGridX(); // grid x of mouse
  int gridY = getGridY(); // grid y of mouse
    //fill(255); // white
  drawGrid(); // reset grid
  //fill(100); // gray
  lightLED(gridX, gridY, color(100)); // fill in corresponding LED
}

public int getGridX() {
  return floor(mouseX / GRIDEDGE);
}

public int getGridY() {
  return floor(mouseY / GRIDEDGE);
}

public void mouseClicked() {
  //int gridX = getGridX();
  LITX[0] = getGridX();
  drop();
  println("click!");
}

public void drawGrid() {
  for (int y = 0; y < EDGES; y++) {
    for (int x = 0; x < EDGES; x++) {
      // rect(x * GRIDEDGE, y * GRIDEDGE, GRIDEDGE, GRIDEDGE);  
      lightLED(x, y, color(255));
    }
  }
}

public void lightLED(int x, int y, int c) {
  fill(c);
  ellipse(x * GRIDEDGE + GRIDEDGE / 2, y * GRIDEDGE + GRIDEDGE / 2, GRIDEDGE / 4, GRIDEDGE / 4);
}

public void dropOld(int col) {
  for (int y = 0; y < EDGES; y++) {
    println("lighting", col, y);
    for (int i = 0; i < 10000; i++) {
      lightLED(col, y, color(random(0, 255), random(0, 255), random(0, 255))); // fill in corresponding LED
    }
    
    
    //drawGrid();
    //fill(100); // gray
    //lightLED(col, y, color(100)); // fill in corresponding LED
    ////delay(100);
    //fill(255);
    //lightLED(col, y, color(255)); // fill in corresponding LED
 
  }
}

public void drop1(int col) {
  //int xLen = LITX.length;
  //LITX[xLen] = col;
  drawGrid();
  LITX[0] = col;
  for (int y = 0; y < EDGES; y++) {
   //int yLen = LITY.length;
   //LITY[yLen] = y;
   //delay(500);
   //LITY = shorten(LITY);
   LITY[0] = y;
  }
}

public void drop() {
  DROPPING = true;
}
  public void settings() {  size(800, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "dropper" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
