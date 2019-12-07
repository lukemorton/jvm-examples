(ns eng-bot.cli-test
  (:require [eng-bot.cli :as cli]
            [eng-bot.factory :as factory]
            [spy.core :refer [spy called-once?]]))

(use 'clojure.test)

(deftest calls-share-channels
  (with-redefs [factory/share-channels (spy)]
    (cli/-main "share-channels")
    (is (called-once? factory/share-channels))))
