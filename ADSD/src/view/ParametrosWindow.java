package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import util.BatCreator;

public class ParametrosWindow {

	private static Display display;
	private static Shell shell;
	private static Label tempoLabel;	
	private static Text tempoText;
	private static Label tempoInterChegadaLabel;	
	private static Text tempoInterChegadaText;
	private static Label qteProdutosLabel;	
	private static Text qteProdutosText;
	private static Label tempoValidadeLabel;	
	private static Text tempoValidadeText;
	private static Label tempoReposicaoLabel;	
	private static Text tempoReposicaoText;
	private static Label mediaAtLabel;	
	private static Text mediaAtText;
	private static Label varAtLabel;	
	private static Text varAtText;
	private static Label mediaCxLabel;	
	private static Text mediaCxText;
	private static Label varCxLabel;	
	private static Text varCxText;
	private static Label pacienciaLabel;	
	private static Text pacienciaText;	
	private static Button qteProdutosCheck;			
	private static Button interchegadaCheck;
	private static Button tempoVencimentoCheck;	
	
	private static Label minLabel;
	
	private static Text minText;
	private static Label maxLabel;
	
	private static Text maxText;

	public static void main(String[] args) {
		display = new Display();
		shell = new Shell(display);
		show();

	}
	
	private static boolean ehInt(String valor) {
		try{
			@SuppressWarnings("unused")
			int d = Integer.valueOf(valor);
			return true;
		}catch (Exception e) {
			return false;
		}	
	}
	
	private static boolean ehDouble(String valor) {
		try{
			@SuppressWarnings("unused")
			double d = Double.valueOf(valor);
			return true;
		}catch (Exception e) {
			return false;
		}		
	}
	
	private static String getParametroVariavelEscolhido(){
		if(qteProdutosCheck.getSelection())
			return "quantidade de produtos";
		if(interchegadaCheck.getSelection())
			return "tempo de interchegada";		
		return "tempo de vencimento";
	}
	
	private static boolean tudoOK() {
		if(!(qteProdutosCheck.getSelection() || interchegadaCheck.getSelection() || 
				tempoVencimentoCheck.getSelection()))
			return false;
		if(!ehDouble(tempoText.getText()))
			return false;
		if(!ehDouble(tempoInterChegadaText.getText()))
			return false;
		if(!ehInt(qteProdutosText.getText()))
			return false;
		if(!ehDouble(tempoValidadeText.getText()))
			return false;
		if(!ehDouble(tempoReposicaoText.getText()))
			return false;
		if(!ehDouble(mediaAtText.getText()))
			return false;
		if(!ehDouble(varAtText.getText()))
			return false;
		if(!ehDouble(mediaCxText.getText()))
			return false;
		if(!ehDouble(varCxText.getText()))
			return false;
		if(!ehDouble(pacienciaText.getText()))
			return false;
		if(!ehInt(maxText.getText()))
			return false;
		if(!ehInt(minText.getText()))
			return false;
		if(!(Integer.parseInt(maxText.getText()) > Integer.parseInt(minText.getText())))
			return false;
		return true;
	}	
	
	private static void mostrarAdvertencia() {
		Shell shell = new Shell(display, SWT.NO_TRIM);
		shell.setText("Advertência");
		
		
		//set the shell just large enough to hold the image				
		shell.setSize(600, 100);
		
		shell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				//get the GC, and a reference to the display
				GC gc = e.gc;
				Display d = e.widget.getDisplay();

				//create a custom colour, with RGB values
				Color color = new Color(d, 255, 0, 0);
				gc.setForeground(color);

				//create a Font object of font Arial,
				//set it to point size 24 and style BOLD and ITALIC
				Font font = new Font(d, "Arial", 24, SWT.BOLD | SWT.ITALIC);
				gc.setFont(font);

				//draw some text
				gc.drawString("Preencha corretamente os campos", 20, 20);

				//make sure to dispose of the color and font
				color.dispose();
				font.dispose();
			}
		});

		//get the bounds of the display to centre the shell on the screen
		Rectangle r = display.getBounds();
		int shellX = (r.width) / 4;
		int shellY = (r.height) / 4;
		shell.setLocation(shellX, shellY);
		
		//time to display the splash screen, in milliseconds
		final int SLEEP_TIME = 1500;	

		//open the shell, and draw the image
		shell.open();		

		//sleep for the required amount of time
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
		}

		//dispose of the image, shell, display		
		shell.dispose();
		
	}

	private static void show() {	
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		shell.setText("Estoque de Mercado");
		shell.setLayout(gridLayout);
		shell.setSize(240, 440);
		
		tempoLabel = new Label(shell, SWT.NONE);
		tempoLabel.setText("Tempo:");
		tempoText = new Text(shell, SWT.BORDER);
		tempoInterChegadaLabel = new Label(shell, SWT.NONE);
		tempoInterChegadaLabel.setText("Tempo de Interchegada:");
		tempoInterChegadaText = new Text(shell, SWT.BORDER);
		qteProdutosLabel = new Label(shell, SWT.NONE);
		qteProdutosLabel.setText("Quantidade de Produtos:");
		qteProdutosText = new Text(shell, SWT.BORDER);
		tempoValidadeLabel = new Label(shell, SWT.NONE);
		tempoValidadeLabel.setText("Tempo de Validade:");
		tempoValidadeText = new Text(shell, SWT.BORDER);
		tempoReposicaoLabel = new Label(shell, SWT.NONE);
		tempoReposicaoLabel.setText("Tempo de Reposição:");
		tempoReposicaoText = new Text(shell, SWT.BORDER);
		mediaAtLabel = new Label(shell, SWT.NONE);
		mediaAtLabel.setText("Média Atendente:");
		mediaAtText = new Text(shell, SWT.BORDER);
		varAtLabel = new Label(shell, SWT.NONE);
		varAtLabel.setText("Variação Atendente:");
		varAtText = new Text(shell, SWT.BORDER);
		mediaCxLabel = new Label(shell, SWT.NONE);
		mediaCxLabel.setText("Média Caixa:");
		mediaCxText = new Text(shell, SWT.BORDER);
		varCxLabel = new Label(shell, SWT.NONE);
		varCxLabel.setText("Variação Caixa:");
		varCxText = new Text(shell, SWT.BORDER);
		pacienciaLabel = new Label(shell, SWT.NONE);
		pacienciaLabel.setText("Paciência Cliente:");
		pacienciaText = new Text(shell, SWT.BORDER);
		
		Label escolhaLabel = new Label(shell, SWT.NONE);
		escolhaLabel.setText("Escolha o que deseja variar:");
		
		qteProdutosCheck = new Button(shell, SWT.CHECK);
		qteProdutosCheck.setText("Quantidade de Produtos");		
		interchegadaCheck = new Button(shell, SWT.CHECK);
		interchegadaCheck.setText("Tempo de Interchegada");
		tempoVencimentoCheck = new Button(shell, SWT.CHECK);
		tempoVencimentoCheck.setText("Tempo de Vencimento");
		
		qteProdutosCheck.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				interchegadaCheck.setSelection(false);
				tempoVencimentoCheck.setSelection(false);
			}
		});	
		
		interchegadaCheck.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				qteProdutosCheck.setSelection(false);
				tempoVencimentoCheck.setSelection(false);				
			}
		});	
		
		tempoVencimentoCheck.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				interchegadaCheck.setSelection(false);
				qteProdutosCheck.setSelection(false);
			}
		});	
		
		minLabel = new Label(shell, SWT.NONE);
		minLabel.setText("Valor Mínimo");
		minText = new Text(shell, SWT.BORDER);
		maxLabel = new Label(shell, SWT.NONE);
		maxLabel.setText("Valor Máximo");
		maxText = new Text(shell, SWT.BORDER);
		
		Button run = new Button(shell, SWT.PUSH);
		run.setText("RUN!!!");
		run.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(!tudoOK()){
					mostrarAdvertencia();
				}
				else{
					String parametroVariavel = getParametroVariavelEscolhido();
					BatCreator.criarBat(tempoText.getText(),
							tempoInterChegadaText.getText(),
							qteProdutosText.getText(),
							tempoValidadeText.getText(),
							tempoReposicaoText.getText(),
							mediaAtText.getText(),
							varAtText.getText(),
							mediaCxText.getText(),
							varCxText.getText(),
							pacienciaText.getText(),							
							minText.getText(),
							maxText.getText(),
							parametroVariavel);
				}
			}
			
		});	
		
//		GridData data = new GridData();
//		data.widthHint = 150;
//		tempoLabel.setLayoutData(data);
////		data = new GridData();
////		data.widthHint = 150;
//		tempoInterChegadaLabel.setLayoutData(data);
////		data = new GridData();
////		data.widthHint = 150;
//		qteProdutosLabel.setLayoutData(data);
		
//		GridData data2 = new GridData(GridData.FILL_HORIZONTAL);
//		tempoText.setLayoutData(data2);
//		data2 = new GridData(GridData.FILL_HORIZONTAL);
//		tempoInterChegadaText.setLayoutData(data2);
//		data2 = new GridData(GridData.FILL_HORIZONTAL);
//		qteProdutosText.setLayoutData(data2);
		
		GridData data3 = new GridData();
		data3.horizontalSpan = 2;
		qteProdutosCheck.setLayoutData(data3);
		interchegadaCheck.setLayoutData(data3);
		tempoVencimentoCheck.setLayoutData(data3);
		
		shell.open();
		
		while(!shell.isDisposed()){
		if(!display.readAndDispatch())
			display.sleep();
		}
		display.dispose();
		
	}

}
