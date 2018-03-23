package org.keirobm.rpgtilestotiled.services.tilesheettypes

import org.keirobm.rpgtilestotiled.services.autotileprocessers.TileProcesser
import org.keirobm.rpgtilestotiled.types.Rect


/**
 * Determine all types of TileSheet that RPG Maker
 * has.
 * @author Angel Biedma
 */
enum class TileSheetType
(

) {

    ;


    abstract val regions: List<Pair<Rect, TileProcesser>>
}