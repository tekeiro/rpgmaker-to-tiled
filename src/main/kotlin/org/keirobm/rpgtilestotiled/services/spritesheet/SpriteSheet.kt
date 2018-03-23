package org.keirobm.rpgtilestotiled.services.spritesheet

import org.keirobm.rpgtilestotiled.adapter.GenericImage
import org.keirobm.rpgtilestotiled.adapter.GenericImageFactory
import java.awt.Dimension


/**
 * Service that organize a group of [GenericImage]
 * into a single and big GenericImage in a
 * sprite sheet.
 */
class SpriteSheet
(
        val imgFactory: GenericImageFactory
)
{

    var maxNumberOfColumns: Int = 4
        get
        set(value) {
            if (value <= 0)
                throw IllegalArgumentException("Maximum number of columns must be greater than 0")

            field = value
        }


    private fun splitIntoRows(imgs: Collection<GenericImage>): List<List<GenericImage>> {
        val matrix = arrayListOf<List<GenericImage>>()

        var col = 0

        var rowImgs = arrayListOf<GenericImage>()
        matrix.add(rowImgs)

        for (img in imgs) {
            if (col < (maxNumberOfColumns-1)) {
                rowImgs.add(img)
                col++
            }
            else {
                col = 0

                rowImgs = arrayListOf()
                matrix.add(rowImgs)

                rowImgs.add(img)
            }
        }

        return matrix
    }


    private fun calculateDimension(matrix: List<List<GenericImage>>): Dimension {
        val dim = Dimension()
        dim.width = 0
        dim.height = 0

        dim.height = matrix.map { row ->
            row.first().height
        }.sum()

        dim.width = matrix.map { row ->
            row.map { col -> col.width }.sum()
        }.max() ?: dim.width

        return dim
    }


    /**
     * Generate a large Generic Image like a sheet
     * of images with maximum number of columns determinated
     * by [maxNumberOfColumns] property.
     * @param imgs Collection of Generic Image to put in sheet
     * @return Newly created [GenericImage] to hold all images
     */
    fun generate(imgs: Collection<GenericImage>): GenericImage {
        val matrix = splitIntoRows(imgs)
        val dimension = calculateDimension(matrix)

        val sheet = imgFactory.createEmpty(dimension.width, dimension.height)

        var r = 0
        matrix.forEach { row ->
            var c = 0
            row.forEach { col ->
                sheet.pasteContent(col, c, r)

                c += col.width
            }
            r += row.first().height
        }

        return sheet
    }

}