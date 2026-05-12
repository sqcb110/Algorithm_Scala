package scala

// 用数组模拟栈
object Test07_ArrayStack {
  def main(args: Array[String]): Unit = {
    val stack = new ArrayStack(10)
    for(i <- 0 to 9){
      stack.push(i)
    }
    stack.pop()
    stack.push(10)
    stack.show()
  }
}

class ArrayStack(size: Int) {
  val maxSize = size
  var stack = new Array[Int](maxSize)
  // 栈顶的索引，默认为-1，栈空的时候同样为-1
  var topIndex = -1

  // 判断栈是否满
  def isFull(): Boolean = {
    return topIndex == maxSize - 1
  }

  // 判断栈是否空
  def isEmpty(): Boolean = {
    return topIndex == -1
  }

  // 入栈
  def push(n: Int): Unit = {
    if(isFull()){
      println("栈已满")
    }else{
      topIndex += 1
      stack(topIndex) = n
    }
  }

  // 出栈
  def pop(): Unit = {
    if(isEmpty()){
      println("栈已空")
    }else{
      stack(topIndex) = -1
      topIndex -= 1
    }
  }

  // 遍历栈
  def show(): Unit = {
    if(isEmpty()){
      println("栈为空")
    }else{
      for(i <- 0 to topIndex){
        printf("下标%d的元素为%d\n",i,stack(i))
      }
    }
  }
}