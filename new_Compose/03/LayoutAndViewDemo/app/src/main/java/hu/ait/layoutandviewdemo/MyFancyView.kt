package hu.ait.layoutandviewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MyFancyView(ctx: Context, attrs: AttributeSet)
    : View(ctx, attrs){

    var paintBg: Paint = Paint()
    var paintLine: Paint = Paint()

    var circleX: Float = -1f
    var circleY: Float = -1f

    init {
        paintBg.setColor(Color.GREEN)
        paintBg.style = Paint.Style.FILL

        paintLine.setColor(Color.BLACK)
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5f
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawRect(0f,0f, width.toFloat(),
            height.toFloat(), paintBg)
        canvas.drawLine(0f,0f, width.toFloat(),
            height.toFloat(), paintLine)

        if (circleX>-1 && circleY>-1) {
            canvas.drawCircle(
                circleX,
                circleY, 75f, paintLine
            )
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_MOVE) {
            circleX = event!!.x
            circleY = event!!.y
            invalidate()
        }

        return true
    }

}