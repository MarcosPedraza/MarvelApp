package com.marcospb.marvelapp.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object StringUtils {


    fun md5Hash(hash: String): String {
        var messageDigest: MessageDigest? = null
        return try {
            messageDigest = MessageDigest.getInstance("MD5")
            messageDigest.update(hash.toByteArray(), 0, hash.length)
            BigInteger(1, messageDigest.digest()).toString()
        } catch (ex: NoSuchAlgorithmException) {
            ex.printStackTrace()
            ""
        } catch (ex: Exception) {
            ex.printStackTrace()
            ""
        }

    }
}