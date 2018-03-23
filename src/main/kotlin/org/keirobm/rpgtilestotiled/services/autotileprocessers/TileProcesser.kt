package org.keirobm.rpgtilestotiled.services.autotileprocessers

import org.keirobm.rpgtilestotiled.adapter.GenericImage


/**
 * Interface to group all Tiles Processer
 */
interface TileProcesser {

    fun process(img: GenericImage): List<GenericImage>

}