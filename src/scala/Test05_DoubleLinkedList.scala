package scala
import util.control.Breaks._

// 双向链表写水浒英雄榜
object Test05_DoubleLinkedList {
  def main(args: Array[String]): Unit = {
    val heroList = new DoubleLinkedList
    val start = System.currentTimeMillis()
    heroList.add(new HeroNode2(1, "宋江1", "及时雨"))
    heroList.add(new HeroNode2(3, "宋江3", "及时雨3"))
    heroList.add(new HeroNode2(4, "宋江4", "及时雨4"))
    heroList.add(new HeroNode2(5, "宋江5", "及时雨4"))
    heroList.add(new HeroNode2(6, "宋江6", "及时雨4"))
    heroList.show()
    heroList.add2(new HeroNode2(2, "宋江2", "及时雨2"))
    println("----------添加2后打印----------")
    heroList.show()
    heroList.update(new HeroNode2(4, "songjiang4", "ontimerain"))
    println("----------修改4后打印----------")
    heroList.show()
    heroList.update(new HeroNode2(4, "宋江4", "及时雨4"))
    println("----------修改4后再次打印----------")
    heroList.show()
    heroList.delete(2)
    println("----------删除2后打印----------")
    heroList.show()
    heroList.delete(6)
    println("----------尝试删除6后打印----------")
    heroList.show()
    val end = System.currentTimeMillis()
    val duration = (end - start)
    printf("消耗时长为：%d毫秒", duration)
  }
}

// 定义双向链表类管理Hero
class DoubleLinkedList {
  // 先初始化一个头节点，头节点一般不会变动
  val headNode = new HeroNode2(0,"","")

  // 添加node
  // 在添加英雄人物时，直接添加到链表的尾部
  def add(node: HeroNode2):Unit = {
    // 因为头节点不能动，因此我们需要有一个临时节点，作为辅助
    var tmp = headNode

    breakable{
      while(true){
        if(tmp.next == null){
          break()
        }else{
          tmp = tmp.next
        }
      }
    }
    tmp.next = node
    node.pre = tmp
  }

  // 添加node的方法2
  // 按照no顺序排列，按照no顺序添加
  // 如果链表中已经有拟插入node的对应no节点，退出。
  def add2(node: HeroNode2): Unit = {
    // 因为头节点不能动，因此我们需要有一个临时节点，作为辅助
    var tmp = headNode

    var flag = false
    breakable{
      while(true){
        // 如果已经到了最后节点了，那就加在这里吧
        if(tmp.next == null){
          flag = true
          break()
        }
        // 如果找到一个节点，它的下一个节点no大于新节点的no，那就加在这个节点和它的下一个节点之间。
        if(tmp.next.no > node.no){
          flag = true
          break()
        }
        // 如果找到一个节点，它的no和新节点的no一样，那就不要加入了。
        if(tmp.no == node.no){
          break()
        }
        // 如果以上情况都不是，那就继续往下找。
        tmp = tmp.next
      }
    }
    // 加入
    if(flag){
      if(tmp.next == null){
        tmp.next = node
        node.pre = tmp
      }else if(tmp.next != null){
        val tmpNext = tmp.next
        tmp.next = node
        node.next = tmpNext
        node.pre = tmp
        tmpNext.pre = node
      }
    }
  }

  // 修改单向链表中的节点,以no为基准
  def update(node: HeroNode2):Unit = {
    // 如果链表为空
    if(headNode.next == null){
      println("链表为空")
    }

    // 否则找寻
    var tmp = headNode.next
    var flag = false
    breakable{
      while(true){
        if(tmp == null){
          break()
        }else if(tmp.no == node.no){
          flag = true
          break()
        }else{
          tmp = tmp.next
        }
      }
    }
    if(flag){
      tmp.name = node.name
      tmp.nickname = node.nickname
    }
  }

  // 删除节点
  def delete(deleteNo:Int): Boolean = {
    // 如果链表为空
    if(headNode.next == null){
      return false
    }

    var tmp = headNode
    var flag = false
    breakable{
      while(true){
        if(tmp.next == null){
          break()
        }else if(tmp.next.no == deleteNo){
          flag = true
          break()
        }else{
          tmp = tmp.next
        }
      }
    }
    if(flag){
      val tmpNext = tmp.next.next
      tmp.next = tmpNext
      if(tmpNext != null){
        tmpNext.pre = tmp
      }
    }
    return flag
  }

  // 遍历双向链表
  def show(): Unit = {
    // 若链表为空，则无法遍历
    if(headNode.next == null){
      println("链表为空")
    }

    // 不为空，则遍历
    var tmp = headNode.next
    while(tmp != null){
      printf("编号为%d的英雄为%s %s\n",tmp.no,tmp.name,tmp.nickname)
      tmp = tmp.next
    }
  }

}

// 双向链表的 HeroNode
class HeroNode2(hNo:Int,hName:String,hNickname:String) {
  var no = hNo
  var name = hName
  var nickname = hNickname
  var pre : HeroNode2 = null // 默认为null
  var next: HeroNode2 = null // 默认为null
}