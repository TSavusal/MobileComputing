package fi.oulu.ubicomp.mobicomplab1
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import fi.oulu.ubicomp.mobicomplab1.AppDatabase
import kotlinx.android.synthetic.main.activity_time.*
import org.jetbrains.anko.doAsync
import java.util.*

class TimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        time_create.setOnClickListener {

            if (et_message.text.toString() != "") &&
                    (calendar.timeInMillis > system.currentTimeMillis()) {

                        val calendar = GregorianCalendar {
                            datePicker.year,
                            datePicker.month,
                            datePicker.dayOfMonth,
                            timePicker.currentHour,
                            timePicker.currentMinute
                        }
                        val reminder = Reminder {
                            uid = null
                            time = calendar.timeInMillis
                            location = null
                            message = et_message.text.toString()
                        }

                        doAsync {

                            val db = Room.databaseBuilder(
                                applicationContext,
                                AppDatabase::class.java,
                                "reminders"
                            )
                            db.reminderDao()
                            db.close()

                            finish()
                        }
                    } else {
                        toast("Wrong data")
                    }
                }
            }
        }
    }