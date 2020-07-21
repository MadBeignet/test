public class respawnSnitchCoords {
	public static void main(String[] args) {
		int seekerHx, seekerHz,seekerAx,seekerAz,range;
		seekerHx = 278; // these numbers are just examples
		seekerHz = 196;
		seekerAx = 25;
		seekerAz = 96;
		range = 100;
	
		int[] snitchCoord = snitchSpawn(seekerHx,seekerHz,seekerAx,seekerAz,range); 
		//while(snitchCoord[1]<=298 && snitchCoord[1]>=-293&&Math.pow(snitchCoord[0]-seekerHx,2)+Math.pow(snitchCoord[1]-seekerHz, 2)>=Math.pow(100, 2) && Math.pow(snitchCoord[0]-seekerAx,2)+Math.pow(snitchCoord[1]-seekerAz, 2)>=Math.pow(100, 2)) {
		System.out.println("x: "+ snitchCoord[0]+"\nz: "+ snitchCoord[1]);
		//snitchCoord = snitchSpawn(seekerHx,seekerHz,seekerAx,seekerAz,range); // these were used to test the numbers
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
	  int posCirclehz = -500, negCirclehz = -500, posCircleaz =-500, negCircleaz= -500;
	  // so in this first part I basically just created the equation of a circle and found both potential points using the random x value
	  // the error I had originally was having a negative square root which I took as needing to absolute value but I just had to write something recognizing that the circle doesn't exist there
	  // home seeker
	  if(Math.pow(range, 2)-Math.pow(x-centerhx, 2)>=0){	
	   posCirclehz = (int)((Math.sqrt(Math.pow(range, 2)-Math.pow(x-centerhx, 2)))+centerhz)+1; // add 1 to make it bigger to account for (int)
		 negCirclehz = (int)(((-(Math.sqrt(Math.pow(range, 2)-Math.pow(x-centerhx, 2)))))+centerhz)-1; // subtract 1 to make it bigger to account for (int)
		 if(posCirclehz >quidMaxz)
				posCirclehz = quidMaxz;  // basically just makes sure that the values on the circle don't go outside of the map
			if(negCirclehz<quidMinz)
				negCirclehz = quidMinz;
	}
	  // same as above but for away seeker
	if((Math.pow(range, 2)-Math.pow(x-centerax, 2))>=0)	{
		 posCircleaz = (int)((Math.sqrt(Math.abs(Math.pow(range, 2)-Math.pow(x-centerax, 2))))+centeraz)+1; // add 1 to make it bigger to account for (int)
		 negCircleaz = (int)((-(Math.sqrt(Math.abs(Math.pow(range, 2)-Math.pow(x-centerax, 2)))))+centeraz)-1; // subtract 1 to make it bigger to account for (int)
		 if(posCircleaz >quidMaxz)
				posCircleaz = quidMaxz; // again, can't be a point outside of the map
			if(negCircleaz<quidMinz)
				negCircleaz = quidMinz;
	}
		int rangez1 = 0;
		int rangez2 = 0;
	  	// if away circle doesn't exist but home does this created a problem so the -500 was needed
	  	// testing for overlap between the circles
		if(posCirclehz>negCircleaz&&negCircleaz!=-500) {
			rangez1 = Math.abs(posCirclehz-negCirclehz) -(Math.abs(posCirclehz-negCircleaz));
			rangez2 = Math.abs(posCircleaz-negCircleaz);
		}
	  	// same as above
		if(posCircleaz>negCirclehz&&negCirclehz!=-500) {
			rangez1 = Math.abs(posCirclehz-negCirclehz)-(Math.abs(posCircleaz-negCirclehz));
			rangez2 = Math.abs(posCircleaz-negCircleaz);
		} // this one basically just makes sure there is no overlap and that the bigger value on one circle is less than the smaller value on another
		if(posCirclehz<negCircleaz||posCircleaz<negCirclehz&&negCircleaz!=-500&&negCirclehz!=-500) {
		rangez1= Math.abs(negCirclehz-posCirclehz);
		rangez2= Math.abs(negCircleaz-posCircleaz);
		} // this one is for if home circle doesn't exist, rangez1 = 0 in this case
		if(Math.pow(range, 2)-Math.pow(x-centerhx, 2)<0&&(Math.pow(range, 2)-Math.pow(x-centerax, 2))>=0){
		
		rangez2= Math.abs(negCircleaz-posCircleaz);
		}
	  	// this one is for if away circle doesn't exist, rangez2 = 0 in this case
		if((Math.pow(range, 2)-Math.pow(x-centerax, 2))<0&&Math.pow(range, 2)-Math.pow(x-centerhx, 2)>=0) {
			
			rangez1=Math.abs(negCirclehz-posCirclehz);
		} // this basically calculates the number of spawnable places for the snitch in the one line of the random x value entered from main method
		int spawnz = (int)(Math.random()*(quidMaxz-quidMinz-rangez1-rangez2)+quidMinz+1);
		// makes sure both circles exist in the line and then sees if the point picked is in the unspawnable range, if so, adds the range of the circle on that line
		if((rangez1!=0&&rangez2!=0&&rangez1>0)&&spawnz <= posCirclehz&&spawnz>=negCirclehz) {
			spawnz+=rangez1; //adds the range of the other circle if it runs into it after being added above
			if(spawnz<=posCircleaz&&spawnz>=negCircleaz)
				spawnz+=rangez2;
		} // same thing as above just flip flopped
		if((rangez1!=0&&rangez2!=0&&rangez1>0)&&spawnz<=posCircleaz&&spawnz>=negCircleaz) {
			spawnz+=rangez2;
			if(spawnz<=posCirclehz&&spawnz>=negCirclehz)
				spawnz+=rangez1;
		} // because I separated rangez1 and rangez2 when the circles overlapped, one was negative, so I made another statement because it would jump 160 blocks and not go back
		if(rangez1<0&&(spawnz<=posCirclehz&&spawnz>=negCirclehz||spawnz<=posCircleaz&&spawnz>=negCircleaz)) {
			spawnz+=rangez1+rangez2;
		} // here we reach the tests for nonexistent circles and if the z value needs to be shifted by the range of the existent circle
		if(rangez1==0&&rangez2!=0&&spawnz<=posCircleaz&&spawnz>=negCircleaz)
			spawnz+=rangez2;
		if(rangez2==0&&rangez1!=0&&spawnz<=posCirclehz&&spawnz>=negCirclehz)
			spawnz+=rangez1;
	  	// obviously no need to test if the ranges are both 0 because whatever random z value picked from the original spawnz equation would work
		return spawnz; // returns the z value
}
}
