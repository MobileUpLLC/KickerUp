package ru.mobileup.kickerup.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.design.widget.CoordinatorLayout
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.graphics.drawable.TintAwareDrawable
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.visible(visible: Boolean, useGone: Boolean = true, withAnimate: Boolean = false) {
    this.visibility = if (visible) View.VISIBLE else if (useGone) View.GONE else View.INVISIBLE
}

fun Resources.color(colorRes: Int) =
    if (Build.VERSION.SDK_INT >= 23) {
        this.getColor(colorRes, null)
    } else {
        this.getColor(colorRes)
    }

fun ImageView.tintSrc(@ColorRes colorRes: Int) {
    val drawable = DrawableCompat.wrap(drawable)
    DrawableCompat.setTint(drawable, ContextCompat.getColor(context, colorRes))
    setImageDrawable(drawable)
    if (drawable is TintAwareDrawable) invalidate() // Because in this case setImageDrawable will not call invalidate()
}

fun Activity.getScreenHeight(): Int {
    val size = Point()
    windowManager.defaultDisplay.getSize(size)
    return size.y
}

fun View.showKeyboard(show: Boolean) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (show) {
        if (requestFocus()) imm.showSoftInput(this, 0)
    } else {
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}

fun spannedFromHtml(source: String): Spanned =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(source, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(source)
    }

fun TextView.setTextAppearanceCompat(res: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setTextAppearance(res)
    } else {
        setTextAppearance(context, res)
    }
}

@SuppressLint("CheckResult")
fun ImageView.load(
    url: String,
    round: Boolean = false,
    cornersRadius: Int = 0,
    @DrawableRes placeholderRes: Int? = null,
    placeholderDrawable: Drawable? = null
) {
    Glide.with(context)
        .load(url)
        .apply {
            if (placeholderRes != null) {
                apply(RequestOptions().placeholder(placeholderRes))
            }
            if (placeholderDrawable != null) {
                apply(RequestOptions().placeholder(placeholderDrawable))
            }
            if (round) {
                apply(RequestOptions.circleCropTransform())
            }
            if (cornersRadius > 0) {
                apply(RequestOptions().fitCenter().transform(RoundedCorners(cornersRadius)))
            }
        }
        .into(this)
}

fun TextView.setCompoundDrawables(left: Drawable? = null, top: Drawable? = null, right: Drawable? = null, bottom: Drawable? = null) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(left, top, right, bottom)
}

fun Context.showToast(str: String, duration: Int = Toast.LENGTH_LONG) =
    Toast.makeText(this, str, duration).show()

fun Context.showToast(strRes: Int, duration: Int = Toast.LENGTH_LONG) = showToast(getString(strRes), duration)

fun <T : CoordinatorLayout.Behavior<*>> View.findBehavior(): T = layoutParams.run {
    if (this !is CoordinatorLayout.LayoutParams) throw IllegalArgumentException("View's layout params should be CoordinatorLayout.LayoutParams")

    (layoutParams as CoordinatorLayout.LayoutParams).behavior as? T
            ?: throw IllegalArgumentException("Layout's behavior is not current behavior")
}

fun ViewPropertyAnimator.show() = alphaBy(0f).alpha(1f)

fun ViewPropertyAnimator.hide() = alphaBy(1f).alpha(0f)

fun <T : View> View.findParentById(@IdRes targetId: Int): T? {
    if (id == targetId) return this as T
    return (parent as? View)?.findParentById(targetId)
}

fun View.setPadding(start: Int = paddingStart, top: Int = paddingTop, end: Int = paddingEnd, bottom: Int = paddingBottom) {
    setPaddingRelative(start, top, end, bottom)
}

