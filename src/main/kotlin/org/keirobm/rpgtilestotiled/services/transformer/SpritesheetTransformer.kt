package org.keirobm.rpgtilestotiled.services.transformer

import org.keirobm.rpgtilestotiled.adapter.GenericImage
import org.keirobm.rpgtilestotiled.adapter.GenericImageFactory
import org.keirobm.rpgtilestotiled.services.spritesheet.SpriteSheet
import org.keirobm.rpgtilestotiled.services.tilesheettypes.TileSheetType
import org.keirobm.rpgtilestotiled.types.Rect

class SpritesheetTransformer
(
        val imgFactory: GenericImageFactory
){

    fun transformSpriteSheet(
            img: GenericImage, cols: Int, tileSheetType: TileSheetType
    ): GenericImage
    {

        val imgsResults = arrayListOf<GenericImage>()

        var x = 0
        var y = 0

        for ((i, tileProcesserKlass) in tileSheetType.processers.withIndex()) {
            val c = i % tileSheetType.cols
            val r = i / tileSheetType.cols

            if (c == 0)
                x = 0

            val tileProcesser = tileProcesserKlass.constructors.first().call(imgFactory)
            val subImgRect = Rect(x, y, tileProcesser.width, tileProcesser.height)
            val subImg = img.subImage(subImgRect)

            imgsResults.addAll(tileProcesser.process(subImg))

            if (c == (tileSheetType.cols-1))
                y += tileProcesser.height
            x += tileProcesser.width
        }

        val spriteSheet = SpriteSheet(imgFactory)
        spriteSheet.maxNumberOfColumns = cols
        val spriteSheetImg = spriteSheet.generate(imgsResults)

        for (imgTmp in imgsResults)
            imgTmp.dispose()

        return spriteSheetImg
    }

}