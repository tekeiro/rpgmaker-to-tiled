package org.keirobm.rpgtilestotiled.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.DragEvent
import javafx.stage.FileChooser
import org.keirobm.rpgtilestotiled.adapter.GenericImage
import org.keirobm.rpgtilestotiled.adapter.GenericImageFactory
import org.keirobm.rpgtilestotiled.adapter.impl.bufferedimage.BufferedImageFactoryImpl

import org.keirobm.rpgtilestotiled.controllers.base.BaseController
import org.keirobm.rpgtilestotiled.services.tilesheettypes.TileSheetType
import org.keirobm.rpgtilestotiled.services.transformer.SpritesheetTransformer
import org.keirobm.rpgtilestotiled.utils.FXAlerts
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.roundToInt

class MainController : BaseController(){

    @FXML
    private lateinit var mnuImport: MenuItem
    @FXML
    private lateinit var mnuSave: MenuItem
    @FXML
    private lateinit var imgOriginal: ImageView
    @FXML
    private lateinit var imgPreview: ImageView
    @FXML
    private lateinit var cbTilesheetType: ComboBox<TileSheetType>
    @FXML
    private lateinit var lbCols: Label
    @FXML
    private lateinit var sldCols: Slider
    @FXML
    private lateinit var btnTransform: Button

    private val fileChooser = FileChooser()
    private val imgFactory: GenericImageFactory = BufferedImageFactoryImpl()

    private var originalImg: GenericImage? = null
    private var previewImg: GenericImage? = null
    private lateinit var transformer: SpritesheetTransformer

    @FXML
    fun initialize() {
        val tileSheetTypes = TileSheetType.values().toList()
        cbTilesheetType.items.addAll(tileSheetTypes)

        sldCols.valueProperty().addListener { _ ->
            lbCols.text = getSlideValue(sldCols).toString()
        }

        fileChooser.title = "Select an image with a RPG Maker Tilesheet"
        fileChooser.extensionFilters.addAll(FileChooser.ExtensionFilter("PNG images", "*.png"))

        transformer = SpritesheetTransformer(imgFactory)
    }


    private fun getSlideValue(slider: Slider)
        = slider.value.roundToInt()


    fun mnuImport_Click(actionEvent: ActionEvent) {
        var file: File? = null
        try {
            file = fileChooser.showOpenDialog(stage)
        }
        catch (ex: Exception) {
            ex.printStackTrace()
            val errorMsg = "An error ocurred. Try again later.\n Exception was: ${ex}"
            FXAlerts.error("Error in image open dialog", errorMsg)
        }

        file?.let {imgFile ->
            try {
                originalImg = imgFactory.openFromFile(imgFile)
            }
            catch (ex: Exception) {
                ex.printStackTrace()
                FXAlerts.error(
                        "Error when trying to open image",
                        "Exception ocurred when trying to open image. Exception was $ex")
            }

            if (originalImg != null) {
                putImageIntoImageView(imgOriginal, originalImg!!)
            }
        }
    }

    private fun putImageIntoImageView(imgView: ImageView, img: GenericImage) {
        imgView.image = img.fxImage
        imgView.fitWidth = img.width.toDouble()
        imgView.fitHeight = img.height.toDouble()
    }

    fun mnuSave_Click(actionEvent: ActionEvent) {
        previewImg?.let { prevImg ->
            var file: File? = null
            try {
                file = fileChooser.showSaveDialog(stage)
            }
            catch (ex: Exception) {
                ex.printStackTrace()
                val errorMsg = "An error ocurred. Try again later.\n Exception was: ${ex}"
                FXAlerts.error("Error when save image dialog was opening", errorMsg)
            }

            file?.let { imgFile ->
                imgFactory.saveToFile(prevImg, imgFile)
            }
        }
    }

    fun btnTransform_Click(actionEvent: ActionEvent) {
        originalImg?.let { origImg ->
            previewImg = transformer.transformSpriteSheet(
                    origImg,
                    getSlideValue(sldCols),
                    cbTilesheetType.value
            )
            putImageIntoImageView(imgPreview, previewImg!!)
        }
    }
}