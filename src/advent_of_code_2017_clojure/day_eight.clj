(ns advent-of-code-2017-clojure.day-eight
  (:require [clojure.string :as str]))

(defn parse-instruction [instruction]
  (let [[_ register action amount cond-operand-1 cond-op cond-operand-2]
        (re-find #"([a-z]+) ([a-z]+) (-?[0-9]+) if ([a-z]+) ([!=><]+) (-?[0-9]+)"
                 instruction)]
    {::register register
     ::action (if (= action "inc")
                +
                -)
     ::amount (Integer/parseInt amount)
     ::cond-op (if (= cond-op "!=")
                 not=
                 (resolve (symbol cond-op)))
     ::cond-operand-1 cond-operand-1
     ::cond-operand-2 (Integer/parseInt cond-operand-2)}))

(defn parse-input [file]
  (map parse-instruction
       (str/split-lines (slurp file))))

(set (map ::register
          (map parse-instruction
               test-data)))

(defn part-one [input]
  (let [registers (into {}
                        (map #(vector % 0)
                             (set (map ::register
                                       input))))]
    (apply max (vals (reduce (fn [regs {:keys [::register
                                               ::action
                                               ::amount
                                               ::cond-op
                                               ::cond-operand-1
                                               ::cond-operand-2] :as a}]
                               (if (cond-op (get regs cond-operand-1)
                                            cond-operand-2)
                                 (update regs register action amount)
                                 regs))
                             registers
                             input)))))

(defn part-two [input]
  (let [registers (into {}
                        (map #(vector % 0)
                             (set (map ::register
                                       input))))]
    (apply max
           (mapcat vals
                   (reductions (fn [regs {:keys [::register
                                                 ::action
                                                 ::amount
                                                 ::cond-op
                                                 ::cond-operand-1
                                                 ::cond-operand-2] :as a}]
                                 (if (cond-op (get regs cond-operand-1)
                                              cond-operand-2)
                                   (update regs register action amount)
                                   regs))
                               registers
                               input)))))

(defn -main [input-file]
  (let [parsed-input (parse-input input-file)]
    (println (str "Answer One: " (part-one parsed-input)))
    (println (str "Answer Two: " (part-two parsed-input)))))