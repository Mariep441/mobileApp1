package org.wit.placemark.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class UserMemStore : UserStore, AnkoLogger {

  val users = ArrayList<UserModel>()


  override fun findAllUsers(): List<UserModel> {
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
    user.id = getId()
    users.add(user)
    logAllUsers()
  }

  override fun updateUser(user: UserModel) {
    var foundUser: UserModel? = users.find { p -> p.id == user.id }
    if (foundUser != null) {
      foundUser.firstName = user.firstName
      foundUser.lastName = user.lastName
      foundUser.email = user.email
      foundUser.password = user.password
      logAllUsers();
    }
  }

  override fun deleteUser(user: UserModel) {
    users.remove(user)
  }

  fun logAllUsers() {
    users.forEach { info("${it}") }
  }

}