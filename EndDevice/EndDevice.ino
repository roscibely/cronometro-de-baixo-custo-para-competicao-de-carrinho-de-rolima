#define BOTAO 5
void setup() {
  pinMode(BOTAO,INPUT);
  Serial.begin(9600);
}
void loop() {
//Envia o valor do botao para serial
  Serial.write(digitalRead(BOTAO)); 
}
