(ns advent-of-code-2017-clojure.day-one
  (:require [clojure.string :as str]))

(defn parse-input [file]
  (map #(Integer/parseInt %)
       (str/split (str/trim (slurp file))
                  #"")))

(defn sum-consecutive-digits [coll]
  (let [len (count coll)]
    (apply + (map first
                  (filter (fn [[a b]]
                            (= a b))
                          (partition 2 1 (take (inc len)
                                               (cycle coll))))))))

;; Clojure does not have a core transpose function, so it is handy to remember the pattern (apply map list ...) does exactly that.
(defn sum-digits-halfway-around [coll]
  (reduce (fn [acc nums]
            (+ acc (apply + nums)))
          0
          (filter (fn [[a b]]
                    (= a b))
                  (apply map
                         list
                         (partition (/ (count coll) 2)
                                    coll)))))

(defn -main [input-file]
  (let [parsed-input (parse-input input-file)]
    (println (str "Answer One: " (sum-consecutive-digits parsed-input)))
    (println (str "Answer Two: " (sum-digits-halfway-around parsed-input)))))