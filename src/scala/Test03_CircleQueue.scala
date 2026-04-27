package scala

import scala.io.StdIn

// 环形队列
// front，队头，默认值-1，只要取过数，队头就增加，第一次取数，增加为索引0
// rear，队尾，默认值-1，只要入过队，队尾就增加，第一次入队，增加为索引0
// first，第一个元素，默认值也是-1，只要
object Test03_CircleQueue {
  def main(args: Array[String]): Unit = {
    // 初始化一个数组
    val queue = new CircleQueue(5)
    var order = ""
    while (true) {
      println("show: 表示显示队列")
      println("add: 表示添加数据")
      println("fetch: 表示取数据")
      println("exit: 表示退出程序")
      println("请输入：")
      order = StdIn.readLine()
      order match {
        case "show" => queue.showQueue()
        case "add" => {
          println("请输入整数：")
          val n = StdIn.readInt()
          queue.addQueue(n)
        }
        case "fetch" => queue.fetchQueue()
        case "exit" => System.exit(0)
      }
    }
  }
}

// 使用环形数组模拟队列
class CircleQueue(n:Int){
  val maxSize = n
  val circleArray = new Array[Int](maxSize)
  var front = -1
  var first = -1
  var rear  = -1

  // 判断环形数组是否是空
  def isEmpty(): Boolean = {
    front == first
  }

  // 判断环形数组是否满
  def isFull(): Boolean = {
    (first == 0 & rear == maxSize - 1) | (first == rear + 1)
  }

  // 往环形数组里加元素
  def addQueue(e: Int): Unit = {
    // 若数组已满
    if(isFull()){
      println("数组已满")
    } else {
      rear = (rear + 1) % maxSize
      circleArray(rear) = e
    }
  }

  // 从环形数组取元素
  def fetchQueue(): Any = {
    if(isEmpty()){
      return new Exception("数组为空")
    } else {
      if(first== maxSize - 1){
        front = (front + 1) % maxSize
        first = (first + 1) % maxSize
        circleArray(front)
      }
    }
  }

  def showQueue(): Unit = {

  }
}
