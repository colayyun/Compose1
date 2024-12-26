package tw.edu.pu.s1110054.compose1

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View

class Finger(context: Context) : View(context), ScaleGestureDetector.OnScaleGestureListener {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var Count: Int = 0
    private val xPos = FloatArray(20)
    private val yPos = FloatArray(20)
    private val rainbow: IntArray = resources.getIntArray(R.array.rainbow)
    private val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.robot)
    private val sg = ScaleGestureDetector(context, this)
    private var factor: Float = 1.0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.LTGRAY)

        for (i in 0 until Count) {
            paint.color = rainbow[i % 7]
            canvas.drawCircle(xPos[i], yPos[i], 80f, paint)
        }

        paint.color = Color.BLUE
        paint.textSize = 50f
        canvas.drawText("多指觸控，圓形呈現彩虹顏色！", 50f, 200f, paint)

        val scaledWidth = (bitmap.width * factor).toInt()
        val scaledHeight = (bitmap.height * factor).toInt()
        val left = (width - scaledWidth) / 2
        val top = (height - scaledHeight) / 2
        val destRect = Rect(left, top, left + scaledWidth, top + scaledHeight)
        canvas.drawBitmap(bitmap, null, destRect, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Count = event.pointerCount.coerceAtMost(20)
        for (i in 0 until Count) {
            xPos[i] = event.getX(i)
            yPos[i] = event.getY(i)
        }
        sg.onTouchEvent(event)
        invalidate()
        return true
    }

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector) {
        invalidate()
    }

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        factor *= detector.scaleFactor
        factor = factor.coerceIn(0.1f, 5.0f)
        invalidate()
        return true
    }
}
