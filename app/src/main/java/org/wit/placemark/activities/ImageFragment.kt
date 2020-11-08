
package org.wit.placemark.activities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_placemark_fragment.*
import org.wit.placemark.R
import org.wit.placemark.models.PlacemarkModel
import java.io.IOException


class ImageFragment : Fragment() {

  var placemark = PlacemarkModel()

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


  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.activity_placemark_fragment, container, false)
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val position = requireArguments().getInt(ARG_POSITION)
    val imagePhotosArray = placemark.images
    val imageFilePath = getString(R.string.image_path)
    val imageNamesArray = requireContext().resources.getStringArray(R.array.image_names)
    setImageFromAssetsFile(requireContext(), imageFilePath)
    imageName.text = imageNamesArray[position]
  }


  private fun setImageFromAssetsFile(context: Context, imageFilePath: String)  {
    val imageBitmap: Bitmap?
    val uri = Uri.parse(imageFilePath)
    if (uri != null) {
      try {
        val parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r")
        val fileDescriptor = parcelFileDescriptor?.getFileDescriptor()
        imageBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor?.close()
        placemarkImage.setImageBitmap(imageBitmap)
      } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, getString(R.string.image_loading_error), Toast.LENGTH_SHORT).show()
      }
    }
  }
}