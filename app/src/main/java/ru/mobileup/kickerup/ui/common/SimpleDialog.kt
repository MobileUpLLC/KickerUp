package ru.mobileup.kickerup.ui.common

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import com.bluelinelabs.conductor.Controller


class SimpleDialog(args: Bundle) : DialogController(args) {

    companion object {
        private val DEFAULT_TAG = "SimpleDialog"

        private const val ARGS_TITLE = "args_title"
        private const val ARGS_MESSAGE = "args_message"
        private const val ARGS_OK = "args_ok"
        private const val ARGS_CANCEL = "args_cancel"
        private const val ARGS_DIALOG_TAG = "args_dialog_tag"
        private const val ARGS_REQUIRES_LISTENER = "args_requires_listener"

        fun newInstance(
            title: String? = null,
            message: String,
            ok: String,
            cancel: String? = null,
            dialogTag: String = DEFAULT_TAG,
            requiresListener: Boolean = true,
            targetController: Controller? = null
        ): SimpleDialog {

            val args = Bundle()
            args.putString(ARGS_TITLE, title)
            args.putString(ARGS_MESSAGE, message)
            args.putString(ARGS_OK, ok)
            args.putString(ARGS_CANCEL, cancel)
            args.putString(ARGS_DIALOG_TAG, dialogTag)
            args.putBoolean(ARGS_REQUIRES_LISTENER, requiresListener)

            return SimpleDialog(args).apply { this.targetController = targetController }
        }
    }

    private val title by lazy { args.getString(ARGS_TITLE) }
    private val message by lazy { args.getString(ARGS_MESSAGE) }
    private val ok by lazy { args.getString(ARGS_OK) }
    private val cancel by lazy { args.getString(ARGS_CANCEL) }
    private val dialogTag by lazy { args.getString(ARGS_DIALOG_TAG) }
    private val requiresListener by lazy { args.getBoolean(ARGS_REQUIRES_LISTENER) }

    private var listener: ResponseListener? = null

    interface ResponseListener {
        fun onSimpleDialogResponse(tag: String, isOk: Boolean)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        if (targetController is ResponseListener) {
            listener = targetController as ResponseListener
        } else if (activity is ResponseListener) {
            listener = activity as ResponseListener
        } else if (targetController != null) {
            if (requiresListener)
                throw IllegalStateException("Activity or parent fragment must implement ResponseListener.")
        }
    }

    override fun onCreateDialog(savedViewState: Bundle?): Dialog {
        return AlertDialog.Builder(activity!!)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                    ok,
                    { _, _ ->
                        dismissDialog()
                        listener?.onSimpleDialogResponse(dialogTag, true)
                    }
            )
            .apply {
                // Allow omitted cancel
                cancel?.let {
                    setNegativeButton(
                            cancel,
                            { _, _ ->
                                dismissDialog()
                                listener?.onSimpleDialogResponse(dialogTag, false)
                            }
                    )
                }
            }
            .create()
    }
}