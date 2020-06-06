package com.sheldon.myyelpcloneapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_resturant.view.*


class ResturantsAdapter(val context: Context, val resturants: List<YelpResturant>) :
    RecyclerView.Adapter<ResturantsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_resturant, parent, false))
    }

    override fun getItemCount() = resturants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val resturant = resturants[position]
        holder.bind(resturant)
    }
        inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
            fun bind(resturant: YelpResturant) {
             itemView.tvName.text= resturant.name
                itemView.ratingBar.rating = resturant.rating.toFloat()
                itemView.tvNumReviews.text = "${resturant.numReviews} Reviews"
                itemView.tvAddress.text = resturant.location.address
                itemView.tvDistance.text = resturant.displayDistance()
                itemView.tvPrice.text = resturant.price
                Glide.with(context).load(resturant.imageUrl).apply(RequestOptions().transforms(
                    CenterCrop(), RoundedCorners(20)
                )).into(itemView.tvimageView)//*Another bug

            }


        }

}
