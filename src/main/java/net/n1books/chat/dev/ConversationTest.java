package net.n1books.chat.dev;

import java.util.Scanner;

import org.junit.Test;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class ConversationTest {

	@Test
	public void testConversation() {
		Scanner sc = new Scanner(System.in);
		Conversation service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
		service.setUsernameAndPassword("19d63ac3-37fe-4290-8ce0-23b5c8b2ce0d", "U2VHjsISnCeV");
		
		MessageResponse response = null;
		Context context = null;
		MessageOptions options = null;
		String msg = "";
		StringBuffer watsonSay = null;
		
		while(true) {
			options = new MessageOptions.Builder()
				    .workspaceId("4d00447e-88cb-47c5-b557-690425bbc051")
				    .input(new InputData.Builder(msg).build())
				    .context(context)
				    .build();
			response = service.message(options).execute();
			System.out.print("Watson : ");
			
			watsonSay = new StringBuffer();			
			for(String text : response.getOutput().getText()) {
				watsonSay.append(text);
				watsonSay.append(" ");
			}
			
			System.out.println(watsonSay);
			System.out.print("I : ");
			msg = sc.nextLine();
			context = response.getContext();
		}
	}
}
