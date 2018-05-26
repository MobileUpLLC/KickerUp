package ru.mobileup.kickerup.system

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri


/**
 * Helps to start out-oriented intents
 */
class OutIntentsHelper(private val context: Context) {

    fun openDialer(phone: String) = checkAndStartActivity(
            Intent(Intent.ACTION_DIAL)
                .apply {
                    data = Uri.fromParts("tel", phone, null)
                }
                .addOutFlags()
    )

    fun openLink(link: String) = checkAndStartActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(link))
                .addOutFlags()
    )

    fun openMail(address: String, subject: String? = null, body: String? = null) = checkAndStartActivity(
            Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
                .apply {
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, body)
                }
                .addOutFlags()
    )

    fun shareText(text: String) = checkAndStartActivity(
            Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }
                .addOutFlags()
    )

    fun pandingIntentOpenRideScreen(context: Context, activityIntent: Intent) = PendingIntent.getActivity(
            context,
            0,
            activityIntent,
            0
    )

    private fun Intent.addOutFlags(clearTop: Boolean = false, notForRecents: Boolean = false): Intent {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK

        if (clearTop) addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        if (notForRecents) addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)

        return this
    }

    private fun checkAndStartActivity(activityIntent: Intent): Boolean =
        if (activityIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(activityIntent)
            true
        } else {
            false
        }
}