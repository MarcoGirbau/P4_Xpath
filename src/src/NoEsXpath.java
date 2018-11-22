/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;

/**
 * @author Marco Girbau
 */
public class NoEsXpath 
{
    Document doc = null;
    public int EjecutaXPath(File fichero) 
    {
        try 
        {
            //Crea un objeto DocumentBuilderFactory para el DOM (JAXP)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            //Crear un árbol DOM (parsear) con el archivo LibrosXML.xml
            Document XMLDoc = factory.newDocumentBuilder().parse(new File("LibrosXML.xml"));
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(fichero);
            
            //Crea el objeto XPath
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            //Crea un XPathExpression con la consulta deseada
            XPathExpression exp = xpath.compile("/Libros/*/Autor");//ARREGLAR DEPENDIENDO DE LO QUE SE ESCRIBA EN LA CONSULTA
            
            //Ejecuta la consulta indicando que se ejecute sobre el DOM y que devolverá el resultado como una lista de nodos.
            Object result = exp.evaluate(XMLDoc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            
            //Ahora recorre la lista para sacar los resultados
            String salida = "";
            for (int i = 0; i < nodeList.getLength(); i++) //Acceder a todos los niveles
            {
                salida = salida + "\n" + nodeList.item(i).getChildNodes().item(0).getNodeValue();
            }
            System.out.println(salida);
            return 0;
        } 
        catch (Exception ex) 
        { 
            System.out.println("Error: " + ex.toString());
            return -1; 
        }
    }
    
//    public int EjecutaXPath() 
//    {
//        try 
//        {
//            //Crea un objeto DocumentBuilderFactory para el DOM (JAXP)
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            //Crear un árbol DOM (parsear) con el archivo LibrosXML.xml
//            Document XMLDoc = factory.newDocumentBuilder().parse(new File("LibrosXML.xml"));
//            //Crea el objeto XPath
//            XPath xpath = XPathFactory.newInstance().newXPath();
//            //Crea un XPathExpression con la consulta deseada
//            XPathExpression exp = xpath.compile("/Libros/*/Autor");
//            //Ejecuta la consulta indicando que se ejecute sobre el DOM y que devolverá el resultado como una lista de nodos.
//            Object result= exp.evaluate(XMLDoc, XPathConstants.NODESET);
//            NodeList nodeList = (NodeList) result;
//            //Ahora recorre la lista para sacar los resultados
//            for (int i = 0; i < nodeList.getLength(); i++) 
//            {
//                salida = salida + "\n" + nodeList.item(i).getChildNodes().item(0).getNodeValue();
//            }
//            System.out.println(salida);
//            return 0;
//        } 
//        catch (Exception ex) 
//        { 
//            System.out.println("Error: " + ex.toString());
//            return -1; 
//        }
//    }
}