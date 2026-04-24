import scala.io.StdIn
// 用数组模拟队列

object Demo2_ArrayQueue {
	def main(args: Array[String]): Unit = {
		println("请输入队列的长度：")
		val length: Int = StdIn.readInt()
		val arr = new Queue(length)
		while (true) {
			println("入列输入：add")
			println("出列输入：fet")
			println("展示队列输入：show")
			println("退出输入：exit")
			val order = StdIn.readLine()
			order match {
				case "add" => {
					println("请输入入列的编号：")
					val num = StdIn.readInt()
					arr.addQueue(num)
				}
				case "fet" => arr.fetchQueue()
				case "exit" => System.exit(0)
				case "show" => arr.showQueue()
			}
		}
	}
}

class Queue(val size: Int) {
	val maxSize = size
	val queue = new Array[Int](maxSize)
	var front = -1
	var rear = -1

	def isEmpty(): Boolean = {
		if (rear == front) true
		else false
	}

	def isFull(): Boolean = {
		if (rear == maxSize - 1) true
		else false
	}

	def addQueue(e: Int): Unit = {
		if (isFull()) {
			println("队列已满")
		} else {
			rear += 1
			queue(rear) = e
		}
	}

	def fetchQueue(): Unit = {
		if (isEmpty()) {
			println("队列为空")
		} else {
			front += 1
			queue(front) = 0
		}
	}

	def showQueue(): Unit = {
		if(isEmpty()){
			println("队列为空")
			return
		}else{
			for(i <- front +1 to rear){
				printf("索引%d为:\t%d\t",i,queue(i))
				println()
			}
		}
	}
}
