#define SOUND_SENSOR_PIN A0 // Pinul analogic conectat la senzorul de sunet

void setup() {
  
  Serial.begin(9600);    // Inițializarea portului serial pentru comunicarea cu monitorul serial
  Serial.println("Sound Sensor test!");

}

void loop() {
  // Citirea nivelului de sunet
  int soundLevel = analogRead(SOUND_SENSOR_PIN);

  // Afișează nivelul de sunet în monitorul serial
  Serial.print("Sound Level: ");
  Serial.println(soundLevel);

  delay(2000);
}

