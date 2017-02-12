package cronometro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.SerialPortEvent;
/*
    CLASSE DESTINADA A REALIZAR A CONECXÃO CONTROLADOR E COMPUTADOR
*/
public class SerialRxTx implements SerialPortEventListener {
	
	SerialPort serialPort = null;
	private Protocolo protocolo = new Protocolo(); //Objeto de gestão de protocolo
	private String appName; //Nome da aplicação
	
	private BufferedReader input; //Objeto para leitura na serial
	private OutputStream output; //Objeto para escrta na serial
	
	private static final int TIME_OUT = 1000; //define tempo de espera de comunicação
	private static int DATA_RATE = 9600; // define velocidade da comunicação
	
	private String serialPortName = "COM8"; //Porta que esta conectado o controlador
	
	public boolean iniciaSerial(){
		try {
			//Obtem portas seriais do sistema
			CommPortIdentifier portId = null;
			Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
			
			while(portId == null && portEnum.hasMoreElements()){
				CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
				if(currPortId.getName().equals(serialPortName) || currPortId.getName().startsWith(serialPortName)){
					serialPort = (SerialPort) currPortId.open(appName, TIME_OUT);
					portId = currPortId;
					System.out.println("Conectado em: "+currPortId.getName());
					break;
				}
			}
			
			if(portId==null || serialPort==null){
				System.out.println("Nao conectou");
				return false;
			}
			
			serialPort.setSerialPortParams( DATA_RATE, 
											SerialPort.DATABITS_8, 
											SerialPort.STOPBITS_1, 
											SerialPort.PARITY_NONE);
			
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Se deu certo, retorna verdadeiro
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void sendData(String data){
		try {
			output = serialPort.getOutputStream();
			output.write(data.getBytes());
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	public synchronized void close(){
		if(serialPort != null){
			serialPort.removeEventListener();
			serialPort.close();
		}
	}
	
	@Override
	public void serialEvent(SerialPortEvent spe){
		//Metodo que lida com a chegada de dados pela serial ao computador
		try {
			switch (spe.getEventType()) {
			case SerialPortEvent.DATA_AVAILABLE:
				if(input == null){
					input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
				}
				if(input.ready()){
					protocolo.setLeituraComando(input.readLine());
					System.out.println("Chegou: "+protocolo.getLeituraComando());
                                        
				}
				
				break;

			default:
				break;
			}
		} catch (Exception e) {
			System.err.println("N�o chegou dado");
			//e.printStackTrace();
		}
	}
        
	public Protocolo getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(Protocolo protocolo) {
		this.protocolo = protocolo;
	}

	public static int getDATA_RATE() {
		return DATA_RATE;
	}

	public static void setDATA_RATE(int dATA_RATE) {
		DATA_RATE = dATA_RATE;
	}

	public String getSerialPortName() {
		return serialPortName;
	}

	public void setSerialPortName(String serialPortName) {
		this.serialPortName = serialPortName;
	}
        
}
