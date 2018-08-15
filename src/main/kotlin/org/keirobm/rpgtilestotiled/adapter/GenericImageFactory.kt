package org.keirobm.rpgtilestotiled.adapter

import java.io.File


/**
 * Interface that its a Generic Image Factory
 * to create Generic Images.
 * Its implementation agnostic.
 */
interface GenericImageFactory {

    /**
     * Create a new Generic Image
     * of given size and empty
     */
    fun createEmpty(width: Int, height: Int): GenericImage

    /**
     * Open a Generic Image from
     * a [File].
     * It can throw a [java.io.IOException] or any
     * other [Exception] when trying to open image
     * @param file File where image is
     */
    fun openFromFile(file: File): GenericImage

    /**
     * Save a Generic Image
     * into a [File].
     * It can throw a [java.io.IOException] or any
     * other [Exception] when trying to save image
     * @param img Image to save
     * @param file File where image will be
     */
    fun saveToFile(img: GenericImage, file: File)

}