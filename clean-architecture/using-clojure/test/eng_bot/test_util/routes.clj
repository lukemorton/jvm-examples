(ns eng-bot.test-util.routes
  (:require [cheshire.core :as json]))

(defn- mocked-slack-channels
  [channels]
  {:status 200
   :content-type "application/json"
   :body (json/generate-string {:ok true :channels channels})})

(def slack-channels
  {"/channels.list" (mocked-slack-channels [{:name_normalized "announcements"}
                                            {:name_normalized "eng-general"}])})

(def slack-post-message
  {"/chat.postMessage" {:status 200
                        :content-type "application/json"
                        :body (json/generate-string {:ok true})}})
