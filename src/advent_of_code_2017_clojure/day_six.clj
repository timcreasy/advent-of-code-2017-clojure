(ns advent-of-code-2017-clojure.day-six
  (:require [clojure.string :as str]))

(defn parse-input [file]
  (mapv #(Integer/parseInt %)
        (str/split (str/trim (slurp file))
                   #"\t")))

(defn distribute-largest-block [memory-bank]
  (let [largest-block-value (apply max memory-bank)
        largest-block-index (.indexOf memory-bank
                                      largest-block-value)
        memory-bank-size (count memory-bank)]
    (reduce (fn [mem-bank idx]
              (update mem-bank idx inc))
            (assoc memory-bank
              largest-block-index 0)
            (map #(mod % memory-bank-size)
                 (range (inc largest-block-index)
                        (+ (inc largest-block-index)
                           largest-block-value))))))

(defn steps-until-dupe-state-encountered [states]
  (reduce (fn [seen-states new-state]
            (if (contains? seen-states
                           new-state)
              (reduced (count seen-states))
              (conj seen-states
                    new-state)))
          #{}
          states))

(defn part-one [input]
  (steps-until-dupe-state-encountered (iterate distribute-largest-block
                                               input)))

(defn part-two [input]
  (steps-until-dupe-state-encountered (drop (part-one input)
                                            (iterate distribute-largest-block
                                                     input))))

(defn -main [input-file]
  (let [parsed-input (parse-input input-file)]
    (println (str "Answer One: " (part-one parsed-input)))
    (println (str "Answer Two: " (part-two parsed-input)))))