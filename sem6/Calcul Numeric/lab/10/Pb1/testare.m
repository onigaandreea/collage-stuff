int = integral(@sin, 0, pi)

trapez = Trapez(@sin, 0, pi, 100)
simpson = Simpson(@sin, 0, pi, 100)
dreptunghi = Dreptunghi(@sin, 0, pi, 100)

trapez2 = Trapez(@sin, 0, pi, 1000)
simpson2 = Simpson(@sin, 0, pi, 1000)
dreptunghi2 = Dreptunghi(@sin, 0, pi, 1000)