package scala

/*
用栈实现计算器的效果
1、一个数字栈，一个运算符栈。
2、对expression进行扫描，一个一个字符地取出来
3、对于数字，直接入数栈
4、对于运算符：
4.1 如果当前符号栈没有数据，直接入栈
4.2 如果符号栈有数据，且当前符号优先级小于符号栈栈顶符号，则符号栈栈顶出栈，数字栈出栈两个数进行运算，运算结果进数字栈。
4.3 反之，则符号栈入栈
 */
object Test08_Calculator {
  def main(args: Array[String]): Unit = {

  }

  def calculate(expression: String): Int = {

  }
}

class StackNumOrOperator(size: Int) {
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
