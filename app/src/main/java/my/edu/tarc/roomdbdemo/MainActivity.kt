package my.edu.tarc.roomdbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import my.edu.tarc.roomdbdemo.data.Product
import my.edu.tarc.roomdbdemo.data.ProductAdapter
import my.edu.tarc.roomdbdemo.data.ProductDB
import my.edu.tarc.roomdbdemo.data.ProductDao

class MainActivity : AppCompatActivity() {

    lateinit var dao: ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = ProductDB.getInstance(application).productDao

        val btnInsert :Button = findViewById(R.id.btnInsert)
        btnInsert.setOnClickListener(){

            val name :String  = findViewById<TextView>(R.id.tfName).text.toString()
            val price:Double =  findViewById<TextView>(R.id.tfPrice).text.toString().toDouble()

            val p = Product(0, name, price)

            CoroutineScope(IO).launch {
                dao.insertProduct(p)
            }

        }

        val btnGet :Button = findViewById(R.id.btnGet)
        btnGet.setOnClickListener(){
            CoroutineScope(IO).launch{
                val productList: List<Product> = dao.getAll()

                CoroutineScope(Main).launch {
                    val productAdapter = ProductAdapter(productList)

                    val recyclerView: RecyclerView = findViewById(R.id.RecProduct)

                    recyclerView.adapter = productAdapter
                    recyclerView.setHasFixedSize(true)
                }

            }

        }

    }
}