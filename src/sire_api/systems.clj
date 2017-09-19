(ns sire-api.systems
  (:require [com.stuartsierra.component :as component]
            [reloaded.repl :as repl :refer [init start stop go reset]]
            [sire-api.ports.handler.pedestal-component :as pedestal-component]
            [io.pedestal.http :as http]
            [sire-api.ports.handler.routes :as routes]))

(defn pedestal-config-fn "Return Pedestal service map" []
  {::http/routes routes/routes
   ::http/type   :jetty
   ::http/port   3000
   ::http/join?  false})

(defn dev-system
  [env]
  (component/system-map

    :controller {:name "controller"}

    :pedestal
    (component/using
      (pedestal-component/pedestal pedestal-config-fn)
      [:controller])))