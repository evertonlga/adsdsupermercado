package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ParametrosWindow {

	private static Display display;
	private static Shell shell;

	public static void main(String[] args) {
		display = new Display();
		shell = new Shell(display);
		show();

	}
	
	
	private static void show() {	
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		shell.setText("Estoque de Mercado");
		shell.setLayout(gridLayout);
		shell.setSize(240, 390);
		
		Label tempoLabel = new Label(shell, SWT.NONE);
		tempoLabel.setText("Tempo:");
		Text tempoText = new Text(shell, SWT.BORDER);
		Label tempoInterChegadaLabel = new Label(shell, SWT.NONE);
		tempoInterChegadaLabel.setText("Tempo de Interchegada:");
		Text tempoInterChegadaText = new Text(shell, SWT.BORDER);
		Label qteProdutosLabel = new Label(shell, SWT.NONE);
		qteProdutosLabel.setText("Quantidade de Produtos:");
		Text qteProdutosText = new Text(shell, SWT.BORDER);
		Label tempoValidadeLabel = new Label(shell, SWT.NONE);
		tempoValidadeLabel.setText("Tempo de Validade:");
		Text tempoValidadeText = new Text(shell, SWT.BORDER);
		Label tempoReposicaoLabel = new Label(shell, SWT.NONE);
		tempoReposicaoLabel.setText("Tempo de Reposição:");
		Text tempoReposicaoText = new Text(shell, SWT.BORDER);
		Label mediaAtLabel = new Label(shell, SWT.NONE);
		mediaAtLabel.setText("Média Atendente:");
		Text mediaAtText = new Text(shell, SWT.BORDER);
		Label varAtLabel = new Label(shell, SWT.NONE);
		varAtLabel.setText("Variação Atendente:");
		Text varAtText = new Text(shell, SWT.BORDER);
		Label mediaCxLabel = new Label(shell, SWT.NONE);
		mediaCxLabel.setText("Média Caixa:");
		Text mediaCx = new Text(shell, SWT.BORDER);
		Label varCxLabel = new Label(shell, SWT.NONE);
		varCxLabel.setText("Variação Caixa:");
		Text varCx = new Text(shell, SWT.BORDER);
		Label pacienciaLabel = new Label(shell, SWT.NONE);
		pacienciaLabel.setText("Paciência Cliente:");
		Text paciencia = new Text(shell, SWT.BORDER);
		
		Label escolhaLabel = new Label(shell, SWT.NONE);
		escolhaLabel.setText("Escolha o que deseja variar:");
		
		final Button qteProdutosCheck = new Button(shell, SWT.CHECK);
		qteProdutosCheck.setText("Quantidade de produtos");		
		final Button interchegadaCheck = new Button(shell, SWT.CHECK);
		interchegadaCheck.setText("Tempo de Interchegada");
		
		qteProdutosCheck.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				interchegadaCheck.setSelection(false);
			}
		});	
		
		interchegadaCheck.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				qteProdutosCheck.setSelection(false);
			}
		});	
		
		Label minLabel = new Label(shell, SWT.NONE);
		minLabel.setText("Valor Mínimo");
		Text minText = new Text(shell, SWT.BORDER);
		Label maxLabel = new Label(shell, SWT.NONE);
		maxLabel.setText("Valor Máximo");
		Text maxText = new Text(shell, SWT.BORDER);
		
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
		
		shell.open();
		
		while(!shell.isDisposed()){
		if(!display.readAndDispatch())
			display.sleep();
		}
		display.dispose();
		
	}

}
