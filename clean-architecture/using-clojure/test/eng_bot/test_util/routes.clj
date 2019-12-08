(ns eng-bot.test-util.routes
  (:require [cheshire.core :as json]))

(defn- mocked-slack-channels
  [channels]
  {:status 200
   :content-type "application/json"
   :body (json/generate-string {:ok true :channels channels})})

(def slack-channels
  {"/channels.list" (mocked-slack-channels [{:id "C0001" :name_normalized "announcements"}
                                            {:id "C0002" :name_normalized "eng-general"}])})

(def slack-channel-messages
  (vec (replicate 100 {:ts "1358546515.000007"
                       :user "U2147483896"
                       :text "Nice one!"})))

(def slack-channel-history
  {"/channels.history" {:status 200
                        :content-type "application/json"
                        :body (json/generate-string {:ok true
                                                     :messages slack-channel-messages
                                                     :has_more false})}})

(def slack-post-message
  {"/chat.postMessage" {:status 200
                        :content-type "application/json"
                        :body (json/generate-string {:ok true})}})

(def slack-channels-history-and-post-message
  (merge slack-channels slack-channel-history slack-post-message))
