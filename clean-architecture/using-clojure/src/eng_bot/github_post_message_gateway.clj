(ns eng-bot.github-post-message-gateway
  (:require [clj-slack.chat :as slack-chat]))

(defn post-message
  [connection & {:keys [channel text]}]
  (let [response (slack-chat/post-message connection channel text)]
    (if (get response :ok)
      true
      (throw (ex-info "Error posting message to Slack" response)))))
