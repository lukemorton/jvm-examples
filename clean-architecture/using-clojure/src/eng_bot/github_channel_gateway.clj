(ns eng-bot.github-channel-gateway
  (:require [clj-slack.channels :as slack-channels]))

(defn- build-channel-hash
  [channel]
  {:name (get channel :name_normalized)})

(defn all-channels
  [connection]
  (let [channels (get (slack-channels/list connection) :channels)]
    (map build-channel-hash channels)))
