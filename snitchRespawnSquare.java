public static void main(String[] args) {
		int seekerHx, seekerHz,seekerAx,seekerAz,range;
		seekerHx = -313;
		seekerHz = -293;
		seekerAx = -313;
		seekerAz = -293;
		range = 100;
		int[] snitchCoord = getRange(seekerHx,seekerHz,seekerAx,seekerAz,range);
	
		System.out.println("x: "+ snitchCoord[0]+"\nz: "+ snitchCoord[1]);
		
	}
public static int[] getRange(int seekerHx, int seekerHz, int seekerAx, int seekerAz,int range) {
		int minhx,minax,maxhx,maxax,minhz,minaz,maxhz,maxaz;
		minhx = seekerHx-range;
		maxhx = seekerHx+range;
		minhz = seekerHz-range;
		maxhz = seekerHz+range;
		minax = seekerAx-range;
		maxax = seekerAx+range;
		minaz = seekerAz-range;
		maxaz = seekerAz+range;
		
		int quidMinx,quidMaxx,quidMinz,quidMaxz;
		quidMinx = -313;
		quidMaxx = 278;
		quidMinz = -293;
		quidMaxz = 298;
		if(minhx<quidMinx) 
			minhx=quidMinx;
		if(maxhx>quidMaxx)
			maxhx=quidMaxx;
		if(minhz<quidMinz)
			minhz=quidMinz;
		if(maxhz>quidMaxz)
			maxhz=quidMaxz;
		if(minax<quidMinx)
			minax=quidMinx;
		if(maxax>quidMaxx)
			maxax=quidMaxx;
		if(minaz<quidMinz)
			minaz=quidMinz;
		if(maxaz>quidMaxz)
			maxaz=quidMaxz;
		int xRange1,xRange2,zRange1,zRange2;
		int spawnz;
		int spawnx;
		if(Math.abs(seekerAx-seekerHx)<2*range) {
			xRange1 = Math.abs(maxhx-minax);
			xRange2 = 0;
		
		}else if(Math.abs(seekerAx-seekerHx)>=2*range){
			xRange1 = 2*range;
			xRange2 = 2*range;
		}else {
			xRange1 = 0;
			xRange2 = 0;
		}
		if(Math.abs(seekerAz-seekerHz)<2*range) {
			zRange1 = Math.abs(maxaz-minhz);
			zRange2 = 0;
		}else if (Math.abs(seekerHz-seekerAz)>=2*range){
			zRange1 = 2*range;
			zRange2 = 2*range;
		}else {
			zRange1 = 0;
			zRange2 = 0;
		}

		spawnx=(int)((Math.random()*(quidMaxx-quidMinx-xRange1-xRange2))+quidMinx+1);
				
		if(minhx<spawnx&&spawnx<maxhx)
			
			spawnx+=xRange1;
		if(minax<spawnx&&spawnx<maxax)
			
			spawnx+=xRange2;
			if(minax<spawnx&&spawnx<maxax)
				spawnx+=xRange1;
		
		spawnz = (int)(Math.random()*(quidMaxz-quidMinz-zRange1-zRange2)+quidMinz+1);
		if(minhz<spawnz&&spawnz<maxhz)
		
			spawnz+=zRange1;
		
		if(minaz<spawnz&&spawnz<maxaz)
			
			spawnz+=zRange2;
			if(minaz<spawnz&&spawnz<maxaz)
				spawnz+=zRange1;
		
		
		
		
			
		return new int[]{spawnx,spawnz};
			
		
	}
