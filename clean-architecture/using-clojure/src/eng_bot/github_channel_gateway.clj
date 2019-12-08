(ns eng-bot.github-channel-gateway
  (:require [clj-slack.channels :as slack-channels]
            [clojure.spec.alpha :as s]))

(s/def ::name string?)
(s/def ::channels (s/keys :req-un [::name]))

(defn- build-channel-hash
  [channel]
  {:name (get channel :name_normalized)})

(defn all-channels
  [connection]
  (let [result (slack-channels/list connection {:exclude_archived "true"})
        channels (get result :channels)]
    (map build-channel-hash channels)))

(s/def ::ts string?)
(s/def ::user string?)
(s/def ::text string?)
(s/def ::message (s/keys :req-un [::ts ::user ::text]))

(defn- build-message-hash
  [message]
  (select-keys message [:ts :user :text]))

(defn channel-messages
  [connection channel]
  (let [result (slack-channels/history connection channel)
        messages (get result :messages)]
    (map build-message-hash messages)))
