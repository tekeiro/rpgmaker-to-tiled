package org.keirobm.rpgtilestotiled.adapter.impl.bufferedimage

import org.keirobm.rpgtilestotiled.adapter.GenericImage
import org.keirobm.rpgtilestotiled.adapter.GenericImageFactory
import java.awt.image.BufferedImage

class BufferedImageFactoryImpl : GenericImageFactory {

    override fun createEmpty(width: Int, height: Int): GenericImage {
        val buffImg = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        return BufferedImageImpl(buffImg)
    }


}