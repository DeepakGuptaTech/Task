package DoubleLinkList;

public class DoubleLinkList 
{
	private ListNode head;
	private ListNode tail;
	private int lenght;
	
	
	public DoubleLinkList() 
	{
		super();
		this.head = null;
		this.tail = null;
		this.lenght = 0;
	}


	private class ListNode
	{
		private int data;
		private ListNode next;
		private ListNode previous;
		
		public ListNode(int data)
		{
			this.data=data;
		}
		
	}
	
	public boolean isEmpty()
	{
		return lenght==0;
	}
	public int length()
	{
		return lenght;
	}


}
