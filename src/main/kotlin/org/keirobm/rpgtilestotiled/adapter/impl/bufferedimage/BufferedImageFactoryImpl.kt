package org.keirobm.rpgtilestotiled.adapter.impl.bufferedimage

import org.keirobm.rpgtilestotiled.adapter.GenericImage
import org.keirobm.rpgtilestotiled.adapter.GenericImageFactory
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class BufferedImageFactoryImpl : GenericImageFactory {

    override fun createEmpty(width: Int, height: Int): GenericImage {
        val buffImg = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        return BufferedImageImpl(buffImg)
    }


    override fun openFromFile(file: File): GenericImage {
        val img = ImageIO.read(file)
        return BufferedImageImpl(img)
    }

    override fun saveToFile(img: GenericImage, file: File) {
        ImageIO.write(img.bufferedImage, "png", file)
    }

}