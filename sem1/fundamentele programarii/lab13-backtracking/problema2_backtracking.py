def isPrim(n):
    if n < 2:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    d = 3
    while d * d <= n:
        if n % d == 0:
            return False
        d += 2
    return True


def prime(n):
    lista_prime = [elem for elem in range(n) if isPrim(elem)]
    return lista_prime


def test_prime():
    test_lista_prime = prime(20)
    assert (len(test_lista_prime) == 8)


def valid(x, nr):
    if sum(x[:len(x)]) > nr:
        return False
    for index in range(len(x)-1):
        if x[index] > x[index + 1]:
            return False
    return True


def test_validate():
    assert valid([3, 5, 7], 20)
    assert not valid([5, 4, 3], 10)


def consistent(x, nr):
    if len(x) > nr // 2 or x[-1] not in prime(nr):
        return False
    for index in range(len(x)-1):
        if x[index] > x[index + 1]:
            return False
    return True


def solutie(x, nr):
    if sum(x[:len(x)]) == nr:
        return True
    return False


def afis(x, nr):
    afis_strig = str(nr) + "= "
    for index in range(len(x)-1):
        afis_strig = afis_strig + str(x[index]) + "+"
    afis_strig = afis_strig + str(x[-1])
    print(afis_strig)


def BackRec(sol, n):
    lista_prime = prime(n)
    element = lista_prime[0]
    sol.append(element)
    for nr in lista_prime:
        sol[-1] = nr
        if valid(sol, n):
            if solutie(sol, n):
                afis(sol, n)
            else:
                BackRec(sol[:], n)


def BackIter(x, n):
    x = [1]
    while len(x) > 0:
        choosed = False
        while not choosed and x[-1] < n - 1:
            x[-1] = x[-1] + 1
            choosed = consistent(x, n)
        if choosed:
            if solutie(x, n):
                afis(x, n)
            x.append(1)
        else:
            x = x[:-1]


BackRec([], 12)
test_prime()
test_validate()
BackIter([], 8)

