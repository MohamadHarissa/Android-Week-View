package com.alamkanak.weekview

import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextUtils
import android.text.style.StyleSpan
import androidx.emoji.text.EmojiCompat

internal val StaticLayout.lineHeight: Int
    get() = height / lineCount

private val emojiCompat: EmojiCompat?
    get() = try {
        EmojiCompat.get()
    } catch (e: IllegalStateException) {
        // EmojiCompat is not set up in this project
        null
    }

internal fun SpannableString.emojify(): SpannableString {
    return emojiCompat?.process(this) as SpannableString
}

internal fun SpannableString.ellipsized(
    textPaint: TextPaint,
    availableArea: Int,
    where: TextUtils.TruncateAt = TextUtils.TruncateAt.END
): SpannableString {
    return when (val result = TextUtils.ellipsize(this, textPaint, availableArea.toFloat(), where)) {
        is SpannableStringBuilder -> result.build()
        is SpannableString -> result
        else -> throw IllegalStateException("Invalid result from ellipsize operation: ${result.javaClass.simpleName}")
    }
}

internal fun SpannableString.setSpan(
    styleSpan: StyleSpan
) = setSpan(styleSpan, 0, length, 0)

internal fun CharSequence.bold() = SpannableString(this).apply {
    setSpan(StyleSpan(Typeface.BOLD))
}

internal fun SpannableStringBuilder.build(): SpannableString = SpannableString.valueOf(this)
