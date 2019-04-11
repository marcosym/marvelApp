package my.repository.marvelapp.characterList.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_character.view.*
import my.repository.marvelapp.R
import my.repository.marvelapp.model.Character
import java.util.*
import kotlin.collections.ArrayList


class CharacterAdapter : PagedListAdapter<Character.Results, CharacterAdapter.CharacterViewHolder>(characterDiff) {

    var callback: ((Character.Results) -> Unit)? = null
    private val userSortedList: SortedList<Character.Results>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val charsModel = getItem(position)

        holder.title.text = charsModel?.name
        Glide.with(holder.itemView.context)
            .load(charsModel?.thumbnail?.path + "." + charsModel?.thumbnail?.extension)
            .transition(withCrossFade())
            .apply(RequestOptions().circleCrop())
            .into(holder.picture)

        holder.container.setOnClickListener { callback?.invoke(charsModel!!) }
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val picture = itemView.item_image!!
        val title = itemView.item_title!!
        val container = itemView.item_container!!
    }

    companion object {
        val characterDiff = object : DiffUtil.ItemCallback<Character.Results>() {
            override fun areItemsTheSame(oldItem: Character.Results, newItem: Character.Results): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Character.Results, newItem: Character.Results): Boolean {
                return oldItem == newItem
            }
        }

        val sortedDiff = SortedList(Character.Results::class.java, object: SortedListAdapterCallback<Character.Results>(CharacterAdapter::class.java.newInstance()){
            override fun areItemsTheSame(item1: Character.Results?, item2: Character.Results?): Boolean {
                return item1 == item2

            }

            override fun compare(o1: Character.Results?, o2: Character.Results?): Int {
                return o1?.id!!.compareTo(o2?.id!!)
            }

            override fun areContentsTheSame(oldItem: Character.Results?, newItem: Character.Results?): Boolean {
                return oldItem == newItem
            }


        })
    }
}