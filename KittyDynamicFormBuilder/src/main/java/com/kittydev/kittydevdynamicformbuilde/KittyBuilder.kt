package com.kittydev.kittydevdynamicformbuilde

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class KittyBuilder(private var context: Context, private var linearLayout: LinearLayout) {
    private var formMap: LinkedHashMap<String, KittyElements> = LinkedHashMap()
    private var viewMap: LinkedHashMap<String, View> = LinkedHashMap()
    private var selectedEditText: EditText? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun build(kittyObjects: List<KittyObject?>) {
        for (formObject in kittyObjects) if (formObject is KittyElements) {
            val tag = formObject.tagOrToString
            formMap[tag] = formObject
            addToLinearLayout(buildElement(formObject), formObject.params)
        } else if (formObject is KittyButton) {
            addToLinearLayout(buildButton(formObject), formObject.params)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun buildElement(kittyElements: KittyElements): View? {
        return when (kittyElements.type) {
            KittyElements.Type.INPUTTEXT -> {
                val InputTextView = LayoutInflater.from(context).inflate(R.layout.input, null)
                val InputTextLayout = TextInputLayout(context, null, R.id.input_et_layout)
                val InputTextLayoutHolder: TextInputLayout =
                    InputTextView.findViewById(R.id.input_et_layout)
                val InputTextEditText: TextInputEditText = InputTextView.findViewById(R.id.input_et)
                val InputTextHeading = InputTextView.findViewById<TextView>(R.id.input_et_Heading)
                val InputTextSubHeading =
                    InputTextView.findViewById<TextView>(R.id.input_et_SubHeading)
                val InputTextSpacer = InputTextView.findViewById<View>(R.id.input_et_spacer)
                val InputTextSubHeadingHolder =
                    InputTextView.findViewById<LinearLayout>(R.id.subHeadingHolder)
                if (kittyElements.subHeading.isNotEmpty()) {
                    InputTextSpacer.visibility = View.VISIBLE
                    InputTextSubHeadingHolder.visibility = View.VISIBLE
                    InputTextSubHeading.text = kittyElements.subHeading
                } else {
                    InputTextSpacer.visibility = View.GONE
                    InputTextSubHeadingHolder.visibility = View.GONE
                }
                if (kittyElements.heading.isNotEmpty()) {
                    InputTextHeading.visibility = View.VISIBLE
                    InputTextHeading.text = kittyElements.heading
                } else {
                    InputTextHeading.visibility = View.GONE
                }
                InputTextLayoutHolder.hint = kittyElements.hint
                InputTextEditText.isEnabled = kittyElements.enabled
                InputTextEditText.setText(kittyElements.value)
                InputTextEditText.inputType = InputType.TYPE_CLASS_TEXT
                InputTextEditText.setText(kittyElements.value)
                viewMap[kittyElements.tagOrToString] = InputTextEditText
                addViewToView(InputTextLayout, InputTextView)
                InputTextLayout
            }

            KittyElements.Type.DROPDOWN -> {
                val DropDownView = LayoutInflater.from(context).inflate(R.layout.input, null)
                val DropDownLayout = TextInputLayout(context, null, R.id.input_et_layout)
                val DropDownLayoutHolder: TextInputLayout =
                    DropDownView.findViewById(R.id.input_et_layout)
                val DropDownEditText: TextInputEditText = DropDownView.findViewById(R.id.input_et)
                val DropDownHeading = DropDownView.findViewById<TextView>(R.id.input_et_Heading)
                val DropDownSubHeading =
                    DropDownView.findViewById<TextView>(R.id.input_et_SubHeading)
                val DropDownSpacer = DropDownView.findViewById<View>(R.id.input_et_spacer)
                val DropDownSubHeadingHolder =
                    DropDownView.findViewById<LinearLayout>(R.id.subHeadingHolder)
                DropDownLayoutHolder.hint = kittyElements.hint
                DropDownEditText.isEnabled = kittyElements.enabled

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21
                    DropDownEditText.showSoftInputOnFocus = false;
                } else { // API 11-20
                    DropDownEditText.setTextIsSelectable(true);
                }

                DropDownEditText.inputType = InputType.TYPE_CLASS_TEXT
                DropDownEditText.setText(
                    kittyElements.optionsSelected.toString().replace("[", "").replace("]", "")
                )
                DropDownEditText.setOnClickListener {

                    val inputManager =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(DropDownEditText.windowToken, 0)

                    pickDialog(DropDownEditText, kittyElements)
                }
                if (kittyElements.subHeading.isNotEmpty()) {
                    DropDownSpacer.visibility = View.VISIBLE
                    DropDownSubHeadingHolder.visibility = View.VISIBLE
                    DropDownSubHeading.text = kittyElements.subHeading
                } else {
                    DropDownSpacer.visibility = View.GONE
                    DropDownSubHeadingHolder.visibility = View.GONE
                }
                if (kittyElements.heading.isNotEmpty()) {
                    DropDownHeading.visibility = View.VISIBLE
                    DropDownHeading.text = kittyElements.heading
                } else DropDownHeading.visibility = View.GONE
                viewMap[kittyElements.tagOrToString] = DropDownEditText
                addViewToView(DropDownLayout, DropDownView)
                DropDownLayout
            }

            KittyElements.Type.SLIDER -> {
                val SliderView = LayoutInflater.from(context).inflate(R.layout.slider, null)
                val Slider: Slider = SliderView.findViewById(R.id.Slider)
                val SliderLayout = TextInputLayout(context, null, R.id.Slider_Layout)
                val SliderHeading = SliderView.findViewById<TextView>(R.id.Slide_Heading)
                val SliderSubHeading =
                    SliderView.findViewById<TextView>(R.id.Slider_SubHeading)
                val SliderSpacer = SliderView.findViewById<View>(R.id.Slider_Spacer)
                val SliderSubHeadingHolder =
                    SliderView.findViewById<LinearLayout>(R.id.Slider_SubHeading_Holder)
                if (kittyElements.subHeading.isNotEmpty()) {
                    SliderSpacer.visibility = View.VISIBLE
                    SliderSubHeadingHolder.visibility = View.VISIBLE
                    SliderSubHeading.text = kittyElements.subHeading
                } else {
                    SliderSpacer.visibility = View.GONE
                    SliderSubHeadingHolder.visibility = View.GONE
                }
                if (kittyElements.heading.isNotEmpty()) {
                    SliderHeading.visibility = View.VISIBLE
                    SliderHeading.text = kittyElements.heading
                } else {
                    SliderHeading.visibility = View.GONE
                }
                addViewToView(SliderLayout, SliderView)
                viewMap[kittyElements.tagOrToString] = Slider
                SliderLayout
            }

            KittyElements.Type.RATING -> {
                val RatingView = LayoutInflater.from(context).inflate(R.layout.rating, null)
                val Rating = RatingView.findViewById<RatingBar>(R.id.Rating)
                val RatingLayout = TextInputLayout(context, null, R.id.RatingLayout)
                val RatingSubHeading =
                    RatingView.findViewById<TextView>(R.id.Rating_SubHeading)
                val RatingHeading = RatingView.findViewById<TextView>(R.id.Rating_Heading)
                val RatingSpacer = RatingView.findViewById<View>(R.id.Rating_Spacer)
                val RatingSubHeadingHolder =
                    RatingView.findViewById<LinearLayout>(R.id.Rating_SubHeading_Holder)
                if (kittyElements.subHeading.isNotEmpty()) {
                    RatingSpacer.visibility = View.VISIBLE
                    RatingSubHeadingHolder.visibility = View.VISIBLE
                    RatingSubHeading.text = kittyElements.subHeading
                } else {
                    RatingSpacer.visibility = View.GONE
                    RatingSubHeadingHolder.visibility = View.GONE
                }
                if (kittyElements.heading.isNotEmpty()) {
                    RatingHeading.visibility = View.VISIBLE
                    RatingHeading.text = kittyElements.heading
                } else {
                    RatingHeading.visibility = View.GONE
                }
                viewMap[kittyElements.tagOrToString] = Rating
                addViewToView(RatingLayout, RatingView)
                RatingLayout
            }
            else -> null
        }
    }

    private fun buildButton(formButton: KittyButton): View {
        val button = Button(context)

        button.text = formButton.title

        if (formButton.backgroundColor != null) {
            button.setBackgroundColor(formButton.backgroundColor!!)
        }
        if (formButton.textColor != null) {
            button.setTextColor(formButton.textColor!!)
        }
        if (formButton.runnable != null) {
            button.setOnClickListener { formButton.runnable!!.run() }
        }
        return button
    }

    private fun addToLinearLayout(view: View?, params: LinearLayout.LayoutParams?) {
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 8, 8, 8)
        view!!.layoutParams = params ?: layoutParams
        linearLayout.addView(view)
    }

    private fun addViewToView(parent: ViewGroup, child: View) {
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        child.layoutParams = layoutParams
        parent.addView(child)
    }

    private fun pickDialog(selectedEditText: EditText, selectedKittyElements: KittyElements) {
        val selectedElements: MutableList<String> = ArrayList(selectedKittyElements.optionsSelected)
        this.selectedEditText = selectedEditText
        val builder = AlertDialog.Builder(context)
        builder.setTitle("")
        builder.setSingleChoiceItems(
            selectedKittyElements.options.toTypedArray<CharSequence>(),
            selectedKittyElements.checkedValue,
            null
        )
        builder.setPositiveButton("OK") { dialogInterface, _ ->
            dialogInterface.dismiss()
            val selectedPosition = (dialogInterface as AlertDialog).listView.checkedItemPosition
            selectedElements.clear() //We only want one input
            selectedElements.add(selectedKittyElements.options[selectedPosition])
            selectedKittyElements.optionsSelected = selectedElements
            selectedEditText.setText(
                selectedKittyElements.optionsSelected.toString().replace("[", "").replace("]", "")
            )
        }
        builder.setNegativeButton("Cancel") { dialogInterface, _ -> dialogInterface.dismiss() }
        builder.show()
    }

    fun getFormValue(Tag: String): String {
        var myvalue = "null"
        for ((_, element) in formMap) {
            val view = viewMap[element.tagOrToString]
            if (element.tag == Tag) {
                when (element.type) {
                    KittyElements.Type.SLIDER -> {
                        val sl = view as Slider?
                        myvalue = sl!!.value.toString()
                    }
                    KittyElements.Type.INPUTTEXT -> {
                        val sl = view as EditText?
                        myvalue = sl!!.text.toString()
                    }
                    KittyElements.Type.DROPDOWN -> {
                        val sl = view as EditText?
                        myvalue = sl!!.text.toString()
                    }
                    KittyElements.Type.RATING -> {
                        val sl = view as RatingBar?
                        myvalue = sl!!.rating.toString()
                    }
                    else -> myvalue = "null"
                }
            }
        }
        return myvalue
    }

}

open class KittyObject
class KittyButton : KittyObject() {
    var title: String? = null
    var backgroundColor: Int? = null
    var textColor: Int? = null
    var runnable: Runnable? = null
    var params: LinearLayout.LayoutParams? = null
    fun setTitle(title: String?): KittyButton {
        this.title = title
        return this
    }

    fun setBackgroundColor(backgroundColor: Int?): KittyButton {
        this.backgroundColor = backgroundColor
        return this
    }

    fun setTextColor(textColor: Int?): KittyButton {
        this.textColor = textColor
        return this
    }

    fun setRunnable(runnable: Runnable?): KittyButton {
        this.runnable = runnable
        return this
    }
}