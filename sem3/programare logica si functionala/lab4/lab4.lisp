;C:/Users/oniga/OneDrive/Desktop/an2sem1/programare logica si functionala/lab/lab4/lab4.lisp


;a) Interclasarea a 2 liste liniare sortate, cu pastrarea dublurilor
;
;Model matematic:
;
;interclaseaza(l1...ln, k1...km) = {k1...km , n=0, m!=0
;					    			l1...ln , m=0
;					    			l1 U interclaseaza(l2...ln, k1...km), n!=0, m!=0, l1<k1
;					    			k1 U interclaseaza(l1...ln, k2...km), n!=0, m!=0, k1<l1
;					    			l1 U k1 U interclaseaza(l2...ln, k2...km), altfel
;									}
;
;l1....ln -prima lista liniara sortata
;k1...km -a doua lista liniara sortata

(defun interclaseaza (l k)
	(cond
		((AND (null l) (not(null k))) k)
		((null k) l)
		((< (car l) (car k)) (cons (car l) (interclaseaza (cdr l) k)))
		((> (car l) (car k)) (cons (car k) (interclaseaza l (cdr k))))
		(t (cons (car l) (cons (car k) (interclaseaza (cdr l) (cdr k)))))
	)
)

(print (interclaseaza '(1 3 5 7 9) '(1 2 4 5 6 8 9)))
(print (interclaseaza '(1 2 3) nil))
(print (interclaseaza nil nil))

;b) substituirea unui element E cu elementele unei liste L1 la toate nivelurile unei liste date L

;Model matematic:

;substituie(l1...ln, e, k1...km) = { [], n=0
;								     k1...km U substituire(l2...ln,e,k1...km), n>0, l1 = e
;								     l1 U substituire(l2...ln,e,k1...km), n>0, l1 atom, l1!=e
;								     substituire(l1,e,k1... km) U substituire(l2...ln,e,k1...km), altfel
;								   }
;
;l1...ln -lista initiala in care se fac substituiri
;e -elementul care se va substitui
;k1...km -lista cu care va fi substituit elementul e


(defun substituire (l e k)
	(cond
		((null l) nil)
		((equal (car l) e)  (append k (substituire (cdr l) e k)))
		((atom (car l)) (cons (car l) (substituire (cdr l) e k)))
		(t (cons (substituire (car l) e k) (substituire (cdr l) e k)))
	)
)

(print (substituire '(1 a (3 5 b a) 1 2 3 (6 7 (3 5 8))) 3 '(1 1)))
(print (substituire nil 0 '(0 0)))


;c) calculeaza suma(ca si numar zecimal) a doua numere in reprezentate sub forma de lista

;Model matematic:

;vom folosi urmatoarele functii:
;	inverseaza
;	sumList
;	suma
;	toNumber

;inverseaza(l1..ln,c1...cm) = { c1...cm, n=0
;								inverseaza(l2...ln,l1 U c1...cm), n > 0
;							  }
;
;l1...ln -lista pe care vrem sa o inversam
;c1...cm -lista colectoare unde vom construi inversa

;sumList(l1...ln, p1...pm, c, s1...sk)={ c U s1...sk, n=0 si m=0
;									     sumList(l2...ln,[],0,(l1 + c) U s1...sk), n!=0, m=0, l1+c<=9
;									  	 sumList([],p2...pm,0,(p1 + c) U s1...sk), n=0, m!=0, p1+c<=9
;										 sumList(l2...ln,[],c,((l1+c)%10) U s1...sk), n!=0, m=0, l1+c>9
;									  	 sumList([],p2...pm,c,((p1+c)%10) U s1...sk), n=0, m!=0, p1+c>9
;									  	 sumList(l2...ln,p2...pm,0,(l1+p1+c) U s1...sk), l1+p1+c<=9 
;									  	 sumList(l2...ln,p2...pm,1,(l1+p1+c)%10 U s1...sk), l1+p1+c>9
;										}
;
;l1...ln -primul numar reprezentat sub forma de lista
;p1...pm -al doilea numar repr sub forma de lista
;c -carry verifica daca s-a efectuat trecerea peste ordin (0- nu s-a trecut, 1- s-a trecut peste ordin)
;s1...sk -colectoarea unde se va construi suma

;toNumber(l1...ln,nr)={ nr, n=0
;						toNumber(l2...ln,nr*10+l1)
;					  }
;l1...ln -lista care va fi transformata in numar
;nr -numarul ce se va construi folosind elementele din lista initiala l1...ln

;suma(l1...ln,p1...pm) = toNumber(sumList(inverseaza(l1...ln),inverseaza(p1...pm),0,[]),0)
;l1...ln -primul numar repr sub forma de lista
;p1...pm -al doilea nr repr sub forma de lista

(defun inverseaza (l c)
	(cond
		((null l) c)
		(t (inverseaza (cdr l) (cons (car l) c)))
	)
)

(defun sumList (l p c s)
	(cond
		((AND (null l) (null p)) (cons c s))
		((AND (null p) (<= (+ (car l) c) 9)) (sumList (cdr l) nil 0 (cons (+ (car l) c) s)))
		((AND (null l) (<= (+ (car p) c) 9)) (sumList nil (cdr p) 0 (cons (+ (car p) c) s)))
		((AND (null p) (> (+ (car l) c) 9)) (sumList (cdr l) nil 1 (cons (mod (+ (car l) c) 10) s)))
		((AND (null l) (> (+ (car p) c) 9)) (sumList nil (cdr p) 1 (cons (mod (+ (car p) c) 10) s)))
		((<= (+ (car l) (car p) c) 9) (sumList (cdr l) (cdr p) 0 (cons (+ (car l) (car p) c) s)))
		(t (sumList (cdr l) (cdr p) 1 (cons (mod (+ (car l) (car p) c) 10)s)))
	)
)

(defun toNumber (l nr)
	(cond
		((null l) nr)
		(t (toNumber (cdr l) (+ (car l) (* nr 10))))
	)
)

(defun suma (l p)
	(toNumber (sumList (inverseaza l nil) (inverseaza p nil) 0 nil) 0)
)

(print (suma nil nil))
(print (suma '(1 2 3) nil))
(print (suma '(4 6 8) '(1 1)))
(print (suma '(9 7 5) '(1 5)))
(print (suma '(5 2 6 7 8 8) '(4 7 3 2 1 2)))

; d) cmmdc al numerelor dintr-o lista liniara

; vom folosi functiile cmmdc(a,b) pentru a calcula cmmdc pt doua numere si cmmdcList(l,c) pentru a calcula cmmdc pentru toate elementele numerice din lista

; cmmdc(a,b) = { a, b=0
;				 b, a=0
;				 cmmdc(a-b,b), a>=b
;				 cmmdc(a,b-a), altfel
;			   }
; a -primul numar luat in considerare pentru calcularea cmmdc
; b -al doilea numar luat in considerare

; cmmdcList(l1...ln,c) = { c , n=0
;						   cmmdcList(l2...ln,cmmdc(c,l1)), n!=0, l1 numar
;						   cmmdcList(l2...ln,c), alfel
;						 }
; l1...ln -lista liniara de unde luam numerele pentru care calculam cmmdc
; c -cmmdc partial

; vom folosi si functia cmmdcSolve(l) = cmmdcList(l,0)

(defun cmmdc (a b)
	(cond
		((= 0 a) b)
		((= 0 b) a)
		((>= a b) (cmmdc (- a b) b))
		(t (cmmdc a (- b a)))
	)
)

(defun cmmdcList (l c)
	(cond
		((null l) c)
		((numberp (car l)) (cmmdcList (cdr l) (cmmdc c (car l))))
		(t (cmmdcList (cdr l) c))
	)
)

(defun cmmdcSolve (l)
	(cmmdcList l 0)
)

(print (cmmdcSolve nil))
(print (cmmdcSolve '(a 5 i 9 3 f 6)))
(print (cmmdcSolve '(a n d r e e a)))
(print (cmmdcSolve '(s 3 b i 9 12 60)))