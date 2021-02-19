import java.util.NoSuchElementException;

import javax.swing.plaf.synth.SynthSeparatorUI;

//	Add node first
//	Add node last
//	Add node at an index
//	Remove first node
//	Remove last node
//	Remove node at an index
//	Find a node in O (1)

public class DoublyLinkedList<T> {

	private Node<T> head;
	private Node<T> tail;
	private int length;

	private class Node<T> {
		private T data; // Can be any generic type
		private Node<T> next;
		private Node<T> previous;

		public Node(T data) {
			this.data = data;
		}
	}

	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.length = 0;
	}

	public boolean isEmpty() {
		return length == 0; // or head == null
	}

	public int length() {
		return length;
	}

	public void displayForward() {
		if (head == null) {
			return;
		}

		Node<T> temp = head;
		while (temp != null) {
			System.out.print(temp.data + ",");
			temp = temp.next;
		}

		System.out.println("");

	}

	public void displayBackward() {
		if (head == null) {
			return;
		}

		Node temp = tail;
		while (temp != null) {
			System.out.print(temp.data + ",");
			temp = temp.previous;
		}
		System.out.println("");

	}

	public void addFirst(T element) {
		Node newNode = new Node(element);
		if (isEmpty()) {
			tail = newNode;
		} else {
			head.previous = newNode;
		}
		newNode.next = head;
		head = newNode;
		length++;
	}

	public void addLast(T element) {
		Node newNode = new Node(element);
		if (isEmpty()) {
			head = newNode;
		} else {
			tail.next = newNode;
			newNode.previous = tail;
		}
		tail = newNode;
		length++;
	}

	public Node deleteFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		Node temp = head;
		if (head == tail) {
			tail = null;
		} else {
			head.next.previous = null;
		}
		head = head.next;
		temp.next = null;
		length--;
		return temp;
	}

	public Node deleteLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		Node temp = tail;
		if (head == tail) {
			head = null;
		} else {
			tail.previous.next = null;
		}
		tail = tail.previous;
		temp.previous = null;
		length--;
		return temp;
	}

	public void addWithPosition(int index, T element) throws InvalidArgumentException {

		if (index < 0 || index > length)
			throw new InvalidArgumentException();
		Node<T> newNode = new Node(element);
		if (head == null) { // list is empty, index must be 0
			head = newNode;
			tail = newNode;
		} else if (index == 0) { // insert before head
			newNode.next = head;
			head.previous = newNode;
			head = newNode;
		} else if (index == length) { // insert after tail
			newNode.previous = tail;
			tail.next = newNode;
			tail = newNode;
		} else { 
			
			Node<T> nodeRef = head;
			for (int i = 1; i < index; i++)
				nodeRef = nodeRef.next;
			// nodeRef now points to the node before the insert point
			// see course notes for pictures!
			newNode.next = nodeRef.next;
			nodeRef.next = newNode;
			newNode.previous = nodeRef;
			newNode.next.previous = newNode;
		}
		length++;
	}

	public void deleteWithPositon(int index) throws InvalidArgumentException {

		if (index < 0 || index > length)
			throw new InvalidArgumentException();

		if (index == 0) {
			this.deleteFirst();// insert before head

		} else if (index == length) {
			this.deleteLast();
		} else {

			// general case
			if (head == null) {
				return;
			}

			Node<T> nodeRef = head;

			for (int i = 1; i <= index; i++)
				nodeRef = nodeRef.next;

			if (nodeRef.next != null)
				nodeRef.next.previous = nodeRef.previous;

			if (nodeRef.previous != null)
				nodeRef.previous.next = nodeRef.next;

			nodeRef = null;
			length--;

		}

	}

	public int find(T element) throws InvalidArgumentException {

		Node temp = head;
		int position = 0;
		while (temp.data != element && temp.next != null) {

			position++;
			temp = temp.next;
		}

		// If the integer not present
		// in the doubly linked list
		if (temp.data != element) {

			return -1;
		}
		// If the integer present in
		// the doubly linked list

		return position;

	}

	public static void main(String[] args) throws InvalidArgumentException {
		DoublyLinkedList<String> list = new DoublyLinkedList();
		
		//Add node last
		list.addLast("Deepak");
		list.addLast("Rahul");
		list.addLast("Ram");
		list.addLast("Sohan");
		
		
		//Add node first
		list.addFirst("Peter");
		//Find a node in O (1)
		System.out.println("Index of elemnt"+list.find("Ram"));
		list.displayForward();
		
		//Remove first node
		list.deleteFirst();
		
		//Remove last node
		list.deleteLast();
		list.displayForward();
		
		
		//Add node at an index
		list.addWithPosition(1, "Ayush");
		list.displayForward();
		list.addWithPosition(0, "Shashi");
		list.displayForward();
		
		//Remove node at an index
		list.deleteWithPositon(1);
		list.displayForward();
		
		list.displayBackward();
		
		
		
		

		//list.displayForward();
		// list.deleteLast();
		// list.deleteLast();

		// list.deleteWithPositon(3);
		//list.addWithPosition(3, "DEFFE");
		//System.out.println(list.find("Sonu"));
		
		//list.deleteFirst();
		//list.displayForward();
	}
}