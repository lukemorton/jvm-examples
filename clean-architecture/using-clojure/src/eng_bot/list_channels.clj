(ns eng-bot.list-channels
  (:require [clojure.string :as str]))

(defn- eng-channel?
  [channel]
  (str/starts-with? (get channel :name) "eng-"))

(defn- eng-channels
  [channels]
  (filterv eng-channel? channels))

(defn exec
  [all-channels]
  (eng-channels (all-channels)))
