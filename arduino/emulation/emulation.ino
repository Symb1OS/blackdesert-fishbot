#include <Keyboard.h>
#include <Mouse.h>
#include <MouseTo.h>

const float CORRECTION_FACTOR = 1;


void setup() {
  Serial.begin(9600);
  Serial.setTimeout(10);
  Keyboard.begin();
  Mouse.begin();
  
  MouseTo.setCorrectionFactor(CORRECTION_FACTOR);
}

void pressKey(char key) {
  Keyboard.press(key);
  delay(random(60, 130));
  Keyboard.releaseAll();
}

void pressKey(char first, char second) {
  Keyboard.press(first);
  Keyboard.press(second);
  delay(random(60, 130));
  Keyboard.releaseAll();
}

void moveTo(int x, int y, char button) {
  MouseTo.setTarget(x, y);
  while (MouseTo.move() == false) {}
  delay(random(1000, 1500));

  Serial.println('Mouse button: ' + button);
  Mouse.press(button);   
  delay(random(120, 150));
  Mouse.release(button);
  delay(1500);
}

void test(){
 MouseTo.setTarget(0, 0);
 while (MouseTo.move() == false) {}
 delay(1000);
 MouseTo.setTarget(1500, 0);
 while (MouseTo.move() == false) {}
 delay(1000);

}

void changeRod(String touch){

  pressKey('i'); 
  
  int sx = touch.indexOf('[') + 1;
  int sy = touch.indexOf(',');
    
  int x = touch.substring(sx, sy).toInt();
  int y = touch.substring(sy + 1, touch.length() - 1).toInt();
  
  Serial.println(x);
  Serial.println(y);

  moveTo(x, y, MOUSE_RIGHT);

  pressKey('i');
}

void useSlot(String slot){
  int start = slot.indexOf('[');

  Serial.println(start);
  char key = slot.charAt(start + 1);
  Serial.println(key);
  pressKey(key);
}

void takeLoot(String loot){

  pressKey(0x80); 
  
  int sx = loot.indexOf('[') + 1;
  int sy = loot.indexOf(',');
    
  int x = loot.substring(sx, sy).toInt();
  int y = loot.substring(sy + 1, loot.length() - 1).toInt();
  
  Serial.println(x);
  Serial.println(y);

  moveTo(x, y, MOUSE_RIGHT);

  pressKey(0x80);
  
}

char getKey(char key) {
  switch  (key) {
    case 'w': return 'w';
    case 's': return 's';
    case 'a': return 'a';
    case 'd': return 'd';
    case 'r': return 'r';
    default : return '@';
  }
}

void personalMessage(String pm){
  
  int usernameStart = pm.indexOf('[') + 1;
  int usernameEnd = pm.indexOf(',');
  int messageEnd = pm.indexOf(']');
  
  String username = pm.substring(usernameStart, usernameEnd);
  String message = pm.substring(usernameEnd + 1,messageEnd);

  delay(1000);
  pressKey(0xB0);

  pressKey(0x82, '3');

  //print message
  for (int index = 0; index < message.length(); index++){
    pressKey(message[index]);  
  }

  pressKey(0xB3);

  // clear username
  for (int index = 0; index < 20; index++){
    pressKey(0xB2);  
  }

  //print TO
  for (int index = 0; index < username.length(); index++){
    pressKey(username[index]);  
  }

  pressKey(0xB3);
  pressKey(0xB0);
}

void exitGame(){
	pressKey(0xB1);
	moveTo(820, 757, MOUSE_LEFT );
  moveTo(961, 296, MOUSE_LEFT );
  moveTo(917, 587, MOUSE_LEFT );
  pressKey(0xB1);
}

void loop() {

  String input = Serial.readString();
  int length = input.length();
  if (length != 0) {
    Serial.println(input);
    if (input.startsWith("space")) {
      pressKey(0x20);
    } else if (input.startsWith("Rod")) {
      changeRod(input);
    } else if (input.startsWith("Loot")) {
      takeLoot(input);
    } else if (input.startsWith("Slot")) {
      useSlot(input);
    } else if (input.startsWith("test")) {
      test();
    } else if (input.startsWith("PM")) {
      personalMessage(input);
    }else if (input.startsWith("Exit")) {
      exitGame();
    }else {
      for (int i = 0; i < length; i++) {
        delay(random(130, 210));
        char symbol = getKey(input[i]);
        if (symbol != '@') pressKey(symbol);
        
      }

    }

  }
  
}
