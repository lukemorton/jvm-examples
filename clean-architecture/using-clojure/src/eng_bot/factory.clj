(ns eng-bot.factory
  (:require [dotenv :refer [env]]
            [eng-bot.list-channels :as list-channels-uc]
            [eng-bot.identify-hottest-channels :as identify-hottest-channels-uc]
            [eng-bot.share-channels :as share-channels-uc]
            [eng-bot.github-channel-gateway :as channel-gateway]
            [eng-bot.github-post-message-gateway :as post-message-gateway]
            [clojure.spec.alpha :as s]
            [clojure.string :as str]))

(s/def ::api-url (s/and string? #(not (str/blank? %))))
(s/def ::token (s/and string? #(not (str/blank? %))))
(s/def ::slack-connection (s/keys :req-un [::api-url ::token]))

(def ^:dynamic *slack-connection* {:api-url (env :SLACK_URL)
                                   :token (env :SLACK_TOKEN)})

(defn list-channels
  []
  (list-channels-uc/exec
    #(channel-gateway/all-channels *slack-connection*)))

(defn identify-hottest-channels
  []
  (identify-hottest-channels-uc/exec
    list-channels
    #(channel-gateway/channel-messages-since *slack-connection* %1 %2)))

(defn share-channels
  []
  (share-channels-uc/exec
    list-channels
    identify-hottest-channels
    #(post-message-gateway/post-message *slack-connection* %1 %2)))
