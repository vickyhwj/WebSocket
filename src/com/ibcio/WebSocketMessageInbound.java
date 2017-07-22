package com.ibcio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.HashMap;

import net.sf.json.JSONObject;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;
import org.apache.commons.collections.map.HashedMap;

public class WebSocketMessageInbound extends MessageInbound {

	//��ǰ���ӵ��û�����
	private final String user;

	public WebSocketMessageInbound(String user) {
		this.user = user;
	}

	public String getUser() {
		String xx=new String("Ddd");
		HashMap<String,String> map=new HashMap<String, String>();
	
		return this.user;
		
	}

	//�������ӵĴ������¼�
	@Override
	protected void onOpen(WsOutbound outbound) {
		// ���������¼��������ӳ����������
		JSONObject result = new JSONObject();
		result.element("type", "user_join");
		result.element("user", this.user);
		//�����������û����͵�ǰ�û����ߵ���Ϣ
		WebSocketMessageInboundPool.sendMessage(result.toString());
		
		result = new JSONObject();
		result.element("type", "get_online_user");
		result.element("list", WebSocketMessageInboundPool.getOnlineUser());
		//�����ӳ���ӵ�ǰ�����Ӷ���
		WebSocketMessageInboundPool.addMessageInbound(this);
		//��ǰ���ӷ��͵�ǰ�����û����б�
		WebSocketMessageInboundPool.sendMessageToUser(this.user, result.toString());
	}

	@Override
	protected void onClose(int status) {
		// �����ر��¼��������ӳ����Ƴ�����
		WebSocketMessageInboundPool.removeMessageInbound(this);
		JSONObject result = new JSONObject();
		result.element("type", "user_leave");
		result.element("user", this.user);
		//�������û����͵�ǰ�û��˳�����Ϣ
		WebSocketMessageInboundPool.sendMessage(result.toString());
	}

	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		throw new UnsupportedOperationException("Binary message not supported.");
	}

	//�ͻ��˷�����Ϣ��������ʱ�����¼�
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		//�����������û�������Ϣ
		String string=message.toString();
		JSONObject jsonObject=new JSONObject().fromObject(string);
		try{
			String from=jsonObject.getString("from");
			String to=jsonObject.getString("to");
			WebSocketMessageInboundPool.sendMessageToUser(to, message.toString());
			WebSocketMessageInboundPool.sendMessageToUser(from, message.toString());
		}catch(Exception e){
			WebSocketMessageInboundPool.sendMessage(message.toString());
		}
		//WebSocketMessageInboundPool.sendMessage(message.toString());
	}
}
