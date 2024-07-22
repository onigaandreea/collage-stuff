separatori = [',', ' ', ';', ':', '"', '(', ')', '{', '}']
operatori = ['-', '+', '*', '%', '=', '==', '!=', '<', '>', '<<', '>>']
cuvinteRez = ['#include', '<iostream>', 'using', 'namespace', 'std', 'int', 'main', 'double',
                 'cout', 'cin', 'endl', 'return', 'while', 'if']

orice = separatori + operatori + cuvinteRez
codificare = dict([(orice[i], i + 2) for i in range(len(orice))])
codificare['identificator'] = 0
codificare['constanta'] = 1