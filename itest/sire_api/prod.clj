(ns sire-api.prod
    (:use midje.sweet)
    (:require
    [environ.core :refer [env]]
    [com.stuartsierra.component :as component]
    [sire-api.systems :refer [prod-system]]
    [sire-api.core :refer [-main]]))

(defn run-env [port]
  (-main port))

(defn stop-env []
  (component/stop prod-system))