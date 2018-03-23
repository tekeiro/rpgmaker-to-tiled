package org.keirobm.rpgtilestotiled.adapter

import org.keirobm.rpgtilestotiled.types.Rect
import java.awt.image.BufferedImage


/**
 * Generic interface that represents an
 * image and reunite common operations associated
 * with them
 */
interface GenericImage {

    /**
     * Width in pixels
     */
    val width: Int

    /**
     * Height in pixels
     */
    val height: Int

    /**
     * Returns a subimage of this GenericImage.
     * Subimage is only a snapshot, so it reflects changes from
     * parent image.
     * @param rect Rectangle of subimage.
     * @return A subimage that its a snapshot of this generic image.
     */
    fun subImage(rect: Rect): GenericImage

    /**
     * Returns a deep copy of this Generic Image.
     * @return A newly created generic image with
     * same content of this image.
     */
    fun copy(): GenericImage

    /**
     * Paste content of another [GenericImage]
     * into this generic image.
     * Target position will be (0, 0).
     * @param otherImg Other GenericImage to paste content
     */
    fun pasteContent(otherImg: GenericImage)


    /**
     * Paste content of another [GenericImage]
     * into this generic image at target position
     * given by x and y parameter
     * @param otherImg Other GenericImage to paste content
     * @param x X coordinate in upper-left corner
     * @para y Y coordinate in upper left corner
     */
    fun pasteContent(otherImg: GenericImage, x: Int, y: Int)


    /**
     * Dispose this generic image and release
     * all allocated memory.
     * This method will need be called when
     * works with this generic image ends.
     */
    fun dispose()

    /**
     * Returns generic image as an instance
     * of [BufferedImage]
     */
    val bufferedImage: BufferedImage

}