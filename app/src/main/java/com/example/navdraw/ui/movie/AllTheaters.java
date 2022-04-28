package com.example.navdraw.ui.movie;

import static javax.xml.parsers.DocumentBuilderFactory.*;

import com.example.navdraw.ui.movie.Theater;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

public class AllTheaters
{
    private ArrayList<Theater> theater_array = new ArrayList<Theater>();

    public AllTheaters()
    {

    }

    public void readXML()
    {
        try
        {
            DocumentBuilder builder = newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("TheatreArea");

            for (int i = 0; i < nList.getLength(); i++)
            {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;

                    theater_array.add(new Theater(element.getElementsByTagName("ID").item(0).getTextContent(),
                            element.getElementsByTagName("Name").item(0).getTextContent()));
                }
            }
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Theater>getArray()
    {
        return theater_array;
    }
}
