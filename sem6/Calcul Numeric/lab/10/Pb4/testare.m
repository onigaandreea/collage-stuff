int = integral(@sin, 0, pi)
rom = Romberg(@sin, 0, pi, 0.001, 100)