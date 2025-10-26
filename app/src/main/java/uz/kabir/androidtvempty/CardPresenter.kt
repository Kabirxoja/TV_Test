package uz.kabir.androidtvempty

import android.content.pm.ResolveInfo
import android.graphics.Color
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.core.view.marginTop
import androidx.leanback.widget.Presenter

class CardPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val context = parent.context

        val frame = FrameLayout(context).apply {

            val margin = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                12f,
                resources.displayMetrics
            ).toInt()

            layoutParams = ViewGroup.MarginLayoutParams(300, 200).apply {
                setMargins(margin, margin, margin, margin)
            }

            isFocusable = true
            isFocusableInTouchMode = true
            setBackgroundColor(Color.DKGRAY)
            setPadding(16, 16, 16, 16)
        }

        val icon = ImageView(context).apply {
            id = View.generateViewId()
            layoutParams = FrameLayout.LayoutParams(100, 100, Gravity.TOP or Gravity.CENTER_HORIZONTAL)
        }

        val name = TextView(context).apply {
            id = View.generateViewId()
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            )
            gravity = Gravity.CENTER
            textSize = 16f
            setTextColor(Color.WHITE)
            maxLines = 1
            ellipsize = TextUtils.TruncateAt.END
        }

        frame.addView(icon)
        frame.addView(name)
        return ViewHolder(frame)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val info = item as ResolveInfo
        val context = viewHolder.view.context
        val pm = context.packageManager

        val frame = viewHolder.view as FrameLayout
        val iconView = frame.getChildAt(0) as ImageView
        val nameView = frame.getChildAt(1) as TextView

        iconView.setImageDrawable(info.loadIcon(pm))
        nameView.text = info.loadLabel(pm)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
}

