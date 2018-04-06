package org.keirobm.rpgtilestotiled.services.tilesheettypes

import org.keirobm.rpgtilestotiled.services.autotileprocessers.*
import kotlin.reflect.KClass


private val autoTile = GenericAutoTileProcesser::class
private val autoWall = GenericAutoWallProcesser::class
private val normalTiles64x96 = ExtractTilesFromLargeImage64x96::class
private val normalTiles64x64 = ExtractTilesFromLargeImage64x64::class

/**
 * Determine all types of TileSheet that RPG Maker
 * has.
 * @author Angel Biedma
 */
enum class TileSheetType
(
    val rows: Int,
    val cols: Int,
    val processers: List<KClass<out TileProcesser>>
) {
    A1(4, 8, listOf(
            autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, normalTiles64x96,
            autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, normalTiles64x96,
            autoTile, autoTile, autoTile, normalTiles64x96, autoTile, autoTile, autoTile, normalTiles64x96,
            autoTile, autoTile, autoTile, normalTiles64x96, autoTile, autoTile, autoTile, normalTiles64x96
    )),
    A2(4, 8, listOf(
            autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile,
            autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile,
            autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile,
            autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile
    )),
    A3(4, 8, listOf(
            autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall,
            autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall,
            autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall,
            autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall
    )),
    A4(6, 8, listOf(
            autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile,
            autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall,
            autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile,
            autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall,
            autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile, autoTile,
            autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall, autoWall
    ))
    ;


}