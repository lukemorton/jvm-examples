(ns eng-bot.factory
  (:require [dotenv :refer [env]]
            [eng-bot.list-channels :as lc]
            [eng-bot.github-channel-gateway :as g]))

(def ^:dynamic *slack-connection* {:api-url (env :SLACK_URL)
                                   :token (env :SLACK_TOKEN)})

(defn list-channels
  []
  (lc/exec #(g/all-channels *slack-connection*)))
