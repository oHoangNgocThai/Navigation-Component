package thaihn.com.navigationcomponent.ui.main.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import thaihn.com.navigationcomponent.R
import thaihn.com.navigationcomponent.util.Utils
import thaihn.com.weatherapp.data.model.DataDaily

class WeeklyAdapter(
        private var items: List<DataDaily>
) : RecyclerView.Adapter<WeeklyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_weekly, parent, false)
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(items[position])
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        private val mTextDay = view.findViewById<TextView>(R.id.text_day)
        private val mTextTempMax = view.findViewById<TextView>(R.id.text_temp_max)
        private val mTextTempMin = view.findViewById<TextView>(R.id.text_temp_min)
        private val mImageIcon = view.findViewById<ImageView>(R.id.image_icon)

        @SuppressLint("SetTextI18n")
        fun binData(item: DataDaily) {
            mTextDay.text = Utils.convertTimeToDay(item.time)
            Glide.with(mImageIcon.context).load(
                    Utils.getImage(Utils.changeIconToName(item.icon), mImageIcon.context)).into(
                    mImageIcon)
            mTextTempMax.text = Utils.changeTempFToC(item.temperatureHigh).toString() + Utils.makeTemp()
            mTextTempMin.text = Utils.changeTempFToC(item.temperatureLow).toString() + Utils.makeTemp()
        }
    }

    fun setNewData(data: List<DataDaily>) {
        this.items = data
        notifyDataSetChanged()
    }
}