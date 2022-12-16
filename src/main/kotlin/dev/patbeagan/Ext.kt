package dev.patbeagan

import java.math.BigInteger
import java.security.MessageDigest

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

inline fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val list = ArrayList<T>()
    for (item in this) {
        list.add(item)
        if (!predicate(item))
            break
    }
    return list
}

interface CanTraverse {
    fun <T> List<List<T>>.traverse(action: (each: T, x: Int, y: Int) -> Unit) {
        forEachIndexed { indexY, each ->
            each.forEachIndexed { indexX, tree ->
                action(tree, indexX, indexY)
            }
        }
    }
}

@JvmInline
value class List2D<T>(val grid: List<List<T>>): CanTraverse