(ns eng-bot.factory
  (:require [dotenv :refer [env]]
            [eng-bot.list-channels :as list-channels-uc]
            [eng-bot.share-channels :as share-channels-uc]
            [eng-bot.github-channel-gateway :as channel-gateway]
            [eng-bot.github-post-message-gateway :as post-message-gateway]))

(def ^:dynamic *slack-connection* {:api-url (env :SLACK_URL)
                                   :token (env :SLACK_TOKEN)})

(defn list-channels
  []
  (list-channels-uc/exec #(channel-gateway/all-channels *slack-connection*)))

(defn share-channels
  []
  (share-channels-uc/exec list-channels #(post-message-gateway/post-message *slack-connection* %1 %2)))
