(ns eng-bot.share-channels
  (:require [clojure.string :as str]))

(defn exec
  [list-channels post-message]
  (let [channels (map #(get % :name) (list-channels))]
    (post-message "eng-test" (str/join ", " channels))))
