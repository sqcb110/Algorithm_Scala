package scala

import scala.util.control.Breaks._

// 单向链表写水浒英雄榜
object Test04_SingleLinkedList {
  def main(args: Array[String]): Unit = {
    val heroList = new SingleLinkedList
    val start = System.currentTimeMillis()
    heroList.add(new HeroNode(1, "宋江1", "及时雨"))
    heroList.add(new HeroNode(3, "宋江3", "及时雨3"))
    heroList.add(new HeroNode(4, "宋江4", "及时雨4"))
    heroList.show()
    heroList.add2(new HeroNode(2, "宋江2", "及时雨2"))
    println("----------添加2后打印----------")
    heroList.show()
    heroList.update(new HeroNode(4, "songjiang4", "ontimerain"))
    println("----------修改4后打印----------")
    heroList.show()
    heroList.update(new HeroNode(4, "宋江4", "及时雨4"))
    println("----------修改4后再次打印----------")
    heroList.show()
    heroList.delete(2)
    println("----------删除2后打印----------")
    heroList.show()
    val end = System.currentTimeMillis()
    val duration = (end - start)
    printf("消耗时长为：%d毫秒", duration)
  }
}

// 定义单向链表类管理Hero
class SingleLinkedList {

  // 先初始化一个头节点，头节点一般不会变动
  val headNode = new HeroNode(0, "", "")

  // 添加node
  // 在添加英雄人物时，直接添加到链表的尾部
  def add(node: HeroNode): Unit = {
    // 因为头节点不能动，因此我们需要有一个临时节点，作为辅助
    var tmp = headNode

    breakable {
      while (true) {
        if (tmp.next == null) {
          break()
        } else {
          tmp = tmp.next
        }
      }
    }
    tmp.next = node
  }

  // 添加node的方法2
  // 按照no顺序排列，按照no顺序添加
  // 如果链表中已经有拟插入node的对应no节点，退出。
  def add2(node: HeroNode): Unit = {
    // 因为头节点不能动，因此我们需要有一个临时节点，作为辅助
    var tmp = headNode

    breakable {
      while (true) {
        // 如果到最后一个节点了，加在这个节点之后就行了
        if (tmp.next == null) {
          tmp.next = node
          break()
        } else if (tmp.next.no > node.no) {
          // 如果tmp的下一个节点的no更大了，那就加在tmp之后
          val nextNode = tmp.next
          tmp.next = node
          node.next = nextNode
          break()
        } else if (tmp.next.no == node.no) {
          println("已有相同排名的英雄")
          break()
        } else {
          tmp = tmp.next
        }
      }
    }
  }

  // 修改单向链表中的节点,以no为基准
  def update(newNode: HeroNode): Unit = {
    if (headNode.next == null) {
      println("链表为空")
      return
    }
    var tmp = headNode.next
    var flag = false
    breakable {
      while (true) {
        if (tmp == null) {
          // println("没找到该节点")
          break()
        }
        if (tmp.no == newNode.no) {
          // 找到该节点
          flag = true
          break()
        }
        tmp = tmp.next
      }
    }
    // 进行修改
    if (flag) {
      tmp.name = newNode.name
      tmp.nickname = newNode.nickname
      printf("")
    } else {
      println("无法修改编号为 %d 的节点", tmp.no)
    }
  }

  // 删除节点
  def delete(deleteNo: Int): Boolean = {
    var tmp = headNode
    var flag = false
    breakable {
      while (true) {
        if (tmp.next == null) {
          break()
        }
        if (tmp.next.no == deleteNo) {
          flag = true
          break()
        }
        tmp = tmp.next
      }
    }
    if (flag) {
      val tmpNext = tmp.next.next
      tmp.next = tmpNext
      return true
    } else {
      return false
    }
  }

  // 遍历单向链表
  def show(): Unit = {
    // 判断当前链表是否为空
    if (headNode.next == null) {
      println("链表为空")
    }
    // 不为空，则进行遍历
    var tmp = headNode.next
    breakable {
      while (true) {
        if (tmp != null) {
          printf("英雄人物为：%d\t%s\t%s\n", tmp.no, tmp.name, tmp.nickname)
        } else {
          break()
        }
        tmp = tmp.next
      }
    }
  }
}

// 先创建HeroNode
class HeroNode(hNo: Int, hName: String, hNickname: String) {
  var no = this.hNo
  var name = this.hName
  var nickname = this.hNickname
  var next: HeroNode = null // 默认为空
}