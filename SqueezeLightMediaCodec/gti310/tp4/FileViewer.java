package gti310.tp4;


import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class FileViewer{
	
	/** Graphical file selection engine */
	private JFileChooser _dialog;
	private boolean a;
	private File file;
	
	public FileViewer(boolean a){
		this.a = a;
		setupFileChooser();
		int returnVal = _dialog.showOpenDialog(null);
		System.out.println("tabarnak");
		if(returnVal == JFileChooser.APPROVE_OPTION){
			file = _dialog.getSelectedFile();
		}	
	}
	
	
	public File getFile(){
		return file;
	}
	
	/**
	 * Set the frame's properties to have a correct display.
	 */
	private void setupFileChooser() {
		_dialog = new JFileChooser() {
			
			@Override
			protected JDialog createDialog( Component parent ) throws HeadlessException {
				JDialog jDialog = super.createDialog(parent);
				jDialog.setAlwaysOnTop(true);
				return jDialog;
			}
		};
		/* remove the *.* option when selecting a file */
		_dialog.setAcceptAllFileFilterUsed(false);
		
		/* set the dialog's caption */
		_dialog.setDialogTitle("Please select a valid image file");
		
		/* make sure only files can be selected, not folders */
		_dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		/* add our custom filter */
		if(a)
			_dialog.setFileFilter(new PPMFileFilter());
		else
			_dialog.setFileFilter(new SZLFileFilter());	
		
		/* allow only one selection */
		_dialog.setMultiSelectionEnabled(false);
		
		/* set the starting directory to the project's dir */
		_dialog.setCurrentDirectory(new File("."));
	}
}
