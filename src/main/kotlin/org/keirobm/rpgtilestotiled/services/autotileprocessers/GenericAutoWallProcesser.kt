package org.keirobm.rpgtilestotiled.services.autotileprocessers

import org.keirobm.rpgtilestotiled.TILE_HEIGHT
import org.keirobm.rpgtilestotiled.TILE_WIDTH
import org.keirobm.rpgtilestotiled.adapter.GenericImage
import org.keirobm.rpgtilestotiled.adapter.GenericImageFactory
import org.keirobm.rpgtilestotiled.types.Rect

/**
 * Process and returns all subimages associated
 * with a RPG Maker generic autowall of 64x64
 */
class GenericAutoWallProcesser
(
        val imgFactory: GenericImageFactory
) : TileProcesser
{

    companion object {

        private val REGIONS = arrayOf(
                Pair(16, 16),   // 0 - No walls
                Pair(0, 0),    // 1 - Free Corner top-left
                Pair(32, 0),   // 2 - Free Corner top-right
                Pair(0, 32),    // 3 - Free Corner bottom-left
                Pair(32, 32),   // 4 - Free Corner bottom-right
                Pair(0, 16),    // 5 - Left wall
                Pair(32, 16),   // 6 - Right wall
                Pair(16, 0),   // 7 - Top wall
                Pair(16, 32)   // 8 - Bottom wall
        )

    }

    override fun process(img: GenericImage): List<GenericImage> {
        val imgsResult = arrayListOf<GenericImage>()

        for ( (i, region) in REGIONS.withIndex() ) {
            val newImg = img.subImage(Rect(region.first, region.second,
                    TILE_WIDTH, TILE_HEIGHT)).copy()
            imgsResult.add(newImg)
        }

        return imgsResult
    }

    override val width: Int
        get() = 64

    override val height: Int
        get() = 64

}