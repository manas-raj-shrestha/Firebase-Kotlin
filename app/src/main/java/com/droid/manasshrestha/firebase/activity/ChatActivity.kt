package com.droid.manasshrestha.firebase.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.droid.manasshrestha.firebase.ChatModel
import com.droid.manasshrestha.firebase.R
import com.droid.manasshrestha.firebase.base.BaseActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.util.*

class ChatActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_chat
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar()
        registerMessageListener()
        setOnClicks()
    }

    private fun setOnClicks() {

        ivSend.setOnClickListener({
            sendMessage(edtMessage?.text.toString())
        })

    }

    private fun sendMessage(message: String) {
        val databaseRef = FirebaseDatabase.getInstance().reference
        var path = "";
        val chars = intent.getStringExtra(KEY_FRIEND_USERNAME).toCharArray()
        val charsSelf = fireAuth?.currentUser?.email!!.toCharArray()

        for (i in 0 until if (chars.size > charsSelf.size) charsSelf.size - 1 else chars.size - 1) {

            if (chars[i].toInt() < charsSelf[i].toInt()) {
                path = String(chars) + String(charsSelf)
                break
            } else if (chars[i].toInt() > charsSelf[i].toInt()) {
                path = String(charsSelf) + String(chars)
                break
            }

            Log.e("char", chars[i].toInt().toString() + " " + charsSelf[i].toInt().toString())
        }

        Log.e("char", path + "path")

        val chatModel = ChatModel(Calendar.getInstance().timeInMillis, message, fireAuth?.currentUser?.email!!)

        databaseRef.child("messages").child(path.replace(".","")).push().setValue(chatModel)
    }

    private fun registerMessageListener() {

    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "General"
    }

    companion object {
        val KEY_FRIEND_USERNAME = "friend_username"

        fun getLaunchIntent(context: Context, friendUsername: String): Intent {
            var intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(KEY_FRIEND_USERNAME, friendUsername)
            return intent
        }
    }

}