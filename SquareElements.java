package code;

import java.util.ArrayList;

public class SquareElements {
	private ArrayList<int[]> _wxyzs;
	private int _e;
	
	public SquareElements(int e){
		_e = e;
		_wxyzs = new ArrayList<int[]>();
		findVariants();
		showVariants();
	}
	
	public void findVariants(){
		for(int w=1; w<_e; w++){
			for(int x=1; x<_e; x++){
				for(int y=1; y<_e; y++){
					for(int z=1; z<_e; z++){
						int[] wxyz = new int[]{w,x,y,z};
						if (allDifferent(wxyz)){
							if (isMagic(wxyz)){
								_wxyzs.add(wxyz);
							}
						}
					}
				}
			}
		}
	}
	public void showVariants(){
		for (int i=0; i<_wxyzs.size(); i++){
			System.out.println("variant " + i + ": ");
			int[] wxyz = _wxyzs.get(i);
			for (int n=0; n<4; n++){
				System.out.print(wxyz[n] + ", ");	
			}
		}
	}
	//----------------------------------------------------------------------------------------------
	public ArrayList<int[]> getElements(){
		return _wxyzs;
	}
	//---------------------------------------------------------------------------------------------
	public boolean allDifferent(int[] wxyz){
		for (int i=0; i<4; i++){
			for(int n=0; n<4; n++){
				if(wxyz[i]==wxyz[n] && i!=n){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isMagic(int[] wxyz){
		//boolean allDifferent = allDifferent(wxyz);
		boolean w = wxyz[0]==wxyz[3]-wxyz[2];
		boolean x = wxyz[1]==wxyz[3]+wxyz[2];
		boolean y = wxyz[2]==wxyz[1]-wxyz[3];
		boolean z = wxyz[3]==wxyz[0]+wxyz[2];
		
		return w && x && y && z;
	}
}
