(ns advent-2019.day_01
  (:require
   [clojure.java.io :as io]
   [clojure.edn :as edn]))

(defn calculate [module-mass] 
  (- (int (Math/floor (/ module-mass 3))) 2))

(with-open [rdr (io/reader "resources/advent_2019/input")]
  (reduce + (map calculate (map edn/read-string (line-seq rdr)))))

