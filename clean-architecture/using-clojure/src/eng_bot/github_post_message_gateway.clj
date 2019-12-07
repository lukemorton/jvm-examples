(ns eng-bot.github-post-message-gateway
  (:require [clj-slack.chat :as slack-chat]))

(defn post-message
  [connection channel text]
  ; {:link_names true}
  (let [options {:icon_emoji ":female-mechanic:"
                 :link_names "true"}
        response (slack-chat/post-message connection channel text options)]
    (if (get response :ok)
      true
      (throw (ex-info "Error posting message to Slack" response)))))
