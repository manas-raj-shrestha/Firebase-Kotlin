package com.droid.manasshrestha.firebase.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

open abstract class BaseActivity : AppCompatActivity() {

    var fireAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fireAuth = FirebaseAuth.getInstance()

        setContentView(getLayout())
    }

    abstract fun getLayout(): Int
}