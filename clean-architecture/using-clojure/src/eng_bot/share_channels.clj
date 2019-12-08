(ns eng-bot.share-channels
  (:require [clojure.string :refer [join]]
            [eng-bot.channels :as c]))

(defn- channel-list-text
  [channels]
  (->> channels
       (map #(get % ::c/name))
       (map #(str "#" %))
       (join ", ")))

(defn- message-text
  [channels]
  (format (str ":hammer_and_wrench: :hammer_and_wrench:\n"
               "Hi fellow engineers! Gee have I got some slack channels for you. "
               "There's nothing like sharing what you know and leaning on others for help. "
               "Why not join and participate in a few engineering channels?\n\n"
               "%s")
          (channel-list-text channels)))

(defn exec
  [list-channels post-message]
  (->> (list-channels)
       (message-text)
       (post-message "eng-test")))
