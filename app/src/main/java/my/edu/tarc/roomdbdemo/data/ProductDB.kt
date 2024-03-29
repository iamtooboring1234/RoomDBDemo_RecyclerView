package my.edu.tarc.roomdbdemo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Product::class], version = 2, exportSchema = false)
abstract  class ProductDB : RoomDatabase() {

    abstract val productDao: ProductDao

    companion object {

        @Volatile
        private var INSTANCE: ProductDB? = null

        fun getInstance(context: Context): ProductDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDB::class.java,
                        "myDB"

                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}