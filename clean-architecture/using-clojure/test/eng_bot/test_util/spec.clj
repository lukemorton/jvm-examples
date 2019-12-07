(ns eng-bot.test-util.spec
  (:require [clojure.spec.alpha :refer [valid? explain-str]]
            [clojure.test :refer [is]]))

(defn is-conforming-to-spec
  [spec v]
  (is (valid? spec v) (explain-str spec v)))
