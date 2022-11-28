package com.mdcgroup.myapplication.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

public class CommonUtil {

    public static CharSequence[] getOptions() {
        final CharSequence[] options = new CharSequence[PhotoOptions.values().length];
        final AtomicInteger atomic = new AtomicInteger(0);

        for (PhotoOptions obj : Arrays.asList(PhotoOptions.values())) {
            options[atomic.getAndIncrement()] = obj.getValue();
        }

        return options;
    }

    public static void photoOptions(@NonNull Context context, @NonNull Function<PhotoOptions, Consumer<Intent>> consumer) {
        final CharSequence[] options = getOptions();

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your category picture");

        builder.setItems(options, (dialog, item) -> {

            if (options[item].equals(PhotoOptions.TAKE_PHOTO.getValue())) {
                Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                consumer.apply(PhotoOptions.TAKE_PHOTO).accept(takePicture);

            } else if (options[item].equals(PhotoOptions.CHOOSE_GALLERY.getValue())) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                consumer.apply(PhotoOptions.CHOOSE_GALLERY).accept(pickPhoto);

            } else if (options[item].equals(PhotoOptions.CHOOSE_FOLDER.getValue())) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                consumer.apply(PhotoOptions.CHOOSE_FOLDER).accept(Intent.createChooser(intent, "Select Picture"));

            } else if (options[item].equals(PhotoOptions.CANCEL.getValue())) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static Uri getImageUri(@NonNull Context context, @NonNull Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, null, null);
        return Uri.parse(path);
    }
}
