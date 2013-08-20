package com.bilouro.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;

/**
 * Created with IntelliJ IDEA.
 * User: bilouro
 * Date: 19/08/13
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    public static void carrega_imagem_reduzida(String filename, ImageView _imageview, int no_image_resource) {
        try {
            Bitmap bmp = BitmapFactory.decodeFile(
                    Environment.getExternalStorageDirectory() + "/" + filename
            );
            Bitmap reduced_bmp = Bitmap.createScaledBitmap(bmp, 100, 100, true);
            _imageview.setImageBitmap(reduced_bmp);
        } catch (Exception e) {
            _imageview.setImageResource(no_image_resource);
        }
    }
}
