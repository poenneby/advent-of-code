(ns advent-2019.day_02
  (:require
   [clojure.string :as str]
   [clojure.edn :as edn]))


(def data (map edn/read-string (str/split (slurp "resources/advent_2019/day_2/input") #",")))

(defn op-from [opcode]
  ({1 +, 2 *} opcode))

(defn replace-at-position-with-operation [position operation value1 value2 input-list]
  (assoc input-list position (operation value1 value2)))

(defn calculate [input] 
 (loop [offset 0
        i input] 
   (let [opcode (nth i (+ 0 offset))] 
     (if (= 99 opcode)
     i
     (let [pos1 (nth i (+ 1 offset))
         pos2 (nth i (+ 2 offset))
         replace-pos (nth i (+ 3 offset))]
    (recur (+ offset 4) (replace-at-position-with-operation replace-pos (op-from opcode) (nth i pos1) (nth i pos2) i))))))) 

(defn restore-gravity [data]
  (assoc (assoc data 1 12) 2 2))

(calculate (restore-gravity (into [] (doall data))))

(defn provide-inputs [data noun verb] 
 (assoc (assoc data 1 noun) 2 verb))

(calculate (provide-inputs (into [] (doall data)) 12 4))

(loop [noun 1
       verb 1]
  (if (<= noun 99) 
    (let [result (calculate (provide-inputs (into [] (doall data)) noun verb))]
      (if (= 19690720 (first result))
        (+ verb (* 100 noun))
        (if (= verb 100) 
          (recur (inc noun) 1)
          (recur noun (inc verb)))))))


