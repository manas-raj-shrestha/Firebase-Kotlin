package com.droid.manasshrestha.firebase

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.droid.manasshrestha.firebase.activity.ChatActivity

open class RvUserAdapter(var users: ArrayList<User>) : RecyclerView.Adapter<RvUserAdapter.ViewHolder>() {

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        viewHolder?.tvUserName?.text = users[position].userName
        viewHolder?.tvUserName?.setTextColor(Color.BLACK)

        viewHolder?.itemView?.setOnClickListener({
            var context = viewHolder?.itemView?.context
            context?.startActivity(ChatActivity.getLaunchIntent(context, users[position].userName))
        })
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(android.R.layout.simple_expandable_list_item_1, parent, false)
        return ViewHolder(view)
    }

    open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var tvUserName: TextView? = null

        init {
            tvUserName = itemView?.findViewById(android.R.id.text1) as TextView
        }

    }

    fun addData(user: User) {
        users.add(user)
        notifyItemChanged(users.size)
    }
}