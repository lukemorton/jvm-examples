(ns eng-bot.share-channels
  (:require [clojure.string :refer [join]]))

(defn- channel-list-message-text
  [channels]
  (->> channels
       (map #(get % :name))
       (map #(str "#" %))
       (join ", ")))

(defn exec
  [list-channels post-message]
  (->> (list-channels)
       (channel-list-message-text)
       (post-message "eng-test")))
