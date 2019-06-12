package com.razytech.razynet.Utils.takeimage;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by A.Noby on 6/9/2019.
 */

public interface TakeImageReceiveView {
    void AftergettingImage(Bitmap bitmap, byte[] array, String fileName, File FilePath);
}
