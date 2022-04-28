package com.example.navdraw.ui.movie;

import static javax.xml.parsers.DocumentBuilderFactory.newInstance;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


public class AllMovies
{
    private String ID = "1029";
    private String date;
    private String title;
    private ArrayList<Movie> movie_array = new ArrayList<Movie>();

    public AllMovies()
    {

    }



    public void readXML()
    {
        try
        {
            DocumentBuilder builder = newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/Schedule/?area=" + ID + "&dt=" + date;
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            NodeList nlist = doc.getElementsByTagName("Show");

            for (int i = 0; i < nlist.getLength(); i++)
            {
                Node node = nlist.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;

                    movie_array.add(new Movie(element.getElementsByTagName("Title").item(0).getTextContent(),
                            element.getElementsByTagName("Theatre").item(0).getTextContent(),
                            element.getElementsByTagName("dttmShowStart").item(0).getTextContent(),
                            element.getElementsByTagName("TheatreAuditorium").item(0).getTextContent(),
                            element.getElementsByTagName("LengthInMinutes").item(0).getTextContent(),
                            element.getElementsByTagName("Rating").item(0).getTextContent()));
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

    public ArrayList<Movie> getArray()
    {
        return movie_array;
    }


}
