package examen;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyOOS extends ObjectOutputStream {

	public MyOOS() throws IOException, SecurityException {
	}

	public MyOOS(OutputStream arg0) throws IOException {
		super(arg0);
	}
	
	@Override
	protected void writeStreamHeader() {
//		No hacer nada
	}
	
}