package fi.oulu.ubicomp.mobicomplab1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.list_view_item.view.*

class ReminderAdapter()context: Context, private val list: Array<String>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
        val row = inflater.inflate(R.layout.list_view_item, parent, false)

        row.itemMessage.text = list[position]

        row.itemTrigger.text = "hello"

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