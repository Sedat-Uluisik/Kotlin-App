package com.sedat.kotlinapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.sedat.kotlinapp.databinding.CodesItemLayoutBinding
import com.sedat.kotlinapp.model.FirebaseItem
import javax.inject.Inject

class CodesAdapter @Inject constructor(
    private val glide: RequestManager
): RecyclerView.Adapter<CodesAdapter.Holder>() {

    //Eski ve yeni listeyi karşılaştırıp gerekli yerleri güncellemek için diffUtil kullanıldı.
    private val diffUtil = object :DiffUtil.ItemCallback<FirebaseItem>() {
        override fun areItemsTheSame(oldItem: FirebaseItem, newItem: FirebaseItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FirebaseItem, newItem: FirebaseItem): Boolean {
            return oldItem == newItem
        }
    }

    private val recylerListDiffer = AsyncListDiffer(this@CodesAdapter, diffUtil)

    var items: List<FirebaseItem>
        get() = recylerListDiffer.currentList
        set(value) = recylerListDiffer.submitList(value)

    //Resime tıklandığında  yakınlaştırma işleminin fragmentten kontrol edilmesi için.
    private var onImageClickListener: ((String) -> Unit) ?= null
    fun setOnImageClickListener(listener: (String) -> Unit){
        onImageClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodesAdapter.Holder {
        val binding = CodesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: CodesAdapter.Holder, position: Int) {
        val firebaseItem = items[position]
        glide.load(firebaseItem.imageUrl).into(holder.item.codesImageview)
        holder.item.codesDesciption.text = firebaseItem.description

        holder.item.codesImageview.setOnClickListener {
            onImageClickListener?.let {
                it(firebaseItem.imageUrl.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class Holder(val item: CodesItemLayoutBinding): RecyclerView.ViewHolder(item.root)

    fun refresh() = notifyDataSetChanged()

}