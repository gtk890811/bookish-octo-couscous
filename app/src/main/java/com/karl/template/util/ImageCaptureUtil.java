package com.karl.template.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;

import com.karl.mvvmtemplate.AppConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by macmini6 on 31/08/2016.
 */
public class ImageCaptureUtil {

    public static String mTempPhotoPath;

    public static File createTempImageFile(Context context){
        if(mTempPhotoPath == null){
            File storageDir = new File(context.getExternalCacheDir(), AppConfig.IMAGE_DIR_NAME);
            if (!storageDir.exists()) {
                storageDir.mkdir();
            }
            File image = new File(storageDir, "tempCapturedImage.jpg");
            mTempPhotoPath = image.getAbsolutePath();
            return image;
        } else {
            return new File(mTempPhotoPath);
        }
    }

    public static void rotateImage(Context context, Uri uri){
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
            // no bit map found
            return;
        }

        Matrix matrix = new Matrix();
        matrix.postRotate(getCameraPhotoOrientation(context, uri));
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        try {
            File file = new File(mTempPhotoPath);
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, AppConfig.IMAGE_COMPRESS_QUALITY, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap.recycle();
        System.gc();
    }

    public static int getCameraPhotoOrientation(Context context, Uri imageUri) {
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imageUri.getPath());
            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:// 8
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:// 3
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:// 6
                    rotate = 90;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

    public static void deleteImage() {
        File file = new File(mTempPhotoPath);
        file.delete();
    }

    public static String encodeImageToBase64(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, AppConfig.IMAGE_COMPRESS_QUALITY, baos);
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

    public static Bitmap resizeImage(Context context){
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse("file://" + ImageCaptureUtil.mTempPhotoPath));
        } catch (IOException e) {
            e.printStackTrace();
            // no bit map found
            return null;
        }

        return Bitmap.createScaledBitmap(bitmap, AppConfig.IMAGE_SIZE, AppConfig.IMAGE_SIZE, true);
    }

    public static File saveImageFile(Context context, String subFolder) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPG_" + timeStamp + "_";
        File image;
        if (!AppConfig.FILE_STORAGE) {
            /** save to external directory */
            File storageDir = new File(Environment.getExternalStorageDirectory(), AppConfig.IMAGE_DIR_NAME);
            if (!storageDir.exists()) {
                storageDir.mkdir();
            }

            if (!subFolder.isEmpty()) {
                storageDir = new File(Environment.getExternalStorageDirectory(), AppConfig.IMAGE_DIR_NAME + "/" + subFolder);
                if (!storageDir.exists()) {
                    storageDir.mkdir();
                }
            }

            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

        } else {
            /** save to file directory */
            File storageDir = new File(context.getExternalFilesDir(null), AppConfig.IMAGE_DIR_NAME);
            if (!storageDir.exists()) {
                storageDir.mkdir();
            }

            if (!subFolder.isEmpty()) {
                storageDir = new File(context.getExternalFilesDir(null), AppConfig.IMAGE_DIR_NAME + "/" + subFolder);
                if (!storageDir.exists()) {
                    storageDir.mkdir();
                }
            }

            if (!storageDir.exists()) {
                storageDir.mkdir();
            }
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        }

        InputStream in = new FileInputStream(mTempPhotoPath);
        OutputStream out = new FileOutputStream(image.getAbsolutePath());

        // Copy the bits from instream to outstream
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();

        return image;
    }
}
