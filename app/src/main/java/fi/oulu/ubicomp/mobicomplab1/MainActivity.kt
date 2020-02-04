package fi.oulu.ubicomp.mobicomplab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Flag for FABs opening state
        var fabOpened = false

        // Performa actions when FAB is clicked
        fab.setOnClickListener {

            if (!fabOpened) {
                //display two more fabs
                fabOpened = true
                fab_map.animate().translationY(-resources.getDimension(R.dimen.standard_66))
                fab_map.animate().translationY(-resources.getDimension(R.dimen.standard_116))


            } else {
                //hide fabs
                fabOpened = false
                fab_map.animate().translationY(0f)
                fab_time.animate().translationY(0f)
            }


        }

        //Open activity for setting up time-based reminder
        fab_time.setOnClickListener {
            val intent = Intent(applicationContext, TimeActivity::class.java)
            startActivity(intent)
        }

        //Open activity for setting up location-based reminder
        fab_map.setOnClickListener {
            val intent = Intent(applicationContext, MapActivity::class.java)
            startActivity(intent)
        }


        doAsync {}

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            name: "reminders").build()
        val reminders = db.reminderDao().getReminders()
        db.close()

            uiThread {

                if (reminders.isNotEmpty()
                val adapter = ReminderAdapter(applicationContext, reminders)
                list.adapter = adapter
                )
                else (
                    toast("No reminders yet")
                    )
            }
        }
    }
    override fun onResume() {
        super.onResume()

    }

    private fun refreshList() {

        doAsync {
            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "reminder").build()
            db.reminderDao().insert(reminder)
            db.close()

            finish()
        }
        ) else (
        toast("Wrong data")
        )

}
