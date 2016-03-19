package hack.dev.jigsaw.support_desk.service;

//Download the twilio-java library from http://twilio.com/docs/libraries
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Sms;

@Service
public final class SmsSenderService {

	 /* Find your sid and token at twilio.com/user/account */
	 public static final String TEST_ACCOUNT_SID = "AC4444d53e2112d1277cb18f27dfd9bb0d";//At Last use this and keep it else you will loose your account and will not be able to recover -> NXAPE9QTIrcdFKWQV/KS7ozQn/kV9TPUkF44Bcrd
	 public static final String TEST_AUTH_TOKEN = "bff0d86e521c60ff3a6ece0536d06814";
	 
	 public static final String AUTH_TOKEN = "4b3d2796e8c5dd6f0794f9ca71599ded";
	 public static final String ACCOUNT_SID = "ACbab60f6b92db5eb9d89ad94b2156a94b";
	 
	 private final TwilioRestClient client;
	 private final Account account;
	 private final SmsFactory messageFactory;
	 
	 public SmsSenderService() {
    	 this.client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
    	 this.account = client.getAccount();
    	 this.messageFactory = account.getSmsFactory();
	 }

     public boolean sendMessage(String from, String to, String message) {
    	 boolean status = false;
    	 if(from == null || from.isEmpty()) {
    		 //from = "+917406002121";
    		 /*
    		  * Your new Phone Number is +16153747243
				For help building your Twilio application, check out the resources on the getting started page.
				Once you've built your application, you can configure this phone number to send and receive calls and messages.
    		  */
    		 from = "+16153747243";
    	 }
    	 if(to == null || to.isEmpty()) {
    		 to = "+917406002121";
    	 }
    	 Map<String, String> params = new ConcurrentHashMap<String, String>();
    	 //The From number must be a valid Twilio registered phone number of your account. 
    	 //The To number can be any outgoing number.
	     params.put("To", to);
	     params.put("From", from);
	     params.put("Body", message);
	     try {
	    	Sms sms = messageFactory.create(params);
	    	status = true;
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = false;
		}
	     return status;
	 }
}
