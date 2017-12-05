(ns advent-of-code-2017-clojure.day-four
  (:require [clojure.string :as str]))

(defn parse-input [file]
  (map (fn [s]
         (str/split s
                    #" "))
       (str/split-lines (slurp file))))

(defn part-one [input]
  (count
    (filter #(= (count %)
                (count (set %)))
            input)))

(defn part-two [input]
  (count (filter #(= (count %)
                     (count (set %)))
                 (map #(map sort %)
                      input))))

(defn -main [input-file]
  (let [parsed-input (parse-input input-file)]
    (println (str "Answer One: " (part-one parsed-input)))
    (println (str "Answer Two: " (part-two parsed-input)))))
