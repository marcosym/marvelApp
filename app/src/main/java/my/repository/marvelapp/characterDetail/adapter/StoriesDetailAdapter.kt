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

class StoriesDetailAdapter (private var items: MutableList<Comic.Results>) : RecyclerView.Adapter<StoriesDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StoriesDetailAdapter.ViewHolder(inflater.inflate(R.layout.item_stories, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val charModel = items[position]
        holder.titleComics.text = charModel.title
    }


    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val titleComics = itemView.title_card_comics!!
    }


}