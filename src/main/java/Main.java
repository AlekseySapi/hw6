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

    private void inOrder(Node rootNode) {
        if (rootNode != null) {
            inOrder(rootNode.leftChild);
            rootNode.display();
            inOrder(rootNode.rightChild);
        }
    }

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


}

public class Main {

    public static void main(String[] args) {

//        Задание 6.1
/*
        Пример использования древовидной структуры:
        Быстрая вставка, поиск и удаление элемента
 */

//        Реализация:

        Tree myTree = new Tree();

        myTree.insert(new Person(7, "Viktor", 24));
        myTree.insert(new Person(5, "Peter", 53));
        myTree.insert(new Person(1, "Ivan", 21));
        myTree.insert(new Person(3, "Sophie", 19));

        myTree.max().display();
        myTree.min().display();

        System.out.println();

        myTree.find(3).display();

        System.out.println();


    }

}
