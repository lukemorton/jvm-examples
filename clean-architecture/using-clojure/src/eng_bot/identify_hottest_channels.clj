(ns eng-bot.identify-hottest-channels
  (:require [eng-bot.channels :as c]
            [clj-time.core :as t]
            [clj-time.coerce :as tc]))

(defn- unix-7-days-ago
  []
  (str (tc/to-epoch (t/minus (t/now) (t/days 7))) ".000000"))

(defn- assoc-recent-message-count-fn
  [channel-messages]
  (fn [channel]
    (assoc channel ::c/recent-message-count (count (channel-messages (::c/id channel) (unix-7-days-ago))))))

(defn exec
  [list-channels channel-messages]
  (vec (take 3 (sort-by ::c/recent-message-count
                        #(compare %2 %1)
                        (->> (list-channels)
                             (remove #(= "eng-general" (get % ::c/name)))
                             (remove #(= "eng-test" (get % ::c/name)))
                             (map (assoc-recent-message-count-fn channel-messages)))))))


