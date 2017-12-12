package net.n1books.chat.dev;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

@RestController
public class WatsonSay {
	private static Logger logger = LoggerFactory.getLogger(WatsonSay.class);

	@RequestMapping(value = "watsonsay")
	public MessageResponse watsonsay(String isay, HttpSession session) {
		logger.info("user input : " + isay);
		
		String imgPath = "https://"+request.getServerName() + "/resources/img/";

		Conversation service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
		service.setUsernameAndPassword("19d63ac3-37fe-4290-8ce0-23b5c8b2ce0d", "U2VHjsISnCeV");

		MessageResponse response = null;
		Context context = (Context) session.getAttribute("context");
		MessageOptions options = null;
		String msg = isay;
		StringBuffer watsonSay = null;

		options = new MessageOptions.Builder().workspaceId("4d00447e-88cb-47c5-b557-690425bbc051")
				.input(new InputData.Builder(msg).build()).context(context).build();
		response = service.message(options).execute();

		watsonSay = new StringBuffer();
		for (String text : response.getOutput().getText()) {
			watsonSay.append(text);
			watsonSay.append(" ");
		}

		logger.info("Watson : " + watsonSay);
		
		jobjText.addProperty("text", watsonSay.toString());
		photo.addProperty("url", imgPath+"bread.png");
		photo.addProperty("width", 450);
		photo.addProperty("height", 350);
		jobjText.add("photo", photo);


		context = response.getContext();
		session.setAttribute("context", context);
		return response;
	}
}
