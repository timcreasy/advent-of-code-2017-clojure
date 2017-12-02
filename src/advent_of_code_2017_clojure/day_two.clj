(ns advent-of-code-2017-clojure.day-two
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :refer [combinations]]))

(defn parse-input [file]
  (map (fn [row]
         (map #(Integer/parseInt %)
              (str/split row
                         #"\t")))
       (str/split-lines (slurp file))))

(defn part-one [input]
  (apply +
         (map (fn [row]
                (- (apply max row)
                   (apply min row)))
              input)))

(defn part-two [input]
  (apply +
         (mapcat (fn [row]
                   (remove ratio?
                           (map (fn [[a b]]
                                  (/ b a))
                                (map sort
                                     (combinations row 2)))))
                 input)))

(defn -main [input-file]
  (let [parsed-input (parse-input input-file)]
    (println (str "Answer One: " (part-one parsed-input)))
    (println (str "Answer Two: " (part-two parsed-input)))))