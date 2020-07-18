package com.daily.lifehacks.app.ui.articlelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import com.daily.database.entity.Section
import com.daily.lifehacks.R
import com.daily.lifehacks.app.util.pxToDp
import kotlinx.android.synthetic.main.viewholder_section.view.*

class SectionListAdapter(
    val sectionList: List<Section>,
    val context: Context,
    val pickListener: SectionPickListener
) : RecyclerView.Adapter<SectionListAdapter.SectionViewHolder>() {

    val sections = ArrayList<Section>()
    var chosenSectionIndex = 0
    var chosenSection: Section

    init {
        setupTykvy()
        sections.addAll(sectionList)
        chosenSection = sections[chosenSectionIndex]
    }

    fun setupTykvy() {
        sections.add(0, Section("-1", "Все", ""))
        sections.add(1, Section("-2", "Сохраненные", ""))
    }

    fun setList(list: ArrayList<Section>) {
        sections.clear()
        setupTykvy()
        sections.addAll(list)
        chosenSection = sections[chosenSectionIndex]
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        return SectionViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.viewholder_section,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = sections.size

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val section = sections[position]

            itemView.flBackground.setOnClickListener {
                val oldIndex = chosenSectionIndex
                chosenSectionIndex = adapterPosition
                notifyItemChanged(oldIndex)
                notifyItemChanged(chosenSectionIndex)
                pickListener.onSectionPicked(sections[chosenSectionIndex])
            }

            val layoutParams = itemView.flBackground.layoutParams as RecyclerView.LayoutParams
            if (position == sections.size - 1) {
                layoutParams.rightMargin = pxToDp(8)
            } else {
                layoutParams.rightMargin = 0
            }

            itemView.flBackground.layoutParams = layoutParams

            if (position == chosenSectionIndex) {
                itemView.flBackground.background =
                    context.getDrawable(R.drawable.drawable_section_button_active)
            } else {
                itemView.flBackground.background =
                    context.getDrawable(R.drawable.drawable_section_button_inactive)
            }

            itemView.txvSection.setText(section.name)
        }
    }
}