(ns robot-cleaner.logic.clean-test
  (:require [robot-cleaner.logic.clean :as logic.clean]
            [clojure.test :refer :all]))

(deftest clean-plan->unique-cleaned-positions
  (testing "Clean-plan with no commands"
    (is (= [{:x 0 :y 0}]
           (logic.clean/clean-plan->unique-cleaned-positions {:start-position {:x 0
                                                                               :y 0}}))))
  (testing "Clean-plan with one north headed command"
    (is (= [{:x 0 :y 0} {:x 0 :y 1}]
           (logic.clean/clean-plan->unique-cleaned-positions {:start-position {:x 0
                                                                               :y 0}
                                                              :commands       [{:compass-direction :N
                                                                                :number-of-steps   1}]}))))
  (testing "Clean-plan with one south headed command"
    (is (= [{:x 0 :y 0} {:x 0 :y -1}]
           (logic.clean/clean-plan->unique-cleaned-positions {:start-position {:x 0
                                                                               :y 0}
                                                              :commands       [{:compass-direction :S
                                                                                :number-of-steps   1}]}))))
  (testing "Clean-plan with one east headed command"
    (is (= [{:x 0 :y 0} {:x 1 :y 0}]
           (logic.clean/clean-plan->unique-cleaned-positions {:start-position {:x 0
                                                                               :y 0}
                                                              :commands       [{:compass-direction :E
                                                                                :number-of-steps   1}]}))))
  (testing "Clean-plan with repetitive positions"
    ;;Cleaned path looks like this:
    ;; _ _ X _ _
    ;; _ _ X _ _
    ;; X X X X X
    ;; _ _ X _ _
    ;; _ _ X _ _
    (is (= [{:x 0 :y 0}
            {:x 0 :y 1}
            {:x 0 :y 2}
            {:x 0 :y -1}
            {:x 0 :y -2}
            {:x 1 :y 0}
            {:x 2 :y 0}
            {:x -1 :y 0}
            {:x -2 :y 0}]
           (logic.clean/clean-plan->unique-cleaned-positions {:start-position {:x 0
                                                                               :y 0}
                                                              :commands       [{:compass-direction :N, :number-of-steps 1}
                                                                               {:compass-direction :N, :number-of-steps 1}
                                                                               {:compass-direction :S, :number-of-steps 1}
                                                                               {:compass-direction :S, :number-of-steps 1}
                                                                               {:compass-direction :S, :number-of-steps 1}
                                                                               {:compass-direction :S, :number-of-steps 1}
                                                                               {:compass-direction :N, :number-of-steps 1}
                                                                               {:compass-direction :N, :number-of-steps 1}
                                                                               {:compass-direction :E, :number-of-steps 1}
                                                                               {:compass-direction :E, :number-of-steps 1}
                                                                               {:compass-direction :W, :number-of-steps 1}
                                                                               {:compass-direction :W, :number-of-steps 1}
                                                                               {:compass-direction :W, :number-of-steps 1}
                                                                               {:compass-direction :W, :number-of-steps 1}
                                                                               {:compass-direction :E, :number-of-steps 1}
                                                                               {:compass-direction :E, :number-of-steps 1}]})))))
