package ru.mobileup.kickerup.extension

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import io.reactivex.Completable
import io.reactivex.Single

fun <T : Any> Task<T>.toSingle(isCompletableTask: Boolean = false) = Single.create<T> { emitter ->
    if (isCompletableTask) {
        addOnCompleteListener {
            if (it.isSuccessful)
                emitter.onSuccess(it.result)
            else
                emitter.onError(it.exception!!)
        }
    } else {
        addOnSuccessListener { emitter.onSuccess(it) }
        addOnFailureListener { emitter.onError(it) }
    }
}

fun <T : Any> Task<T>.toCompletable() = Completable.create { emitter ->
    addOnCompleteListener {
        if (it.isSuccessful)
            emitter.onComplete()
        else
            emitter.onError(it.exception!!)
    }
}

fun DocumentReference.toSingle() = Single.create<DocumentSnapshot> { emitter ->
    addSnapshotListener { documentSnapshot, exception ->
        if (exception != null)
            emitter.onError(exception)
        else
            emitter.onSuccess(documentSnapshot!!)
    }
}