public static void main(String[] args) {
		int seekerHx, seekerHz,seekerAx,seekerAz,range;
		seekerHx = -313;
		seekerHz = -293;
		seekerAx = -313;
		seekerAz = -293;
		range = 100;
	
		int[] snitchCoord = snitchSpawn(seekerHx,seekerHz,seekerAx,seekerAz,range);
		
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
		int posCirclehz = (int)(Math.sqrt(Math.abs(Math.pow(range, 2)-Math.pow(x-centerhx, 2))))+centerhz+1;
		int negCirclehz = (int)((-(Math.sqrt(Math.abs(Math.pow(range, 2)-Math.pow(x-centerhx, 2))))))+centerhz+1;
		if(posCirclehz >quidMaxz)
			posCirclehz = quidMaxz;
		if(negCirclehz<quidMinz)
			negCirclehz = quidMinz;
		int posCircleaz = (int)(Math.sqrt(Math.abs(Math.pow(range, 2)-Math.pow(x-centerax, 2))))+centeraz+1;
		int negCircleaz = (int)((-(Math.sqrt(Math.abs(Math.pow(range, 2)-Math.pow(x-centerax, 2)))))+centeraz+1);
		if(posCircleaz >quidMaxz)
			posCircleaz = quidMaxz;
		if(negCircleaz<quidMinz)
			negCircleaz = quidMinz;
		int rangez1 = 0;
		int rangez2 = 0;
		if(posCirclehz>=negCircleaz) {
			rangez1 = Math.abs(posCirclehz-negCirclehz) + Math.abs(posCircleaz-negCircleaz)-(Math.abs(posCirclehz-negCircleaz));
			rangez2 = 0;
		}
		if(posCircleaz>=negCirclehz) {
			rangez1 = Math.abs(posCirclehz-negCirclehz)+Math.abs(posCircleaz-negCircleaz)-(Math.abs(posCircleaz-negCirclehz));
			rangez2 = 0;
		}
		if(posCirclehz<=negCircleaz||posCircleaz<=negCirclehz) {
		rangez1= Math.abs(negCirclehz-posCirclehz);
		rangez2= Math.abs(negCircleaz-posCircleaz);
		}
		int spawnz = (int)(Math.random()*(quidMaxz-quidMinz-rangez1-rangez2)+quidMinz);
		if(spawnz < posCirclehz&&spawnz>negCirclehz||spawnz<posCircleaz&&spawnz>negCircleaz) {
			spawnz+=rangez1;
		}
		if(spawnz <posCircleaz&&spawnz>negCircleaz) {
			spawnz+=rangez2;
		}
		return spawnz;
}
