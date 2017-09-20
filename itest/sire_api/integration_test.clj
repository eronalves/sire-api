(ns sire-api.integration_test
    (:use midje.sweet)
    (:require
    [org.httpkit.client :as http]
    [cheshire.core :refer :all]
    [environ.core :refer [env]]
    [sire-api.dev :as dev]
    [sire-api.prod :as prod]))

(def port (read-string (env :http-port)))

(defn parse-body [body]
  (parse-string body true))

(defn create-url [port url]
  (str "http://localhost:" port url))

(defn create-url-pull-requests [port api-version url id]
  (str (create-url port (str "/api/" api-version "/pull-requests/" id)) url))

(defn do-home [port]
  @(http/get (create-url port "") {:as :text}))

(def enviroments
  [{:env "development"
    :start dev/run-env
    :stop dev/stop-env}])
   ;{:env "production"
   ; :start prod/run-env
   ; :stop prod/stop-env}])

(facts "Integration tests" :it
  (doseq [enviroment enviroments]
    (let [fn-run (:start enviroment)
          fn-stop (:stop enviroment)]

      ; RUN
      (fn-run (str port))

      (fact {:midje/description (str "Enviorment: " (:env enviroment))}

        (fact "GET '/' endpoint must be return a message to access /api-docs"
              (let [{:keys [status headers body error] :as resp} (do-home port)]
                200 => status
                "Welcome to Sire API!" => body))

        (fact "Random endpoint must be not found message"
              (let [{:keys [status headers body error] :as resp}
                    @(http/get (create-url port "/batman-cave") {:as :text})]
                404 => status
                "Not Found" => body))

        ;  STOP
        (fn-stop)

        (fact "GET '/' endpoint must be return an error response"
              (let [{:keys [status headers body error] :as resp} (do-home port)]
                status => nil
                java.net.ConnectException => (type error)
                "Connection refused" => (.getMessage error)))))))




