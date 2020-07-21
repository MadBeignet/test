public class respawnSnitchCoords {
	public static void main(String[] args) {
		int seekerHx, seekerHz,seekerAx,seekerAz,range;
		seekerHx = 278;
		seekerHz = 196;
		seekerAx = 25;
		seekerAz = 96;
		range = 100;
	
		int[] snitchCoord = snitchSpawn(seekerHx,seekerHz,seekerAx,seekerAz,range);
		while(snitchCoord[1]<=298 && snitchCoord[1]>=-293&&Math.pow(snitchCoord[0]-seekerHx,2)+Math.pow(snitchCoord[1]-seekerHz, 2)>=Math.pow(100, 2) && Math.pow(snitchCoord[0]-seekerAx,2)+Math.pow(snitchCoord[1]-seekerAz, 2)>=Math.pow(100, 2)) {
		System.out.println("x: "+ snitchCoord[0]+"\nz: "+ snitchCoord[1]);
		snitchCoord = snitchSpawn(seekerHx,seekerHz,seekerAx,seekerAz,range);
		}
		System.out.println("x: "+ snitchCoord[0]+"\nz: "+ snitchCoord[1]);
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
	  
	  
	  if(Math.pow(range, 2)-Math.pow(x-centerhx, 2)>=0){	
	   posCirclehz = (int)((Math.sqrt(Math.pow(range, 2)-Math.pow(x-centerhx, 2)))+centerhz)+1;
		 negCirclehz = (int)(((-(Math.sqrt(Math.pow(range, 2)-Math.pow(x-centerhx, 2)))))+centerhz)-1;
		 if(posCirclehz >quidMaxz)
				posCirclehz = quidMaxz;
			if(negCirclehz<quidMinz)
				negCirclehz = quidMinz;
	}
	if((Math.pow(range, 2)-Math.pow(x-centerax, 2))>=0)	{
		 posCircleaz = (int)((Math.sqrt(Math.abs(Math.pow(range, 2)-Math.pow(x-centerax, 2))))+centeraz)+1;
		 negCircleaz = (int)((-(Math.sqrt(Math.abs(Math.pow(range, 2)-Math.pow(x-centerax, 2)))))+centeraz)-1;
		 if(posCircleaz >quidMaxz)
				posCircleaz = quidMaxz;
			if(negCircleaz<quidMinz)
				negCircleaz = quidMinz;
	}
		int rangez1 = 0;
		int rangez2 = 0;
		if(posCirclehz>negCircleaz&&negCircleaz!=-500) {
			rangez1 = Math.abs(posCirclehz-negCirclehz) -(Math.abs(posCirclehz-negCircleaz));
			rangez2 = Math.abs(posCircleaz-negCircleaz);
		}
		if(posCircleaz>negCirclehz&&negCirclehz!=-500) {
			rangez1 = Math.abs(posCirclehz-negCirclehz)-(Math.abs(posCircleaz-negCirclehz));
			rangez2 = Math.abs(posCircleaz-negCircleaz);
		}
		if(posCirclehz<negCircleaz||posCircleaz<=negCirclehz&&negCircleaz!=-500&&negCirclehz!=-500) {
		rangez1= Math.abs(negCirclehz-posCirclehz);
		rangez2= Math.abs(negCircleaz-posCircleaz);
		}
		if(Math.pow(range, 2)-Math.pow(x-centerhx, 2)<0&&(Math.pow(range, 2)-Math.pow(x-centerax, 2))>=0){
		
		rangez2= Math.abs(negCircleaz-posCircleaz);
		}
		if((Math.pow(range, 2)-Math.pow(x-centerax, 2))<0&&Math.pow(range, 2)-Math.pow(x-centerhx, 2)>=0) {
			
			rangez1=Math.abs(negCirclehz-posCirclehz);
		}
		int spawnz = (int)(Math.random()*(quidMaxz-quidMinz-rangez1-rangez2)+quidMinz+1);

		if((rangez1!=0&&rangez2!=0&&rangez1>0)&&spawnz <= posCirclehz&&spawnz>=negCirclehz) {
			spawnz+=rangez1;
			if(spawnz<=posCircleaz&&spawnz>=negCircleaz)
				spawnz+=rangez2;
		}
		if((rangez1!=0&&rangez2!=0&&rangez1>0)&&spawnz<=posCircleaz&&spawnz>=negCircleaz) {
			spawnz+=rangez2;
			if(spawnz<=posCirclehz&&spawnz>=negCirclehz)
				spawnz+=rangez1;
		}
		if(rangez1<0&&(spawnz<=posCirclehz&&spawnz>=negCirclehz||spawnz<=posCircleaz&&spawnz>=negCircleaz)) {
			spawnz+=rangez1+rangez2;
		}
		if(rangez1==0&&rangez2!=0&&spawnz<=posCircleaz&&spawnz>=negCircleaz)
			spawnz+=rangez2;
		if(rangez2==0&&rangez1!=0&&spawnz<=posCirclehz&&spawnz>=negCirclehz)
			spawnz+=rangez1;
		return spawnz;
}
}
