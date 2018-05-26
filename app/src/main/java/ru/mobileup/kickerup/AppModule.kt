package ru.mobileup.kickerup

import android.app.NotificationManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.module.AndroidModule
import org.koin.dsl.context.Context
import ru.mobileup.kickerup.system.PermissionsHelper


class AppModule: AndroidModule() {

    override fun context(): Context = applicationContext {

        provide { FirebaseAuth.getInstance() }
        provide { FirebaseFirestore.getInstance() }
        provide { FirebaseMessaging.getInstance() }
        provide { PermissionsHelper() }
        provide { get<android.content.Context>().getSystemService(android.content.Context.NOTIFICATION_SERVICE) as NotificationManager }
    }
}