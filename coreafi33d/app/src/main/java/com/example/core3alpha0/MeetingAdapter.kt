package com.example.core3alpha0

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView

class MeetingAdapter(private var meetings: List<Meeting>) : RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder>() {

    private var filteredMeetings: MutableList<Meeting> = meetings.toMutableList()

    inner class MeetingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val mainText: TextView = view.findViewById(R.id.mainText)
        val subtitle1: TextView = view.findViewById(R.id.subtitle1)
        val subtitle2: TextView = view.findViewById(R.id.subtitle2)
        val type:TextView = view.findViewById(R.id.type)
        val dateTime:TextView = view.findViewById(R.id.dateTime)
        val icon: ImageView = view.findViewById(R.id.icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return MeetingViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeetingViewHolder, position: Int) {
        val meeting = filteredMeetings[position]
        //holder.mainText.text = meeting.mainText
        holder.subtitle1.text = meeting.subtitle1
        holder.subtitle2.text = meeting.subtitle2
        holder.type.text = meeting.type
        holder.dateTime.text = meeting.dateTime

        if (meeting.subtitle2 == "Online") {
            holder.icon.setImageResource(R.drawable.online_icon)
        } else {
            holder.icon.setImageResource(R.drawable.in_person)
        }
    }

    override fun getItemCount() = filteredMeetings.size
    private fun sortMeetings() {
        filteredMeetings = filteredMeetings.sortedWith(compareBy { it.type }).toMutableList()
        notifyDataSetChanged()
    }
    fun filter(type: String) {
        filteredMeetings = when (type) {
            "Online" -> {
                ArrayList(meetings.filter { it.subtitle2 == "Online" })
            }
            "In-person" -> {
                ArrayList(meetings.filter { it.subtitle2 != "Online" })
                // This will include all meetings that are not "Online"
            }
            "All" -> {
                ArrayList(meetings)  // Show all meetings
            }
            else -> {
                ArrayList(meetings)
            }
        }
        sortMeetings()
    }
}
