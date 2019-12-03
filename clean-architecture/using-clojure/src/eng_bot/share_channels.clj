(ns eng-bot.share-channels)

(defn exec
  [list-channels post-message]
  (let [channels (map #(get % :name) (list-channels))]
    (post-message :channel "eng-test" :text channels)))
