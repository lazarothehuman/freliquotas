package mz.humansolutions.managers;

import java.io.IOException;
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

		try {
			String username = "admin";
			String password = "lj070797";
			String originator = "06201234567";// dummy

			String requestUrl = "http://127.0.0.1:9501/api?action=sendmessage&" + "username="
					+ URLEncoder.encode(username, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8")
					+ "&recipient=" + URLEncoder.encode(recipient, "UTF-8") + "&messagetype=SMS:TEXT" + "&messagedata="
					+ URLEncoder.encode(message, "UTF-8") + "&originator=" + URLEncoder.encode(originator, "UTF-8")
					+ "&serviceprovider=sislog" + "&responseformat=html";

			URL url = new URL(requestUrl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();

			System.out.println(uc.getResponseMessage());

			uc.disconnect();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());

		}

	}

	@Override
	public void sendSmsNotification(List<Membro> membros) {
		// nome do mes
		if (membros != null) {
			for (Membro membro : membros) {
				String recipient = membro.getTelefone();
				String message = "Caro(a) camarada, "+membro.getNome().toUpperCase()+" lembramos-lhe do seu dever de pagamento de QUOTAS e do envio do comprovativo a Sede da Cidade. Caso ja tenha feito, ignore esta mensagem.\n\nDAF Cidade de Maputo";
				if (recipient != null) {
					try {
						sendSms(recipient, message);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

}
