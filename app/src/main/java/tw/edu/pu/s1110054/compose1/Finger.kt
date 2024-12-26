package tw.edu.pu.s1110054.compose1

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View

class Finger(context: Context?) : View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    //var xPos:Float = 200f
    //var yPos:Float = 200f

    var Count:Int = 0;
    var xPos = FloatArray(20)
    var yPos = FloatArray(20)
    var rainbow = IntArray(7)

    lateinit var bitmap: Bitmap

    init {
        rainbow = resources.getIntArray(R.array.rainbow) // 修正語法
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.robot)
    }



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.LTGRAY)

        for (i in 0..Count-1) {
            paint.color = rainbow[i % 7]
            canvas.drawCircle(xPos[i], yPos[i], 80f, paint)
        }

        paint.color = Color.BLUE
        paint.textSize = 50f
        canvas.drawText("多指觸控，圓形呈現彩虹顏色！", 50f,200f, paint)

        var SrcRect: Rect = Rect(0, 0, bitmap.width, bitmap.height) //裁切(顯示全部)
        var DestRect:Rect = Rect(200, 300,
            bitmap.width/4+200, bitmap.height/4+300) //原圖較大，縮成1/4顯示
        canvas.drawBitmap(bitmap, SrcRect, DestRect, paint)

    }


    override fun onTouchEvent(event: MotionEvent): Boolean{
        //xPos = event.getX()
        //yPos = event.getY()
        Count = event.getPointerCount()
        for (i in 0..Count-1) {
            xPos[i] = event.getX(i)
            yPos[i] = event.getY(i)
        }

        invalidate()
        return true
    }


}