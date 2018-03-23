package org.keirobm.rpgtilestotiled.tests

import org.keirobm.rpgtilestotiled.adapter.impl.bufferedimage.BufferedImageFactoryImpl
import org.keirobm.rpgtilestotiled.services.autotileprocessers.GenericAutoTileProcesser

fun main(args: Array<String>) {

    val imgFactory = BufferedImageFactoryImpl()
    val autoTileProcesser = GenericAutoTileProcesser(imgFactory)


}