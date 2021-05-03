package com.ulanapp.skywebprotestapp.presentation.images.paging

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ulanapp.skywebprotestapp.R
import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import kotlinx.android.synthetic.main.recycler_item_image.view.*
import kotlinx.android.synthetic.main.recycler_item_image_footer.view.*
import javax.inject.Inject

class ImagesPagedListAdapter @Inject constructor():
    PagedListAdapter<ImagesResponse, RecyclerView.ViewHolder>(imagesDiffCallback) {

    private var state = State.LOADING

    companion object {

        private const val DATA_VIEW_TYPE = 1
        private const val FOOTER_VIEW_TYPE = 2

        val imagesDiffCallback = object : DiffUtil.ItemCallback<ImagesResponse>() {
            override fun areItemsTheSame(
                oldItem: ImagesResponse,
                newItem: ImagesResponse
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ImagesResponse,
                newItem: ImagesResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE)
            ImageViewHolder.create(parent)
        else ListFooterViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE)
            (holder as ImageViewHolder).bind(getItem(position))
        else (holder as ListFooterViewHolder).bind(state)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    // проверка есть ли футер
    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    // изменяем состояние
    fun changeState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}

class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(image: ImagesResponse?) {
        if (image != null) {
            itemView.author.text = image.author
            Picasso.get().load(image.downloadUrl).into(itemView.picture)
        }
    }

    companion object {
        fun create(parent: ViewGroup): ImageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_image, parent, false)
            return ImageViewHolder(view)
        }
    }
}

class ListFooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(status: State?) {
        itemView.progress_bar.visibility = if (status == State.LOADING) VISIBLE else View.INVISIBLE
    }

    companion object {
        fun create(parent: ViewGroup): ListFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_image_footer, parent, false)
            return ListFooterViewHolder(view)
        }
    }
}