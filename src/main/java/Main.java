import java.util.Arrays;
import java.util.Random;

class Person {
    public String name;
    public int id;
    public int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}


//        Задание 6.2

class Node {
    public Person person;
    public Node leftChild;
    public Node rightChild;

    public void display() {
        System.out.println("Имя: " + person.name + " Возраст: " + person.age);
    }
}


class Tree {
    private Node root;

    //        Задание 6.3

    public void insert(Person person) {
        Node node = new Node();
        node.person = person;
        if (root == null) {
            root = node;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (person.id < current.person.id) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = node;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = node;
                        return;
                    }
                }
            }
        }
    }

    public Node find(int key) {
        Node current = root;
        while (current.person.id != key) {
            if (key < current.person.id) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }


    //        Задание 6.4

    public Node min() {
        Node current, last = null;
        current = root;
        while (current != null) {
            last = current;
            current = current.leftChild;
        }

        return last;
    }

    public Node max() {
        Node current, last = null;
        current = root;
        while (current != null) {
            last = current;
            current = current.rightChild;
        }

        return last;
    }

    private void preOrder(Node rootNode) {
        if (rootNode != null) {
            rootNode.display();
            preOrder(rootNode.leftChild);
            preOrder(rootNode.rightChild);
        }
    }

    private void postOrder(Node rootNode) {
        if (rootNode != null) {
            postOrder(rootNode.leftChild);
            postOrder(rootNode.rightChild);
            rootNode.display();
        }
    }

    private void inOrder(Node rootNode) {
        if (rootNode != null) {
            inOrder(rootNode.leftChild);
            rootNode.display();
            inOrder(rootNode.rightChild);
        }
    }


    public boolean delete(int id) {

        Node current = root;
        Node parent = root;

        boolean isLeftChild = true;

        while (current.person.id != id) {
            parent = current;
            if (id < current.person.id) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null) {
                return false;
            }
        }
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        } else if (current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
        } else if (current.leftChild == null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
        } else {
            Node successor = getSuccesor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;
        }
        return true;
    }

    public Node getSuccesor(Node node) {
        Node successorParent = node;
        Node successor = node;
        Node current = node.rightChild;


        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if (successor != node.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = node.rightChild;
        }

        return successor;
    }

    public void displayTree() {
        Node current = root;
        System.out.println("Симметричный");
        inOrder(root);
        System.out.println("Прямой");
        preOrder(root);
        System.out.println("Обратный");
        postOrder(current);
    }
}


public class Main {

    public static long startTime, endTime;

    public static void main(String[] args) {

//        Задание 6.1
/*
        Пример использования древовидной структуры:
        Быстрая вставка, поиск и удаление элемента
 */

//        Реализация (6.2, 6.3):

        Tree myTree = new Tree();

        myTree.insert(new Person(7, "Viktor", 24));
        myTree.insert(new Person(5, "Peter", 53));
        myTree.insert(new Person(1, "Ivan", 21));
        myTree.insert(new Person(3, "Sophie", 19));

        startTime = System.nanoTime();
        myTree.max().display();
        endTime = System.nanoTime();
        System.out.println("Поиск max-элемента занял: " + (endTime - startTime) + " нс");

        System.out.println();

        startTime = System.nanoTime();
        myTree.min().display();
        endTime = System.nanoTime();
        System.out.println("Поиск min-элемента занял: " + (endTime - startTime) + " нс");

        System.out.println();

        startTime = System.nanoTime();
        myTree.find(3).display();
        endTime = System.nanoTime();
        System.out.println("Поиск элемента по ключу занял: " + (endTime - startTime) + " нс");

        System.out.println();


//        Задание 6.5

        startTime = System.nanoTime();
        myTree.delete(5);
        endTime = System.nanoTime();
        System.out.println("Удаление заняло: " + (endTime - startTime) + " нс");

        System.out.println();

        startTime = System.nanoTime();
        myTree.displayTree();
        endTime = System.nanoTime();

        System.out.println();
        System.out.println("Вывод дерева занял: " + (endTime - startTime) + " нс");

        System.out.println();


//        Задание 6.6

        int[] arr;
        Random rand = new Random();
        arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10);
        }

        int[] copyArr = Arrays.copyOf(arr, arr.length);


        System.out.println(Arrays.toString(arr));

        HeapSort arrSort = new HeapSort();

        startTime = System.nanoTime();
        arrSort.sort(arr);
        endTime = System.nanoTime();
        System.out.println("Пирамидальная сортировка заняла: " + (endTime - startTime) + " нс");

        startTime = System.nanoTime();
        Arrays.sort(copyArr);
        endTime = System.nanoTime();
        System.out.println("Сортировка методом sort заняла: " + (endTime - startTime) + " нс");







    }

}
