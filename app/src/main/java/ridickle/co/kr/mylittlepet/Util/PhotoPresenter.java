package ridickle.co.kr.mylittlepet.Util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ridickle on 2017. 10. 10..
 */

public class PhotoPresenter {
    private static PhotoPresenter instance;
    private static Bitmap tempBitmap = null;

    public static synchronized PhotoPresenter getInstance() {
        if (instance == null) {
            instance = new PhotoPresenter();
        }
        return instance;
    }

    public Bitmap getBitmap() {
        return tempBitmap;
    }

    public static void bitmapClear() {
        tempBitmap.recycle();
        tempBitmap = null;
    }


    public void setBitmap(Context context, Intent intent) {
        if (intent.getData() != null) {
            try {
                tempBitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), intent.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            tempBitmap = (Bitmap) intent.getExtras().get("data");
        }
    }

    public File getFile(File file) {
        try {
            file.createNewFile();
            //Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            tempBitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapData = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public Bitmap imgRotate(){
        int width = tempBitmap.getWidth();
        int height = tempBitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        Bitmap resizedBitmap = Bitmap.createBitmap(tempBitmap, 0, 0, width, height, matrix, true);
        bitmapClear();
        tempBitmap = resizedBitmap;

        return resizedBitmap;
    }
}
