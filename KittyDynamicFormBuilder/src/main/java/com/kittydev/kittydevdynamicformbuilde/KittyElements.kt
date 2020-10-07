package com.kittydev.kittydevdynamicformbuilde

import android.widget.LinearLayout
import java.util.*

/**
 * Created by PadmaDev on 07/10/2020
 */
class KittyElements : KittyObject() {
    var tag: String? = null
    var type: Type? = null
    private val title: String? = null
    var value: String? = null
    var hint: String? = null
    var heading = ""
    var subHeading = ""
    var options: List<String>
    var optionsSelected: List<String>
    var required = false
    var enabled = true
    val params: LinearLayout.LayoutParams? = null
    fun setTag(tag: String?): KittyElements {
        this.tag = tag
        return this
    }

    val tagOrToString: String
        get() = if (tag == null) toString() else tag!!

    fun Type(type: Type?): KittyElements {
        this.type = type
        return this
    }

    fun Hint(hint: String?): KittyElements {
        this.hint = hint
        return this
    }

    fun Heading(hint: String): KittyElements {
        heading = hint
        return this
    }

    fun SubHeading(hint: String): KittyElements {
        subHeading = hint
        return this
    }

    fun Options(optionslist: List<String>): KittyElements {
        this.options = optionslist
        return this
    }

    fun isRequired(required: Boolean): KittyElements {
        this.required = required
        return this
    }

    fun isEnabled(enabled: Boolean): KittyElements {
        this.enabled = enabled
        return this
    }

    val checkedValue: Int
        get() {
            if (optionsSelected.isNotEmpty()) {
                val element = optionsSelected[0]
                if (options.contains(element)) {
                    return options.indexOf(element)
                }
            }
            return 0
        }

    enum class Type {
        INPUTTEXT, DROPDOWN, SLIDER, RATING
    }

    init {
        options = ArrayList()
        optionsSelected = ArrayList()
    }
}