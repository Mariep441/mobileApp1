package org.wit.placemark.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlacemarkModel(var id: Long = 0,
                          var userid: Long = 0,
                          var title: String = "",
                          var description: String = "",
                          var image1: String = "",
                          var image2: String = "",
                          var checkbox_visited: Boolean = false,
                          var lat : Double = 0.0,
                          var lng: Double = 0.0,
                          var zoom: Float = 0f) : Parcelable



@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable

