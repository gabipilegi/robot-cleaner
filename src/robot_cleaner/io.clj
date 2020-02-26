(ns robot-cleaner.io)

(defn input-list! []
  (let [number-of-lines (-> (read-line) Integer. int (+ 1))]
    (take number-of-lines (line-seq (java.io.BufferedReader. *in*)))))
