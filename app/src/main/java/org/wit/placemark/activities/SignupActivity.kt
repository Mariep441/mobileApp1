package org.wit.placemark.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.*
import org.wit.placemark.R
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_landing.*
import org.wit.placemark.main.MainApp


class SignupActivity : AppCompatActivity() {

    lateinit var firstName: EditText
    lateinit var lastName:EditText
    lateinit var email: EditText
    lateinit var password:EditText
    val MIN_PASSWORD_LENGTH = 6;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
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
        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Checking if the input in form is valid
    fun validateInput(): Boolean {
        if (firstName.text.toString().equals("")) {
            firstName.setError("Please Enter First Name")
            return false
        }
        if (lastName.text.toString().equals("")) {
            lastName.setError("Please Enter Last Name")
            return false
        }
        if (email.text.toString().equals("")) {
            email.setError("Please Enter Email")
            return false
        }
        if (password.text.toString().equals("")) {
            password.setError("Please Enter Password")
            return false
        }

        // checking the proper email format
        if (!isEmailValid(email.text.toString())) {
            email.setError("Please Enter Valid Email")
            return false
        }

        // checking minimum password Length
        if (password.text.length < MIN_PASSWORD_LENGTH) {
            password.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters")
            return false
        }
        return true
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Hook Click Event

    fun performSignUp (view: View) {
        if (validateInput()) {

            // Input is valid, here send data to your server

            val firstName = firstName.text.toString()
            val lastName = lastName.text.toString()
            val email = email.text.toString()
            val password = password.text.toString()

            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show()
            startActivity<PlacemarkListActivity>()

        }
    }

}