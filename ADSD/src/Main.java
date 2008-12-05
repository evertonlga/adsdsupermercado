



public class Main {
	
	private static int qteProduto;
	
	public static int getQteProduto(){
		qteProduto++;
		return qteProduto;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		
		qteProduto = 0;		
		
//		File arquivo;
//		arquivo = new File("entrada.txt");
//		
//		FileOutputStream fos;
//		try {
//			String qteProduto = "";
//			fos = new FileOutputStream(arquivo);
//			for (int i = 1; i <= 10; i++) {
//				
//				qteProduto += i + "\n";  
//				fos.write(qteProduto.getBytes());	
//				
//			}
//			fos.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
		
//		for (int i = 1; i <= 10; i++) {
			Simulator s = new Simulator();
			s.run();		
//		}

	}

}
