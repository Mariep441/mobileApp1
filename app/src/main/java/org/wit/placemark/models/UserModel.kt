package org.wit.placemark.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class  UserModel  (var id: Long = 0,
                        var firstName: String = "",
                        var lastName: String = "",
                        var email: String = "",
                        var password: String = "",
                        var admin: Boolean = false) : Parcelable