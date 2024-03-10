; C:/Users/oniga/OneDrive/Desktop/an2sem1/programare logica si functionala/lab/lab6/lab6.lisp

; nr_noduri(x,n,k) = { 1, x atom si n = k
;                      0, x atom si n != k
;                      ∑ (i=1 -> m) nr_noduri(xi, n+1, k), x lista (x=xi....xm)
;                    }

(defun nr_noduri(x n k)
    (cond
        ((AND (atom x) (= n k)) 1)
        ((AND (atom x) (NOT (= n k))) 0)
        (t (apply '+ (mapcar #'(lambda (a) (nr_noduri a (+ n 1) k)) x)))
    )
)

; nr_nod_aux(x,k) = nr_noduri(x,0,k)

(defun nr_nod_aux(x k)
    (nr_noduri x -1 k)
)

(print (nr_nod_aux '() 5)) ; => 0
(print (nr_nod_aux '(a (b (c)) (d) (e (f))) 1)) ; => 3
(print (nr_nod_aux '(a (b (c d (e)) f (g)) (h (i j))) 2)) ; => 5
(print (nr_nod_aux '(a (b (c d (e)) f (g)) (h (i j))) 5)) ; => 0