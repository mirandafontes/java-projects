package blok.skins.factories.defaultskin;

import blok.interfaces.*;

public class DefaultSkin implements ISkinFactory {

	@Override
	public IBrick createBrick() {
	
		return new DefaultBrick(); 
		
	}

	@Override
	public IBackground createBackground() {
		
		return new DefaultBackground();
		
	}

}
