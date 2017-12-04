(ns advent-of-code-2017-clojure.day-three
  (:require [clojure.string :as str]))

;;  17 16 15 14 13
;;  18 5  4  3  12
;;  19 6  1  2  11
;;  20 7  8  9  10
;;  21 22 23 24 25


;;; Via Concrete Mathematics by Graham, Knuth, and Patashnik, Exercise 3.40

;(defn calculate-x-coord [n]
;  (let [m (Math/floor (Math/sqrt n))]
;    (* (Math/pow -1 m)
;       (+ (* (+ n
;                (* (* -1 m)
;                   m)
;                (* -1 m))
;             (if (= 0.0 (mod (Math/floor (* 2
;                                            (Math/sqrt n)))
;                             2))
;               1
;               0))
;          (Math/ceil (double (/ m 2)))))))
;
;(defn calculate-y-coord [n]
;  (let [m (Math/floor (Math/sqrt n))]
;    (* (Math/pow -1 m)
;       (+ (* (+ n
;                (* (* -1 m)
;                   m)
;                (* -1 m))
;             (if (not= 0.0 (mod (Math/floor (* 2
;                                               (Math/sqrt n)))
;                                2))
;               1
;               0))
;          (Math/ceil (double (/ m 2)))))))
;
;(defn closest-odd-square [num]
;  (loop [n num]))