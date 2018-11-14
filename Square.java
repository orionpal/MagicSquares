package code;

public class Square {
	private int[] _square = new int[9];
	private int _k;
	private boolean _magic;
	/*	Form of each square:
	 * 
	 *  a b c	0 1 2    OG: 2 7 6
	 *  d e f	3 4 5		 9 5 1
	 *  g h i	6 7 8		 4 3 8
	 *  
	 *  magic sum: k = 3e
	 *  
	 *  (e-y) (e+x) (e+w)
	 *  
	 *  (e+z) ( e ) (e-z)
	 *  
	 *  (e-w) (e-x) (e+y)
	 *  
	 */
	
	//create magic square using array of numbers to set square
	public Square(int[] square){
		_square = square;
		_k = 3*_square[4];
		findMagic();
		if (_magic == false){
			//System.out.println("cannot be a magic square");
		}
		else{
			for (int n: _square){
				System.out.print(n+ ", ");
			}
			System.out.println("is a magic square with magic sum " + _k);
		}
	}
	
	//create magic square based on a magic constant
	public Square(int k){
		_k = k;
		if(_k%3 != 0){
			//System.out.println(_k + " cannot produce a magic square");
		}
		else{
			int e = _k/3;
			_square[0] = e-3;
			_square[1] = e+2;
			_square[2] = e+1;
			_square[3] = e+4;
			_square[4] = e;
			_square[5] = e-4;
			_square[6] = e-1;
			_square[7] = e-2;
			_square[8] = e+3;
		}
		findMagic();
		if (_magic){
			for (int n: _square){
				System.out.print(n+ ", ");
			}
			System.out.println("magic constant " +_k + " can produce a magic square");
		}
		else{
			//System.out.println(_k + " cannot produce a magic square");
		}
		
	}
	
	
	//create magic square based on middle number 'e' and differences
	public Square(int e, int[] wxyz){
		_k = 3*e;
		
		int w = wxyz[0];
		int x = wxyz[1];
		int y = wxyz[2];
		int z = wxyz[3];
		
		_square[0] = e-y;
		_square[1] = e+x;
		_square[2] = e+w;
		_square[3] = e+z;
		_square[4] = e;
		_square[5] = e-z;
		_square[6] = e-w;
		_square[7] = e-x;
		_square[8] = e+y;
		findMagic();
		if (_magic){
			for (int n: _square){
				System.out.print(n+ ", ");
			}
			System.out.println("can produce a magic square with differences: ");
			for (int n: wxyz){
				System.out.print(n+ ", ");
			}
		}
		else{
			System.out.println("is not a magic square");
		}
	}
	//-----------------------------------------------------------------------
	public int getMSum(){
		return _k;
	}
	public void setMSum(int k){
		_k = k;
	}
	public int[] getSquare(){
		return _square;
	}
	public void setSquare(int[] square){
		_square = square;
	}
	public boolean isMagic(){
		return _magic;
	}
	//-----------------------------------------------------------------------
	public void findMagic(){
		if (_k%3!=0){
			_magic =  false;
		}
		else {
			_magic = allDifferent() //unique
				&&eMSum(0,1,2) 	 //abc
				&&eMSum(3,4,5)   //def
				&&eMSum(6,7,8)   //ghi
				&&eMSum(0,3,6)   //adg
				&&eMSum(1,4,7)   //beh
				&&eMSum(2,5,8)   //cfi
				&&eMSum(6,4,2)   //gec
				&&eMSum(0,4,8);  //aei
		}
	}
	public boolean eMSum(int a, int b, int c){
		return _square[a]+_square[b]+_square[c]==_k;
	}
	public boolean allDifferent(){
		for (int i=0; i<9; i++){
			for(int n=0; n<9; n++){
				if(_square[i]==_square[n] && i!=n){
					return false;
				}
			}
		}
		return true;
	}
}
