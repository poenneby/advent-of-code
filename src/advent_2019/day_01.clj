(ns advent-2019.day_01
  (:require
   [clojure.java.io :as io]
   [clojure.edn :as edn]))

(defn calculate-fuel [module-mass] 
  "Calculates fuel required to launch a given module based on its' mass"
  (- (int (Math/floor (/ module-mass 3))) 2))


(defn fuel-required-for [fuel] 
  (let [fuel-required (calculate-fuel fuel)]
    (loop [sum 0
           f fuel-required]
      (if (< f 0)
        sum
        (recur (+ sum f) (calculate-fuel f))))))

(with-open [rdr (io/reader "resources/advent_2019/day_1/input")]
  (reduce + (map fuel-required-for (map edn/read-string (line-seq rdr)))))
