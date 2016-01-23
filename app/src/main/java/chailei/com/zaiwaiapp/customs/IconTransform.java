package chailei.com.zaiwaiapp.customs;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.v4.view.ViewCompat;

import com.squareup.picasso.Transformation;

/**
 * Created by Administrator on 16-1-23.
 */
public class IconTransform implements Transformation {


    @Override
    public Bitmap transform(Bitmap source) {

        Bitmap bitmap = Bitmap.createBitmap(source.getWidth(),source.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);//没有毛边，是图片边缘平滑
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        Canvas canvas = new Canvas(bitmap);

//        RectF rectF = new RectF(source.getHeight(),source.getWidth(), source.getHeight(), source.getWidth());
//        RectF rectF = new RectF(20,20,20,20);

//        canvas.drawRoundRect(rectF,5,5,paint);
        canvas.drawCircle(source.getWidth()/2,source.getHeight()/2,source.getWidth()/2,paint);
        source.recycle();
        return bitmap;
        }

@Override
public String key() {
        return "RoundRect";
        }
        }
