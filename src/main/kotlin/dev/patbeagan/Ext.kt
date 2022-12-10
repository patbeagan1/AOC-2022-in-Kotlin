package dev.patbeagan

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * Reads lines from the given input txt file.
 */
fun String.obtainFile() = File("src", this)