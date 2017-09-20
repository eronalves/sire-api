(defproject sire-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure          "1.8.0"]
                 [environ                      "1.1.0"]
                 [com.stuartsierra/component   "0.3.2"]
                 [prismatic/schema             "1.1.6"]
                 [metosin/ring-http-response   "0.9.0"]
                 [io.pedestal/pedestal.service "0.5.2"]
                 [io.pedestal/pedestal.route   "0.5.2"]
                 [io.pedestal/pedestal.jetty   "0.5.2"]
                 [pedestal-api                 "0.3.1"]
                 [org.slf4j/slf4j-simple       "1.7.21"]
                 [org.clojure/data.json        "0.2.6"]]
  :plugins [[lein-environ "1.1.0"]
            [lein-cloverage "1.0.9"]]
  :repl-options {:init-ns user}
  :test-paths ["test" "itest"]
  :aliases {"itest" ["midje" ":filters" "it"]
            "test"  ["midje"]
            "utest" ["midje" ":filters" "-it"]}
  :main sire-api.core)
