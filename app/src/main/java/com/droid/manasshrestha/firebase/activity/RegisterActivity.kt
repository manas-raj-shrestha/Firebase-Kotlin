package com.droid.manasshrestha.firebase.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.droid.manasshrestha.firebase.R
import com.droid.manasshrestha.firebase.User
import com.droid.manasshrestha.firebase.base.BaseActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnClicks()
    }

    private fun setOnClicks() {
        tvSignUp.setOnClickListener({
            if (validateFields())
                tvSignUp.setOnClickListener({
                    fireAuth?.createUserWithEmailAndPassword(edtUsername.text.toString(), edtPassword.text.toString())?.
                            addOnCompleteListener({
                                if (it.isSuccessful) {
                                    sendUserInfoToFire()
                                    navigateToChatBoard()
                                } else
                                    android.util.Log.e("Failure", "SignUp " + it.exception)
                            })
                })
        })
    }

    private fun sendUserInfoToFire() {
        val databaseRef = FirebaseDatabase.getInstance().reference
        val user = User(fireAuth?.currentUser?.email!!, fireAuth?.currentUser?.uid!!)
        databaseRef.child("users").push().setValue(user)
    }

    private fun navigateToChatBoard() {
        startActivity(Intent(this, UserListActivity::class.java))
    }

    fun validateFields(): Boolean {
        var valid = true

        if (TextUtils.isEmpty(edtUsername.text)) {
            valid = !valid
            edtUsername.error = "Username cannot be empty"
        } else if (TextUtils.isEmpty(edtPassword.text)) {
            valid = !valid
            edtPassword.error = "Password cannot be empty"
        } else if (TextUtils.isEmpty(edtConfirmPassword.text)) {
            valid = !valid
            edtConfirmPassword.error = "Password cannot be empty"
        } else if (!edtConfirmPassword.text.toString().contentEquals(edtPassword.text)) {
            valid = !valid
            edtConfirmPassword.error = "Passwords do not match"
        }

        return valid
    }

}