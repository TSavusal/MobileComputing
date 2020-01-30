package fi.oulu.ubicomp.mobicomplab1

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Reminder::class], version = 1)
abstract class AppDAtabase : RoomDatabase() {
    abstract fun reminderDao() : ReminderDao
}