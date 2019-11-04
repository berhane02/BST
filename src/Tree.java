//Yemane Berhane
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
public class Tree {
	PrintStream out = System.out;
	public Node root;
	class Node{
		Node left, right;
		long data, sum;	
		public Node(long d) {
			left = null;
			right = null;
			data=d;
			sum=d;
		}
	}
	
	public Tree(String string) {
		File fileR = new File(string);
		try {	
			Scanner file = new Scanner(fileR);
			Insert(Integer.parseInt(file.nextLine()));
			while(file.hasNext()) {
				Insert(Integer.parseInt(file.nextLine()));
			}
			//close file after finish reading 
			file.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	private void Insert(long n) {
		/*
		 * create new node to insert
		 */
		Node newNode = new Node(n);
		Node current;
		Node newParent;
		
		//check if root is null and make the newNode root. 
		if(root == null) {
			root = newNode;
		}else {
			//root is not null so insert new node at proper position.
			root.sum = root.sum+n;
			current = root;
			while(true) {
				newParent = current;
				if(current.data>=n) {
						current = current.left;
						//if the last element to compare is null then insert newNode
						if(current == null) {
							newParent.left = newNode;
							return;
							}
						current.sum = current.sum+n;
					}
				else {
						current = current.right;
						//if the last element to compare is null then insert newNode
						if(current == null) {
							newParent.right = newNode;
							return;
							}
						current.sum = current.sum+n;			
					}
				}
		 }
		
	}
public boolean evenSumRange(long a, long b) {
		
		/*create tempt node to get root address and preserve 
		 root and initialize evenSumRange to store sum result. 
		*/
		Node current = root;
		long sum=0;
		if(root !=null) {
		 sum=current.sum;
		}
		//get the sum in the lower range and subtract it from root sum. 
		while(current !=null) {
			//go to the left if the current data is less than lower bound
			if(current.data>=a) {
				current = current.left;
			}else {
				//subtract the sum if we have elements out of bound 
					sum-=current.sum;
					if(current.right !=null) {
					sum+=current.right.sum;}
				
				current = current.right;
			}
		}
		//get the sum in the upper range and subtract it from root sum.
		current = root;
		while(current != null) {
			//go to the right if the current data is less than upper bound
			if(current.data<=b) {
				current = current.right;
			}else {
				//subtract the sum if we have elements out of bound 
				sum -=current.sum;
				if(current.left !=null) {
					sum +=current.left.sum;
				}current =current.left;
			}
			
		}
		long mod = sum%2;
		return mod == 0;
		
	}
public void readRange(String k) {
	int n =0;
	File file = new File(k);
	
	try {
		//FileWriter fw = new FileWriter("output");
		Scanner input = new Scanner(file);
		//PrintWriter pw = new PrintWriter(fw);
		//read until end of file 
		while(input.hasNext()) {
			String line =input.nextLine();
			int len = line.length();
			int spaceIndex = line.indexOf(' ');
			int i = spaceIndex;
			while(line.charAt(i) == ' ')
				i++;
			n++;
			long a = Integer.parseInt(line.substring(0,spaceIndex));
			long b = Integer.parseInt(line.substring(i, len));
			boolean result = evenSumRange(a,b);
			if(result){
				out.println("Range ["+a+","+b+"]: even sum");
				//pw.println("Range ["+a+","+b+"]: even sum");
			}else{
				out.println("Range ["+a+","+b+"]: odd sum");
				//pw.println("Range ["+a+","+b+"]: odd sum");
			}
			
		}
		//close file after finish reading 
		//pw.close();
		input.close();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
}

}
