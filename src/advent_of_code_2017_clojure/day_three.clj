(ns advent-of-code-2017-clojure.day-three
  (:require [clojure.string :as str]))

;;  17 16 15 14 13
;;  18 5  4  3  12
;;  19 6  1  2  11
;;  20 7  8  9  10
;;  21 22 23 24 25

;;; Via Concrete Mathematics by Graham, Knuth, and Patashnik, Exercise 3.40

(defn calculate-x-coord [n]
  (let [m (Math/floor (Math/sqrt n))]
    (* (Math/pow -1 m)
       (+ (* (+ n
                (* (* -1 m)
                   m)
                (* -1 m))
             (if (even? (int (Math/floor (* 2
                                            (Math/sqrt n)))))
               1
               0))
          (Math/floor (double (/ m 2)))))))

(defn calculate-y-coord [n]
  (let [m (Math/floor (Math/sqrt n))]
    (* (Math/pow -1 m)
       (- (* (+ n
                (* (* -1 m)
                   m)
                (* -1 m))
             (if (odd? (int (Math/floor (* 2
                                           (Math/sqrt n)))))
               1
               0))
          (Math/ceil (double (/ m 2)))))))

(defn part-one [n]
  ;; Dec n by one because forumla's above assume 0 based spiral
  (+ (Math/abs (calculate-x-coord (dec n)))
     (Math/abs (calculate-y-coord (dec n)))))

(defn turn [facing]
  (get {:right :up
        :up :left
        :left :down
        :down :right}
       facing))

(defn next-position [{:keys [coords
                             facing]
                      :as board}]
  (let [next-position (fn [facing [x y]]
                        (cond
                          (= facing :up) [x (inc y)]
                          (= facing :left) [(dec x) y]
                          (= facing :down) [x (dec y)]
                          (= facing :right) [(inc x) y]))]
    (if (get-in board
                (next-position (turn facing)
                               coords))
      {:facing facing
       :coords (next-position facing
                              coords)}
      {:facing (turn facing)
       :coords (next-position (turn facing)
                             coords)})))

(defn make-board [n]
  (reduce (fn [board num]
            (let [{:keys [coords facing] :as abc} (next-position board)]
              (assoc (assoc-in board coords num)
                :facing facing
                :coords coords)))
          {:coords [1 0]
           :facing :up
           0 {0 1}
           1 {0 2}}
          (range 3
                 (inc n))))

(defn -main [n]
  (println (str "Answer One: " (part-one n))))