package fi.oulu.reminder2020

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import fi.oulu.ubicomp.mobicomplab1.R
import kotlinx.android.synthetic.main.list_view_item.view.*

class ReminderAdapter(context: Context, private val list: List<String>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, pl: View?, parent: ViewGroup?): View {
        val row = inflater.inflate(R.layout.list_view_item, parent,false)
        row.itemMessage.text = list[position]
        row.itemTrigger.text = "hello"

        return row
    }

    override fun getItem(position: Int): Any {

        return list[position]
    }

    override fun getItemId(position: Int): Long {

        return position.toLong()
    }

    override fun getCount(): Int {

        return list.size
    }
}