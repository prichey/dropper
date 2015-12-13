int EDGE = 800;
int EDGES = 8;
int GRIDEDGE = EDGE / EDGES;
int[] LITX = {0};
int[] LITY = {0, 0};
int thisX, thisY;
boolean DROPPING = false;
color CURRENTCOLOR;

void setup() {
  size(800, 800);
  drawGrid();
  frameRate(10);
  CURRENTCOLOR = color(255);
}

void draw() {
  for (int y = 0; y < LITY.length; y++) {
    thisY = LITY[y];
    for (int x = 0; x < LITX.length; x++) {
      thisX = LITX[x];
      lightLED(thisX, thisY, CURRENTCOLOR);
    }
  }
  //followMouse();
  drip();
  //flurry();
}

void followMouse() {
  int gridX = getGridX(); // grid x of mouse
  int gridY = getGridY(); // grid y of mouse
    //fill(255); // white
  drawGrid(); // reset grid
  lightLED(gridX, gridY, color(100));
}

int getGridX() {
  return floor(mouseX / GRIDEDGE);
}

int getGridY() {
  return floor(mouseY / GRIDEDGE);
}

void mouseClicked() {
  //int gridX = getGridX();
  LITX[0] = getGridX();
  drop();
  println("click!");
}

void drawGrid() {
  for (int y = 0; y < EDGES; y++) {
    for (int x = 0; x < EDGES; x++) { 
      lightLED(x, y, color(255));
    }
  }
}

void lightLED(int x, int y, color c) {
  fill(c);
  ellipse(x * GRIDEDGE + GRIDEDGE / 2, y * GRIDEDGE + GRIDEDGE / 2, GRIDEDGE / 4, GRIDEDGE / 4);
}

void drop1(int col) {
  for (int y = 0; y < EDGES; y++) {
    println("lighting", col, y);
    for (int i = 0; i < 10000; i++) {
      lightLED(col, y, color(random(0, 255), random(0, 255), random(0, 255))); // fill in corresponding LED
    }
  }
}

void drop2(int col) {
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

void drop() {
  DROPPING = true;
}

void drip() {
  
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
   LITX[0] = int(random(0, 8));
   DROPPING = true;
  }
}

// why doesn't this work?
void flurry() {
  LITX[0] = int(random(0, 9));
  LITY[0] = int(random(0, 9)); 
}