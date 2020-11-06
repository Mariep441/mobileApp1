package org.wit.placemark.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.placemark.models.*

class MainApp : Application(), AnkoLogger {

  lateinit var placemarks: PlacemarkStore
  lateinit var users: UserStore

  override fun onCreate() {
    super.onCreate()
    placemarks = PlacemarkJSONStore(applicationContext)
    users = UserJSONStore(applicationContext)
    info("Placemark started")
  }
}