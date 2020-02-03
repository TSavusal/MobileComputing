package fi.oulu.remider2020

import androidx.room.Database
import androidx.room.RoomDatabase
import fi.oulu.ubicomp.mobicomplab1.Reminder
import fi.oulu.ubicomp.mobicomplab1.ReminderDao

@Database(entities = [Reminder::class], version  = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
}