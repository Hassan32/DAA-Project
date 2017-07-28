package daa.project;

/**
 *
 * @author hassa
 */
public class DAAProject {

    public static void main(String[] args) {
        
        MyGraphh graphObj=new MyGraphh(5);
      
       for(int i=65;i<70;i++){
          graphObj.addUser(Character.toString((char)i));
            }
      // view AdjList after each edge insertion 
      graphObj.makeFriend("A", "B");
      System.out.println("\nAdjList\n========");
      graphObj.printNetwork();
      
      graphObj.makeFriend("B", "D");
      System.out.println("\nAdjList\n========");
      graphObj.printNetwork();
      
      graphObj.makeFriend("C", "B");
      System.out.println("\nAdjList\n========");
      graphObj.printNetwork();
      
      graphObj.makeFriend("D", "E");
      System.out.println("\nAdjList\n========");
      graphObj.printNetwork();
      
      System.out.println("");
    }
    
}

class user{
	String label;
	boolean visit;
	Linkledlist<user> neighbor=new Linkledlist<user>();

	user(String d){
		label=d;
		visit=false;         
	}
        
}

class MyGraphh {
	
     user[] AdjList;
     
     MyGraphh(){
    	 AdjList=new user[10];	 
    }
     MyGraphh(int s){
    	 AdjList=new user[s];
    }
     
  
     public void addUser(String l){
    	 int i=0;
    	 user newUser=new user(l);
    	 while((AdjList[i]!=null)&&!(AdjList[i].equals(l)) && i<AdjList.length){
    	   i++;
         }
    	 if(i<AdjList.length && AdjList[i]==null)
    	   AdjList[i]=newUser;
         
     }
     public void makeFriend(String user1,String user2){
    	 int i=0;
    	 int j=0;
    	 while(AdjList[i]!=null &&i<AdjList.length && !(AdjList[i].label.equals(user1))){
    		 i++;    		 
    	 }
    	 while(AdjList[j]!=null &&j<AdjList.length && !(AdjList[j].label.equals(user2))){
    		 j++;
    		 
    	 }
    	 System.out.println("\n\n\nPossibilities when "+AdjList[i].label+" and "+AdjList[j].label +" are already friends:");
    	 if(i<AdjList.length && j<AdjList.length){
    		Linkledlist<user> local1=AdjList[i].neighbor;
    		if(local1.search(AdjList[j])==false)
    		    AdjList[i].neighbor.insert(AdjList[j]);
                
    		
    		// undirected graph put reveres edge also
    		Linkledlist<user> lcoal2=AdjList[j].neighbor;
    		if(lcoal2.search(AdjList[i])==false)// if not already placed
    		    AdjList[j].neighbor.insert(AdjList[i]);
    		
    		
    	 }
     }

     public void printNetwork(){
    	 for(int i=0;i<AdjList.length;i++){
    		 System.out.print("\n"+AdjList[i].label +" --> ");
    		 int k=0;
    		 while(k<AdjList[i].neighbor.Length()){
    		     System.out.print(AdjList[i].neighbor.get(k).label);
    		 k++;
                }
        }
     }
}
     
class MyNode<T>{
	T data;
	MyNode<T> next;
	
	MyNode(T d){
            data=d;
	}
}

class Linkledlist<T> {
	MyNode<T> headNode; 
	int count;
      
	public void insert(T d){
		count++;
		MyNode<T> nd=new MyNode<T>(d); // create MyNode
                
		if(headNode== null)
		headNode=nd;
                
		else{
			MyNode<T> local=headNode;
			while(local.next!=null){
				local=local.next;			
			}
			local.next=nd;
		}
	} 
	
        public boolean search(T d){
		MyNode<T> local=headNode;
		
		while(local!=null && local.data!=d){
			local=local.next;		
		}
	    if(local!=null && local.data==d)
		 return true;
		else
		return false;
	}
	public int Length(){
		return count;
	}
	
	public T get(int i){
		MyNode<T> local=headNode;
		int j=0;
		while(j<i && local!=null){
			local=local.next;
			j++;
		}
		T val=local.data;
		return val;
	}
	public void printNetwork(){
		MyNode<T> local=headNode;
		while(local!=null){
			System.out.print(" "+local.data);
			local=local.next;
		}
	}
	
    
}
