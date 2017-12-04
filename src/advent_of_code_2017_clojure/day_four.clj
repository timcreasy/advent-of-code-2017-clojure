(ns advent-of-code-2017-clojure.day-four
  (:require [clojure.string :as str]))

(defn parse-input [file]
  (map (fn [s]
         (str/split s
                    #" "))
       (str/split-lines (slurp file))))

(defn part-one [input]
  (count
    (filter #(every? #{1}
                     (vals %))
            (map frequencies
                 input))))

(defn part-two [input]
  (count
    (filter (fn [passphrase]
              (apply distinct?
                     (map frequencies
                          passphrase)))
            input)))

(defn -main [input-file]
  (let [parsed-input (parse-input input-file)]
    (println (str "Answer One: " (part-one parsed-input)))
    (println (str "Answer Two: " (part-two parsed-input)))))
