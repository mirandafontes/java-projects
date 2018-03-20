package blok.skins.factories.kappaskin;

import blok.interfaces.*;

public class KappaSkin implements ISkinFactory {

	@Override
	public IBrick createBrick() {

		return new KappaBrick();
		
	}

	@Override
	public IBackground createBackground() {

		return new KappaBackground();
		
	}

}
