package com.troubi.newspapr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

/**
 * Created by michaeljakob on 10/5/14.
 */
public class Utility {


    public static Bitmap createBlurImage(Context ctx, Bitmap photo) {
        final RenderScript rs = RenderScript.create(ctx);
        final Allocation input = Allocation
                .createFromBitmap(rs, photo, Allocation.MipmapControl.MIPMAP_NONE,
                        Allocation.USAGE_SCRIPT);
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur
                .create(rs, Element.U8_4(rs));
        script.setInput(input);
        script.setRadius(25f);
        script.forEach(output);
        output.copyTo(photo);
        return photo;
    }

    public static Bitmap createBlurImage(Context ctx, ImageView imageView) {
        BitmapDrawable drawable = ((BitmapDrawable) imageView.getDrawable());
        Bitmap bm = drawable.getBitmap();
        return createBlurImage(ctx, bm.copy(Bitmap.Config.ARGB_8888, true));
    }
}
