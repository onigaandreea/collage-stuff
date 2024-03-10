; C:/Users/oniga/OneDrive/Desktop/an2sem1/programare logica si functionala/lab/lab5/lab5.lisp

; _convert(l1...ln,col1...colm) = { col1, n=0
;									_convert([[l2]l3...ln],col1...colm), l1=0
;									_convert([[col1,l2]l3...ln],col2...colm), l1=1
;									_convert([[col2,col1,l2]l3...ln],col3...colm), l1=2
;									_convert([l2...ln],l1 U col1...colm), altfel

; l1...ln lista cu reprezentarea arborelui in tipul 1
; col1...colm colectoarea unde vom avea subarborii

(defun _convert (l stack)
	(cond
		((null l) (car stack))

		((and (numberp (car l)) (= (car l) 0)) (_convert (cons (list (cadr l)) (cddr l)) stack))
		((and (numberp (car l)) (= (car l) 1)) (_convert (cons (list (car stack) (cadr l)) (cddr l)) (cdr stack)))
		((and (numberp (car l)) (= (car l) 2)) (_convert (cons (list (cadr stack) (car stack) (cadr l)) (cddr l))  (cddr stack)))
		(T (_convert (cdr l) (cons (car l) stack)))
	)
)

; convert(l) => converted list

; reverse_tree(l1...ln,c1...cm) = { c1...cm, n=0
;									reverse_tree(l2...ln, l1 U c1...cm), l1 atom
;									reverse_tree(l2...ln, reverse_tree(l1,[]) U c1..cm), altfel (l1 este o lista)

(defun reverse_tree (l c)
	(cond
		((null l) c)
		((atom (car l)) (reverse_tree (cdr l) (cons (car l) c)))
		(t (reverse_tree (cdr l) (cons (reverse_tree (car l) nil) c)))
	)
)

(print (reverse_tree '(a (b c (d e) f) g (h)) nil))

; convert(l1..ln) = reverse_tree(_convert(reverse_tree(l), nil)))
(defun convert (l)
	(reverse_tree(_convert (reverse_tree l nil) nil) nil)
)

(print (convert '(A 0)))
; => '(A)
(print (convert '(A 1 B 0)))
; => '(A (B))
(print (convert '(A 2 B 1 C 2 D 0 E 0 F 1 G 0)))
; => '(A (B (C (D) (E))) (F (G)))
(print (convert '(A 2 B 0 C 2 D 0 E 0)))
; => '(A (B) (C (D) (E)))
(print (convert '(A 2 B 1 H 2 NIL 0 I 2 J 0 K 0 C 2 D 2 NIL 0 G 0 E 0)))