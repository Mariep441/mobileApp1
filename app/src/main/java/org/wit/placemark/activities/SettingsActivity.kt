package org.wit.placemark.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.*
import org.wit.placemark.R
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_landing.*
import org.wit.placemark.main.MainApp


class SettingsActivity : AppCompatActivity() {

    lateinit var app: MainApp

    lateinit var firstName: EditText
    lateinit var lastName:EditText
    lateinit var email: EditText
    lateinit var password:EditText
    val id = 5678241182792043635;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    // Menu

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<PlacemarkActivity>(200)
            R.id.item_map -> startActivity<PlacemarkMapsActivity>()
            R.id.item_settings -> startActivity<SettingsActivity>()
            R.id.item_logout -> finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }




    // Hook Click Event

    fun performSignUp (view: View) {

            // Input is valid, here send data to your server

            val firstName = firstName.text.toString()
            val lastName = lastName.text.toString()
            val email = email.text.toString()
            val password = password.text.toString()

            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show()
            startActivity<PlacemarkListActivity>()


    }

}