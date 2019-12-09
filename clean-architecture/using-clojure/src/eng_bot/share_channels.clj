(ns eng-bot.share-channels
  (:require [clojure.string :refer [join]]
            [eng-bot.channels :as c]))

(defn- channel-list-text
  [channels]
  (->> channels
       (map #(get % ::c/name))
       (map #(str "#" %))
       (join ", ")))

(defn- hot-channel-list-text
  [hot-channels]
  (->> hot-channels
       (map #(format "- #%s with %s messages" (get % ::c/name) (get % ::c/recent-message-count)))
       (join "\n")))

(defn- message-text
  [channels hot-channels]
  (format (str "Hi fellow engineers! Gee have I got some slack channels for you. "
               "There's nothing like sharing what you know and learning from others. "
               "Why not join and participate in a few engineering channels?\n\n"
               "%s\n\n"
               "Last weeks hottest channels:\n"
               "%s")
          (channel-list-text channels)
          (hot-channel-list-text hot-channels)))

(defn exec
  [list-channels identify-hottest-channels post-message]
  (->> (message-text (list-channels) (identify-hottest-channels))
       (post-message)))
