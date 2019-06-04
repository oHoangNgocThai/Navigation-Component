package thaihn.com.navigationcomponent.ui.main.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import thaihn.com.navigationcomponent.R
import thaihn.com.navigationcomponent.util.Utils
import thaihn.com.weatherapp.data.model.DataHourly

class HourlyAdapter(
        var items: List<DataHourly>
) : RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hourly, parent, false)
    )


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position])
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        private val mTextTime = view.findViewById<TextView>(R.id.text_time)
        private val mTextTemp = view.findViewById<TextView>(R.id.text_temp)
        private val mImageIcon = view.findViewById<ImageView>(R.id.image_icon)

        fun binData(hourly: DataHourly) {
            mTextTime.text = Utils.convertTimeToHour(hourly.time)
            Glide.with(mImageIcon.context).load(
                    Utils.getImage(Utils.changeIconToName(hourly.icon), mImageIcon.context)).into(
                    mImageIcon)
            mTextTemp.text = Utils.changeTempFToC(hourly.temperature).toString() + Utils.makeTemp()
        }
    }

    fun setNewData(newItems: List<DataHourly>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}
