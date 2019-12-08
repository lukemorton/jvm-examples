(ns eng-bot.identify-hottest-channels
  (:require [eng-bot.channels :as c]))

(defn- assoc-recent-message-count-fn
  [channel-messages]
  (fn [channel]
    (assoc channel ::c/recent-message-count (count (channel-messages (::c/id channel))))))

(defn exec
  [list-channels channel-messages]
  (vec (take 3 (sort-by ::c/recent-message-count
                        #(compare %2 %1)
                        (map (assoc-recent-message-count-fn channel-messages)
                             (list-channels))))))

