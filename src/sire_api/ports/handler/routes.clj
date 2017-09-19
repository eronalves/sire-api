(ns sire-api.ports.handler.routes
  (:require
    [io.pedestal.http.body-params :refer [body-params]]
    [io.pedestal.http.route :as route]
    [io.pedestal.http.route.definition :refer [defroutes]]
    [io.pedestal.http :refer [json-response]]
    [ring.util.http-response :refer :all]
    [sire-api.ports.handler.component-pedestal :refer [using-component use-component]]))

(defn home [request]
  (ok "Welcome to Sire API!"))

(defn all-pull-requests [request]
  (let [controller (use-component request :controller)]
    (json-response [{:id 1 :name "test"} {:id 2 :name "test2"}])))

(defn get-pull-request [request]
  (let [controller (use-component request :controller)
        id (get-in request [:path-params :id])]
    (json-response {:id id :name "test"})))

(defn run-pull-request [request]
  (let [controller (use-component request :controller)
        id (get-in request [:path-params :id])]
    (json-response {:message (str "Pull request " id " running")})))

(defn stop-pull-request [request]
  (let [controller (use-component request :controller)
        id (get-in request [:path-params :id])]
    (json-response {:message (str "Pull request " id " stopped")})))

(defroutes routes
  [[["/" {:get home} ^:interceptors [(body-params)]

    ["/pull-requests" {:get all-pull-requests} ^:interceptors [(using-component :controller)]
     ["/:id" {:get get-pull-request}
      ["/run" {:post run-pull-request}]
      ["/stop" {:post stop-pull-request}]]]]]])

