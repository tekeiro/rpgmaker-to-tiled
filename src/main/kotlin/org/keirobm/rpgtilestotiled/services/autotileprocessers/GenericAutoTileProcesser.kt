package org.keirobm.rpgtilestotiled.services.autotileprocessers

import org.keirobm.rpgtilestotiled.TILE_HEIGHT
import org.keirobm.rpgtilestotiled.TILE_WIDTH
import org.keirobm.rpgtilestotiled.adapter.GenericImage
import org.keirobm.rpgtilestotiled.adapter.GenericImageFactory
import org.keirobm.rpgtilestotiled.types.Rect


/**
 * Process and returns all subimages associated
 * with a RPG Maker generic autotile of 64x96
 */
class GenericAutoTileProcesser
(
        val imgFactory: GenericImageFactory
) : TileProcesser
{

    companion object {

        private val MINI_TILE_WIDTH = 16
        private val MINI_TILE_HEIGHT = 16

        private val REGIONS = arrayOf(
                Pair(16, 48),   // 0 - No walls
                Pair(0, 32),    // 1 - Free Corner top-left
                Pair(32, 32),   // 2 - Free Corner top-right
                Pair(0, 64),    // 3 - Free Corner bottom-left
                Pair(32, 64),   // 4 - Free Corner bottom-right
                Pair(0, 48),    // 5 - Left wall
                Pair(32, 48),   // 6 - Right wall
                Pair(16, 32),   // 7 - Top wall
                Pair(16, 64),   // 8 - Bottom wall
                Pair(32, 0),    // 9 - Corner top-left
                Pair(48, 0),    // 10 - Corner top-right
                Pair(32, 16),   // 11 - Corner bottom-left
                Pair(48, 16)    // 12 - Corner bottom-right
        )

        private val CORNER_CROPS = mapOf(
                Pair(9,     Pair(0, 0)),
                Pair(10,    Pair(16, 0)),
                Pair(11,    Pair(0, 16)),
                Pair(12,    Pair(16, 16))
        )
    }


    override fun process(img: GenericImage): List<GenericImage> {
        val imgsResult = arrayListOf<GenericImage>()
        var blankImg: GenericImage? = null

        for ( (i, region) in REGIONS.withIndex() ) {
            if (i < 9) {
                val newImg = img.subImage(Rect(region.first, region.second,
                        TILE_WIDTH, TILE_HEIGHT)).copy()
                if (i == 0)
                    blankImg = newImg
                imgsResult.add(newImg)
            }
            else {
                blankImg?.let { blnkImg ->
                    val newImg = imgFactory.createEmpty(TILE_WIDTH, TILE_HEIGHT)
                    newImg.pasteContent(blnkImg)
                    val tmpImg = img.subImage(Rect(region.first, region.second,
                            MINI_TILE_WIDTH, MINI_TILE_HEIGHT)
                    ).copy()
                    val cornerCrop = CORNER_CROPS[i] ?: throw IllegalArgumentException("Index invalid $i must be in range from 9 to 12")
                    newImg.pasteContent(tmpImg, cornerCrop.first, cornerCrop.second)
                    imgsResult.add(newImg)
                    tmpImg.dispose()
                }
            }
        }

        return imgsResult
    }

    override val width: Int
        get() = 64

    override val height: Int
        get() = 96

}