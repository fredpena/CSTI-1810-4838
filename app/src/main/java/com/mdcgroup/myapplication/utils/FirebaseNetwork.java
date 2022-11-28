package com.mdcgroup.myapplication.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseNetwork {

    private static final String TAG = "FirebaseNetwork";

    private static final String PATH_UPLOAD = "test/";
    private static final String URL_DOWNLOAD = String.format("gs://pucmm-b2fdb.appspot.com/%s", PATH_UPLOAD);
    private static final long ONE_MEGABYTE = 1024 * 1024;

    private static FirebaseNetwork sInstance;

    public static FirebaseNetwork obtain() {
        if (sInstance == null) {
            sInstance = new FirebaseNetwork();
        }
        return sInstance;
    }

    public FirebaseStorage getStorage() {
        return FirebaseStorage.getInstance();
    }

    public StorageReference getStorageReference() {
        return getStorage().getReference();
    }

    public void delete(final String key, final NetResponse<String> response) {
        final StorageReference reference = getStorageReference().child(PATH_UPLOAD + key);
        reference.delete()
                .addOnSuccessListener(aVoid -> {
                    Log.i(TAG, "delete:onSuccess");
                    response.onResponse("Successfully deleted on Firebase");
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "delete:onFailure");
                    response.onFailure(e);
                })
                .addOnCompleteListener(task -> Log.i(TAG, "delete:onComplete"))
                .addOnCanceledListener(() -> Log.i(TAG, "delete:onCanceled"));
    }


    public void upload(final Uri uri, final String key, final NetResponse<String> response) {
        final StorageReference reference = getStorageReference().child(PATH_UPLOAD + key);

        reference.putFile(uri)
                .addOnSuccessListener(taskSnapshot -> {
                    Log.i(TAG, "upload:onSuccess");
//                    taskSnapshot.getUploadSessionUri();
                    response.onResponse("Successfully upload on Firebase");
                }).addOnCanceledListener(() -> Log.i(TAG, "upload:onCanceled"))
                .addOnCompleteListener(task -> Log.i(TAG, "upload:onComplete"))
                .addOnFailureListener(e -> {
                    Log.e(TAG, "upload:onFailure");
                    response.onFailure(e);
                })
                .addOnPausedListener(taskSnapshot -> Log.i(TAG, "upload:onPaused"))
                .addOnProgressListener(taskSnapshot -> Log.i(TAG, "upload:onProgress"));
    }

    public void download(final String key, final NetResponse<Bitmap> response) {
        final StorageReference reference = getStorage().getReferenceFromUrl(URL_DOWNLOAD + key);

        reference.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(bytes -> {
                    Log.i(TAG, "download:onSuccess");
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    response.onResponse(bitmap);
                })
                .addOnCanceledListener(() -> Log.i(TAG, "download:onCanceled"))
                .addOnCompleteListener(task -> Log.i(TAG, "download:onComplete"))
                .addOnFailureListener(e -> {
                    Log.e(TAG, "download:onFailure");
                    response.onFailure(e);
                });
    }




}
