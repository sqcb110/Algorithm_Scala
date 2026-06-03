package scala

import scala.util.control.Breaks._

// 用环形单向链表解决约瑟夫问题
object Test06_Josephu {
  def main(args: Array[String]): Unit = {
    // 生成一个元素个数为n的单向环形链表，模拟小孩围坐一圈。
    val game = new KidsGame(7)
    game.addKid(7)
    game.showKids()
    game.countKids(3,3,7)
    println("出列顺序为：")
    for(i <- 0 until game.outArr.length ){
      printf("%d ",game.outArr(i))
    }
  }
}

// 小孩类
class Kid(kId: Int) {
  val id: Int = kId
  var next: Kid = null
}

// 游戏建立
class KidsGame(numKids: Int) {
  val num = numKids
  var firstKid: Kid = null
  var currentKid: Kid = null
  val outArr = new Array[Int](num)
  // 生成玩游戏的一圈围坐的小孩
  def addKid(num: Int): Unit = {
    if (num < 1) {
      println("人数不能小于1")
      return
    }
    for (i <- 1 to num) {
      val kid = new Kid(i)
      if (i == 1) { // 第一个小孩
        firstKid = kid
        firstKid.next = firstKid // 形成环形
        currentKid = firstKid
      } else {
        currentKid.next = kid
        kid.next = firstKid
        currentKid = kid
      }
    }
  }

  // 展示小孩
  def showKids(): Unit = {
    if (firstKid.next == null) {
      println("没有显示的人")
    }
    var cur = firstKid
    breakable {
      while (true) {
        printf("编号%d的小孩\n", cur.id)
        cur = cur.next
        if (cur.next == firstKid) {
          printf("编号%d的小孩\n", cur.id)
          break()
        }
      }
    }
    // printf("当前小孩为：%d",currentKid.id)
  }

  // 数数出列
  // startId：从几号小孩开始数数；countNum：一次数几个数；nums：总共有几个小孩
  def countKids(startId: Int, countNum: Int, nums: Int): Unit = {
    if (firstKid.next == null || startId > nums || startId < 1){
      println("参数不对")
    }
    // 开始数数前，将currentKid定位到startId的位置
    for(i <- 1 to startId){
      currentKid = currentKid.next
    }
    // 同时定位一个preKid，在currentKid前一个位置。
    var preKid = currentKid
    breakable{
      while(true){
        if(preKid.next == currentKid){
          break()
        }
        preKid = preKid.next
      }
    }
    // 开始数数，每次数countNum个数，每次数完countNum个数，数到谁，谁出列。
    var cnt = 0
    var tmp : Kid = null
    breakable{
      while(true){
        // 当环形中只剩最后一个小孩时，中断循环。
        if(currentKid.next == currentKid){
          outArr(cnt) = currentKid.id
          cnt += 1
          break()
        }
        // 环形中还剩多个小孩时，正常循环
        for(i <- 1 until countNum){
          currentKid = currentKid.next
          preKid = preKid.next
        }
        outArr(cnt) = currentKid.id
        cnt += 1
        preKid.next = currentKid.next
        currentKid = preKid.next
      }
    }
  }
}