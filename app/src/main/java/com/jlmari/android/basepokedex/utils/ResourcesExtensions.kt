package com.jlmari.android.basepokedex.utils

import android.content.res.Resources
import androidx.annotation.IntegerRes
import com.jlmari.android.basepokedex.R

/**
 * Method that indicates if the device is a mobile phone or not.
 *
 * @return The [Boolean] that indicates if is mobile
 */
fun Resources.isMobile(): Boolean = getBoolean(R.bool.mobile_mode)

/**
 * Method that indicates if the device is a tablet or not.
 *
 * @return The [Boolean] that indicates if is tablet
 */
fun Resources.isTablet(): Boolean = !isMobile()

/**
 * Method to get an integer [Int] value based on its [IntegerRes] identifier.
 *
 * @param integerResId The integer-resource identifier
 * @return The desired [Int]
 */
fun Resources.getInt(@IntegerRes integerResId: Int) = getInteger(integerResId)

/**
 * Method to get a long [Long] value based on its [IntegerRes] identifier.
 *
 * @param integerResId The integer-resource identifier
 * @return The desired [Int] converted to [Long]
 */
fun Resources.getLong(@IntegerRes integerResId: Int) = getInt(integerResId).toLong()
