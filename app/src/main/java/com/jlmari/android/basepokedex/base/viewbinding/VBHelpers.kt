package com.jlmari.android.basepokedex.base.viewbinding

import androidx.viewbinding.ViewBinding

interface VBHelpers<VBI : ViewBinding> {

    fun getNullableBinding(): VBI?

    fun getBinding(): VBI

    fun usingBinding(block: ((binding: VBI) -> Unit))

    fun withBinding(block: (VBI.() -> Unit)): VBI

    fun isBindingAvailable(): Boolean
}
