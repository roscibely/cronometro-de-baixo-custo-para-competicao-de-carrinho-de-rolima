#include <SoftwareSerial.h>
#define SENSOR 5
int partida = 1;
int chegada = 0;
int con = 2;
SoftwareSerial mySerial(2,3); // RX, TX
void setup() {
  pinMode(SENSOR,INPUT);
  mySerial.begin(9600);
  Serial.begin(9600);
}
void loop() {
  partida = mySerial.read();
  chegada = digitalRead(SENSOR);
  
  if(partida == 0){
    con = 1;
    Serial.println(con);
  }
  else if(con==1 && chegada == 0){
    con = 0;
    Serial.println(con);
    con = 2;
  } 
}
