package cronometro;
/*
    CLASSE DESTINADA A TRATAR OS DADOS ADVINDO DA SERIAL
*/
public class Protocolo{
	private String start; //para acionar o cronometro

	private String leituraComando;
	
	private void interpretaComando(){
		//Separa a string de comando em subtring delimitadas por caracteres especificos
		//$STS,40,70,180,1,0,1023
		start = leituraComando;

		
	}

  
	public String getLeituraComando() {
		return leituraComando;
	}

	public void setLeituraComando(String leituraComando) {
		this.leituraComando = leituraComando; //Temos a String de dados.
		this.interpretaComando(); //Interpretamos a String.
	}

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
