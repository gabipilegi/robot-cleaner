(ns robot-cleaner.logic.clean)

(defn- compass-direction->direction+direction-fn
  [compass-direction]
  (case compass-direction
    :N {:direction :y :direction-fn +}
    :S {:direction :y :direction-fn -}
    :E {:direction :x :direction-fn +}
    :W {:direction :x :direction-fn -}))

(defn- next-positions
  [initial-position
   {:keys [compass-direction number-of-steps]}]
  (let [positions                        (map inc (range number-of-steps))
        {:keys [direction
                direction-fn]} (compass-direction->direction+direction-fn compass-direction)]
    (map (fn [position]
           (update
            initial-position
            direction
            #(direction-fn % position)))
         positions)))

(defn- cleaned-path
  [current-position
   commands
   past-positions]
  (if (seq commands)
    (let [next-positions (next-positions current-position (first commands))]
      (cleaned-path
       (last next-positions)
       (rest commands)
       (concat past-positions next-positions)))
    past-positions))

(defn clean-plan->unique-cleaned-positions
  [{:keys [start-position commands]}]
  (distinct (cleaned-path start-position commands [{:x 0 :y 0}])))
