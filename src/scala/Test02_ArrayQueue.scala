package scala

import scala.io.StdIn

// 用数组实现队列
object Test02_ArrayQueue {
  def main(args: Array[String]): Unit = {
    // 初始化一个队列
    val queue = new ArrayQueue(3)
    var key = ""
    while (true) {
      println("show: 表示显示队列")
      println("add: 表示添加数据")
      println("fetch: 表示取数据")
      println("exit: 表示退出程序")
      println("请输入：")
      key = StdIn.readLine()
      key match {
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

// 使用数组模拟队列
class ArrayQueue(arrMaxSize: Int) {
  val maxSize = arrMaxSize
  val arr = new Array[Int](maxSize)
  var front = -1 // 指向队首,指向队列数据的前一个位置
  var rear = -1 // 指向队尾,指向队列的最后一个数据

  // 判断队列是否满
  def isFull(): Boolean = {
    rear == maxSize - 1
  }

  // 判断队列是否空
  def isEmpty(): Boolean = {
    rear == front
  }

  // 往队列中加数据
  def addQueue(e: Int): Unit = {
    if(isFull()){
      println("队列已满")
      return
    }
    rear += 1
    arr(rear) = e
  }

  // 从队列中取数据
  def fetchQueue(): Unit = {
    if(isEmpty()){
      println("队列已空")
      return
    }
    arr(front) = 0
    front += 1
  }

  // 显示队列的所有数据
  def showQueue(): Unit = {
    if (isEmpty()) {
      println("队列为空")
      return
    }
    for (i <- front + 1 to rear) {
      printf("arr[%d]=%d\n", i, arr(i))
    }
  }

}