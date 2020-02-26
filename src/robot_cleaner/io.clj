(ns robot-cleaner.io
  (:require [clojure.string :as str]))

(defn- read-int-ln []
  (-> (read-line)
      Integer.
      int
      (+ 1)))

(defn- input->str-list [number-of-lines]
  (take
   number-of-lines
   (line-seq (java.io.BufferedReader. *in*))))

(defn- str->command [str-command]
  (let [[compass-direction number-of-steps] (str/split str-command #" ")]
    {:compass-direction (keyword compass-direction)
     :number-of-steps (Integer. number-of-steps)}))

(defn read! []
  (let [number-of-lines                 (read-int-ln)
        [start-position & str-commands] (input->str-list number-of-lines)
        [start-x start-y]               (map #(Integer. %) (str/split start-position #" "))
        result {:start-position {:x start-x :y start-y}}]
    (if str-commands
      (assoc result :commands (map str->command str-commands))
      result)))

(defn write! [positions]
  (println (str "=> Cleaned: " positions)))
