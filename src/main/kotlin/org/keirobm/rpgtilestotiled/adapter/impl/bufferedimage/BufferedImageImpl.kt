package org.keirobm.rpgtilestotiled.adapter.impl.bufferedimage

import org.keirobm.rpgtilestotiled.adapter.GenericImage
import org.keirobm.rpgtilestotiled.types.Rect
import java.awt.image.BufferedImage

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
        val raster = image.copyData(null)
        val newImage = BufferedImage(image.colorModel, raster, image.isAlphaPremultiplied, null)
        return BufferedImageImpl(newImage)
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
}