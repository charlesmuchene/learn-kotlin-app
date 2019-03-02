package com.charlesmuchene.kotlin.learn.utilities

/**
 * On recycler view item click listener alias
 */
typealias OnItemClickListener<T> = (T) -> Unit

/**
 * Convert the given string to a comma-separated string
 *
 * @param array [Array] to convert
 * @return Comma-separated string
 */
fun stringifyArray(array: Array<String>) = array.filterNot { it.isEmpty() }.joinToString()