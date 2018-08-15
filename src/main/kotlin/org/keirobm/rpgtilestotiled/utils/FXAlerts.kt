package org.keirobm.rpgtilestotiled.utils

import javafx.scene.control.Alert

object FXAlerts {


    fun error(errorTitle: String, errorMsg: String) {
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = errorTitle
        alert.headerText = errorTitle
        alert.contentText = errorMsg

        alert.showAndWait()
    }

}