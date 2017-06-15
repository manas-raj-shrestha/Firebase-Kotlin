package com.droid.manasshrestha.firebase.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.droid.manasshrestha.firebase.R
import com.droid.manasshrestha.firebase.RvUserAdapter
import com.droid.manasshrestha.firebase.User
import com.droid.manasshrestha.firebase.base.BaseActivity
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.util.*

class UserListActivity : BaseActivity() {

    var rvUserAdapter: RvUserAdapter = RvUserAdapter(ArrayList<User>())

    override fun getLayout(): Int {
        return R.layout.activity_users
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUserList()
        setUserListener()
        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Contacts"
    }


    private fun setUserList() {
        rvUserList.layoutManager = LinearLayoutManager(this)
        rvUserList.adapter = rvUserAdapter
    }

    private fun setUserListener() {
        val postListener = (object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot?, p1: String?) {
                Log.e("rec","rec");
                rvUserAdapter.addData(dataSnapshot?.getValue(User::class.java)!!)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot?, s: String?) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot?) {
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot?, s: String?) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        val database = com.google.firebase.database.FirebaseDatabase.getInstance().reference
        database.child("users").addChildEventListener(postListener)

    }
}