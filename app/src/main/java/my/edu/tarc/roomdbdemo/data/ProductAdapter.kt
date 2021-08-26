package my.edu.tarc.roomdbdemo.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.roomdbdemo.R

class ProductAdapter (private val ProductList: List<Product>) : RecyclerView.Adapter<ProductAdapter.productViewHolder>() {

    class productViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val productName: TextView = itemView.findViewById(R.id.tvName)
        val productPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.product_item, parent, false
        )

        return productViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: productViewHolder, position: Int) {

        val currentProduct = ProductList[position]
        holder.productName.text = currentProduct.name
        holder.productPrice.text = currentProduct.price.toString()

    }

    override fun getItemCount(): Int {
        return ProductList.size
    }

}