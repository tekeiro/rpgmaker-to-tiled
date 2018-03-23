package org.keirobm.rpgtilestotiled.controllers.base

import javafx.stage.Stage

abstract class BaseController {
    protected lateinit var stage: Stage

    fun passStage(stage: Stage) {
        this.stage = stage
    }

}