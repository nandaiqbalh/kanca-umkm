package com.nandaiqbalh.kancaumkm.util.components

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import com.google.android.material.textfield.TextInputEditText

class CustomEmailEditText : TextInputEditText {
	private var emailPattern: Regex = Patterns.EMAIL_ADDRESS.toRegex()

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
				val email = s.toString()
				error = if (email.isNotEmpty() && !email.matches(emailPattern)) {
					"Invalid email address"
				} else {
					null
				}
			}
		})
	}
}
