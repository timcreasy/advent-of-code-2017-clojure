(ns advent-of-code-2017-clojure.day-five
  (:require [clojure.string :as str]))

(defn parse-input [file]
  (map #(Integer/parseInt %)
       (str/split-lines (slurp file))))

(defn part-one [input]
  (let [instruction-count (count input)]
    (loop [instructions (vec input)
           current-instruction 0
           counter 1]
      (let [next-instruction (+ current-instruction
                                (get instructions current-instruction))]
        (if (<= next-instruction
                (dec instruction-count))
          (recur (update instructions
                         current-instruction
                         inc)
                 next-instruction
                 (inc counter))
          counter)))))

(defn part-two [input]
  (let [instruction-count (count input)]
    (loop [instructions (vec input)
           current-instruction 0
           counter 1]
      (let [offset (get instructions current-instruction)
            next-instruction (+ current-instruction
                                offset)]
        (if (<= next-instruction
                (dec instruction-count))
          (recur (update instructions
                         current-instruction
                         (if (>= offset 3)
                           dec
                           inc))
                 next-instruction
                 (inc counter))
          counter)))))

(defn -main [input-file]
  (let [parsed-input (parse-input input-file)]
    (println (str "Answer One: " (part-one parsed-input)))
    (println (str "Answer Two: " (part-two parsed-input)))))
