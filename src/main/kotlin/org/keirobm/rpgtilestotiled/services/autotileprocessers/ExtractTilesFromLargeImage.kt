package org.keirobm.rpgtilestotiled.services.autotileprocessers

import org.keirobm.rpgtilestotiled.TILE_HEIGHT
import org.keirobm.rpgtilestotiled.TILE_WIDTH
import org.keirobm.rpgtilestotiled.adapter.GenericImage
import org.keirobm.rpgtilestotiled.adapter.GenericImageFactory
import org.keirobm.rpgtilestotiled.types.Rect


/**
 * Extracts tiles of a size TILE_WIDTHxTILE_HEIGHT
 * from a large [GenericImage]
 */
class ExtractTilesFromLargeImage
(
        val imgFactory: GenericImageFactory
)
    : TileProcesser
{


    private fun isInBoundsOfImage(img: GenericImage, rect: Rect): Boolean {
        if (rect.x >= img.width)
            return false
        if (rect.y >= img.height)
            return false
        if ( (rect.x + rect.width) > img.width)
            return false
        if ( (rect.y + rect.height) > img.height)
            return false
        return true
    }

    override fun process(img: GenericImage): List<GenericImage> {
        val imgsResult = arrayListOf<GenericImage>()

        var r = 0
        while (r < img.height) {
            var c = 0
            while (c < img.width) {
                val rect = Rect(c, r, TILE_WIDTH, TILE_HEIGHT)
                if (isInBoundsOfImage(img, rect)) {
                    val subImg = img.subImage(rect).copy()
                    imgsResult.add(subImg)
                }

                c += TILE_WIDTH
            }

            r += TILE_HEIGHT
        }

        return imgsResult
    }

}