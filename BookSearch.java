import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BookSearch extends JFrame{
	
	JPanel authorPanel, titlePanel, resultPanel;
	JLabel author, title, genre, resultLabel;
	public JTextField authorField, titleField, genreField;
	JComboBox<String> genreBox;
	JButton searchBtn;
	JScrollPane resultScrollPane;
	JTextArea resultArea;
	
	String[] genreArr = {"Search all genres", "Computer", "Fantasy", "Romance", 
											"Horror", "Science Fiction"};
	
	public BookSearch(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setSize(520,400);
		this.setLocationRelativeTo(null);
		this.setTitle("Look for a book!");
		this.setResizable(false);
		
		// Create and add author panel 
		authorPanel = new JPanel();
		authorPanel.setPreferredSize(new Dimension(500,20));
		authorPanel.setLayout(new BorderLayout());
		author = new JLabel("Author ");
		authorPanel.add(author,BorderLayout.WEST);
		authorField = new JTextField();
		authorField.setColumns(20);
		authorPanel.add(authorField,BorderLayout.CENTER);
		this.add(authorPanel);
		
		// Create and add title panel 
		titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(480,20));
		titlePanel.setLayout(new BorderLayout());
		title = new JLabel("Title ");
		titlePanel.add(title,BorderLayout.WEST);
		titleField = new JTextField();
		titleField.setColumns(20);
		titlePanel.add(titleField,BorderLayout.CENTER);
		this.add(titlePanel);
		
		// Add JComboBox for choosing genre
		genreBox = new JComboBox<>(genreArr);
		JPanel comboBoxPanel = new JPanel();
		comboBoxPanel.setPreferredSize(new Dimension(480,40));
		comboBoxPanel.add(genreBox);
		this.add(comboBoxPanel);
		
		// Create and add search button
		searchBtn = new JButton("Search");
		JPanel searchPanel = new JPanel();
		//searchPanel.setPreferredSize(new Dimension(480,20));
		searchPanel.setLayout(new BorderLayout());
		searchBtn.addActionListener(new SearchHandler());
		searchPanel.add(searchBtn,BorderLayout.CENTER);
		this.add(searchPanel);
		
		//Create and add result panel
		resultPanel = new JPanel();
		resultPanel.setPreferredSize(new Dimension(480,300));
		JPanel resultLabelPanel = new JPanel();
		resultLabelPanel.setPreferredSize(new Dimension(480,20));
		resultLabel = new JLabel("Search results");
		resultLabelPanel.add(resultLabel);
		resultPanel.add(resultLabelPanel);
		resultArea = new JTextArea();
		resultArea.setLineWrap(true);
		resultArea.setWrapStyleWord(true);
		resultArea.setEditable(false);
		resultScrollPane = new JScrollPane(resultArea);
		resultScrollPane.setPreferredSize(new Dimension(450,200));
		resultPanel.add(resultScrollPane);
		this.add(resultPanel);
		
		this.setVisible(true);
	}
	
	// Action handler class 
	
	public class SearchHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == searchBtn){
				
				SearchXml xmlSearch = new SearchXml(authorField.getText(), titleField.getText(), 
						genreBox.getSelectedItem().toString());
				String result = xmlSearch.resultString;
				resultArea.setText(result);
			}
		}
	} // action handler ends here
}
