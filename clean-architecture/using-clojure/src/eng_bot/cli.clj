(ns eng-bot.cli
  (:require [eng-bot.factory :as factory])
  (:gen-class))

(defn -main
  [& args]
  (println (factory/list-channels)))
