package com.kittydev.dynamicformbuilder

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.kittydev.kittydevdynamicformbuilde.KittyButton
import com.kittydev.kittydevdynamicformbuilde.KittyObject
import com.kittydev.kittydevdynamicformbuilde.KittyBuilder
import com.kittydev.kittydevdynamicformbuilde.KittyElements

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sas: ArrayList<KittyObject> = ArrayList()
        val mLinearLayout = findViewById<View>(R.id.TableLayout1) as LinearLayout
        val kittyBuilder: KittyBuilder = KittyBuilder(this, mLinearLayout)
        sas.add(KittyElements().setTag("daad").Type(KittyElements.Type.INPUTTEXT).isEnabled(true).Heading("head").SubHeading("sub").Hint("hint"))
        val sample: ArrayList<String> = ArrayList()
        sample.add("meow")
        sample.add("meow1")
        sample.add("meow2")
        sas.add(KittyElements().setTag("daad").Type(KittyElements.Type.DROPDOWN).Options(sample).isEnabled(true).Heading("head").SubHeading("sub").Hint("hint"))
        sas.add(KittyButton().setTitle("submit").setBackgroundColor(Color.parseColor("#1a237e")).setTextColor(Color.WHITE).setRunnable {})
        val kittyObjects: List<KittyObject> = sas
        kittyBuilder.build(kittyObjects)

    }
}