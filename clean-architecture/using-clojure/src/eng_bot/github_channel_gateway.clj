(ns eng-bot.github-channel-gateway
  (:require [clj-slack.channels :as slack-channels]))

(defn- build-channel-hash
  [channel]
  {:name (get channel :name_normalized)})

(defn all-channels
  [connection]
  (let [result (slack-channels/list connection {:exclude_archived "true"})
        channels (get result :channels)]
    (map build-channel-hash channels)))

(defn- build-message-hash
  [message]
  (select-keys message [:ts :user :text]))

(defn channel-messages
  [connection channel]
  (let [result (slack-channels/history connection channel)
        messages (get result :messages)]
    (map build-message-hash messages)))
