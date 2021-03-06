package org.wit.placemark.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_placemark.*
import kotlinx.android.synthetic.main.activity_placemark_fragment.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.placemark.R
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.Location
import org.wit.placemark.models.PlacemarkModel
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import org.wit.placemark.helpers.readImage
import org.wit.placemark.helpers.showImagePicker

class PlacemarkActivity : AppCompatActivity(), AnkoLogger {

  var placemark = PlacemarkModel()
  lateinit var app: MainApp
  val IMAGE_REQUEST = 4
  val LOCATION_REQUEST = 2


  private lateinit var imageNamesArray: Array<String>
  private lateinit var imagePhotosArray: Array<String>


  //Define page change callback here
  private var imagePageChangeCallback = object : OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
      Toast.makeText(this@PlacemarkActivity, "IMAGE: $position", Toast.LENGTH_SHORT).show()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark)
    toolbarAdd.title = title
    setSupportActionBar(toolbarAdd)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    app = application as MainApp

    imageNamesArray = resources.getStringArray(R.array.image_names)


    ///Wire ImageAdapter with ViewPager2 here
    val imageAdapter = ImageAdapter(this, imageNamesArray.size)
    imageViewPager.adapter = imageAdapter

    //Register page change callback here
    imageViewPager.registerOnPageChangeCallback(imagePageChangeCallback)


    //Connect TabLayout and ViewPager2
    TabLayoutMediator(tabLayout, imageViewPager) { tab, position ->
      tab.text = imageNamesArray[position].substringBefore(' ')
    }.attach()

    var edit = false

    if (intent.hasExtra("placemark_edit")) {
      edit = true
      placemark = intent.extras?.getParcelable<PlacemarkModel>("placemark_edit")!!
      placemarkTitle.setText(placemark.title)
      description.setText(placemark.description)
      checkbox_visited.setChecked(placemark.checkbox_visited)
     btnAdd.setText(R.string.save_placemark)
   }


   btnAdd.setOnClickListener() {
     placemark.title = placemarkTitle.text.toString()
     placemark.description = description.text.toString()
     if (placemark.title.isEmpty()) { toast(R.string.enter_placemark_title)
     } else {
       if (edit) { app.placemarks.update(placemark.copy())
       } else { app.placemarks.create(placemark.copy())
       }
     }
     info("add Button Pressed: $placemarkTitle")
     setResult(AppCompatActivity.RESULT_OK)
     finish()
   }

   chooseImage.setOnClickListener {
     showImagePicker(this, IMAGE_REQUEST);
   }



   placemarkLocation.setOnClickListener {
     val location = Location(52.245696, -7.139102, 15f)
     if (placemark.zoom != 0f) {
       location.lat =  placemark.lat
       location.lng = placemark.lng
       location.zoom = placemark.zoom
     }
     startActivityForResult(intentFor<MapActivity>().putExtra("location", location), LOCATION_REQUEST)
   }
}

 //Menu

 override fun onCreateOptionsMenu(menu: Menu): Boolean {
   menuInflater.inflate(R.menu.menu_placemark, menu)
   return super.onCreateOptionsMenu(menu)
 }

 override fun onOptionsItemSelected(item: MenuItem): Boolean {
   when (item?.itemId) {
     R.id.item_delete -> {
       app.placemarks.delete(placemark)
       finish()
     }
     R.id.item_cancel -> {
       finish()
     }
   }
   return super.onOptionsItemSelected(item)
 }


 override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
   super.onActivityResult(requestCode, resultCode, data)
   when (requestCode) {
     IMAGE_REQUEST -> {
       if (data != null) {

              placemark.images[0] = data.getData().toString()


         placemarkImage.setImageBitmap(readImage(this, resultCode, data))
         chooseImage.setText(R.string.change_placemark_image)
       }
     }
     LOCATION_REQUEST -> {
       if (data != null) {
         val location = data.extras?.getParcelable<Location>("location")!!
         placemark.lat = location.lat
         placemark.lng = location.lng
         placemark.zoom = location.zoom
       }
     }
   }
 }

  fun onCheckboxClicked(view: View) {
    if (view is CheckBox) {
      val checked: Boolean = view.isChecked
      when (view.id) {
        R.id.checkbox_visited -> {
          if (checked) {
            placemark.checkbox_visited = true
          } else {
            placemark.checkbox_visited = false
          }
        }
      }
    }
  }


 override fun onDestroy() {
   super.onDestroy()
   //Unregister page change callback here
   imageViewPager.unregisterOnPageChangeCallback(imagePageChangeCallback)
 }
}

