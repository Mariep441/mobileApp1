package org.wit.placemark.models

interface UserStore {
  fun findAllUsers(): List<UserModel>
  fun findUserById(id:Long) : UserModel?
  fun findUserByEmail(email:String) : UserModel?
  fun createUser(user: UserModel)
  fun updateUser(user: UserModel)
  fun deleteUser(user: UserModel)
}