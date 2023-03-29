n1 = input("Primul numar este: ")
n2 = input("Al doilea numar este: ")
a = len(n1)
b = len(n2)
k = 0
for i in range(a):
    for j in range(b):
        if n1[i] == n2[j]:
            k = i
    if k != i:
        print("Numerele nu au proprietatea P")
        break
if k == i:
    print("Numerele au proprietatea P")
    
