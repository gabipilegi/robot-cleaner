(ns robot-cleaner.core
  (:gen-class))

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
  (println "Not implemented"))
