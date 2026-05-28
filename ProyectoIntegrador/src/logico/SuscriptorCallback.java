package logico;
import database.SQLData;
import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SuscriptorCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Conexion Perdida...");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Mensaje recibido en topic: " + topic);
        String json = message.toString();
        System.out.println("Contenido: " + json);

        Gson gson = new Gson();
        Sensor sensor = gson.fromJson(json, Sensor.class);

        int estacionId = 1;

        if(sensor.getSensorId().equals("estacion-2")) {
            estacionId = 2;
        }

        SQLData.insertarLectura(
                estacionId,
                sensor.getPrecipitacion(),
                sensor.getDireccionViento(),
                sensor.getVelocidadViento()
        );

        System.out.println(
                "Datos guardados en MariaDB"
        );
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("Informacion Recibida.");
    }
}
