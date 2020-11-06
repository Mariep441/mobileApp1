
package org.wit.placemark.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_placemark.*
import kotlinx.android.synthetic.main.activity_placemark_fragment.*
import org.wit.placemark.R
import org.wit.placemark.helpers.readImage
import org.wit.placemark.models.PlacemarkModel
import org.wit.placemark.helpers.readImageFromPath



class ImageFragment : Fragment() {

  companion object {
    const val ARG_POSITION = "position"

    fun getInstance(position: Int): Fragment {
      val imageFragment = ImageFragment()
      val bundle = Bundle()
      bundle.putInt(ARG_POSITION, position)
      imageFragment.arguments = bundle
      return imageFragment
    }
  }

  var placemark = PlacemarkModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.activity_placemark_fragment, container, false)
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val position = requireArguments().getInt(ARG_POSITION)
    val imageFilePath = getString(R.string.image_path, position)
    val imageNamesArray = requireContext().resources.getStringArray(R.array.image_names)
    setImageFromAssetsFile(requireContext(), imageFilePath)
    imageName.text = imageNamesArray[position]

  }


  private fun setImageFromAssetsFile(context: Context, imageFilePath: String) {
    var bitmap: Bitmap? = null
    val uri = Uri.parse(imageFilePath.toString())
    if (uri != null) {
      try {
        val parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r")
        val fileDescriptor = parcelFileDescriptor?.getFileDescriptor()
        bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor?.close()
      } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, getString(R.string.image_loading_error), Toast.LENGTH_SHORT).show()
      }
    }
  }
}
