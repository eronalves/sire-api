(ns sire-api.ports.handler.pedestal-config
  (:require
    [com.stuartsierra.component :as component]
    [io.pedestal.http :as http]
    [sire-api.ports.handler.routes :as routes]))

(defn config "Return Pedestal service map" [env]
  (fn [] {::http/routes routes/routes
          ::http/type   :jetty
          ::http/port   (:port env)
          ::http/join?  false}))