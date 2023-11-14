package com.nandaiqbalh.kancaumkm.util.components

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class CustomEditText : TextInputEditText {
	private val MIN_PASSWORD_LENGTH = 8

	constructor(context: Context) : super(context)
	constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
	constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
		context,
		attrs,
		defStyleAttr
	)

	init {
		addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
			}

			override fun afterTextChanged(s: Editable?) {
				val password = s.toString()
				error = if (password.length < MIN_PASSWORD_LENGTH) {
					"Password must be at least $MIN_PASSWORD_LENGTH characters"
				} else {
					null
				}
			}
		})
	}
}
