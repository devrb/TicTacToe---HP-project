import java.util.*;
import java.util.Random;
public class tic
{
	static int filled=0;	
	public static void display(char[] b)
	{
		System.out.printf("  %c	|   %c	|  %c\n",b[0],b[1],b[2]);	
		System.out.printf(" %s	   %s	   %s\n","---","---","---");
		System.out.printf("  %c	|   %c	|  %c\n",b[3],b[4],b[5]);
		System.out.printf(" %s	   %s	   %s\n","---","---","---");
		System.out.printf("  %c	|   %c	|  %c\n",b[6],b[7],b[8]);
	}
	public static int winboard(char[] c,String s)
	{
		if((c[0]==c[4]) && (c[4]==c[8]) && (c[8]!='\0'))
		return ((s.charAt(0)=='X')? 10:-10); 
		
		else if((c[2]==c[4]) && (c[4]==c[6]) && (c[6]!='\0'))
		return ((s.charAt(0)=='X')? 10:-10); 
		
		else if((c[0]==c[3]) && (c[3]==c[6]) && (c[6]!='\0'))
		return ((s.charAt(0)=='X')? 10:-10); 
		
		else if((c[1]==c[4]) && (c[4]==c[7]) && (c[7]!='\0'))
		return ((s.charAt(0)=='X')? 10:-10); 
		
		else if((c[2]==c[5]) && (c[5]==c[8]) && (c[8]!='\0'))
		return ((s.charAt(0)=='X')? 10:-10); 
		
		else if((c[0]==c[1]) && (c[1]==c[2]) && (c[2]!='\0'))
		return ((s.charAt(0)=='X')? 10:-10); 
		
		else if((c[3]==c[4]) && (c[4]==c[5]) && (c[5]!='\0'))
		return ((s.charAt(0)=='X')? 10:-10); 
		
		else if((c[6]==c[7]) && (c[7]==c[8]) && (c[8]!='\0'))
		return ((s.charAt(0)=='X')? 10:-10); 
		
		else if(filled < 9)
		return -1;
		
		else
		return 0; 
	}
	public static int[] minimax(char[] u,boolean play)
	{
		int z=0;
		int[] w=new int[2];
		int[] r=new int[2];
		if(play==true)
		w[0]=-100;
		else
		w[0]=100;

		for(int i=0;i < u.length;i++)
		{
			if(u[i]=='\0')
			{
				
				if(play==true)
				{
					u[i]='X';
					filled++;
					z=winboard(u,"X");				
					if(z==-1)
					{
						r=minimax(u,!play);	
						if(r[0] > w[0])
						{
							w[0]=r[0];
							w[1]=r[1];
						}
						u[i]='\0';
						filled--;
					}
					else
					{
						if(z > w[0])
						{
							w[0]=z;
							w[1]=i;
						}
						u[i]='\0';
						filled--;
					
																	
					}
				}
				else
				{
					u[i]='O';
					filled++;
					z=winboard(u,"O");
					if(z==-1)	
					{
						r=minimax(u,!play);
						if(r[0]<w[0])
						{
							w[0]=r[0];
							w[1]=r[1];
						}
						u[i]='\0';
						filled--;	
					}
					else
					{
						if(z < w[0])
						{
							w[0]=z;
							w[1]=i;
						}
						u[i]='\0';
						filled--;
							
					}		
				}
			}
		}
		return w;		

	}
	public  static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		Random r=new Random();
		char[] a=new  char[9];
		System.out.println("******BEAT ME IF U CAN******");
		System.out.println("LET TOSS");
		System.out.println("1.HEAD");
		System.out.println("2.TAIL");
		System.out.println("COMPUTER -> PLAY WITH X");
		System.out.println("YOU PLAY WITH -> CAPITAL O");
		int l=in.nextInt();
		int k=r.nextInt(3);
		String  s;
		int pos,x,y;
		int[] c=new int[2];
		int[] t=new int[2];
		int p=0;
		boolean player;
		int count=0;
		if(l==k)
		{
			System.out.println("YOU WIN SO U START");
			player =false;
		}
		else
		{
			System.out.println("COMPUTER  WILL START");
			player =true;
		}
		
		while(filled < 9)
		{
			if(player == true)
			{
				
				t[0]=-100;
				for(int i=0;  i < 9;i++)
				{
						
					if(a[i]=='\0')
					{
                                        
	
						a[i]='X';
						filled++;		
						y=winboard(a,"X");
						if(y==0)
						break;
						if(y==10)
						{
							count=1;
							System.out.println("computer chance");
							display(a);
							System.out.println("***COMPUTER WIN***");
							p=1;
							break;
						}
						else
						{
			
							c=minimax(a,!player);
							a[i]='\0';
							filled--;
							
							
							if(c[0] > t[0])
							{
								t[0]=c[0];
								t[1]=i;
							}
										
						}						
					}		
				}
				if(p==1)
				break;
				System.out.println("computer chance");
				a[t[1]]='X';
				filled++;
				display(a);
				player =false;
			}
			else
			{
				System.out.print("ENTER A VALID POSITION (1 to 9)= ");
				
				pos=in.nextInt();
				System.out.println();
				a[pos-1]='O';
				filled++;						
				display(a);
				System.out.println();
			
				player =true;		
													
			}
			
		}
		if(filled==9 &&count==0)
		System.out.println("draw");
		
		
	}
		
}