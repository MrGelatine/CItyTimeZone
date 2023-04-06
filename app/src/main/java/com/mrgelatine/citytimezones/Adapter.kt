package com.mrgelatine.citytimezones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.*
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.SwipeLayout.SwipeListener
import com.mrgelatine.citytimezones.databinding.CityRowBinding

import java.util.*


class Adapter : RecyclerView.Adapter<CityRowViewHolder>() {
    var recyclerRows:List<CityRow> = listOf()
    var refreshing:ObservableBoolean = ObservableBoolean(false)
    var loading:ObservableBoolean = ObservableBoolean(true)
    var cityCount:ObservableField<String> = ObservableField<String>("Cities: 0")
    fun setData(data:List<CityRow>){
        recyclerRows = data
        notifyDataSetChanged()
        cityCount.set("Cities: " + getItemCount().toString())
        loading.set(false)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityRowViewHolder {
        val binding:CityRowBinding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.city_row, parent, false);

        return CityRowViewHolder(binding);
    }

    override fun onBindViewHolder(holder: CityRowViewHolder, position: Int) {
        holder.bind(recyclerRows[position]);
    }

    fun refresh(){
        refreshing.set(true)
        val newGMT = Calendar.getInstance()
        newGMT.add(Calendar.MILLISECOND, -newGMT.timeZone.getOffset(newGMT.timeInMillis))
        recyclerRows.forEach() {
            it.refreshTime(newGMT.clone() as Calendar)
        }
        notifyDataSetChanged()
        cityCount.set("Cities: " + getItemCount().toString())
        refreshing.set(false)
    }

    override fun getItemCount(): Int {
        return recyclerRows.size
    }
}
class CityRowViewHolder(var cityRowBinding: CityRowBinding) :
    RecyclerView.ViewHolder(cityRowBinding.root) {

    fun bind(obj: Any?) {
        cityRowBinding.setVariable(BR.model, obj)
    }
}
