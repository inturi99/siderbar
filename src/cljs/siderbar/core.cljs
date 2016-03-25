
(ns siderbar.core
  (:require-macros [secretary.core :refer [defroute]])
  (:require [goog.events :as events]
            [goog.dom :as dom]
            [goog.history.EventType :as EventType]
            [bouncer.core :as b]
            [bouncer.validators :as v]
            [secretary.core :as secretary]
            [reagent.core :as reagent :refer [atom render]])
  (:import goog.History))


(defn sidebar []
  [:div#sidebar-wrapper
   [:ul.sidebar-nav
    [:li.sidebar-brand>a {:href "#"} "LendinIndia"]
    [:li>a {:href "#"} "My Profile"]
    [:li>a {:href "#"} "Training"]
    [:li>a {:href "#"} "FAQ"]
    [:li>a {:href "#"} "Connections"]]])

(defn menu-toggle-render []
  [:div.btn.btn-default "Toggle Menu"])

(defn menu-toggle-did-mount [this]
  (.click (js/$ (reagent/dom-node this))
          (fn [e]
            (.preventDefault e)
            (.toggleClass (js/$ "#wrapper") "toggled") ;#wrapper will be the id of a div in our home component
            )))

(defn menu-toggle []
  (reagent/create-class {:reagent-render menu-toggle-render
                         :component-did-mount menu-toggle-did-mount}))
(defn home []
  [:div#wrapper
   [sidebar]
   [:div.page-content-wrapper>div.container-fluid>div.row>div.col-lg-12
    [menu-toggle]]])


(defn render-toggle []
  (reagent/render-component [home]
                            (.getElementById js/document "app")))

(render-toggle)

