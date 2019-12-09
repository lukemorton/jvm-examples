(ns eng-bot.github-channel-gateway
  (:require [clj-slack.channels :as slack-channels]
            [eng-bot.channels :as c]))

(defn- build-channel-hash
  [channel]
  {::c/id (get channel :id)
   ::c/name (get channel :name_normalized)})

(defn all-channels
  [connection]
  (let [result (slack-channels/list connection {:exclude_archived "true"})
        channels (get result :channels)]
    (map build-channel-hash channels)))

(defn- build-message-hash
  [message]
  {::c/ts (get message :ts)
   ::c/user (get message :user)
   ::c/text (get message :text)})

(defn channel-messages-since
  [connection channel since-ts]
  (let [result (slack-channels/history connection channel {:oldest since-ts})]
    (if (get result :ok)
      (map build-message-hash (get result :messages))
      (throw (ex-info "Error finding Slack channel history" result)))))
