package mz.humansolutions.managers;

import java.io.IOException;
import java.util.List;

import mz.humansolutions.models.Membro;

public interface NotificationManager {
	
	void sendSms(String phoneNumber, String message) throws IOException;

	void sendSmsNotification(List<Membro> membros);

	void sendSmsNotificationSislog(String phoneNumber, String message) throws IOException;

	void sendSmsNotificationPersonalized(List<Membro> membros, String message);

}
