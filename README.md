# Kittydev Dynamic FormBulider

[![GitHub release](https://img.shields.io/github/release/padmadev/Kittydev-Dynamic-FormBulider)](https://GitHub.com/padmadev/Kittydev-Dynamic-FormBulider/releases/)


**Attention**: This library is Currently in Initial Stage with Limited elements.

 * Build `Dynamic Forms` Easily
 * Material Design Elements
 



Library projects
--------




```groovy
//In build.gradle(project)
allprojects {  
  repositories {  
        maven { url 'https://jitpack.io' }  
 }}


//In build.gradle(app)
dependencies {
	implementation 'com.github.padmadev:Kittydev-Dynamic-FormBulider:1.0'  
	implementation 'com.google.android.material:material:1.2.1'
}

//In Style.xml Add any Material Design Theme
parent="Theme.MaterialComponents.Light.NoActionBar.Bridge"
 

```

Current Support
--------

* Text Field (only text)
* DropDown / Single Select
* Slider
* Rating (Star) 
* Button


Usage
--------

**XML**

```xml
<?xml version="1.0" encoding="utf-8"?>  
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"  
  xmlns:app="http://schemas.android.com/apk/res-auto"  
  xmlns:tools="http://schemas.android.com/tools"  
  android:layout_width="match_parent"  
  android:layout_height="match_parent"  
  tools:context=".MainActivity">  
  
 <LinearLayout  android:id="@+id/kittylayout"  
  android:layout_width="match_parent"  
  android:layout_height="0dp"  
  android:orientation="vertical"  
  app:layout_constraintBottom_toBottomOf="parent"  
  app:layout_constraintEnd_toEndOf="parent"  
  app:layout_constraintStart_toStartOf="parent"  
  app:layout_constraintTop_toTopOf="parent" />  
  
</androidx.constraintlayout.widget.ConstraintLayout>
```
**Kotlin**

```kotlin
val FormArray: ArrayList<KittyObject> = ArrayList()  
val mLinearLayout = findViewById<View>(R.id.kittylayout) as LinearLayout  
val kittyBuilder: KittyBuilder = KittyBuilder(this, mLinearLayout)

//DropDown / Single Select
val options: ArrayList<String> = ArrayList()  
options.add("apple")  
options.add("orange")  
options.add("graph")

FormArray.add(KittyElements().setTag("elementid").Type(KittyElements.Type.DROPDOWN).Options(options).isEnabled(true).Heading("heading").SubHeading("subheading").Hint("hint"))

//Text Field
FormArray.add(KittyElements().setTag("elementid").Type(KittyElements.Type.INPUTTEXT).isEnabled(true).Heading("heading").SubHeading("subheading").Hint("hint"))

//Rating
FormArray.add(KittyElements().setTag("elementid").Type(KittyElements.Type.RATING).isEnabled(true))  

//Slider
FormArray.add(KittyElements().setTag("elementid").Type(KittyElements.Type.SLIDER).isEnabled(true))

//Button
FormArray.add(KittyButton().setTitle("submit").setBackgroundColor(Color.parseColor("#1a237e")).setTextColor(Color.WHITE).setRunnable {

//Get Data from Form Elements using elementid

kittyBuilder.getFormValue("elementid")

})

val kittyObjects: List<KittyObject> = FormArray  
kittyBuilder.build(kittyObjects)
```

**Java**
``` java	
  List < KittyObject > FormArray = new ArrayList < KittyObject > ();
  LinearLayout mLinearLayout = findViewById(R.id.kittylayout);
  FormBuilder KittyBuilder = new FormBuilder(this, mLinearLayout);

  //DropDown / Single Select
  List < String > options = new ArrayList < String > ();
  options.add("apple");
  options.add("orange");
  options.add("graph");

  FormArray.add(new FormElement().setTag("elementid").Hint("hint").Heading("heading").SubHeading("subheading").Type(FormElement.Type.DROPDOWN).Options(options));

  //Text Field
  FormArray.add(new FormElement().setTag("elementid").Hint("hint").Heading("heading").SubHeading("subheading").Type(FormElement.Type.INPUTTEXT).isEnabled(true));

  //Rating
  FormArray.add(new FormElement().setTag("elementid").Hint("hint").Heading("heading").SubHeading("subheading").Type(FormElement.Type.RATING).isEnabled(true));

  //Slider
  FormArray.add(new FormElement().setTag("elementid").Hint("hint").Heading("heading").SubHeading("subheading").Type(FormElement.Type.SLIDER).isEnabled(true));

  //Button
  FormArray.add(new FormButton().setTitle("submit").setBackgroundColor(Color.parseColor("#1a237e")).setTextColor(Color.WHITE).setRunnable(new Runnable() {
      @Override
      public void run() {

          //Get Data from Form Elements using elementid 
          String textValue = KittyBuilder.getFormValue("elementid");
      }
  }));

  KittyBuilder.build(FormArray);

```

License
-------

    Copyright 2020 Padma Dev

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
