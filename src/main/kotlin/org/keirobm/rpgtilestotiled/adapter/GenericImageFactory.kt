package org.keirobm.rpgtilestotiled.adapter


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

}