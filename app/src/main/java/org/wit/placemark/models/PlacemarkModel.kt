package org.wit.placemark.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlacemarkModel(var id: Long = 0,
                          var userid: Long = 0,
                          var title: String = "",
                          var description: String = "",
                          var images: MutableList<String> = mutableListOf(""),
                          var checkbox_visited: Boolean = false,
                          var lat : Double = 0.0,
                          var lng: Double = 0.0,
                          var zoom: Float = 0f) : Parcelable

val images = mutableListOf("https://picsum.photos/id/1043/5184/3456","https://picsum.photos/id/1039/6945/4635","https://picsum.photos/id/1038/3914/5863","https://picsum.photos/id/1037/5760/3840")



@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable

