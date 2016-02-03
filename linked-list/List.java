/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package linked-list;

/**
 *
 * @author msm
 */
public class List {
    Node first;
    Node last;
    
    public void addToBeginning(int data){
        Node newNode = new Node(data);
        if(first == null){
            first = newNode;
            last = newNode;
            return;
        }else{
            newNode.next = first;
            first = newNode;
            return;
        }
    }
    
    public void addToEnd(int data){
        Node newNode = new Node(data);
        if(first == null){
            first = newNode;
            last = newNode;
            return;
        }else{
            last.next = newNode;
            last = newNode;
            return;
        }
    }
    
    public void addAtPosition(int data, int position){
        Node newNode = new Node(data);
        if(position == 1){
            addToBeginning(data);
        }else{
            int tracker = 1;
            Node current = first;
            while(tracker < position-1 && current != null){
                current = current.next;
                tracker++;
            }
            if(current == null){
                addToEnd(data);
            }else{
                Node temp = current.next;
                current.next = newNode;
                newNode.next = temp;
            }
        }
    }
    
    public void delete(int data){
        if(first.data == data){
            first = first.next;
        }
        Node current = first;
        Node previous = current;
        while(current != null){
            if(current.data == data){
                previous.next = current.next;
            }
            previous = current;
            current = current.next;
        }
        
    }
    
    public void display(){
        Node current = first;
        while(current != null){
            System.out.print(current.data + "->");
            current = current.next;
        }
        System.out.println();
    }
    
    public void reverse(){
        Node current = first;
        Node previous = null;
        while(current != null){
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        last = first;
        first = previous;
    }
    
    public void getMiddleElement(){
        Node fast = first;
        Node slow = first;
        while(fast != null){
            if(fast.next == null || fast.next.next == null){
                System.out.println("Middle element = " + slow.data);
                return;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
    }
    
    public void getNthToLastElement(int n){
        int tracker = 1;
        Node fast = first;
        Node normal = first;
        while(fast != null){
            if(tracker > n){
                normal = normal.next;
            }
            fast = fast.next;
            tracker++;
        }
        
        System.out.println("nth to last element = " + normal.data);
        return;
    }
    
    public void sort(){
        
    }
    
    public Node reverseEveryKNodes(Node first,int k){
        Node current = first;
        Node previous = null;
        int tracker = 1;
        while(tracker <= k && current != null){
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            tracker++;
        }
        
        if(current != null){
            first.next = reverseEveryKNodes(current,k);
        }
        return previous;
    }
    
    public void deleteNafterM(int m , int n){
        int mTracker = 1;
        int nTracker = 1;
        Node current = first;
        while(current != null){
            if(mTracker == m){
                Node temp = current;
                while(nTracker <= n && temp != null){
                    temp= temp.next;
                    nTracker++;
                }
                if(temp != null){
                    current.next = temp.next;
                }else{
                    current.next = null;
                }
                nTracker = 1;
                mTracker = 0;
            }
            mTracker++;
            current = current.next;
        }
    }
    
    public void deleteAlternateNodes(){
        Node current = first;
        while(current != null && current.next != null){
            current.next = current.next.next;
            current = current.next;
        }
    }
    
    public Node swapEveryTwoNodes(Node first){
        Node current = first;
        Node previous = null;
        int tracker = 1;
        while(tracker <= 2 && current != null){
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            tracker++;
        }
        if(current != null){
            first.next = swapEveryTwoNodes(current);
        }
        return previous;
    }
    
    public void mergeTwoLists(List list1, List list2){
        Node current1 = list1.first;
        Node current2 = list2.first;
        Node next1 = null;
        Node next2 = null;
        Node previous1 = null;
        Node previous2 = null;
        while(current1 != null && current2 != null){
            next1 = current1.next;
            next2 = current2.next;
            current1.next = current2;
            current2.next = next1;
            previous1 = current1;
            previous2 = current2;
            current1 = next1;
            current2 = next2;
         }
         if(current1 == null && current2 != null){
             previous2.next = current2;
         }
        
    }
    
    public static void main(String[] args){
       /* List list = new List();
        list.addToBeginning(5);
        list.addToBeginning(4);
        list.addToBeginning(3);
        list.addToEnd(6);
        list.addToEnd(7);
        list.addToEnd(8);
        list.display();
        list.reverse();
        list.display();
        list.getMiddleElement();
        list.getNthToLastElement(3);
        list.getNthToLastElement(8);
        list.getNthToLastElement(1);
        list.first = list.reverseEveryKNodes(list.first,4);
        list.display();
        list.deleteNafterM(2,1);
        list.display();
        list.addAtPosition(3,4);
        list.display();
        list.addAtPosition(30,4);
        list.display();
        list.first = list.swapEveryTwoNodes(list.first);
        list.display();*/
        
        List list1 = new List();
        list1.addToBeginning(9);
        list1.addToBeginning(7);
        list1.addToBeginning(5);
        list1.addToBeginning(3);
        list1.addToBeginning(1);
        list1.display();
        
        List list2 = new List();
        list2.addToBeginning(10);
        list2.addToBeginning(8);
        list2.display();
        
        list1.mergeTwoLists(list1, list2);
        list1.display();
    }
}
