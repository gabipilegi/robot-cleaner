(ns robot-cleaner.core
  (:gen-class)
  (:require [robot-cleaner.io :as io]
            [robot-cleaner.logic.clean :as logic.clean]))

(defn run-cleaner! []
  (-> (io/read!)
      (logic.clean/clean-plan->unique-cleaned-positions)
      count
      (io/write!)))

(defn -main
  "This program:
  1. Reads standard inputs in the format:
    ```
    <number-of-commands>
    <initial-x initial-y>
    & <compass-direction> <number-of-steps>
    ```
    e.g:
    ```
    2
    10 22
    E 2
    N 1
    ```
  2. Runs Clean Logic
  3. Outputs standat out in the format:
    ```
    Cleaned: <number of unique cleaned positions>
    ```"
  [& args]
  (println "Give me cleaning instructions")
  (run-cleaner!))
