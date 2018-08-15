package org.keirobm.rpgtilestotiled.services.autotileprocessers

import org.keirobm.rpgtilestotiled.adapter.GenericImage
import org.keirobm.rpgtilestotiled.adapter.GenericImageFactory


/**
 * Extracts tiles of a size TILE_WIDTHxTILE_HEIGHT
 * from a large [GenericImage]
 */
class ExtractTilesFromLargeImage64x96
(
        imgFactory: GenericImageFactory
)
    : GenericExtractTilesFromLargeImage(imgFactory)
{

    override val width: Int
        get() = 64

    override val height: Int
        get() = 96

}