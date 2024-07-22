clc
A1=diag(ones(1,10)*5)-diag(ones(1, 9),-1)-diag(ones(1,9),1)
b1=[4;ones(8,1)*3;4]
x0=A1\b1
x1=rezolvaGaussSeidel(A1,b1,1e-5)
x2=rezolvaJacobi(A1,b1,1e-5)
x3=rezolvaSOR(A1,b1,0.5,1e-5)


clc
A2=diag(ones(1,11)*5)-diag(ones(1, 10),-1)-diag(ones(1,10),1) - diag(ones(1,8),-3) - diag(ones(1,8),3)
b2=[3;2;2;ones(5,1);2;2;3]
x0=A2\b2
x1=rezolvaGaussSeidel(A2,b2,1e-5)
x2=rezolvaJacobi(A2,b2,1e-5)
x3=rezolvaSOR(A2,b2,0.5,1e-5)
