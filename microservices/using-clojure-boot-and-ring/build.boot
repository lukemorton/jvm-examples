(set-env!
  :resource-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.8.0"]
                  [ring/ring-core "1.6.3"]
                  [ring/ring-jetty-adapter "1.6.3"]
                  [ring/ring-devel "1.6.3"]])

(deftask dev
  "Run server hot reloading Clojure namespaces"
  [p port PORT int "Server port (default 3000)"]
  (require '[sample :as app])
  (apply (resolve 'app/run-dev-server) [(or port 3000)]))
