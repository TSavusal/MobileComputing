package fi.oulu.ubicomp.mobicomplab1

import androidx.room.*

@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey(autoGenerate = true) var uid: Int?,
    @ColumnInfo(name = "time") var time: Long?,
    @ColumnInfo(name = "location") var location: String?,
    @ColumnInfo(name = "message") var message: Long?
)

@Dao
interface ReminderDao {
    @Transaction @Insert
    fun insert(reminder: Reminder)

    @Query("SELECT * FROM reminder")
    fun getReminders(): List<Reminder>
}