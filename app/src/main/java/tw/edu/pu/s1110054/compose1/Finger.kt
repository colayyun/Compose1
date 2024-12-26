package tw.edu.pu.s1110054.compose1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class Finger(context: Context?) : View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    //var xPos:Float = 200f
    //var yPos:Float = 200f

    var Count:Int = 0
    var xPos = FloatArray(20)
    var yPos = FloatArray(20)
    var rainbow = IntArray(7)



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.LTGRAY)

        for (i in 0..Count-1) {
            paint.color = Color.YELLOW
            canvas.drawCircle(xPos[i], yPos[i], 80f, paint)
        }

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