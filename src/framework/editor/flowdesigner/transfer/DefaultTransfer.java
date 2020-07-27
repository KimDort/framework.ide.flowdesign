package framework.editor.flowdesigner.transfer;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

public class DefaultTransfer extends ByteArrayTransfer{

	private static DefaultTransfer instance = new DefaultTransfer();
	private static final String TYPE_NAME = "default-transfer-format";
	private static final int TYPEID = registerType(TYPE_NAME);
	
	@Override
	protected int[] getTypeIds() {
		return new int[] {this.TYPEID};
	}

	@Override
	protected String[] getTypeNames() {
		return new String[] {TYPE_NAME};
	}
	
	@Override
	protected Object nativeToJava(TransferData transferData) {
		return transferData;
	}

	public static DefaultTransfer getInstance() {
		return instance;
	}
	

}
