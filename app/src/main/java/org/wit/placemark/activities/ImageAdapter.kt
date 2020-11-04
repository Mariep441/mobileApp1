package org.wit.placemark.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ImageAdapter(activity: AppCompatActivity, private val itemsCount: Int) :
  FragmentStateAdapter(activity) {

  override fun getItemCount(): Int {
    return itemsCount
  }

  override fun createFragment(position: Int): Fragment {
    return ImageFragment.getInstance(position)
  }
}
