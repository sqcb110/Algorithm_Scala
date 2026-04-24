package scala

import scala.collection.mutable.ArrayBuffer

// 稀疏数组，创建和使用

object Test01_SparseArray {
  def main(args: Array[String]): Unit = {
    // 演示一个稀疏数组的使用案例
    val chessMap = Array.ofDim[Int](10, 10)
    val rowSize = 10
    val colSize = 10
    // 初始化棋盘
    chessMap(1)(2) = 1 // 1 表示黑子
    chessMap(2)(3) = 2 // 2 表示白子

    // 输出原始的地图
    for (item <- chessMap) {
      for (item2 <- item) {
        printf("%d\t", item2)
      }
      println()
    }

    // 将原始数组转化为稀疏数组
    // 思路 --> 达到对数据压缩的目的，可以保存任意大小的二维数组
    // class Node(row,col,value)
    // ArrayBuffer

    val arrayBuffer = ArrayBuffer[Node]()
    val nodeSize = new Node(rowSize,colSize,0)
    arrayBuffer.+=(nodeSize)
    for(i <- 0 until chessMap.length)(
      for(j <- 0 until chessMap(i).length){
        if(chessMap(i)(j) != 0){
          val node = new Node(i, j, chessMap(i)(j))
          arrayBuffer.+=(node)
        }
      }
    )
    // 输出稀疏数组
    println("----------稀疏数组----------")
    for(node <- arrayBuffer){
      printf("%d\t%d\t%d\n",node.row,node.col,node.value)
    }

    // 存盘

    // 读盘 --> 稀疏数组

    // 稀疏数组 --> 原始数组
    val newNode = arrayBuffer(0)
    val rowSize2 = newNode.row
    val colSize2 = newNode.col

    val chessMap2 = Array.ofDim[Int](rowSize2, colSize2)
    for(i <- 1 until arrayBuffer.length){
      val tmpNode = arrayBuffer(i)
      chessMap2(tmpNode.row)(tmpNode.col) = tmpNode.value
    }
    // 遍历复原后的原始数组
    println("-------从稀疏数组复原后的原始数组-------")
    for(i <- 0 until chessMap2.length){
      for(j <- 0 until chessMap2(i).length){
        printf("%d\t",chessMap2(i)(j))
      }
      println()
    }

  }

  class Node(val row: Int, val col: Int, val value: Int)
}
