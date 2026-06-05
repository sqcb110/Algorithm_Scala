package scala
import scala.io.StdIn
import scala.util.control.Breaks._
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
    println("请输入表达式字符：")
    val str = StdIn.readLine()
    printf("计算结果是：%d\n" , calculator(str))
  }

  // 计算方法
  def calculator(expression: String): Int = {
    // 先准备两个栈，一个数栈，一个符号栈
    val numStack = new StackNumOrOperator(6)
    val optStack = new StackNumOrOperator(5)

    // 把表达式拆成字符
    val charArray = disassembleString(expression)

    // 把字符放入两个栈
    putCharIntoStacks(charArray, numStack, optStack)

    // 出栈计算
    popCalculate(numStack, optStack)

  }

  // 把表达式拆成字符
  def disassembleString(expression: String): Array[Char] = {
    expression.toCharArray
  }

  // 把字符放入两个栈
  def putCharIntoStacks(arr: Array[Char], stack1: StackNumOrOperator, stack2: StackNumOrOperator): Unit = {
    val numArray = Array("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
    val optArray = Array('+', '-', '*', '/')

    for (i <- 0 until arr.length) {
      if (numArray.contains(arr(i).toString)) {
        // 如果是数字，直接入数栈。
        stack1.push(arr(i).toString.toInt)
      } else if (optArray.contains(arr(i))) {
        // 如果是符号，则：
        if (!stack2.isEmpty && getOptPriority(stack2.stack(stack2.topIndex).toChar) <= getOptPriority(arr(i))) {
          // 4.1 如果符号栈有数据，且当前符号优先级小于等于符号栈栈顶符号，则符号栈栈顶出栈，数字栈出栈两个数进行运算，运算结果进数字栈。
          val optTop = stack2.pop().toChar
          val num1 = stack1.pop()
          val num2 = stack1.pop()
          val resultMid = optTop match {
            case '*' => num2 * num1
            case '/' => num2 / num1
            case '+' => num2 + num1
            case '-' => num2 - num1
            case _   => throw new IllegalArgumentException(s"不支持的运算符: $optTop")
          }
          stack1.push(resultMid)
          stack2.push(arr(i).toInt)
        } else if (stack2.isEmpty || getOptPriority(stack2.stack(stack2.topIndex).toChar) > getOptPriority(arr(i))) {
          // 4.2 如果当前符号栈没有数据，直接入栈
          // 4.3 反之，则符号栈入栈
          stack2.push(arr(i).toInt)
        }
      }
    }
  }

  // 出栈计算最终结果
  def popCalculate(nStack: StackNumOrOperator, oStack: StackNumOrOperator): Int = {
      while (!oStack.isEmpty() && nStack.topIndex >= 1) {
        val num1 = nStack.pop()
        val num2 = nStack.pop()
        val opt = oStack.pop().toChar
        val midResult = opt match {
          case '*' => num2 * num1
          case '/' => num2 / num1
          case '+' => num2 + num1
          case '-' => num2 - num1
          case _   => throw new IllegalArgumentException(s"不支持的运算符: $opt")
        }
          nStack.push(midResult)
      }
    nStack.pop()
  }

  // 计算符号优先级
  def getOptPriority(opt: Char): Int = {
    if (opt == '*' | opt == '/') {
      return 0
    } else{
      return 1
    }
  }
}

class StackNumOrOperator(size: Int) {
  val maxSize = size
  var stack = new Array[Int](maxSize) // 如果用arraybuffer，无容量限制
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
    if (isFull()) {
      println("栈已满")
    } else {
      topIndex += 1
      stack(topIndex) = n
    }
  }

  // 出栈
  def pop(): Int = {
    if (isEmpty()) {
      println("栈已空")
      -1
    } else {
      val tmp = stack(topIndex)
      stack(topIndex) = -1
      topIndex -= 1
      tmp
    }
  }

  // 遍历栈
  def show(): Unit = {
    if (isEmpty()) {
      println("栈为空")
    } else {
      for (i <- 0 to topIndex) {
        printf("下标%d的元素为%d\n", i, stack(i))
      }
    }
  }
}
