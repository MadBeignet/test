public class respawnSnitch {
	public static void main(String[] args) {
		int seekerHx, seekerHz,seekerAx,seekerAz,range;
		seekerHx = (int)(Math.random()*(278+313)-313+1);
		seekerHz = (int)(Math.random()*(298+293)-293+1);
		seekerAx = (int)(Math.random()*(278+313)-313+1);
		seekerAz = (int)(Math.random()*(298+293)-293+1);
		range = 100;
	
		int[] snitchCoord = snitchSpawn(seekerHx,seekerHz,seekerAx,seekerAz,range);
		//while(snitchCoord[1]<=298 && snitchCoord[1]>=-293&&Math.pow(Math.abs(snitchCoord[0]-seekerHx)+1,2)+Math.pow(Math.abs(snitchCoord[1]-seekerHz)+1, 2)>=Math.pow(100, 2) && Math.pow(Math.abs(snitchCoord[0]-seekerAx)+1,2)+Math.pow(Math.abs(snitchCoord[1]-seekerAz)+1, 2)>=Math.pow(100, 2)) {
		System.out.println("x: "+ snitchCoord[0]+"\nz: "+ snitchCoord[1]);
		//snitchCoord = snitchSpawn(seekerHx,seekerHz,seekerAx,seekerAz,range);  // these were used to test the numbers
		//}
		//System.out.println("x: "+ snitchCoord[0]+"\nz: "+ snitchCoord[1]);
	}
public static int[] snitchSpawn(int seekerHx, int seekerHz, int seekerAx, int seekerAz, int range) {
		int quidMinx = -313;
		int quidMaxx = 278;
		int quidMinz = -293;
		int quidMaxz = 298;
		int x = (int)(Math.random()*(quidMaxx-quidMinx)+quidMinz+1);
		int z = circle(x, seekerHz, seekerHx, seekerAz, seekerAx, range, quidMinz, quidMaxz);
		return new int[] {x,z};
	}
  public static int circle(int x,int centerhz,int centerhx, int centeraz, int centerax, int range, int quidMinz, int quidMaxz) {
	  double posCirclehz = -500, negCirclehz = -500, posCircleaz =-500, negCircleaz= -500;
	  
	// so in this first part I basically just created the equation of a circle and found both potential points using the random x value
		  // the error I had originally was having a negative square root which I took as needing to absolute value but I just had to write something recognizing that the circle doesn't exist there
		  // home seeker
	  if(Math.pow(range, 2)-Math.pow(x-centerhx, 2)>=0){	
	   posCirclehz = ((Math.sqrt(Math.pow(range, 2)-Math.pow(x-centerhx, 2)))+centerhz);
		 negCirclehz = (((-(Math.sqrt(Math.pow(range, 2)-Math.pow(x-centerhx, 2)))))+centerhz);
		 if(posCirclehz >quidMaxz)
				posCirclehz = quidMaxz; // basically just makes sure that the values on the circle don't go outside of the map
			if(negCirclehz<quidMinz)
				negCirclehz = quidMinz;
				}

	// same as above but for away seeker
	if((Math.pow(range, 2)-Math.pow(x-centerax, 2))>=0)	{
		 posCircleaz = (Math.sqrt(Math.abs(Math.pow(range, 2)-Math.pow(x-centerax, 2))))+centeraz;
		 negCircleaz = ((-(Math.sqrt(Math.abs(Math.pow(range, 2)-Math.pow(x-centerax, 2)))))+centeraz);
		 if(posCircleaz >quidMaxz)
				posCircleaz = quidMaxz; // again, can't be a point outside of the map
			if(negCircleaz<quidMinz)
				negCircleaz = quidMinz;
		
			
	}
		
	
		int rangez1 = 0;
		int rangez2 = 0;
		// if away circle doesn't exist but home does this created a problem so the -500 was needed
	  	// testing for overlap between the circles
		if(posCirclehz>negCircleaz&&negCircleaz!=-500&&Math.abs(posCirclehz-negCircleaz)<Math.abs(posCirclehz-negCirclehz)) {
			rangez1 = (int)(Math.abs(posCirclehz-negCirclehz) -(Math.abs(posCirclehz-negCircleaz)))+1;
			rangez2 = (int) Math.abs(posCircleaz-negCircleaz)+1;
	
		}
		// same as above
		if(posCircleaz>negCirclehz&&negCirclehz!=-500&&Math.abs(posCircleaz-negCirclehz)<Math.abs(posCircleaz-negCircleaz)) {
			rangez1 = (int)(Math.abs(posCirclehz-negCirclehz)-(Math.abs(posCircleaz-negCirclehz)))+1;
			rangez2 = (int) Math.abs(posCircleaz-negCircleaz)+1;
	
		}
		// checks if one range is inside of the other range and accounts for that 
		if((negCirclehz!=-500&&negCircleaz!=-500)&&((posCirclehz>=posCircleaz&&negCirclehz<=negCircleaz)||(posCircleaz>=posCirclehz&&negCircleaz<=negCirclehz))) {
			if(Math.abs(posCirclehz-negCirclehz)>Math.abs(posCircleaz-negCircleaz)) {
				rangez1=(int) Math.abs(posCirclehz-negCirclehz)/2+1;
				rangez2=(int) Math.abs(posCirclehz-negCirclehz)/2;
			}else {
				
			rangez1=(int)Math.abs(posCircleaz-negCircleaz)/2;
			rangez2=(int)Math.abs(posCircleaz-negCircleaz)/2;
			}
		} // this one basically just makes sure there is no overlap and that the bigger value on one circle is less than the smaller value on another
		if((negCircleaz!=-500&&negCirclehz!=-500)&&(posCirclehz<negCircleaz||posCircleaz<=negCirclehz)) {
		
		rangez1= (int) Math.abs(negCirclehz-posCirclehz)+1;
		rangez2= (int) Math.abs(negCircleaz-posCircleaz)+1;
		}
		// this one is for if home circle doesn't exist, rangez1 = 0 in this case
		if(Math.pow(range, 2)-Math.pow(x-centerhx, 2)<0&&(Math.pow(range, 2)-Math.pow(x-centerax, 2))>=0){
		
		rangez2= (int) Math.abs(negCircleaz-posCircleaz)+1;
		}
		// this one is for if away circle doesn't exist, rangez2 = 0 in this case
		if((Math.pow(range, 2)-Math.pow(x-centerax, 2))<0&&Math.pow(range, 2)-Math.pow(x-centerhx, 2)>=0) {
			
			rangez1=(int)Math.abs(negCirclehz-posCirclehz)+1;
		}
		// this basically calculates the number of spawnable places for the snitch in the one line of the random x value entered from main method
		int spawnz = (int)(Math.random()*(quidMaxz-quidMinz-rangez1-rangez2)+quidMinz);
		
		//this is basically for points where the one range is around the other, but the point (spawnz) is inside one or both of the ranges
		if(((posCirclehz>=posCircleaz&&negCirclehz<=negCircleaz)||(posCircleaz>=posCirclehz&&negCircleaz<=negCirclehz))&&((spawnz<posCirclehz&&spawnz>negCirclehz)||(spawnz<posCircleaz&&spawnz>negCircleaz))){
			spawnz+=rangez1+rangez2;
		}
		// makes sure both circles exist in the line and then sees if the point picked is in the unspawnable range, if so, adds the range of the circle on that line
		if((rangez1!=0&&rangez2!=0&&rangez1>0)&&spawnz <= posCirclehz&&spawnz>=negCirclehz) {
			spawnz+=rangez1; //adds the range of the other circle if it runs into it after being added above
			
			if(spawnz<=posCircleaz&&spawnz>=negCircleaz) {
				spawnz+=rangez2;
			}		
		}// same thing as above just flip flopped
		if((rangez1!=0&&rangez2!=0&&rangez1>0)&&spawnz<=posCircleaz&&spawnz>=negCircleaz) {
			spawnz+=rangez2;

			if(spawnz<=posCirclehz&&spawnz>=negCirclehz) {
				spawnz+=rangez1;
			}
			if((rangez1!=0&&rangez2!=0&&rangez1>0)&&spawnz<=posCirclehz&&spawnz<=posCircleaz&&spawnz>=negCirclehz&&spawnz>=negCircleaz){
				spawnz+=rangez1+rangez2;		
			}
		}// because I separated rangez1 and rangez2 when the circles overlapped, one was negative, so I made another statement because it would jump 160 blocks and not go back
		if(rangez1<0&&(spawnz<=posCirclehz&&spawnz>=negCirclehz||spawnz<=posCircleaz&&spawnz>=negCircleaz)) {
			spawnz+=rangez1+rangez2;
		}
		// here we reach the tests for nonexistent circles and if the z value needs to be shifted by the range of the existent circle
		if(rangez1==0&&rangez2!=0&&spawnz<=posCircleaz&&spawnz>=negCircleaz) {
			spawnz+=rangez2;	
		}
		if(rangez2==0&&rangez1!=0&&spawnz<=posCirclehz&&spawnz>=negCirclehz) {
			spawnz+=rangez1;
		}
		// obviously no need to test if the ranges are both 0 because whatever random z value picked from the original spawnz equation would work
		
		return spawnz; //returns the value for the z coordinate that the snitch should spawn at
  	}
}
