package uz.abbosbek.codialtaskmusobaqa.presenter.page.adminDiscountPage.adminAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.abbosbek.codialtaskmusobaqa.data.model.response.Warehouse
import uz.abbosbek.codialtaskmusobaqa.databinding.ItemRvBinding


class AdminDiscountAdapter(var list: ArrayList<Warehouse> = ArrayList()) :
    RecyclerView.Adapter<AdminDiscountAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(warehouse: Warehouse) {
            itemRvBinding.itemTvName.text = warehouse.name
            Glide.with(itemView.context).load(warehouse.phone).into(itemRvBinding.itemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}