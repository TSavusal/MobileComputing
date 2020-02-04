package fi.oulu.ubicomp.mobicomplab1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.list_view_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ReminderAdapter(context: Context, private val list: Array<Reminder>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
        val row = inflater.inflate(R.layout.list_view_item, parent, false)

        row.itemMessage.text = list[position].message

        if(list[position].time != null) {

            val sdf = SimpleDateFormat("HH:mm dd.MM.yyyy")
            sdf.timeZone = TimeZone.getDefault()
            val time = list[position].time
            val readableTim = sdf.format(time)
        }
        return row
    }

    override fun getItem(position: Int): Any {

        return list[position]
    }

    override fun getItemID(position: Int): Long {

        return position.toLong()
    }

    override fun getgetCount(): Int {

        return list.size
    }
}