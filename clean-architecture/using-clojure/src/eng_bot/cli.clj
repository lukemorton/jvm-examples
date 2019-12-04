(ns eng-bot.cli
  (:require [eng-bot.factory :as factory])
  (:gen-class))

(defn -main
  [fn-name]
  (case fn-name
    "share-channels" (factory/share-channels)))
