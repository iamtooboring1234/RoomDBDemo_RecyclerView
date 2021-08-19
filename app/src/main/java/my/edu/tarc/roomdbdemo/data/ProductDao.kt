package my.edu.tarc.roomdbdemo.data

import androidx.room.Insert

interface ProductDao {

    @Insert
    fun insertProduct (product: Product)



}