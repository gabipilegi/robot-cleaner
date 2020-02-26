(ns robot-cleaner.logic.clean)

(defn- next-position
  [{:keys [x y]}
   {:keys [compass-direction number-of-steps]}]
  (case compass-direction
    :N {:x x :y (+ y number-of-steps)}
    :S {:x x :y (- y number-of-steps)}
    :E {:x (+ x number-of-steps) :y y}
    :W {:x (- x number-of-steps) :y y}))

(defn- cleaned-path
  [current-position
   commands
   past-positions]
  (if (seq commands)
    (let [next-position (next-position current-position (first commands))]
      (cleaned-path
        next-position
       (rest commands)
       (conj past-positions next-position)))
    past-positions))

(defn clean-plan->unique-cleaned-positions
  [{:keys [start-position commands]}]
  (distinct (cleaned-path start-position commands [{:x 0 :y 0}])))
