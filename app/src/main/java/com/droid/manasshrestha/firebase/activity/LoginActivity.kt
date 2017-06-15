package com.droid.manasshrestha.firebase.activity

import android.content.Intent
import com.droid.manasshrestha.firebase.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : com.droid.manasshrestha.firebase.base.BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun onStart() {
        super.onStart()
        val currentUser = fireAuth!!.currentUser

        if (currentUser == null) {
            android.util.Log.e("not logged", "not logged")
        } else
           navigateToChatBoard()

        edtPassword.setText("bbbbbb")
        edtUsername.setText("b@b.com")
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        tvSignUp.setOnClickListener({
            startActivity(Intent(this, RegisterActivity::class.java))
        })

        tvSignIn.setOnClickListener({
            fireAuth?.signInWithEmailAndPassword(edtUsername.text.toString(), edtPassword.text.toString())?.
                    addOnCompleteListener({
                        if (it.isSuccessful) {
                            android.util.Log.e("Success", "SignIn")
                            navigateToChatBoard()
//                            val database = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("users")
//
//                            val test = com.droid.manasshrestha.firebase.Name("asd", 39)
////                            val testList: ArrayList<Name> = ArrayList<Name>()
////
////                            testList?.add(test)
//
//                            database.child(fireAuth?.currentUser?.uid).child("hello").push().setValue(test)
                        } else
                            android.util.Log.e("Failure", "SignIn")

                    })
        })
//
//        val postListener = (object : com.google.firebase.database.ChildEventListener {
//            override fun onChildAdded(dataSnapshot: com.google.firebase.database.DataSnapshot?, p1: String?) {
//                val name = dataSnapshot
//                        ?.child("name")?.value as String?
//
//
//                android.util.Log.e("received", dataSnapshot?.key + "received " + name)
//            }
//
//            override fun onChildChanged(dataSnapshot: com.google.firebase.database.DataSnapshot?, s: String?) {
//
//            }
//
//            override fun onChildRemoved(dataSnapshot: com.google.firebase.database.DataSnapshot?) {
////                adapter.remove(dataSnapshot.child("title").value as String?)
//            }
//
//            override fun onChildMoved(dataSnapshot: com.google.firebase.database.DataSnapshot?, s: String?) {
//
//            }
//
//            override fun onCancelled(databaseError: com.google.firebase.database.DatabaseError) {
//
//            }
//        })
//
//        val database = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("users")
//        database.child(fireAuth?.currentUser?.uid).child("hello").addChildEventListener(postListener)
    }

//    class Name {
//
//        var name :String = ""
//        var age: Int = 0
//
//        constructor(name1: String, age1: Int){
//            this.name = name1
//            this.age = age1
//        }
//
//        constructor()
//    }

    private fun navigateToChatBoard() {
        startActivity(Intent(this, UserListActivity::class.java))
    }
}
