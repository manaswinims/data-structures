package binary-search-tree;

public class tree {
    
    Node root;
    
    public void insert(int data){
        Node newNode = new Node(data);
        if(root == null){
            root = newNode;
            return;
        }
        
        Node current = root;
        Node parent = root;
        while(true){
            if(data < current.data){
                parent = current;
                current = current.left;
                if(current == null){
                    parent.left = newNode;
                    return;
                }
            }else{
                parent=current;
                current = current.right;
                if(current == null){
                    parent.right = newNode;
                    return;
                }
            }
        }
    }
    
    public void delete(int data){
        Node current = root;
        Node parent = current;
        boolean leftChild = false;
        while(current.data != data){
            parent = current;
            if(data < current.data){
                current = current.left;
                leftChild = true;
            }else{
                current = current.right;
                leftChild = false;
            }
        }
        
        if(current.left == null && current.right == null){
            if(current == root){
                root = null;
            }else if(leftChild){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }else if(current.left != null || current.right != null){
            if(current.left != null){
                if(current == root){
                    root = current.left;
                }else if(leftChild){
                    parent.left = current.left;
                }else{
                    parent.right = current.left;
                }
            }else{
                if(current == root){
                    root = current.right;
                }else if(leftChild){
                    parent.left = current.right;
                }else{
                    parent.right = current.right;
                }
            }
        }else{
            Node successor = getSuccessor(current);
            if(current == root){
                root = successor;
            }else if(leftChild){
                parent.left = successor;
            }else{
                parent.right = successor;
            }
            
            successor.left = current.left;
        }
    }
    
    public Node getSuccessor(Node node){
        Node successor = null;
        Node parent = null;
        Node current = node.right;
        
        while(current != null){
            parent = successor;
            successor = current;
            current = current.left;
        }
        
        if(successor != node.right){
            parent.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }
    
    public Node find(int data){
        Node current = root;
        
        while(current.data != data){
            if(data < current.data){
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return current;
    }
    
    public Node findRecursive(Node node, int data){
        if(node == null){
            return null;
        }
        
        if(data == node.data){
            return node;
        }
        
        if(data < node.data){
            return findRecursive(node.left, data);
        }else{
            return findRecursive(node.right, data);
        }
    }
    
    public int depth(Node node, int depth){
        if(node == null){
            return 0;
        }
        
        int left = depth(node.left, depth);
        int right = depth(node.right, depth);
        
        return left>right?left+1:right+1;
    }
    
    public int nodeDepth(Node node, int depth, int data){
        if(node == null){
            return 0;
        }
        
        if(node.data == data){
            return depth;
        }else if(data < node.data){
            return nodeDepth(node.left, depth+1, data);
        }else{
            return nodeDepth(node.right, depth+1, data);
        }
    }
    
    public void inorder(Node node){
        if(node != null){
            inorder(node.left);
            System.out.print(node.data + "->");
            inorder(node.right);
        }
    }
    
    public void postorder(Node node){
        if(node != null){
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data + "->");
        }
    }
    
    public void preorder(Node node){
        if(node != null){
            System.out.print(node.data + "->");
            preorder(node.left);
            preorder(node.right);
        }
    }
    
    public void levelOrder(Node node){
        int depth = depth(node, 0);
        for(int i=1; i<=depth; i++){
            printLevel(node, i);
        }
    }
    
    public void printLevel(Node node, int level){
        if(node == null){
            return;
        }else if(level == 1){
            System.out.print(node.data + "->");
        }else{
            printLevel(node.left,level-1);
            printLevel(node.right, level-1);
        }
    }
    
    public void spiralOrder(Node node){
        int depth = depth(node, 0);
        boolean l2r = true;
        for(int i=1; i<=depth;i++){
            spiralLevelPrint(node, i, l2r);
            l2r = !l2r;
        }
    }
    
    public void spiralLevelPrint(Node node, int level, boolean l2r){
        if(node == null){
            return;
        }else if(level == 1){
            System.out.print(node.data + "->");
        }else{
            if(l2r){
                spiralLevelPrint(node.left, level-1, l2r);
                spiralLevelPrint(node.right, level-1, l2r);
            }else{
                spiralLevelPrint(node.right, level-1, l2r);
                spiralLevelPrint(node.left, level-1, l2r);
            }
        }
    }
    
    public Node inorderSuccessor(Node node){
        node = find(node.data);
        if(node == null){
            return null;
        }
        
        if(node.right != null){
            return successor(node);
        }
        Node parent = parent(node);
        Node current = node;
        
        while(parent != null && current == parent.right){
            current = parent;
            parent = parent(parent);
        }
        return parent;
    }
    
    public Node successor(Node node){
        Node current = node.right;
        Node successor = null;
        Node parent = null;
        while(current != null){
            parent = successor;
            successor = current;
            current = current.left;
        }
        return successor;
    }
    
    public Node parent(Node node){
        Node current = root;
        Node parent = null;
        
        while(current.data != node.data){
            parent = current;
            if(node.data < current.data){
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return parent;
    }
    
    public Node inorderPredecessor(Node node){
        node = find(node.data);
        if(node == null){
            return null;
        }
        if(node.left != null){
            return predecessor(node);
        }
        Node parent = parent(node);
        Node current = node;
        
        while(parent != null && current == parent.left){
            current = parent;
            parent = parent(parent);
        }
        return parent;
    }
    
    public Node predecessor(Node node){
        Node current = node.left;
        Node parent = null;
        Node predecessor = null;
        
        while(current != null){
            parent = predecessor;
            predecessor = current;
            current = current.right;
        }
        
        return predecessor;
    }
    
    public Node leastCommonAncestor(Node node, int p, int q){
        if(node == null){
            return null;
        }
        if(node.data > p && node.data > q){
            return leastCommonAncestor(node.left, p, q);
        }else if(node.data < p && node.data < q){
            return leastCommonAncestor(node.right, p, q);
        }
        return node;
    }
    public static void main(String[] args){
        tree tree = new tree();
	}
}
