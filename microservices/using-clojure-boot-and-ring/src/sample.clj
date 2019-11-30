(ns sample
  (:require
   [ring.adapter.jetty :refer [run-jetty]]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello world"})

(defn run-dev-server
  [port]
  (run-jetty handler {:port port}))
