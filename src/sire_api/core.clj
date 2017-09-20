(ns sire-api.core
  (:gen-class)
  (:require
    [com.stuartsierra.component :as component]
    [sire-api.systems :refer [prod-system]]))

(defn -main
  "Start a production system."
  [& args]
  (println "chegou" args)
  (if (nil? (first args))
    (throw (ex-info "Provide the http port on first argument when executing production enviorment" {}))
    (component/start (prod-system {:port (read-string (first args))}))))