package com.sedat.kotlinapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.sedat.kotlinapp.databinding.RetrofitImageItemLayoutBinding
import com.sedat.kotlinapp.model.RetrofitImageResult
import javax.inject.Inject

class RetrofitAdapter @Inject constructor(
    private val glide: RequestManager
): RecyclerView.Adapter<RetrofitAdapter.Holder>() {

    //Eski ve yeni listeyi karşılaştırıp gerekli yerleri güncellemek için diffUtil kullanıldı.
    private val diffUtil = object :DiffUtil.ItemCallback<RetrofitImageResult>(){
        override fun areItemsTheSame(
            oldItem: RetrofitImageResult,
            newItem: RetrofitImageResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RetrofitImageResult,
            newItem: RetrofitImageResult
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val recylerListDiffer = AsyncListDiffer(this, diffUtil)

    var images: List<RetrofitImageResult>
        get() = recylerListDiffer.currentList
        set(value) = recylerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitAdapter.Holder {
        val binding = RetrofitImageItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: RetrofitAdapter.Holder, position: Int) {
        val image = images[position]
        glide.load(image.previewURL).into(holder.item.retrofitImage)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class Holder(val item: RetrofitImageItemLayoutBinding): RecyclerView.ViewHolder(item.root)

}