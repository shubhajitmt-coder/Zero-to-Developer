package com.example.data

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_progress")
    fun getAllTaskProgress(): Flow<List<TaskProgress>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateTask(task: TaskProgress)

    @Query("UPDATE task_progress SET isCompleted = :isCompleted WHERE taskId = :taskId")
    suspend fun setTaskCompleted(taskId: String, isCompleted: Boolean)
}

@Dao
interface DsaDao {
    @Query("SELECT * FROM dsa_progress")
    fun getAllDsaProgress(): Flow<List<DsaProgress>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateDsa(progress: DsaProgress)
}

@Dao
interface SettingsDao {
    @Query("SELECT * FROM user_settings WHERE key = :key")
    suspend fun getSetting(key: String): UserSetting?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSetting(setting: UserSetting)
}

@Database(
    entities = [TaskProgress::class, DsaProgress::class, UserSetting::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun dsaDao(): DsaDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sde_roadmap_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
