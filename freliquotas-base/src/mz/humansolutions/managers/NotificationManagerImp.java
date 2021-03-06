package mz.humansolutions.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import mz.humansolutions.models.Membro;


public class NotificationManagerImp implements NotificationManager {

	/**
	 * a mensagem tem que entrar 28 de cada mes, adicionar mensagem para enviar
	 * comprovativo de pagamento
	 */

	@Override
	public void sendSms(String recipient, String message) throws IOException {
		String requestUrl = "http://67.43.3.105:9501/api?action=sendmessage&username=lazaroj&password=4K99Qj9e&recipient="
				+ URLEncoder.encode(recipient, "UTF-8")
				+ "&messagetype=SMS:TEXT&messagedata="
				+ URLEncoder.encode(message, "UTF-8");
		System.out.println(recipient + ", " + message);
		URL url = new URL(requestUrl);
		HttpURLConnection uc = (HttpURLConnection) url.openConnection();
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(uc.getInputStream()))) {
			String responseBody = bufferedReader.toString();
			boolean sentSuccessfully = responseBody
					.contains("<statuscode>0</statuscode>");
			System.out.println("Enviado com sucesso: " + sentSuccessfully);
		} finally {
			uc.disconnect();
		}
	}

	@Override
	public void sendSmsNotification(List<Membro> membros) {
		// nome do mes
		if (membros != null) {
			for (Membro membro : membros) {
				if(membro.getPaidAllYear())
					membros.remove(membro);
			}
			for (Membro membro : membros) {
				String recipient = membro.getTelefone();
				String message = "Caro(a) camarada, "+membro.getNome().toUpperCase()+" lembramos-lhe do seu dever de pagamento de QUOTAS e do envio do comprovativo a Sede da Cidade. Caso ja tenha feito, ignore esta mensagem.\n\nDAF Cidade de Maputo";
				if (recipient != null) {
					try {
						sendSmsNotificationSislog(recipient, message);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
	
	@Override
	public void sendSmsNotificationSislog(String phoneNumber, String message)
			throws IOException {
		String requestUrl = "http://67.43.3.105:9501/api?action=sendmessage&username=lazaroj&password=4K99Qj9e&recipient="
				+ URLEncoder.encode(phoneNumber, "UTF-8")
				+ "&messagetype=SMS:TEXT&messagedata="
				+ URLEncoder.encode(message, "UTF-8");
		System.out.println(phoneNumber + ", " + message);
		URL url = new URL(requestUrl);
		HttpURLConnection uc = (HttpURLConnection) url.openConnection();
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(uc.getInputStream()))) {
			String responseBody = bufferedReader.toString();
			boolean sentSuccessfully = responseBody
					.contains("<statuscode>0</statuscode>");
			System.out.println("Enviado com sucesso: " + sentSuccessfully);
		} finally {
			uc.disconnect();
		}
	}
	
	@Override
	public void sendSmsNotificationPersonalized(List<Membro> membros, String content) {
		if(membros!=null) {
			for (Membro membro : membros) {
				String recipient = membro.getTelefone();
				try {
					sendSmsNotificationSislog(recipient, content);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		}
		
		
	}

}
