package com.client.loanapproval.view

/**
* This class contain a point values on the view.
* They are x, y values and the time (milisecond) when user move finger to the point.
*/
class Point(x:Float, y:Float, time:Long) {
  var x:Float = 0.toFloat()
  var y:Float = 0.toFloat()
  var time:Long = 0
  init{
    this.x = x
    this.y = y
    this.time = time
  }
  /**
	 * Caculate the distance between current point to the other.
	 * @param p the other point
	 * @return
	 */
  private fun distanceTo(p:Point):Float {
    return (Math.sqrt(Math.pow((x - p.x).toDouble(), 2.0) + Math.pow((y - p.y).toDouble(), 2.0))).toFloat()
  }
  /**
	 * Caculate the velocity from the current point to the other.
	 * @param p the other point
	 * @return
	 */
  fun velocityFrom(p:Point):Float {
    return distanceTo(p) / (this.time - p.time)
  }
}