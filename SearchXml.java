import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class SearchXml {
	
	String authorSearch;
	String titleSearch;
	String genreSearch;
	String resultString="";
	
	String xPathExpression;
	
	public SearchXml(String as, String ts, String gs){
		this.authorSearch = as;
		this.titleSearch = ts;
		this.genreSearch = gs;
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		// not case insensitive query
		/*if(genreSearch.equals("Search all genres")){
			xPathExpression = "//book/author[contains(text(),\""+authorSearch+"\") "
					+ "and contains(../title,\"" + titleSearch + "\")]";
		}*/
		
		// new, case insensitive 
		if(genreSearch.equals("Search all genres")){
			xPathExpression = "//book/author[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),\""+authorSearch+"\")"
					+ "and contains(translate(../title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),\"" + titleSearch + "\")]";
		}
		
		else{
			xPathExpression = "//book/author[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),\""+authorSearch+"\") "
					+ "and contains(translate(../title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),\"" + titleSearch + "\")"
							+"and ../genre[text()=\'"+genreSearch+"\']]";
		}
		
		try {
			InputSource inputSource = new InputSource("./src/books.xml");
			
			NodeList authorNodes = (NodeList) xpath.evaluate(xPathExpression, inputSource, XPathConstants.NODESET);
			
			if(authorNodes.getLength() > 0){
				for(int i=0; i<authorNodes.getLength();i++){
					resultString += authorNodes.item(i).getParentNode().getTextContent();
				}
			}
			else{
				resultString = "No matches were found!";
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		
	}
}
