package org.keirobm.rpgtilestotiled.adapter.impl.bufferedimage

import javafx.embed.swing.SwingFXUtils
import javafx.scene.image.Image
import org.keirobm.rpgtilestotiled.adapter.GenericImage
import org.keirobm.rpgtilestotiled.types.Rect
import java.awt.image.BufferedImage
import javax.swing.SwingUtilities

class BufferedImageImpl
(
        val image: BufferedImage
)
    : GenericImage
{

    override val width: Int
        get() = image.width

    override val height: Int
        get() = image.height

    override fun subImage(rect: Rect): GenericImage {
        val subImg = image.getSubimage(rect.x, rect.y, rect.width, rect.height)
        return BufferedImageImpl(subImg)
    }

    override fun copy(): GenericImage {
        val newImg = BufferedImage(image.width, image.height, image.type)
        val graphics = newImg.createGraphics()
        graphics.drawImage(image, 0, 0, null)
        graphics.dispose()
        return BufferedImageImpl(newImg)
    }

    override fun pasteContent(otherImg: GenericImage) {
        pasteContent(otherImg, 0, 0)
    }

    override fun pasteContent(otherImg: GenericImage, x: Int, y: Int) {
        val g2d = image.createGraphics()
        g2d.drawImage(otherImg.bufferedImage, x, y, otherImg.width, otherImg.height, null)
        g2d.dispose()
    }

    override fun dispose() {
        image.flush()
    }

    override val bufferedImage: BufferedImage
        get() = image

    override val fxImage: Image
        get() = SwingFXUtils.toFXImage(image, null)
}