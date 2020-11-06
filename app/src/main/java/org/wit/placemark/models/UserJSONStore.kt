package org.wit.placemark.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.placemark.helpers.*
import java.util.*

val USER_JSON_FILE = "users.json"
val user_gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val userlistType = object : TypeToken<java.util.ArrayList<UserModel>>() {}.type


class UserJSONStore : UserStore, AnkoLogger {

  val context: Context
  var users = mutableListOf<UserModel>()

  constructor (context: Context) {
    this.context = context
    if (exists(context, USER_JSON_FILE)) {
      deserializeUser()
    }
  }

  override fun findAllUsers(): MutableList<UserModel> {
    return users
  }


  override fun findUserById(id:Long) : UserModel? {
    val foundUser: UserModel? = users.find { it.id == id }
    return foundUser
  }

  override fun findUserByEmail(email:String) : UserModel? {
    val foundUser: UserModel? = users.find { it.email == email }
    return foundUser
  }


  override fun createUser(user: UserModel) {
    user.id = generateRandomId()
    users.add(user)
    serializeUser()
  }

  override fun updateUser(user: UserModel) {
    val usersList = findAllUsers() as ArrayList<UserModel>
    var foundUser: UserModel? = usersList.find { u -> u.id == user.id }
    if ( foundUser != null) {
      foundUser.firstName =user.firstName
      foundUser.lastName =user.lastName
      foundUser.email =user.email
      foundUser.password =user.password
    }
    serializeUser()
  }

  override fun deleteUser(user: UserModel) {
    users.remove(user)
    serializeUser()
  }

  private fun serializeUser() {
    val jsonString = gsonBuilder.toJson(users, userlistType)
    write(context, USER_JSON_FILE, jsonString)
  }

  private fun deserializeUser() {
    val jsonString = read(context, USER_JSON_FILE)
    users = Gson().fromJson(jsonString, userlistType)
  }



}
