package org.wit.placemark.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_landing.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.placemark.R
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel
import org.wit.placemark.models.UserModel

/**
 * This section has been inspired from
 * https://handyopinion.com/login-activity-in-android-studio-kotlin-java/
 */

class LoginActivity : AppCompatActivity() {

    lateinit var app: MainApp
    lateinit var email: EditText
    lateinit var  password: EditText
    val MIN_PASSWORD_LENGTH = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        app = application as MainApp
        toolbar.title = title
        setSupportActionBar(toolbar)
        viewInitializations()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_landing, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_login -> startActivityForResult<LoginActivity>(200)
            R.id.item_signup -> startActivity<SignupActivity>()
        }
        return super.onOptionsItemSelected(item)
    }


    fun viewInitializations() {
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Checking if the input in form is valid
    fun validateInput(): Boolean {
        if (email.text.toString() == "") {
            email.error = "Please Enter Email"
            return false
        }
        if (password.text.toString() == "") {
            password.error = "Please Enter Password"
            return false
        }

        // checking the proper email format
        if (!isEmailValid(email.text.toString())) {
            email.error = "Please Enter Valid Email"
            return false
        }


        // checking minimum password Length
        if (password.text.length < MIN_PASSWORD_LENGTH) {
            password.error = "Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters"
            return false
        }
        return true
    }

    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }



    // Hook Click Event
    fun performLogin(v: View) {
        if (validateInput()) {

            // Input is valid, here send data to your server
            val email = email!!.text.toString()
            val password = password!!.text.toString()
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
            startActivity<PlacemarkListActivity>()
        }
    }
}