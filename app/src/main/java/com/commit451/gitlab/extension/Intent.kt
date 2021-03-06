package com.commit451.gitlab.extension

import android.content.Intent
import org.parceler.Parcels

/**
 * Get Parcelable that was put in the intent using Parceler
 */
fun <T> Intent.getParcelerParcelable(key: String): T? {
    return Parcels.unwrap<T>(getParcelableExtra(key))
}

fun Intent.putParcelParcelableExtra(key: String, thing: Any?) {
    this.putExtra(key, org.parceler.Parcels.wrap(thing))
}
