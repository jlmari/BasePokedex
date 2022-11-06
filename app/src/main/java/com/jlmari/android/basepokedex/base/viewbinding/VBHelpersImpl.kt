package com.jlmari.android.basepokedex.base.viewbinding

import androidx.viewbinding.ViewBinding

/**
 * [VBHelpersImpl] is a manager class for a view binding instance.
 * When an object of this class is created, it initializes a nullable instance of variable
 * [_binding] of type T:ViewBinding. Later on, when the actual binding instance is available, this
 * [_binding] variable could be updated with the new instance via [updateBinding] function as
 * `helperImplObj.updateBinding(childClassBinding)`.
 *
 * This class is used for [delegation by implementation](https://kotlinlang.org/docs/reference/delegation.html#implementation-by-delegation)
 *
 * [More info in medium article](https://anshsachdevawork.medium.com/using-view-binding-with-convenience-87979c680707)
*/
class VBHelpersImpl<T : ViewBinding> : VBHelpers<T> {

    /** Private variables */
    private var _binding: T? = null

    /**
     * Implemented functions coming from [VBHelpers] interface. These functions are accessible
     * both via [VBHelpersImpl] instance as well as classes implementing [VBHelpersImpl] by
     * delegation.
     */
    override fun getNullableBinding(): T? = _binding

    override fun getBinding(): T = _binding ?: error(ERROR_BINDING_IS_NULL)

    override fun usingBinding(block: (binding: T) -> Unit) {
        if (_binding == null) {
            error(ERROR_BINDING_IS_NULL)
        } else {
            _binding?.let { block.invoke(it) }
        }
    }

    override fun withBinding(block: T.() -> Unit): T {
        if (_binding == null) {
            error(ERROR_BINDING_IS_NULL)
        } else {
            val bindingAfterRunning: T? = _binding?.apply { block.invoke(this) }
            return bindingAfterRunning ?: error(ERROR_BINDING_IS_NULL)
        }
    }

    override fun isBindingAvailable(): Boolean {
        return _binding != null
    }

    /**
     * More functions: only accessible via [VBHelpersImpl] instance.
     * And this way, classes extending won't be able to access these methods.
     */
    fun updateBinding(bindInstance: T) {
        this._binding = bindInstance
    }

    fun destroyBinding() {
        this._binding = null
    }

    companion object {
        // View Binding Error Messages
        const val ERROR_BINDING_IS_NULL = "Binding not found"
    }
}