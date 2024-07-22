//const int buzzer = D2;
const int prop = D2;
const int trigPin = D5;
const int echoPin = D6;

long duration;
int distance;

void setup() {
  Serial.begin(115200);
  //pinMode(buzzer, OUTPUT);
  pinMode(prop, OUTPUT);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
}

void loop() {
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  duration = pulseIn(echoPin, HIGH);
  distance = duration * 0.034 / 2;

  if(distance < 15) {
    // Turn on the buzzer and propeller
    //tone(buzzer, 1000);
    digitalWrite(prop, HIGH);
  } else {
    // Turn off the buzzer and propeller
    //noTone(buzzer);
    digitalWrite(prop, LOW);
  }
  delay(100);
}
