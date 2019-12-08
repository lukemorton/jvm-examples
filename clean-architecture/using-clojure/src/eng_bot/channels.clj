(ns eng-bot.channels
  (:require [clojure.spec.alpha :as s]))

(s/def ::id string?)
(s/def ::name string?)
(s/def ::channel (s/keys :req [::id ::name]))

(s/def ::recent-message-count integer?)
(s/def ::hot-channel (s/merge ::channel
                              (s/keys :req [::recent-message-count])))

(s/def ::ts string?)
(s/def ::user string?)
(s/def ::text string?)
(s/def ::message (s/keys :req [::ts ::user ::text]))