package com.client.loanapproval.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.client.loanapproval.R
import java.io.OutputStream

class DrawView : View {
    //The stroke width (default will be 7 pixels).
    private var STROKE_WIDTH = 7

    // A value is used in a lowpass filter to calculate the velocity between two points.
    private var VELOCITY_FILTER_WEIGHT = 0.2f

    // Those of values present for : ---startPoint---previousPoint----currentPoint
    private lateinit var previousPoint: Point
    private lateinit var startPoint: Point
    private lateinit var currentPoint: Point

    // contain the last velocity. Will be used to calculate the Stroke Width
    private var lastVelocity: Float = 0.toFloat()

    // contain the last stroke width. Will be used to calculate the Stroke Width
    private var lastWidth: Float = 0.toFloat()

    // The paint will be used to drawing the line
    private lateinit var paint: Paint

    // We 'll draw lines to this bitmap.
    private lateinit var bmp: Bitmap

    // This paint will be used to draw the bitmap @bmp to view's canva
    private lateinit var paintBm: Paint

    // The Canvas which is used to draw line and contain data to the bitmap @bmp
    private lateinit var canvasBmp: Canvas

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.setWillNotDraw(false)
        this.setDrawingCacheEnabled(true)
        val array = context.obtainStyledAttributes(attrs, R.styleable.SignView)
        // Get the STROKE_WIDTH and VELOCITY_FILTER_WEIGHT from xml
        if (array != null) {
            STROKE_WIDTH =
                array.getDimensionPixelSize(R.styleable.SignView_strokeSize, STROKE_WIDTH)
            VELOCITY_FILTER_WEIGHT =
                array.getFloat(R.styleable.SignView_filterWeight, VELOCITY_FILTER_WEIGHT)
        }
        array.recycle()
        init()
    }

    constructor(context: Context) : super(context) {
        this.setWillNotDraw(false)
        this.setDrawingCacheEnabled(true)
        init()
    }

    /**
     * This method is used to init the paints.
     */
    fun init() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paintBm = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.setAntiAlias(true)
        paint.setStyle(Paint.Style.STROKE)
        paint.setStrokeJoin(Paint.Join.ROUND)
        paint.setStrokeCap(Paint.Cap.ROUND)
        paint.setStrokeWidth(STROKE_WIDTH.toFloat())
        paint.setColor(Color.BLACK)
        paintBm.setAntiAlias(true)
        paintBm.setStyle(Paint.Style.STROKE)
        paintBm.setStrokeJoin(Paint.Join.ROUND)
        paintBm.setStrokeCap(Paint.Cap.ROUND)
        paintBm.setStrokeWidth(STROKE_WIDTH.toFloat())
        paintBm.setColor(Color.BLACK)
        paintBm.setAlpha(100)
    }

    override fun onLayout(
        changed: Boolean, left: Int, top: Int, right: Int,
        bottom: Int
    ) {
        super.onLayout(changed, left, top, right, bottom)
        /**
         * Recreate the bitmap when the layout has changed.
         * Note : after recreate the bitmap, all drawing will be gone.
         */
        bmp = Bitmap.createBitmap(right - left, bottom - top, Bitmap.Config.ARGB_8888)
        canvasBmp = Canvas(bmp)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        when (event.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                // In Action down currentPoint, previousPoint, startPoint will be set at the same point.
                currentPoint = Point(event.getX(), event.getY(), System.currentTimeMillis())
                previousPoint = currentPoint
                startPoint = previousPoint
            }
            MotionEvent.ACTION_MOVE -> {
                // Those of values present for : ---startPoint---previousPoint----currentPoint-----
                startPoint = previousPoint
                previousPoint = currentPoint
                currentPoint = Point(event.getX(), event.getY(), System.currentTimeMillis())
                // Calculate the velocity between the current point to the previous point
                var velocity = currentPoint.velocityFrom(previousPoint)
                // A simple lowpass filter to mitigate velocity aberrations.
                velocity =
                    VELOCITY_FILTER_WEIGHT * velocity + (1 - VELOCITY_FILTER_WEIGHT) * lastVelocity
                // Caculate the stroke width based on the velocity
                var strokeWidth = getStrokeWidth(velocity)
                // Draw line to the canvasBmp canvas.
                drawLine(canvasBmp, paint, lastWidth, strokeWidth)
                // Tracker the velocity and the stroke width
                lastVelocity = velocity
                lastWidth = strokeWidth
            }
            MotionEvent.ACTION_UP -> {
                startPoint = previousPoint
                previousPoint = currentPoint
                currentPoint = Point(event.getX(), event.getY(), System.currentTimeMillis())
                drawLine(canvasBmp, paint, lastWidth, 0f)
            }
            else -> {
            }
        }
        invalidate()
        return true
    }

    private fun getStrokeWidth(velocity: Float): Float {
        return STROKE_WIDTH - velocity
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bmp, 0.0f, 0.0f, paintBm)
    }

    // Generate mid point values
    private fun midPoint(p1: Point, p2: Point): Point {
        return Point((p1.x + p2.x) / 2.0f, (p1.y + p2.y) / 2, (p1.time + p2.time) / 2)
    }

    private fun drawLine(canvas: Canvas, paint: Paint, lastWidth: Float, currentWidth: Float) {
        val mid1 = midPoint(previousPoint, startPoint)
        val mid2 = midPoint(currentPoint, previousPoint)
        draw(canvas, mid1, previousPoint, mid2, paint, lastWidth, currentWidth)
    }

    /**
     * This method is used to draw a smooth line. It follow "Bézier Curve" algorithm (it's Quadratic curves).
     * </br> For reference, you can see more detail here: <a href="http://en.wikipedia.org/wiki/B%C3%A9zier_curve">Wiki</a>
     * </br> We 'll draw a smooth curves from three points. And the stroke size will be changed depend on the start width and the end width
     *
     * @param canvas : we 'll draw on this canvas
     * @param p0 the start point
     * @param p1 mid point
     * @param p2 end point
     * @param paint the paint is used to draw the points.
     * @param lastWidth start stroke width
     * @param currentWidth end stroke width
     */
    private fun draw(
        canvas: Canvas,
        p0: Point,
        p1: Point,
        p2: Point,
        paint: Paint,
        lastWidth: Float,
        currentWidth: Float
    ) {
        var xa: Float
        var xb: Float
        var ya: Float
        var yb: Float
        var x: Float
        var y: Float
        var different = (currentWidth - lastWidth)
        var i = 0f
        while (i < 1) {
            // This block of code is used to calculate next point to draw on the curves
            xa = getPt(p0.x, p1.x, i)
            ya = getPt(p0.y, p1.y, i)
            xb = getPt(p1.x, p2.x, i)
            yb = getPt(p1.y, p2.y, i)
            x = getPt(xa, xb, i)
            y = getPt(ya, yb, i)
            //
            // reset strokeWidth
            paint.setStrokeWidth(lastWidth + different * (i))
            canvas.drawPoint(x, y, paint)
            i += 0.01f
        }
    }

    // This method is used to calculate the next point cordinate.
    private fun getPt(n1: Float, n2: Float, perc: Float): Float {
        val diff = n2 - n1
        return n1 + (diff * perc)
    }

    /**
     * This method is used to save the bitmap to an output stream
     * @param outputStream
     */
    fun save(outputStream: OutputStream) {
        val bitmap = drawingCache
        bitmap?.compress(Bitmap.CompressFormat.PNG, 80, outputStream)
    }

    companion object {
        private val TAG = "DrawView"
    }
}