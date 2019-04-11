package my.repository.marvelapp.characterDetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_comics.view.*
import my.repository.marvelapp.R
import my.repository.marvelapp.model.Comic
import java.util.*

class EventsDetailAdapter (private var items: MutableList<Comic.Results>) : RecyclerView.Adapter<EventsDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventsDetailAdapter.ViewHolder(inflater.inflate(R.layout.item_events, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val charModel = items[position]

        Glide.with(holder.itemView.context)
            .load(charModel.thumbnail?.path+"."+charModel.thumbnail?.extension)
            .apply(RequestOptions.centerCropTransform())
            .apply(RequestOptions().transform(RoundedCorners(10)))
            .transition(withCrossFade())
            .into(holder.imgComics)
        holder.titleComics.text = charModel.title
    }


    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val imgComics = itemView.img_card_comics!!
        val titleComics = itemView.title_card_comics!!
    }


}