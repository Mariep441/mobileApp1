package org.wit.placemark.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_landing.toolbar
import kotlinx.android.synthetic.main.activity_placemark.*
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.*
import org.wit.placemark.R
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel
import org.wit.placemark.models.UserModel


class SettingsActivity : AppCompatActivity(), AnkoLogger {

    var user = UserModel()
    lateinit var app: MainApp
    var edit = false;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setSupportActionBar(toolbar)
        info("SignUp Activity started..")

        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        app = application as MainApp

        if (intent.hasExtra("user_edit")) {
            edit = true
            user = intent.extras?.getParcelable<UserModel>("user_edit")!!
            firstName.setText(user.firstName)
            lastName.setText(user.lastName)
            email.setText(user.email)
            password.setText(user.password)
            if (user.id != null) {
                chooseImage.setText(R.string.edit_user_details)
            }
            btnSignup.setText(R.string.save_user)
        }


        btnSignup.setOnClickListener() {
            user.firstName = firstName.text.toString()
            user.lastName = lastName.text.toString()
            user.email = email.text.toString()
            user.password = password.text.toString()
            if (user.password.isEmpty()) {
                toast(R.string.enter_user_password)
            } else {
                app.users.createUser(user.copy())
            }
            setResult(AppCompatActivity.RESULT_OK)
            finish()
            startActivity<PlacemarkListActivity>()
        }
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

}