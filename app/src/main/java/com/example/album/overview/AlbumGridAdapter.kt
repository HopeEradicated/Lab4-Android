package com.example.album.overview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.album.databinding.GridViewItemBinding
import com.example.album.network.Albom

class AlbumGridAdapter :
    ListAdapter<Albom, AlbumGridAdapter.AlbumViewHolder>(DiffCallback) {
    class AlbumViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsPhoto: Albom) {
            itemView.setOnClickListener {
                OverviewViewModelSecond.albid = marsPhoto.id;
                Log.e("ss","ALLL DONE" + marsPhoto.id);
            }
            binding.photo = marsPhoto
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Albom>() {
        override fun areItemsTheSame(oldItem: Albom, newItem: Albom): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Albom, newItem: Albom): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumViewHolder {
        return AlbumViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}
