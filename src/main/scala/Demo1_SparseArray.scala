import scala.collection.mutable.ArrayBuffer
// 稀疏数组，模拟棋盘的存盘和读盘

object Demo1_SparseArray {
	def main(args: Array[String]): Unit = {
		// 创建一个二维数组棋盘
		val chessMap: Array[Array[Int]] = Array.ofDim[Int](10, 10)
		val rowSize = 10
		val colSize = 10
		chessMap(1)(2) = 1
		chessMap(2)(3) = 2
		println("-------------展示二维数组-------------")
		for(i <- 0 until chessMap.length){
			for(j <- 0 until chessMap(i).length){
				printf("%d\t",chessMap(i)(j))
			}
			println()
		}

		// 创建一个稀疏数组
		val spArr = new ArrayBuffer[Node]()

		// 棋盘 --> 稀疏数组
		// 第一行，存原始数组的规模
		spArr.+=(new Node(rowSize,colSize,0))

		// 第二行开始，存数组的非默认值数据
		for(i <- 0 until chessMap.length){
			for(j <- 0 until chessMap(i).length){
				if(chessMap(i)(j) != 0){
					spArr.+=(new Node(i,j,chessMap(i)(j)))
				}
			}
		}

		println("-------------展示稀疏数组-------------")
		for(e <- spArr){
			printf("%d\t%d\t%d\n",e.row,e.col,e.value)
		}

		// 稀疏数组 --> 文件存盘

		// 读取硬盘中的文件

		// 从稀疏数组恢复成新的棋盘
		// 创建新二维数组，用来模拟从稀疏数组恢复的棋盘
		val newChessMap: Array[Array[Int]] = Array.ofDim[Int](10, 10)
		for(i <- 1 until spArr.length){
			val tmpNode: Node = spArr(i)
			newChessMap(tmpNode.row)(tmpNode.col) = tmpNode.value
		}

		println("-------------展示新的二维数组-------------")
		for(i <- 0 until newChessMap.length){
			for(j <- 0 until newChessMap(i).length){
				printf("%d\t",newChessMap(i)(j))
			}
			println()
		}
	}
	class Node(val row: Int, val col: Int, val value: Int)
}

