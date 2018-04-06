package org.keirobm.rpgtilestotiled.services.autotileprocessers

import org.keirobm.rpgtilestotiled.adapter.GenericImageFactory


class ExtractTilesFromLargeImage64x64
(
        imgFactory: GenericImageFactory
)
    : GenericExtractTilesFromLargeImage(imgFactory)
{

    override val width: Int
        get() = 64

    override val height: Int
        get() = 64
}