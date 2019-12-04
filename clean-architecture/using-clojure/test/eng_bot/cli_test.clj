(ns eng-bot.cli-test
  (:require [eng-bot.cli :as cli]))

(use 'clojure.test)

(deftest calls-share-channels
  (is (not= nil (cli/-main "share-channels"))))
