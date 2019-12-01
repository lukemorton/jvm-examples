(set-env!
  :resource-paths #{"src"}
  :source-paths #{"test"}
  :dependencies '[[org.clojure/clojure "1.8.0"]
                  [lynxeyes/dotenv "1.0.2"]
                  [org.julienxx/clj-slack "0.6.3"]
                  [cheshire "5.9.0"]
                  [se.haleby/stub-http "0.2.7"]
                  [adzerk/boot-test "1.2.0"]])

(require '[adzerk.boot-test :as boot-test])

(deftask test
  "Run full test suite in watch mode"
  []
  (comp (watch)
        (notify :audible false
                :visual true
                :title "Test Results")
        (boot-test/test :include #".*-test")))
