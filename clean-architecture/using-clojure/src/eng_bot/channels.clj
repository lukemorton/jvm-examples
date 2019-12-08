(ns eng-bot.channels
  (:require [clojure.spec.alpha :as s]))

(s/def ::name string?)
(s/def ::channel (s/keys :req-un [::name]))

(s/def ::recent-message-count integer?)
(s/def ::hot-channel (s/merge ::channel
                              (s/keys :req-un [::recent-message-count])))

(s/def ::ts string?)
(s/def ::user string?)
(s/def ::text string?)
(s/def ::message (s/keys :req-un [::ts ::user ::text]))